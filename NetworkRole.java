import java.net.*;
import java.io.*;
import java.io.IOException;


/**
 * Spencer Lefever
 * COSC330 Project1 Battleship
 * 
 * Network role interface for client and server
 */

 public abstract class NetworkRole {
    public ServerSocket serverSocket;
    public Socket clientSocket;
    public PrintWriter out;
    public BufferedReader in;

    abstract void openConnection(int port) throws IOException;

    abstract void sendData(String message) throws IOException;

    abstract String readData() throws IOException;

    abstract void closeConnection() throws IOException;
 }

