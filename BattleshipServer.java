/**
 * Spencer Lefever
 * COSC330 Battleship
 * 
 * Battleship Server Class
 * Singleton Pattern class
 */

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.SwingUtilities;
import java.net.InetAddress;
import java.net.UnknownHostException;



public class BattleshipServer implements Role {

   private ObjectOutputStream output; // output stream to client
   private ObjectInputStream input; // input stream from client
   private ServerSocket server; // server socket
   private Socket connection; // connection to client
   private String message;
   private int counter = 1; // counter of number of connections



    public BattleshipServer() { }
    
    //Setup method to run the server
    public void setup() {
        try // set up server to receive connections; process connections
      {
         server = new ServerSocket( 12345, 100 ); // create ServerSocket

         while ( true ) 
         {
            try 
            {
                //wait for connection
                connect();
                //get input and output streams
                getStreams();
                //process connection
                processConnection();

            } // end try
            catch ( EOFException eofException ) 
            {
               
            } // end catch
            finally 
            {
               //  close connection
               counter++;
            } // end finally
         } // end while
      } // end try
      catch ( IOException ioException ) 
      {
         ioException.printStackTrace();
      } // end catch
    }


    //Wait for connection method
    public void waitForConnection() throws IOException {
        //Send waiting for connection message to controller
        System.out.println("Waiting for connection\n");
        //Connect with client
        connection = server.accept();

        //Send connection successful message
        System.out.println("Connection received from: " 
            + connection.getInetAddress().getHostName());

    }

    public void connect() throws IOException {
        //Send waiting for connection message to controller
        System.out.println("Waiting for connection\n");
        //Connect with client
        connection = server.accept();

        //Send connection successful message
        System.out.println("Connection received from: " 
            + connection.getInetAddress().getHostName());

    }

    //Method to get input and output streams to send to controller
    public void getStreams() throws IOException {
        output = new ObjectOutputStream(connection.getOutputStream());

        //THIS MIGHT BE A PROBLEM LATER
        message = output.toString();
        output.flush();

        input = new ObjectInputStream( connection.getInputStream() );

        System.out.println("Got i/o steams\n");
    }

    //Method to process the connection with the client
   public String processConnection() throws IOException {
        
        String message = "Connection successful";

        //Send connection successful message to client
        sendData(message);

        
        do {
            try {
                message = (String) input.readObject();

                //TODO Update players view

                
            } catch (ClassNotFoundException classNotFoundException) {
                System.out.println("Unkown object type received\n");
            }
        } while (!message.equals("CLIENT>>> TERMINATE"));
        return message;
    }

    //Method for server to close connection
    public void closeConnection() {

        System.out.println("Terminating connection\n");

        try {
            output.close();
            input.close();
            connection.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void sendData(String message) {
        try {
            output.writeObject("SERVER>>> " + message);
            output.flush();

            //TODO find out how to update view
        }
        catch(IOException ioException) {
            System.out.println("Error writing object");
        }
    }

    //Method to get output object
    public String getOutputString() {
        return message;
    }

  
 }