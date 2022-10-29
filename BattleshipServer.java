import java.net.*;
import java.io.*;
import java.io.IOException;

/**
 * Spencer Lefever
 * COSC330 Project1 Battleship
 * 
 * Network Role Interface Implemnted for Server Role
 */

 public class BattleshipServer implements NetworkRole {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public  BattleshipServer(int port) {
        try {
            openConnection(port);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    public void openConnection(int port) throws IOException {
        System.out.println("Waiting for client to connect");
        serverSocket = new ServerSocket(port, 100);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void sendData(String message) throws IOException {
        System.out.println("SERVER>>> " + message);
        out.println("SERVER>>> " + message);
    }

    public String readData() throws IOException {
        String message = in.readLine();
        System.out.println(message);
        return message;
    }

    public void closeConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
 }