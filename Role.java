import java.io.IOException;

/**
 * Spencer Lefever
 * COSC330 Project 1 Battleship
 * 
 * Role interface for the server or client role
 */

 public interface Role {

    public void setup();

    public void processConnection() throws IOException;
 }