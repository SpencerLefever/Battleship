import java.net.*;
import java.io.*;
import java.io.IOException;

/**
 * Spencer Lefever
 * COSC330 Project1 Battleship
 * 
 * Network Role Interface Implemnted for Server Role
 */

 public class BattleshipServer extends NetworkRole {

    // public ServerSocket serverSocket;
    // public Socket clientSocket;
    // public PrintWriter out;
    // public BufferedReader in;

    public  BattleshipServer(String ip, int port) {
        try {
            openConnection(ip, port);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    public void openConnection(String ip, int port) throws IOException {
        System.out.println("Waiting for client to connect");
        serverSocket = new ServerSocket(port, 100);

        System.out.println(InetAddress.getLocalHost().getHostAddress());

        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void sendData(String message) throws IOException {
        out.println("SERVER>>> " + message);
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