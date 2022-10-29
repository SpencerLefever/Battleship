import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.BadLocationException;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class BattleshipController{
    private BattleshipView view;
    private BattleshipModel model;
    private NetworkRole networkRole;
    private String networkRoleString;
    private boolean shotSent = false;

    BattleshipController(BattleshipModel m, BattleshipView v, String networkRoleString, String ip){
        view = v;
        model = m;
        this.networkRoleString = networkRoleString;

        //Create client or server object based on networkRoleString input
        if(networkRoleString.equalsIgnoreCase("server")) {
            networkRole = new BattleshipServer(Integer.parseInt(ip));
        }
        else if (networkRoleString.equalsIgnoreCase("client")) {
            networkRole = new BattleshipClient(Integer.parseInt(ip));
        }

        //Add listeners
        view.addFireListener(new FireListener());
        view.addConfirmListener(new ConfirmListener());
        view.addRestartListener(new RestartListener());
        view.addRandomListener(new RandomListener());
        view.addTargetGridListener(new TargetGridListener());
        view.addOceanGridListener(new OceanGridListener());
        // view.addTargetButtonListener(new TargetButtonListener());
        // view.addupdateGridListener(new updateGridListener());

        //TODO When grid listeners are done add to view
        
        view.setVisible(true);

    }

 class FireListener implements ActionListener{
     public void actionPerformed(ActionEvent e){
        //TODO Disable buttons after fire button is pressed
        for(int i = 0; i < 10; i++) {
                        for(int j=0; j< 10; j++) {
                            if(view.targetGridButtonArr[i][j].isSelected())
                            {
                                //System.out.println("Button coordinate " + i + " " + j);

                                //Populate message to send to opponent
                                String message = "Shot fired x: " + (char)(i+'A') + " y: " + Integer.toString(j) + "\n";

                                try {
                                    //Send the data
                                    networkRole.sendData(message);

                                    //Append the message to the game log
                                    view.gameLog.append(message);

                                } catch (IOException err) {
                                    err.printStackTrace();
                                }
                                model.fire(i,j);
                            }
                        }
                }
                shotSent = true;
     }
 }


class ConfirmListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
        view.randomButton.setEnabled(false);
        view.confirmButton.setEnabled(false);
    }
}

class RestartListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
        System.out.println("Restarted");
    }
}

class RandomListener implements ActionListener {
    public void actionPerformed(ActionEvent e){
        clearShipPlacement();
        model.p.setGridRand();
        updateGridView();
    }
}

//Target and Ocean grid are Document Listeners because they listen to the game log for changes
//Only insertUpdate method is needed because only new message will be inserted to game log
class TargetGridListener implements DocumentListener {
    
    public void insertUpdate(DocumentEvent e) {
        //Read the message from the network

            // String message = e.getDocument().getText((e.getDocument().getLength() - e.getLength()), e.getDocument().getLength());
            Runnable runnable = new Runnable() {
                public void run() {

                    try {
                        String message = networkRole.readData();
                        view.gameLog.append(message);
                    } catch (IOException err) {
                        err.printStackTrace();
                    }
                }
            };
            SwingUtilities.invokeLater(runnable);           
      


        //If it is the result message update the target grid
    }

    public void removeUpdate(DocumentEvent e) { }

    public void changedUpdate(DocumentEvent e) { }
    
}

class OceanGridListener implements DocumentListener {

    public void insertUpdate(DocumentEvent e) {
        //Read the message from the network

        //If it is the result message update the target grid
    }

    public void removeUpdate(DocumentEvent e) { }

    public void changedUpdate(DocumentEvent e) { }

}



/*
class TargetButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
        //get source of button
        JToggleButton btn = (JToggleButton) e.getSource();
        
        //
    }
}
*/

/*
class updateGridListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(model.p.shipGrid.getCell(i, j) == 0){
                    view.oceanGridButtonArr[i][j].setBackground(Color.BLACK);
                }
            }
        }
    }
}
*/

public void clearShipPlacement(){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(model.p.shipGrid.getCell(i, j) == model.p.SHIP_GOOD){
                    model.p.shipGrid.clearCell(i,j);
                    view.oceanGridButtonArr[i][j].setBackground(null);
                }
            }
        }

}

public void updateGridView(){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(model.p.shipGrid.getCell(i, j) == model.p.SHIP_GOOD){
                    view.oceanGridButtonArr[i][j].setBackground(Color.BLACK);
                }
            }
        }
}

void updateCellView(int x, int y){
    if(model.p.shipGrid.getCell(x, y) == 0){
        view.oceanGridButtonArr[x][y].setBackground(Color.BLACK);
    }
}

// class TargetGridButtonListener implements ActionListener{
//     public void actionPerformed(ActionEvent e) {        
//         //loop through buttons in array
//         for(int i = 0; i < 10; i++) {
//                         for(int j=0; j< 10; j++) {
//                             if(view.targetGridButtonArr[i][j].getModel().isPressed())
//                             {
//                                 System.out.println(i + " " + j);
//                                 //view.targetGridButtonArr[i][j];
//                             }
//                         }
//                 }
//         //If the button is not the one that was most recently pressed, deactivate
//     }
// }


/**
 * This method allows the server to take the first turn
 */

 //TODO Have method reactivate target grid buttons after ship is received
void playGame() {

    String resultMessage;

    //Have players only be able to place ships
    while(!model.p.isWinner()) {
    
        //Server takes first shot once boards are set
        if(networkRoleString.equalsIgnoreCase("server")) {
            while(!shotSent){ } //Waiting for server to send a shot

            while(waitForOpponentResultMessage()){ } //Waiting for result message back from client

            //Append result message to the JTextArea

            while(waitForOpponentShotMessage()){ } //Wait for the opponent to send the shot message
            
            try {
                resultMessage = checkShot(networkRole.readData());
                networkRole.sendData(resultMessage);
                //view.gameLog.append(resultMessage);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(networkRoleString.equalsIgnoreCase("client")) {
            while(waitForOpponentShotMessage()){ } //Waiting for shot message from server

            //Send result to opponent
            try {
                resultMessage = checkShot(networkRole.readData());
                networkRole.sendData(resultMessage);
                //view.gameLog.append(resultMessage);
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            while(!shotSent){ } //Waiting to send back message to server

            while(waitForOpponentResultMessage()) { }

        }
    }

}

//Wait until the message has been update to a coordinate message
//Returns false if message has been received for use in while loop
boolean waitForOpponentShotMessage() {

    try {
        String message = networkRole.readData();

        //Check if message is the shot message
        if(message.substring(10, 21).equalsIgnoreCase("Shot fired")) {
            return false;
        }
    
    } catch (IOException e) {
        e.printStackTrace();
    }
    return true;
}

//Wait for the opponents result message
boolean waitForOpponentResultMessage() {

    try {
        String message = networkRole.readData();
        System.out.println("Inside wait for result message: " + message);

        //Check if message is the shot message
        if(message.substring(10, 15).equalsIgnoreCase("HIT ") || message.substring(10, 15).equalsIgnoreCase("MISS")) {
            return false;
        }
    
    } catch (IOException e) {
        e.printStackTrace();
    }
    return true;
}


String checkShot(String message) {
    String x = message.substring(0,0); 
    String y = message.substring(2, 2);
    String resultString;

    int result = model.p.shipGrid.getCell(Integer.parseInt(x), Integer.parseInt(y));

    if(result == model.p.SHOT_MISS) {
       resultString = "miss";
       return resultString;
    } else if (result == model.p.SHOT_HIT) {
       resultString = "hit";
       return resultString;
    } else {
       resultString = null;
       return resultString;
    }
}



}

