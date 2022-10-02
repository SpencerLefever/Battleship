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

    private Ship[] shipArr = new Ship[NUM_SHIPS];

    /**
     * This grid will hold the true false values
     * for the location of the ships while the other
     * grid holds true false values for hits/misses
     * 
     * This grid will be used in the player class to
     * set the grid and check if a user shot is a hit or miss
     */

    // Constructor with deep copy of ship array
    public OceanGrid(int l, int w) {
        super(l, w);

        
        // Set sArr to dif battleships
        shipArr[0] = new Ship(5, "Carrier", false, 0, 0, true);
        shipArr[1] = new Ship(4, "Battleship", false, 0, 0, true);
        shipArr[2] = new Ship(3, "Cruiser", false, 0, 0, true);
        shipArr[3] = new Ship(3, "Submarine", false, 0, 0, true);
        shipArr[4] = new Ship(2, "Destroyer", false, 0, 0, true);

        // Deep copy shipArr
        for (int i = 0; i < NUM_SHIPS; i++) {
            shipArr[i] = shipArr[i];
        }
    }

    OceanGrid(OceanGrid o) {
        super(o);
        // Deep copy shipArr
        for (int i = 0; i < NUM_SHIPS; i++) {
            this.shipArr[i] = o.shipArr[i];
        }
    }

    /*
    // Copy constructor
    public OceanGrid(OceanGrid oceanGrid) {
        super(oceanGrid.getLength(), oceanGrid.getWidth(), oceanGrid.getGrid());
        this.shipArr = oceanGrid.shipArr;

    }
    */
    // Ship array getters and setters
    public Ship[] getShipArr() {
        return shipArr;
    }

    public void setShipArr(Ship[] sArr) {
        // Deep copy shipArr
        for (int i = 0; i < NUM_SHIPS; i++) {
            shipArr[i] = sArr[i];
        }
    }

}