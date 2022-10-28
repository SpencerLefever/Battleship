import java.io.IOException;

/**
 * Spencer Lefever
 * COSC330 Project1 Battleship
 * 
 * Network role interface for client and server
 */

 public interface NetworkRole {
    public void openConnection(int port) throws IOException;

    public void sendData(String message) throws IOException;

    public String readData() throws IOException;

    public void closeConnection() throws IOException;
 }

