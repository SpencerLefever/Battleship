/**
 * Spencer Lefever
 * COSC330 Project 1 Battleship
 * 
 * Battleship view class
 * for the Battleship game
 * using the MVC design pattern
 * 
 * For now this is just a simple view with a text
 * box to input the coordinates to send
 */

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


 public class BattleshipView extends JFrame{

    private ObjectOutputStream output; // output stream to client
    private ObjectInputStream input; // input stream from client
    private JTextField inputField;
    private JTextArea displayArea;


    public BattleshipView() {
       super("Main Frame");
       setInputField();
       setOutputField();
       add( inputField, BorderLayout.NORTH);
       add( new JScrollPane(displayArea), BorderLayout.CENTER);

    }

    public void setInputField() {
        inputField = new JTextField();
        inputField.setEditable(true);
    }

    public JTextField getInputField () {
        return inputField;
    }

    public void setOutputField() {
        displayArea = new JTextArea();
    }

 }