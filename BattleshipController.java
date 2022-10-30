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
    private String currentShip;

    BattleshipController(BattleshipModel m, BattleshipView v, String networkRoleString, String ip, String port){
        view = v;
        model = m;
        this.networkRoleString = networkRoleString;

        //Create client or server object based on networkRoleString input
        if(networkRoleString.equalsIgnoreCase("server")) {
            networkRole = new BattleshipServer(ip, Integer.parseInt(port));
        }
        else if (networkRoleString.equalsIgnoreCase("client")) {
            networkRole = new BattleshipClient(ip, Integer.parseInt(port));
        }

        //Add listeners
        view.addFireListener(new FireListener());
        view.addConfirmListener(new ConfirmListener());
        view.addConfirmShipListener(new ConfirmShipListener());
        view.addRandomListener(new RandomListener());
        view.addTargetGridListener(new TargetGridListener());
        //view.addOceanGridListener(new OceanGridListener());
        view.addShipSelectionListener(new ShipSelectionListener());
        view.addOceanGridButtonListener(new OceanGridButtonListener());
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
                                view.targetGridButtonArr[i][j].setSelected(false);
                                view.targetGridButtonArr[i][j].setEnabled(false);
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
                                //model.fire(i,j);
                            }
                        }
                }

            //Disable fire button after press until next turn
            view.fireButton.setEnabled(false);

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
        view.fireButton.setEnabled(true);
    }
}

boolean cruiserPlaced = false;
boolean battleshipPlaced = false;
boolean submarinePlaced = false;
boolean carrierPlaced = false;
boolean destroyerPlaced = false;

class ConfirmShipListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
        for(int i = 0; i < 5; i++){
            if(view.shipSelectionButtonArr[i].isSelected()){
                view.shipSelectionButtonArr[i].setEnabled(false);
            } else {
                view.shipSelectionButtonArr[i].setEnabled(true);
            }
           
        }

        if(carrierPlaced) {
                view.shipSelectionButtonArr[0].setEnabled(false);
        }

        if(battleshipPlaced) {
                view.shipSelectionButtonArr[1].setEnabled(false);
         }

        if(cruiserPlaced) {
                view.shipSelectionButtonArr[2].setEnabled(false);
         }

        if(submarinePlaced) {
                view.shipSelectionButtonArr[3].setEnabled(false);
         }

        if(destroyerPlaced) {
                view.shipSelectionButtonArr[4].setEnabled(false);
         }
    }
}

class RandomListener implements ActionListener {
    public void actionPerformed(ActionEvent e){
        clearShipPlacement();
        model.p.setGridRand();
        updateOceanGridView();

        for(int i = 0; i < 5; i++){
            view.shipSelectionButtonArr[i].setEnabled(false);
        }
    }
}

class ShipSelectionListener implements ActionListener {
    public void actionPerformed(ActionEvent e){
        JTextField shipTextField;
        view.shipButtonArrPanel.removeAll();
        for(int i = 0; i < 5; i++){
            if(view.shipSelectionButtonArr[i].isSelected()){
                switch(i){
                    case 0:
                    // Carrier
                    shipTextField = new JTextField("Carrier");
                    shipTextField.setEditable(false);
                    view.shipButtonArrPanel.setLayout(new GridLayout(6,1,view.buttonSpacer,view.buttonSpacer));
                    view.shipButtonArrPanel.add(shipTextField);
                    initShipButtonArr(5);
                    currentShip = "Carrier";

                    

                    break;

                    case 1:

                    // Battleship
                    shipTextField = new JTextField("Battleship");
                    shipTextField.setEditable(false);
                    view.shipButtonArrPanel.setLayout(new GridLayout(5,1,view.buttonSpacer,view.buttonSpacer));
                    view.shipButtonArrPanel.add(shipTextField);
                    initShipButtonArr(4);
                    currentShip = "Battleship";


                    break;

                    case 2:

                    // Cruiser
                    shipTextField = new JTextField("Cruiser");
                    shipTextField.setEditable(false);
                    view.shipButtonArrPanel.setLayout(new GridLayout(4,1,view.buttonSpacer,view.buttonSpacer));
                    view.shipButtonArrPanel.add(shipTextField);
                    initShipButtonArr(3);
                    currentShip = "Cruiser";

                    break;

                    case 3:

                    // Submarine
                    shipTextField = new JTextField("Submarine");
                    shipTextField.setEditable(false);
                    view.shipButtonArrPanel.setLayout(new GridLayout(4,1,view.buttonSpacer,view.buttonSpacer));
                    view.shipButtonArrPanel.add(shipTextField);
                    initShipButtonArr(3);
                    currentShip = "Submarine";

                    break;

                    case 4:

                    // Destroyer
                    shipTextField = new JTextField("Destroyer");
                    shipTextField.setEditable(false);
                    view.shipButtonArrPanel.setLayout(new GridLayout(3,1,view.buttonSpacer,view.buttonSpacer));
                    view.shipButtonArrPanel.add(shipTextField);
                    initShipButtonArr(2);
                    currentShip = "Destroyer";

                    break;

                    default:


                

                
                }

                //view.shipSelectionButtonArr[i].setEnabled(false);
            } else {
                view.shipSelectionButtonArr[i].setEnabled(false);
            }
        }
    }
}


// class ShipSelectionListener implements ActionListener {
//     public void actionPerformed(ActionEvent e){
//         Object selected = view.shipSelection.getSelectedItem();
        
//         switch(selected.toString()){
//             case "Carrier":
//                 currentShip = "Carrier";
//                 System.out.println(currentShip);
//                 /*
//                 if(e.getStateChange() == ItemEvent.SELECTED){
//                     //System.out.println("Carrier");
//                     view.shipLabel.setLayout(new GridLayout(5,1,view.buttonSpacer,view.buttonSpacer));
//                     initShipButtonArr(5);
//                 }

//                    if(e.getStateChange() == ItemEvent.DESELECTED){
//                     view.shipLabel.removeAll();
//                 }
//                 */
                
//                 break;

//             case "Battleship":
//            // System.out.println("Battleship");
//                 currentShip = "Battleship";
//                 System.out.println(currentShip);
//                 /*
//                 if(e.getStateChange() == ItemEvent.SELECTED){
//                     //System.out.println("Battleship");
//                     view.shipLabel.setLayout(new GridLayout(4,1,view.buttonSpacer,view.buttonSpacer));
//                     initShipButtonArr(4);
//                 }

//                 if(e.getStateChange() == ItemEvent.DESELECTED){
//                     System.out.println("Battleship deselected");
//                     view.shipLabel.removeAll();
//                 }
//                 */

//                 break;

//             case "Cruiser":
//                 currentShip = "Cruiser";
//             //System.out.println("Carrier");
//                 /*
//                 if(e.getStateChange() == ItemEvent.SELECTED){
//                     view.shipLabel.setLayout(new GridLayout(3,1,view.buttonSpacer,view.buttonSpacer));
//                     initShipButtonArr(3);
//                 }

//                    if(e.getStateChange() == ItemEvent.DESELECTED){
//                     view.shipLabel.removeAll();
//                 }
//                 */

//                 break;

//             case "Submarine":
//                 currentShip = "Submarine";
//                 /*
//                 if(e.getStateChange() == ItemEvent.SELECTED){
//                     view.shipLabel.setLayout(new GridLayout(3,1,view.buttonSpacer,view.buttonSpacer));
//                     initShipButtonArr(3);
//                 }

//                    if(e.getStateChange() == ItemEvent.DESELECTED){
//                     view.shipLabel.removeAll();
//                 }
//                 */

//                 break;

//             case "Destroyer":
//                 currentShip = "Destroyer";
//                 /*
//                 if(e.getStateChange() == ItemEvent.SELECTED){
//                     view.shipLabel.setLayout(new GridLayout(2,1,view.buttonSpacer,view.buttonSpacer));
//                     initShipButtonArr(2);
//                 }

//                    if(e.getStateChange() == ItemEvent.DESELECTED){
//                     view.shipLabel.removeAll();
//                 }
//                 */

//                 break;

//             default:
//         }

        
//     }
// }




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
                    view.gameLog.append("\n");
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
                view.gameLog.append("\n");

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

class OceanGridButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e){
        Ship newShip;
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(view.oceanGridButtonArr[i][j].isSelected()){
                    System.out.println(i + " " + j);
                    switch(currentShip){
                        case "Carrier":
                            //If ship has not been placed
                            if(model.p.shipArr[0].getxLocation() == -1) {
                                newShip = new Ship(5, "Carrier", false, i, j, true);
                                model.p.shipArr[0] = newShip;
                                if(!model.p.checkOverlap(model.p.shipGrid.getGrid(), model.p.shipArr[0])){
                                model.p.setShip(model.p.shipGrid.getGrid(), model.p.shipArr[0]);
                                }
                                updateOceanGridView();
                            } else { //Ship has already been placed
                                clearCurrentShip(model.p.shipArr[0]);
                                model.p.shipArr[0].setxLocation(i);
                                model.p.shipArr[0].setyLocation(j);
                                System.out.println("Replacing ship");
                                 if(!model.p.checkOverlap(model.p.shipGrid.getGrid(), model.p.shipArr[0])){
                                model.p.setShip(model.p.shipGrid.getGrid(), model.p.shipArr[0]);
                                }
                                updateOceanGridView();
                                model.p.shipGrid.printGrid();
                            }
                            carrierPlaced = true;

                        break;

                        case "Battleship":
                                   //If ship has not been placed
                            if(model.p.shipArr[1].getxLocation() == -1) {
                                newShip = new Ship(4, "Battleship", false, i, j, true);
                                model.p.shipArr[1] = newShip;
                                if(!model.p.checkOverlap(model.p.shipGrid.getGrid(), model.p.shipArr[1])){
                                model.p.setShip(model.p.shipGrid.getGrid(), model.p.shipArr[1]);
                                }
                                updateOceanGridView();
                            } else { //Ship has already been placed
                                clearCurrentShip(model.p.shipArr[1]);
                                model.p.shipArr[1].setxLocation(i);
                                model.p.shipArr[1].setyLocation(j);
                                System.out.println("Replacing ship");
                                 if(!model.p.checkOverlap(model.p.shipGrid.getGrid(), model.p.shipArr[1])){
                                model.p.setShip(model.p.shipGrid.getGrid(), model.p.shipArr[1]);
                                }
                                updateOceanGridView();
                                model.p.shipGrid.printGrid();
                            }

                            battleshipPlaced = true;


                        break;
                        
                        case "Cruiser":
                                   //If ship has not been placed
                            if(model.p.shipArr[2].getxLocation() == -1) {
                                newShip = new Ship(3, "Cruiser", false, i, j, true);
                                model.p.shipArr[2] = newShip;
                                if(!model.p.checkOverlap(model.p.shipGrid.getGrid(), model.p.shipArr[2])){
                                model.p.setShip(model.p.shipGrid.getGrid(), model.p.shipArr[2]);
                                }
                                updateOceanGridView();
                            } else { //Ship has already been placed
                                clearCurrentShip(model.p.shipArr[2]);
                                model.p.shipArr[2].setxLocation(i);
                                model.p.shipArr[2].setyLocation(j);
                                System.out.println("Replacing ship");
                                 if(!model.p.checkOverlap(model.p.shipGrid.getGrid(), model.p.shipArr[2])){
                                model.p.setShip(model.p.shipGrid.getGrid(), model.p.shipArr[2]);
                                }
                                updateOceanGridView();
                                model.p.shipGrid.printGrid();
                            }

                            cruiserPlaced = true;

                        break;

                        case "Submarine":
                                     //If ship has not been placed
                            if(model.p.shipArr[3].getxLocation() == -1) {
                                newShip = new Ship(3, "Submarine", false, i, j, true);
                                model.p.shipArr[3] = newShip;
                                if(!model.p.checkOverlap(model.p.shipGrid.getGrid(), model.p.shipArr[3])){
                                model.p.setShip(model.p.shipGrid.getGrid(), model.p.shipArr[3]);
                                }
                                updateOceanGridView();
                            } else { //Ship has already been placed
                                clearCurrentShip(model.p.shipArr[3]);
                                model.p.shipArr[3].setxLocation(i);
                                model.p.shipArr[3].setyLocation(j);
                                System.out.println("Replacing ship");
                                 if(!model.p.checkOverlap(model.p.shipGrid.getGrid(), model.p.shipArr[3])){
                                model.p.setShip(model.p.shipGrid.getGrid(), model.p.shipArr[3]);
                                }
                                updateOceanGridView();
                                model.p.shipGrid.printGrid();
                            }

                            submarinePlaced = true;

                        break;

                        case "Destroyer":
                                    //If ship has not been placed
                            if(model.p.shipArr[4].getxLocation() == -1) {
                                newShip = new Ship(2, "Destroyer", false, i, j, true);
                                model.p.shipArr[4] = newShip;
                                if(!model.p.checkOverlap(model.p.shipGrid.getGrid(), model.p.shipArr[4])){
                                model.p.setShip(model.p.shipGrid.getGrid(), model.p.shipArr[4]);
                                }
                                updateOceanGridView();
                            } else { //Ship has already been placed
                                clearCurrentShip(model.p.shipArr[4]);
                                model.p.shipArr[4].setxLocation(i);
                                model.p.shipArr[4].setyLocation(j);
                                System.out.println("Replacing ship");
                                 if(!model.p.checkOverlap(model.p.shipGrid.getGrid(), model.p.shipArr[4])){
                                model.p.setShip(model.p.shipGrid.getGrid(), model.p.shipArr[4]);
                                }
                                updateOceanGridView();
                                model.p.shipGrid.printGrid();
                            }

                            destroyerPlaced = true;

                        break;

                        default: 

                    }
                            
                    
                
                    }
                }
                
            }
        }
    }

    public void clearCurrentShip(Ship s){
        // Vertical orientation
        if(s.getOrientation()){
            for(int i = 0; i < s.getSize(); i++){
                if((s.getxLocation() + i) < 9 && (model.p.shipGrid.getCell(s.getxLocation() + i, s.getyLocation()) != model.p.SHIP_GOOD)){
                    model.p.shipGrid.clearCell(s.getxLocation() + i, s.getyLocation());
                }
            }
        } else {
            for(int i = 0; i < s.getSize(); i++){
                if((s.getyLocation() + i) < 9 && (model.p.shipGrid.getCell(s.getxLocation(), s.getyLocation() + i) != model.p.SHIP_GOOD)){
                    model.p.shipGrid.clearCell(s.getxLocation(), s.getyLocation() + i);
                }
            }
        }
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
class updateGridListener implements ActionListener{if(e.getStateChange() == ItemEvent.SELECTED){
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

public void initShipButtonArr(int shipSize){
    JButton [] shipButtonArr = new JButton[shipSize];

    //view.shipLabel.removeAll();

    for(int i = 0; i < shipSize; i++){
        shipButtonArr[i] = new JButton();
        shipButtonArr[i].setMaximumSize(new Dimension(10, 10));
        //shipButtonArr[i].setVisible(true);
        view.shipButtonArrPanel.add(shipButtonArr[i]);
        view.shipSelectionPanel.validate();
    }
}

public void clearShipPlacement(){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(model.p.shipGrid.getCell(i, j) == model.p.SHIP_GOOD){
                    model.p.shipGrid.setCell(i,j, model.p.NO_SHIP);
                    //view.oceanGridButtonArr[i][j].setBackground(null);
                }
            }
        }

}

// public void updateGridView(){
//         for(int i = 0; i < 10; i++){
//             for(int j = 0; j < 10; j++){
//                 if(model.p.shipGrid.getCell(i, j) == model.p.SHIP_GOOD){
//                     view.oceanGridButtonArr[i][j].setBackground(Color.BLACK);
//                 }
//                 if(model.p.shipGrid.getCell(i, j) == model.p.SHIP_HIT){
//                     view.oceanGridButtonArr[i][j].setBackground(Color.RED);
//                 }
//                 if(model.p.shipGrid.getCell(i, j) == model.p.NO_SHIP){
//                     view.oceanGridButtonArr[i][j].setBackground(Color.BLUE);
//                 }               
                
//             }
//         }
// }

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

    //Enable fire button for next turn
    view.fireButton.setEnabled(true);

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
        //view.gameLog.append(resultMessage);
        // updateOceanGridView();
        // updateTargetGridView();
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
        //view.gameLog.append(resultMessage);
    }
    catch (IOException e) {
        e.printStackTrace();
    }
    view.fireButton.setEnabled(true);

    //Reset boolean values for next turn
    shotSentServer = false;
    shotReceivedServer = false;
    resultReceivedServer = false;
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

    //Disable clients button until server send first shot
    //view.fireButton.setEnabled(false);

    //1. Wait for opponent to send shot message
    System.out.println("Waiting for opponent shot message");
    while(!shotReceivedClient){shotMessage = waitForOpponentShotMessage();} //Waiting for shot message from server
    System.out.println("Opponent shot message received");

    //2. Interpret shot and send results
    try {
        resultMessage = checkShot(shotMessage);
        System.out.println("Sending results to opponent " + resultMessage);
        networkRole.sendData(resultMessage);
        //view.gameLog.append(resultMessage);
        // updateOceanGridView();
        // updateTargetGridView();
    }
    catch (IOException e) {
        e.printStackTrace();
    }

    //Enable fire button
    view.fireButton.setEnabled(true);

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
        //view.gameLog.append(resultMessage);
        // updateOceanGridView();
        // updateTargetGridView();
        //view.gameLog.append(resultMessage);
    }
    catch (IOException e) {
        e.printStackTrace();
    }

    //Reset boolean values for next turn
    shotSentClient = false;
    shotReceivedClient = false;
    resultReceivedClient = false;
}

//Wait until the message has been update to a coordinate message
//Returns false if message has been received for use in while loop
String waitForOpponentShotMessage() {

    try {
        String message = networkRole.readData();
        System.out.println("Opponent shot message inside wait " + message);

        if(message.length() > 0 && message.length() > 14) {     
            //Check if message is the shot message
            if(message.substring(10, 20).equalsIgnoreCase("Shot fired")) {
                System.out.println("Message detected " + message.substring(20,30));
                
                //Append message to game log history
                view.gameLog.append(message);
                
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

        if(message.length() == 16) {
        
            //Check if message is the shot message
            if(message.substring(12, 16).equalsIgnoreCase("HIT ") || message.substring(12, 16).equalsIgnoreCase("MISS")) {
                String x = message.substring(10,11); 
                // char xChar = x.charAt(0);
                char[] xChar = x.toCharArray();
                String y = message.substring(11, 12);

                //int xCoord = (Integer.parseInt(xChar)) - 'A';
                int xCoord = Integer.parseInt(x);

                int yCoord = Integer.parseInt(y);

                System.out.println("Result Message Received!! " + message.substring(12, 16));

                //Append the result message to the chat log
                view.gameLog.append(message);

                if(message.substring(12, 16).equalsIgnoreCase("HIT ")) {
                    model.p.targetGrid.setCell(xCoord, yCoord, model.p.SHOT_HIT);
                }
                if(message.substring(12, 16).equalsIgnoreCase("MISS")) {
                    model.p.targetGrid.setCell(xCoord, yCoord, model.p.SHOT_MISS);
                }

                //Update boolean based on networkRoleString
                if(networkRoleString.equalsIgnoreCase("server")) {
                    resultReceivedServer = true;
                }
                if(networkRoleString.equalsIgnoreCase("client")) {
                    resultReceivedClient = true;
                }
                return message.substring(12,16);
            }
            
        }
    
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null;
}


String checkShot(String message) {
    String x = message.substring(4,5); 
    char[] xChar = x.toCharArray();
    String y = message.substring(9, 10);
    String resultString;

    //int xCoord = (Integer.parseInt(xChar)) - 'A';
    int xCoord = xChar[0] - 'A';

    int yCoord = Integer.parseInt(y);

    System.out.println("Parsed coordinate in checkShot " + xCoord + " " + yCoord);

    if(model.p.shipGrid.getCell(xCoord, yCoord) == model.p.NO_SHIP) {
        System.out.println("Shot missed");
        resultString = String.format("%d%d%s", xCoord, yCoord, "MISS\n");
       //resultString = Integer.toString(yCoord) + "MISS\n";

       //model.p.shipGrid.setCell(xCoord, yCoord, model.p.SHIP_GOOD);
       return resultString;
    } else if (model.p.shipGrid.getCell(xCoord, yCoord) == model.p.SHIP_GOOD) {
        System.out.println("Shot hit");
       //resultString = xCoord + yCoord + "HIT \n";
       resultString = String.format("%d%d%s", xCoord, yCoord, "HIT \n");
       model.p.shipGrid.setCell(xCoord, yCoord, model.p.SHIP_HIT);
       return resultString;
    } else {
       resultString = null;
       return resultString;
    }
}

}