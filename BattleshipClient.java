/**
 * Spencer Lefever
 * COSC330 Battleship
 * 
 * Battleship Client Class
 * Singleton Pattern class
 */

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class BattleshipClient implements Role
{

    private ObjectInputStream input; // input stream from server
    private ObjectOutputStream output;
    private String message = ""; // message from server
    private String battleshipServer; // host server for this application
    private Socket client; // socket to communicate with server
  
    public BattleshipClient(String host) { 
        battleshipServer = host;
    }


    //Method to connect to server and process messages from server
    public void setup() {
        try {
            //Connect to server
            connectToServer();

            //Get i/o streams
            getStreams();

            //process connection
            processConnection();

        } catch ( EOFException eofException ) {
            System.out.println("Client terminated connection");
        } catch ( IOException ioException ) {
            ioException.printStackTrace();
        }
        finally {
            closeConnection();
        }
    }

    //Method to connect to the server
    public void connectToServer() throws IOException {
        System.out.println("Attempting to connect");

        client = new Socket( InetAddress.getByName(battleshipServer), 12345);

        System.out.println("Connected to: " + client.getInetAddress().getHostName());
    }

    //Method to send and receive data
    public void getStreams() throws IOException {

        output = new ObjectOutputStream(client.getOutputStream());
        
        //MIGHT CAUSE AN ISSUE
        message = output.toString();

        output.flush();

        input = new ObjectInputStream(client.getInputStream());

        System.out.println("Got i/o streams");
    }

    //Method to process connection with server
    public String processConnection() throws IOException {

        //Send message to client
        sendData(message);

        do {
            try {
                message = (String) input.readObject(); // read new message

                //TODO send message to controller to update data
            }
            catch (ClassNotFoundException classNotFoundException) {
                System.out.println("Unkown object type received");
            }
        } while ( !message.equals("SERVER>>> TERMINATE") );
        return message;
    }

    //Method to close the connection
    public void closeConnection() {
        System.out.println("Closing connection");

        try {
            output.close();
            input.close();
            client.close();
        } catch ( IOException ioException ) {
            ioException.printStackTrace();
        }
    }

    public void sendData(String message) {
        
        try {
            output.writeObject("CLIENT>>> " + message);
            output.flush();  
            
            //TODO Find out how to update view
        }
        catch (IOException ioException) {
            System.out.println("Error writing object");
        }
    }

    //Method to get output object
    public String getOutputString() {
        return message;
    }

} // end class Client