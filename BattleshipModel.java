/**
 * Spencer Lefever 
 * COSC330 Project1 Battleship
 * 
 * Battleship model class for the
 * model view controller design pattern
 */



 public class BattleshipModel {

    final int NUM_SHIPS = 5;
    final int GRID_LENGTH = 10;
    final int GRID_WIDTH = 10;

    Player player;
    String networkRole;
    String hostName;


    public BattleshipModel(){
        player = new Player(GRID_LENGTH, GRID_WIDTH, networkRole, hostName);
    }

    public String getRole() {
        return networkRole;
    }

    public void setRole(String role) {
        this.networkRole = role;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    
 }