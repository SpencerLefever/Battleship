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

public class BattleshipClient {

    private ObjectOutputStream output; 
    private ObjectInputStream input; 
    private String message = ""; 
    private String chatServer; 
    private Socket client; 
    private BattleshipClient instance = null;

    private BattleshipClient(String host) {
        chatServer = host;
        
        sendData(host);
    }

    public BattleshipClient getInstance(String host) {
      if(instance == null) {
         instance = new BattleshipClient(host);
      }

      return instance;
    }

     // connect to server and process messages from server
   public void runClient() 
   {
      try // connect to server, get streams, process connection
      {
         connectToServer(); // create a Socket to make connection
         getStreams(); // get the input and output streams
         processConnection(); // process connection
      } // end try
      catch ( EOFException eofException ) 
      {
         System.out.println( "\nClient terminated connection" );
      } // end catch
      catch ( IOException ioException ) 
      {
         ioException.printStackTrace();
      } // end catch
      finally 
      {
         closeConnection(); // close connection
      } // end finally
   } // end method runClient

   // connect to server
   private void connectToServer() throws IOException
   {      
      System.out.println( "Attempting connection\n" );

      // create Socket to make connection to server
      client = new Socket( InetAddress.getByName( chatServer ), 12345 );

      // display connection information
      System.out.println( "Connected to: " + 
         client.getInetAddress().getHostName() );
   } // end method connectToServer

   // get streams to send and receive data
   private void getStreams() throws IOException
   {
      // set up output stream for objects
      output = new ObjectOutputStream( client.getOutputStream() );      
      output.flush(); // flush output buffer to send header information

      // set up input stream for objects
      input = new ObjectInputStream( client.getInputStream() );

      System.out.println( "\nGot I/O streams\n" );
   } // end method getStreams

   // process connection with server
   private void processConnection() throws IOException
   {
      do // process messages sent from server
      { 
         try // read message and display it
         {
            message = ( String ) input.readObject(); // read new message
            System.out.println( "\n" + message ); // display message
         } // end try
         catch ( ClassNotFoundException classNotFoundException ) 
         {
            System.out.println( "\nUnknown object type received" );
         } // end catch

      } while ( !message.equals( "SERVER>>> TERMINATE" ) );
   } // end method processConnection

   // close streams and socket
   private void closeConnection() 
   {
      System.out.println( "\nClosing connection" );

      try 
      {
         output.close(); // close output stream
         input.close(); // close input stream
         client.close(); // close socket
      } // end try
      catch ( IOException ioException ) 
      {
         ioException.printStackTrace();
      } // end catch
   } // end method closeConnection

   // send message to server
   private void sendData( String message )
   {
      try // send object to server
      {
         output.writeObject( "CLIENT>>> " + message );
         output.flush(); // flush data to output
         System.out.println( "\nCLIENT>>> " + message );
      } // end try
      catch ( IOException ioException )
      {
         System.out.println( "\nError writing object" );
      } // end catch
   } // end method sendData


}