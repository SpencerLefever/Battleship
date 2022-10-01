/** 
 * Spencer Lefever
 * COSC330 Project1
 * 
 * Battleship Game Player class
 * 
 * Each player has a Ship array 
 * An ocean and target grid
 * and a shot object
 * Player also has a winStatus data
 * memeber that will update if a user
 * wins the game
 * 
 * The ship grid holds the location
 * of the players ships with true/false
 * values for location
 */
 import java.lang.Math;


 public class Player {

    final int NUM_SHIPS = 5;

    //Create ocean and target grid objects
    boolean winStatus;

    TargetGrid targetGrid;
    OceanGrid oceanGrid;
    Grid shipGrid;

    public Player(TargetGrid t, OceanGrid o, Grid s, boolean w) {
        targetGrid = t;
        oceanGrid = o;
        shipGrid = s;
        winStatus = w;
    }

    //winStatus getter and setter
    public boolean getWinStatus() {
        return winStatus;
    }

    public void setWinStatus(boolean w) {
        winStatus = w;
    }

    /**
     * Method to determine is a player has lost
     * the method will check the status of the 
     * player's ships to see if he has any standing
     */
    public void isLoser() {
        
    }

    /**
     * Method for a player to fire a shot
     * the method will take coordinates for
     * the shot from the user and check if 
     * the shot is a hit or miss
     */

    public void fireShot(Shot s) {
       
    }

    /** 
     * Method for a player to check if 
     * a shot fired by the other player
     * is a hit or a miss
     */

    public void checkShot() {

    }

    /** 
     * Method for the player to set their
     * ocean grid how they want
     */
    public void setGrid(){}

    /**
     * Method to randomly set a players ocean grid
     */

    public void setGridRand(OceanGrid oceanGrid) {

        for(int i=0; i<NUM_SHIPS; i++){            
        
            Ship[] sArr = oceanGrid.getShipArr();

            do
            {
                //Set random location for ship head
                sArr[i].setxLocation((int)(10.0*Math.random() + 1));
                sArr[i].setyLocation((int)(10.0*Math.random() + 1));
                //Check for overlapping ships
                for(int j=0; j<NUM_SHIPS; j++) {

                }

            } while(checkOverlap(shipGrid.getGrid(),sArr[i]));

            //Update ship array to true values for ship
        }
    }

    /**
     * Method used to check if two ships
     * will overlap 
     */
    public boolean checkOverlap(boolean[][] sGrid, Ship ship) {
        int size = ship.getSize();
        int x = ship.getxLocation();
        int y = ship.getyLocation();
        //Check grid when ship is vertical
        if(ship.getOrientation()) {
            for(int i=0; i<size; i++) {
                //Evaluated to true if there is a ship in the spot
                if(sGrid[x][y+i]) {
                    return true;
                }
            }

        } else { //Check when ship is horizontal
            //Evaluate to true if ship is in the spot
            for(int i=0; i<size; i++) {
                if(sGrid[x+i][y]) {
                    return true;
                }
            }
        }
        return false;

    }


 }