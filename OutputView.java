/**
 * Spencer Lefever
 * COSC330 Project 1 Battleship
 * 
 * Output view class implementing observer
 * 
 * This class is used for testing network connection with basic gui
 */


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;

public class OutputView extends JTextArea implements Observer{



   public OutputView() {
       super();
       setVisible(true);
   }

   
   /**
    * This update method receives the message
    * of hit or miss and displays the results 
    * in displayField 
    * @overrides Observer update method
    */
   public void update(String message) {
        append(message);
   }

   /**
    * Method to parse message being passed in
    * 
    * Messages will either be coordinates or
    * and indication of a hit or miss
    */
   
   
    public void parseMessage(String message) {

   }
}