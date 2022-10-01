/**
 * Spencer Lefever 
 * COSC330 Project 1 Battleship
 * 
 * OceanGrid class for Battleship game
 * This is the grid where the player
 * will place their ships for the game
 * 
 * Extends the grid class and adds
 * a ship array for the user to place
 * 
 */

 public class OceanGrid extends Grid { 

    final int NUM_SHIPS = 5;

    private Ship[] shipArr;

    /**
     * This grid will hold the true false values
     * for the location of the ships while the other
     * grid holds true false values for hits/misses
     * 
     * This grid will be used in the player class to
     * set the grid and check if a user shot is a hit or miss
     */

    //Constructor with deep copy of ship array
    public OceanGrid(int l, int w, boolean[][] g, Ship[] sArr, Grid sGrid) {
        super(l,w,g);

        sArr = new Ship[NUM_SHIPS];
        //Set sArr to dif battleships
        sArr[0] = new Ship(5, "Carrier", false, 0, 0, true);
        sArr[1] = new Ship(4, "Battleship", false, 0, 0, true);
        sArr[2] = new Ship(3, "Cruiser", false, 0, 0, true);
        sArr[3] = new Ship(3, "Submarine", false, 0, 0, true);
        sArr[4] = new Ship(2, "Destroyer", false, 0, 0, true);

        //Deep copy shipArr
        for(int i=0; i<NUM_SHIPS; i++) {
            shipArr[i] = sArr[i];
        }
    }

    //Ship array getters and setters
    public Ship[] getShipArr() {
        return shipArr;
    }

    public void setShipArr(Ship[] sArr) {
        //Deep copy shipArr
        for(int i=0; i<NUM_SHIPS; i++) {
            shipArr[i] = sArr[i];
        }
    }


 }