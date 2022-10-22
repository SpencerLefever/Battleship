/**
 * Spencer Lefever
 * COSC330 Project1 Battleship
 * 
 * Battleship Controller class
 * for the Battleship game using
 * the MVC design pattern
 */

import javax.swing.SwingUtilities;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

 public class BattleshipController {
    
   private static BattleshipModel model;
   private static BattleshipView view;
   String roleString;
   String inputString;
   String outputString;

   private Role networkRole;

   public BattleshipController(BattleshipModel m, BattleshipView v, String roleString, String host) {

      if(roleString == "server") {
         InetAddress ip;

         try {

            ip = InetAddress.getLocalHost();
            System.out.println(ip);
         } catch (UnknownHostException e) {
            e.printStackTrace();
         }
         networkRole = new BattleshipServer();
      }
      else if(roleString == "client") {
         networkRole = new BattleshipClient(host);
      }
      model = m;
      view = v;
      this.roleString = roleString;
   }

   /**
    * Method to play the game
    *
    * Server goes first and shoots
    * Client sends hit/miss
    * Server gets data and updates
    * Client shoots
    * Server sends hit/miss
    * Client updates
    */

   public void playGame() {

      //TODO Set the player grid
      model.player.setGridRand();

      //Update grids after theyre set
      


      //TODO Establish network connection
      try { 
         networkRole.connect();
      } catch (IOException ioException) {
         ioException.printStackTrace();
      } 


     //Loop to play the game until a player has won
      while(!model.player.isWinner()) {
         

         try {

            //Server takes first shot
            /**
             * After the server shot,
             * outputString holds the output from the server
             * inputString holds message the client received from the server
             */
            serverShot();

            /**
             * Take input string and update the model
             * 
             * For the text field test gui 
             * <0-9><space><0-9> will be passed in
             */
            int xCoordinate, yCoordinate;


            //Update board

            //Client should send message to server regarding the results
            clientSendResutlt();

            //Notify observers of new message being sent
            view.notifyObserver(inputString);


            /**
             * After the client shot
             * outputString hold the output from the client
             * inputString hold the message the server received from the client
             */
            //Client takes second shot
            clientShot();

            //Update board

            //Server should send message to server regarding the results
            serverSendResult();

            view.notifyObserver(inputString);

         } catch (IOException ioException) {
            ioException.printStackTrace();
         }

      }

      //After a player has won, send a message to opponent to indicate the game is over

      //Close the connection
      networkRole.closeConnection();

   }


   //Method for the server to shoot
   public void serverShot() throws IOException {

      if(roleString == "server") {
         //Do while no input has been sent
         do {
            networkRole.getStreams();
            networkRole.processConnection();
            outputString = networkRole.getOutputString();
            System.out.println("Printing output string " + outputString);  

         } while(!outputString.equals("SERVER>>> "));
         
      } else if (roleString == "client") {

         do {
            networkRole.getStreams();
            inputString = networkRole.processConnection();
         } while( !inputString.equals("SERVER>>> TERMINATE") );

      }

   }

   //Method for the client to shoot
   public void clientShot() throws IOException {

      if(roleString == "client") {
            networkRole.getStreams();
            networkRole.processConnection();
            outputString = networkRole.getOutputString();
            System.out.println("Printing output string " + outputString);  

         
      } else if (roleString == "server") {

         //Dont allow Server to type in input field while it's the client's turn
         view.inputField.setEditable(false);
         networkRole.getStreams();
         inputString = networkRole.processConnection();
      }

   }

   //Method for the client to send the result to the server
   public void clientSendResutlt() throws IOException {
      if(roleString == "client") {
         String message = interpretMessage(inputString);
         networkRole.sendData(message);
         

      } else if (roleString == "server") {   //Wait to receive result if client
         networkRole.getStreams();
         networkRole.processConnection();
         outputString = networkRole.getOutputString();
      }
   }

   //Method for the server to send the result of the shot to the client
   public void serverSendResult() throws IOException {
      if(roleString == "server") {
         String message = interpretMessage(inputString);
         networkRole.sendData(message);

      } else if (roleString == "client") {   //Wait to receive results if client
         networkRole.getStreams();
         networkRole.processConnection();
         outputString = networkRole.getOutputString();
      }
   }

   /**
    * Method to interpret the coordinate message
    * Method assumes shot message comes in as <x><space><y>
    */

   final int NO_SHOT = -1;
   final int SHOT_MISS = 0;
   final int SHOT_HIT = 1;

   public String interpretMessage(String message) {
      String x = message.substring(0,0); 
      String y = message.substring(2, 2);
      String resultString;

      int result = model.player.checkShot(Integer.parseInt(x), Integer.parseInt(y));

      if(result == SHOT_MISS) {
         resultString = "miss";
         return resultString;
      } else if (result == SHOT_HIT) {
         resultString = "hit";
         return resultString;
      } else {
         resultString = null;
         return resultString;
      }

   }


 }