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
    private boolean winStatus;

    private TargetGrid targetGrid;
    private OceanGrid oceanGrid;
    private Grid shipGrid;

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

    public void fireShot(boolean[][] sGrid, Shot s) {
       int x = s.getxCoordinate();
       int y = s.getyCoordinate();

       //Evaluates to true is shot is a hit
       if(sGrid[x][y]) {
            //Update players target grid to reflect shot
            this.targetGrid.setCell(x,y,true);
       } else {
            //Update players target grid to reflect shot
            this.targetGrid.setCell(x,y,false);
       }
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
        
            Ship[] sArr = this.oceanGrid.getShipArr();

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
            setShip(shipGrid.getGrid(), sArr[i]);
        }
    }

    /**
     * Method used to check if two ships
     * will overlap 
     */
    public boolean checkOverlap(boolean[][] sGrid, Ship ship) {
        int size = ship.getSize();
        //Get coordinate and subtract one for array index
        int x = (ship.getxLocation())-1;
        int y = (ship.getyLocation()) -1;
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

    /**
     * Method to set a new ship onto 
     * the ship grid used in setGrid method
     */

    public void setShip(boolean[][] sGrid, Ship ship) {
        //Evaluated to true if ship is vertical
        if(ship.getOrientation()) {
            for(int i=0; i<ship.getSize(); i++) {
                sGrid[ship.getxLocation()][(ship.getyLocation())+i] = true;
            }
        } else {
            for(int i=0; i<ship.getSize(); i++) {
                sGrid[(ship.getxLocation())+i][ship.getyLocation()] = true;
            }
        }
    }


 }