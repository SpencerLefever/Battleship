/**
 * Spencer Lefever
 * COSC330 Project1
 * 
 * Battleship Game Shop class
 * for shots fired by player in game
 * 
 * Shot 2 location variables int x and int y  
 */

public class Shot {

    //X and Y coordinate variables
    private int xCoordinate;
    private int yCoordinate;

    public Shot(int x, int y) {
        xCoordinate = x;
        yCoordinate = y;
    }

    //xCoordinate getter and setter
    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int x) {
        xCoordinate = x;
    }

    //yCoordinate getter and setter
    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int y) {
        yCoordinate = y;
    }

}
