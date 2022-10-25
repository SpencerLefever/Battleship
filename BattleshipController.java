/**
 * Spencer Lefever
 * COSC330 Project1 Battleship
 * 
 * Battleship Controller class
 * for the Battleship game using
 * the MVC design pattern
 */

import javax.swing.SwingUtilities;
import java.awt.event.*;
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

   public BattleshipController(BattleshipModel m, BattleshipView v, String role, String host) {
      roleString = role;

      if(roleString.equals("server")) {
         InetAddress ip;

         try {

            ip = InetAddress.getLocalHost();
         } catch (UnknownHostException e) {
            e.printStackTrace();
         }
         networkRole = new BattleshipServer();
      }
      else if(roleString.equals("client")) {
         networkRole = new BattleshipClient(host);
      }
      else {
         System.out.println("Player must be server or client role");
         UnknownHostException err = new UnknownHostException();
         err.printStackTrace();
      }
      model = m;
      view = v;
      view.setVisible(true);
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
      

      //Connect across the network
      networkRole.setup();
      
      

     //Loop to play the game until a player has won
      while(!model.player.isWinner()) {
         
                  
      }
      System.out.println("After is winner while loop");


      //After a player has won, send a message to opponent to indicate the game is over

      //Close the connection
      networkRole.closeConnection();

   }

   /**
    * Method to interpret the coordinate message
    * Method assumes shot message comes in as <x><space><y>
    */

   final int NO_SHOT = -1;
   final int SHOT_MISS = 0;
   final int SHOT_HIT = 1;

   public String interpretShot(String message) {
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

   /**
    * Method for the controller to interpret the results of the shot message
    */

    public void interpretResult(String message) {

    }

 }