import java.net.*;
import java.io.*;
import java.io.IOException;

/**
 * Spencer Lefever
 * COSC330 Project1 Battleship
 * 
 * Network Role Interface Implemnted for Server Role
 */

 public class BattleshipClient implements NetworkRole {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public  BattleshipClient(int port) {
        try {
            openConnection(port);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    public void openConnection(int port) throws IOException {
        //THIS InetAddress.getLocalHost() could cause errors when connecting across the network
        System.out.println("Attempting to connect to server");
        clientSocket = new Socket(InetAddress.getLocalHost(), port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void sendData(String message) throws IOException {
        out.println("CLIENT>>> " + message);
    }

    public String readData() throws IOException {
        String message = in.readLine();
        return message;
    }

    public void closeConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
 }