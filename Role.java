import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Spencer Lefever
 * COSC330 Project 1 Battleship
 * 
 * Role interface for the server or client role
 */

 public interface Role {

   public void setup();

   public void getStreams() throws IOException;

   public String processConnection() throws IOException;

   public void closeConnection();

   public String getOutputString();

   public void sendData(String message);

   public void connect() throws IOException;

 }