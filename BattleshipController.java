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


 public class BattleshipController {
    
   private static BattleshipModel model;
   private static BattleshipView view;
   private ObjectOutputStream output; // output stream to server
   private ObjectInputStream input; // input stream from server

   private Role role;

   public BattleshipController(BattleshipModel m, BattleshipView v, String roleString) {

      if(roleString == "server") {
         role = new BattleshipServer();
      }
      else if(roleString == "client") {
         role = new BattleshipClient();
      }
      model = m;
      view = v;
   }


   //Method to establish connection
   public void connect() {
      role.setup();
   }

 }