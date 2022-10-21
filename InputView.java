/**
 * Spencer Lefever
 * COSC330 Project 1 Battleship
 * 
 * Input view class implementing observer
 * 
 * This class is used for testing network connection with basic gui
 */

 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import javax.swing.JTextField;

 public class InputView extends JTextField implements Observer{

    JTextField inputField;
    private String message;
    

    public InputView() {
        inputField = new JTextField();
        inputField.setEditable(true);
        inputField.setVisible(true);
        inputField.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    message = event.getActionCommand();
                }
            }
        );
    }

    //Overridden update method
    public void update(String message) {
        
    }

    //message getter and setter
    public String getMessage(){
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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