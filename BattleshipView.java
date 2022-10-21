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

import java.util.ArrayList;
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


//  public class BattleshipView implements Subject{
 public class BattleshipView implements Subject {

    private ArrayList<Observer> observers;
    //Variable to hold message being sent across the network
    private String message;

    private JFrame mainFrame;
    // private JTextField inputField;
    // private JTextArea displayField;

    public InputView inputField;
    public OutputView displayField;



    public BattleshipView() {
        this.observers = new ArrayList<>();
        inputField = new InputView();
        displayField = new OutputView();

        mainFrame = new JFrame("Battleship");


        this.setInputField();
        mainFrame.add(inputField, BorderLayout.NORTH);

        this.setDisplayField();
        mainFrame.add( new JScrollPane(displayField) , BorderLayout.CENTER);

        mainFrame.setSize(300,150);
        mainFrame.setVisible(true);

        addObserver(displayField);
        addObserver(inputField);
    }

    //Subject interface methods
    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void deleteObserver(Observer o) {
        int i= observers.indexOf(o);
        if(i>=0) {
            observers.remove(i);
        }
    }

    public void notifyObserver(String message) {
        for(Observer o: observers) {
            o.update(message);
        }
    }

    //Getters and setters
    public void setInputField() {
        inputField = new InputView();
        inputField.setEditable(true);

        inputField.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    message = event.getActionCommand();
                    inputField.setText("");
                }
            }
        );
    }

    public JTextField getInputField () {
        return inputField;
    }

    public void setDisplayField() {
        displayField = new OutputView();
    }
    
    public JTextArea getDisplayField() {
        return displayField;
    }

    public String sendData(String message) {
        return message;
    }
 }