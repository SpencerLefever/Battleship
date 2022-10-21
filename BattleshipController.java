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


            /**
             * After the client shot
             * outputString hold the output from the client
             * inputString hold the message the server received from the client
             */
            //Client takes second shot
            clientShot();

            //Update board

            //Server should send message to server regarding the results


         } catch (IOException ioException) {
            ioException.printStackTrace();
         }

      }

      //After a player has won, send a message to opponent to indicate the game is over

   }


   //Method for the server to shoot
   public void serverShot() throws IOException {

      if(roleString == "server") {
         //Do while no input has been sent
         do {
            networkRole.getStreams();
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
         //Do while no input has been sent
         do {
            networkRole.getStreams();
            outputString = networkRole.getOutputString();
            System.out.println("Printing output string " + outputString);  

         } while(!outputString.equals("CLIENT>>> "));
         
      } else if (roleString == "server") {

         do {
            networkRole.getStreams();
            inputString = networkRole.processConnection();
         } while( !inputString.equals("CLIENT>>> TERMINATE") );

      }

   }


 }