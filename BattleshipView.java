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

    private JTextField inputField;
    private JTextArea outputField;


    public BattleshipView() {
       super("Main Frame");
       setInputField();
       setOutputField();
       add( inputField, BorderLayout.NORTH);
       add( new JScrollPane(outputField), BorderLayout.CENTER);

    }

    public void setInputField() {
        inputField = new JTextField();
        inputField.setEditable(false);
    }

    public JTextField getInputField () {
        return inputField;
    }

    public void setOutputField() {
        outputField = new JTextArea();
    }






 }