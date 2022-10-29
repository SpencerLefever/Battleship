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
    private boolean shotSentServer = false;
    private boolean shotSentClient = false;
    private boolean shotReceivedServer = false;
    private boolean shotReceivedClient = false;
    private boolean resultReceivedServer = false;
    private boolean resultReceivedClient = false;

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
        view.setVisible(true);

    }

 class FireListener implements ActionListener{
     public void actionPerformed(ActionEvent e){
        //TODO Disable buttons after fire button is pressed and update color to reflect hit or miss
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
                                    view.gameLog.append(networkRoleString.toUpperCase() + ">>> " + message);

                                } catch (IOException err) {
                                    err.printStackTrace();
                                }
                                model.fire(i,j);
                            }
                        }
                }
                if(networkRoleString.equalsIgnoreCase("server")) {
                    shotSentServer = true;
                }
                if(networkRoleString.equalsIgnoreCase("client")) {
                    shotSentClient = true;
                }
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
        updateOceanGridView();
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

                    updateOceanGridView();
                    updateTargetGridView();
                    // try {
                    //     String message = networkRole.readData();
                    //     view.gameLog.append(message);
                    //     updateOceanGridView();
                    //     updateTargetGridView();
                    // } catch (IOException err) {
                    //     err.printStackTrace();
                    // }
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
         Runnable runnable = new Runnable() {

            public void run() {

                updateTargetGridView();
                updateOceanGridView();

                // try {
                //     String message = networkRole.readData();
                //     view.gameLog.append(message);
                //     updateTargetGridView();
                //     updateOceanGridView();
                // } catch (IOException err) {
                //     err.printStackTrace();
                // }

            }
        };
        SwingUtilities.invokeLater(runnable);         
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
                if(model.p.shipGrid.getCell(i, j) == model.p.SHIP_HIT){
                    view.oceanGridButtonArr[i][j].setBackground(Color.RED);
                }
                if(model.p.shipGrid.getCell(i, j) == model.p.NO_SHIP){
                    view.oceanGridButtonArr[i][j].setBackground(Color.BLUE);
                }               
                
            }
        }
}

public void updateOceanGridView() {
    for(int i = 0; i < 10; i++){
        for(int j = 0; j < 10; j++){
            if(model.p.shipGrid.getCell(i, j) == model.p.SHIP_GOOD){
                view.oceanGridButtonArr[i][j].setBackground(Color.BLACK);
            }
            if(model.p.shipGrid.getCell(i, j) == model.p.SHIP_HIT){
                view.oceanGridButtonArr[i][j].setBackground(Color.RED);
            }
            if(model.p.shipGrid.getCell(i, j) == model.p.NO_SHIP){
                view.oceanGridButtonArr[i][j].setBackground(Color.BLUE);
            }               
            
        }
    }
}

public void updateTargetGridView() {
    for(int i = 0; i < 10; i++){
        for(int j = 0; j < 10; j++){
            if(model.p.targetGrid.getCell(i, j) == model.p.SHOT_MISS){
                view.targetGridButtonArr[i][j].setBackground(Color.BLUE);
            }
            if(model.p.targetGrid.getCell(i, j) == model.p.SHOT_HIT){
                view.targetGridButtonArr[i][j].setBackground(Color.RED);
            }
        }
    }
}

void updateCellView(int x, int y){
    if(model.p.shipGrid.getCell(x, y) == model.p.SHIP_GOOD){
        view.oceanGridButtonArr[x][y].setBackground(Color.BLACK);
    }
    if(model.p.shipGrid.getCell(x, y) == model.p.SHIP_HIT){
        view.oceanGridButtonArr[x][y].setBackground(Color.RED);
    }
    if(model.p.shipGrid.getCell(x, y) == model.p.NO_SHIP){
        view.oceanGridButtonArr[x][y].setBackground(Color.BLUE);
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
    String shotMessage;

    //Have players only be able to place ships
    while(!model.p.isWinner()) {
    
        //Server takes first shot once boards are set
        if(networkRoleString.equalsIgnoreCase("server")) {
            System.out.println("Playing game as server");
           playGameServer();
        }
        else if(networkRoleString.equalsIgnoreCase("client")) {
            System.out.println("Playing game as client");
            playGameClient();
        }
    }

}


//Method to play game as server role used in playgame method
/**
 * Server steps for a turn of the game
 * 
 * 1. Send shot data to opponent
 * 2. Wait for opponent to send result
 * 3. Wait for opponent to send shot
 * 4. Interpret shot and send results to opponent
 */
void playGameServer() {
    String resultMessage = "";
    String shotMessage = "";

    //1. Send shot data
    System.out.println("Waiting for for shot to be fired");
    while(!shotSentServer) {System.out.print("");} //Waiting for server to send a shot
    System.out.println("Shot has been fired");

    //2. Wait for opponent to send result
    System.out.println("Waiting for opponent result message");
    while(!resultReceivedServer){resultMessage = waitForOpponentResultMessage();} //Waiting for result message back from client
    System.out.println("Opponent result message received");

    //Append result message to the JTextArea
    try {
        System.out.println("Result Message: " + resultMessage);
        //resultMessage = checkShot(networkRole.readData());
        networkRole.sendData(resultMessage);
        view.gameLog.append(resultMessage);
        updateOceanGridView();
        updateTargetGridView();
    }
    catch (IOException e) {
        e.printStackTrace();
    }

    //3. Wait for opponent to send shot message
    System.out.println("Waiting for opponent shot message");
    while(!shotReceivedServer){shotMessage = waitForOpponentShotMessage(); } //Wait for the opponent to send the shot message
    System.out.println("Opponent shot message received");
    
    //4. Interpret results and send shot
    try {
        resultMessage = checkShot(shotMessage);
        networkRole.sendData(resultMessage);
        view.gameLog.append(resultMessage);
    }
    catch (IOException e) {
        e.printStackTrace();
    }

    shotSentServer = false;
    shotReceivedClient = false;
    shotReceivedServer = false;
}


//Method for player to play game as client role
/**
 * Client Steps for a turn of the game
 * 
 * 1. Wait for the opponent to send their shot message
 * 2. Interpret shot and send the result 
 * 3. Fire shot and send message to opponent
 * 4. Wait for the result message from the opponent
 */
void playGameClient() {
    String resultMessage = "";
    String shotMessage = "";

    //1. Wait for opponent to send shot message
    System.out.println("Waiting for opponent shot message");
    while(!shotReceivedClient){shotMessage = waitForOpponentShotMessage();} //Waiting for shot message from server
    System.out.println("Opponent shot message received");

    //2. Interpret shot and send results
    try {
        resultMessage = checkShot(shotMessage);
        System.out.println("Sending results to opponent " + resultMessage);
        networkRole.sendData(resultMessage);
        view.gameLog.append(resultMessage);
        //view.gameLog.append(resultMessage);
    }
    catch (IOException e) {
        e.printStackTrace();
    }

    //3. Fire shot and send message to opponent
    System.out.println("Waiting for fire button to be pressed");
    while(!shotSentClient){System.out.print(""); }//Waiting to send back message to server
    System.out.println("Shot has been fired");

    //4. Wait for the results of the shot from the opponent

    //TODO Look at result message and update board
    while(!resultReceivedClient) {resultMessage = waitForOpponentResultMessage(); }
     //Send result to opponent
     try {
        System.out.println("Result Message: " + resultMessage);
        //resultMessage = checkShot(resultMessage);
        networkRole.sendData(resultMessage);
        view.gameLog.append(resultMessage);
        updateOceanGridView();
        updateTargetGridView();
        //view.gameLog.append(resultMessage);
    }
    catch (IOException e) {
        e.printStackTrace();
    }

    shotSentClient = false;
    shotReceivedClient = false;
    resultReceivedClient = false;

}

//Wait until the message has been update to a coordinate message
//Returns false if message has been received for use in while loop
String waitForOpponentShotMessage() {

    try {
        String message = networkRole.readData();

        if(message.length() > 0) {     
            //Check if message is the shot message
            if(message.substring(10, 20).equalsIgnoreCase("Shot fired")) {
                System.out.println("Message detected " + message.substring(20,30));
                //view.gameLog.append(message);
                if(networkRoleString.equalsIgnoreCase("server")) {
                    shotReceivedServer = true;
                }
                if(networkRoleString.equalsIgnoreCase("client")) {
                    shotReceivedClient = true;
                }
                return message.substring(20, 30);
            }
        }
    
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null;
}

//Wait for the opponents result message
String waitForOpponentResultMessage() {

    try {
        String message = networkRole.readData();
        System.out.println("Inside wait for result message: " + message);

        if(message.length() > 0) {
        
            //Check if message is the shot message
            if(message.substring(10, 14).equalsIgnoreCase("HIT ") || message.substring(10, 14).equalsIgnoreCase("MISS")) {
                System.out.println("Result Message Received!! " + message.substring(10, 14));

                if(networkRoleString.equalsIgnoreCase("server")) {
                    resultReceivedServer = true;
                }
                if(networkRoleString.equalsIgnoreCase("client")) {
                    resultReceivedClient = true;
                }
                return message.substring(10,14);
            }
            
        }
    
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null;
}


//TODO REDO Parsing to reflect new message format and decode char
String checkShot(String message) {
    String x = message.substring(4,5); 
    // char xChar = x.charAt(0);
    char[] xChar = x.toCharArray();
    String y = message.substring(9, 10);
    String resultString;

    //int xCoord = (Integer.parseInt(xChar)) - 'A';
    int xCoord = xChar[0] - 'A';

    int yCoord = Integer.parseInt(y);

    System.out.println("Parsed coordinate in checkShot " + xCoord + " " + yCoord);

    if(model.p.shipGrid.getCell(xCoord, yCoord) == model.p.NO_SHIP) {
        System.out.println("Shot missed");
       resultString = "MISS\n";
       //model.p.shipGrid.setCell(xCoord, yCoord, model.p.SHIP_GOOD);
       return resultString;
    } else if (model.p.shipGrid.getCell(xCoord, yCoord) == model.p.SHIP_GOOD) {
        System.out.println("Shot hit");
       resultString = "HIT \n";
       model.p.shipGrid.setCell(xCoord, yCoord, model.p.SHIP_HIT);
       return resultString;
    } else {
       resultString = null;
       return resultString;
    }
}

}

