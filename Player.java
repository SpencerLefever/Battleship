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
 import java.lang.Math; //Used for random ship placement


 public class Player {

    final int NUM_SHIPS = 5;
    final int GRID_LENGTH = 10;
    final int GRID_WIDTH = 10;

    
    /**
     * * Ocean/Ship Grid Constants Key
     *  -1: No ship
     *   0: Ship Good
     *   1: Ship Hit
     * 
     *  Target Grid Key
     *  -1: Null/No Shot fired
     *   0: Miss/Shot fired
     *   1: Hit/Shot fired 
     */

     final int NO_SHIP = -1;
     final int SHIP_GOOD = 0;
     final int SHIP_HIT = 1;

     final int NO_SHOT = -1;
     final int SHOT_MISS = 0;
     final int SHOT_HIT = 1;


   //Create grids and array of ships for player
    private Grid targetGrid;
    private Grid shipGrid;
    private Ship[] shipArr = new Ship[NUM_SHIPS];

    //Variables for server and client
    private BattleshipClient client;
    private BattleshipServer server;
    
    public Player(int length, int width, String role, String host) {
        //Initialize grids
        targetGrid = new Grid(length, width);
        shipGrid = new Grid(length, width);
        
        //Initialize shipArr
        shipArr[0] = new Ship(5, "Carrier", false, -1, -1, true);
        shipArr[1] = new Ship(4, "Battleship", false, -1, -1, true);
        shipArr[2] = new Ship(3, "Cruiser", false, -1, -1, true);
        shipArr[3] = new Ship(3, "Submarine", false, -1, -1, true);
        shipArr[4] = new Ship(2, "Destroyer", false, -1, -1, true);
    }

    public Grid getTargetGrid() {
        return targetGrid;
    }

    public void setTargetGrid(Grid t) {
        targetGrid = t;
    }

    public Grid getShipGrid() {
        return shipGrid;
    }

    public void setShipGrid(Grid s) {
        shipGrid = s;
    }

    public BattleshipClient getBattleshipClient() {
        return client;
    }

    public void setBattleshipClient(BattleshipClient c) {
        client = c;
    }

    public BattleshipServer getBattleshipServer() {
        return server;
    }

    public void setBattleshipServer(BattleshipServer s) {
        server = s;
    }
   
    /**
     * Method to determine is a player has won
     * the method will check the status of the 
     * player's target grid to see if they have made 
     * the necessary amount of hits to sink all ships
     * 
     * @returns false when player has not won
     * @returns true when player has won
     * 
     * The method loops through the target grid 
     * to count amount of hits the player has
     * if the player has 17 total hits, that 
     * means they have hit every ship completely
     */
    public boolean isWinner() {
        int numHits = 0;

        /**
         * Loop to accumulate the ship size to 
         * check if a player has sunk every ship 
         */
        int shipSize = 0;
        for(int i=0; i<NUM_SHIPS; i++){
            shipSize += shipArr[i].getSize();
        }

        for(int i=0; i<GRID_LENGTH; i++) {
            for(int j=0; j<GRID_WIDTH; j++) {
                if(targetGrid.getCell(i,j) == SHOT_HIT) {
                    numHits++;
                }
            }
        }
        if(numHits == shipSize) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Method for a player to fire a shot
     * the method will take coordinates for
     * the shot from the user and check if 
     * the shot is a hit or miss
     * 
     * Passes in the opponents ship grid and ocean grid
     * to check for ships and update ocean grid
     * Server or Host role is also passed in so correct method is called
     */

    public void fireShot(Grid opponentGrid, Shot s) {
       int x = s.getxCoordinate();
       int y = s.getyCoordinate();

       //Return if shot is a repeat shot
       if(targetGrid.getCell(x, y) == SHOT_MISS || targetGrid.getCell(x, y) == SHOT_HIT) {
            return;
       }

       //Evaluates to true is shot is a hit
       if(opponentGrid.getCell(x, y) == SHIP_GOOD) {
            /**
             * Update the player's target grid to reflect hit
             * Update opponents ship grid that was passed in
             */
            targetGrid.setCell(x,y,SHIP_HIT);
            opponentGrid.setCell(x, y, SHIP_HIT);
       } else if (opponentGrid.getCell(x, y) == NO_SHIP) {

          /**
           * Update player's target grid to reflect miss
           */
            targetGrid.setCell(x,y,0);
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

    public void setGridRand() {

        for(int i=0; i<NUM_SHIPS; i++){            
            do
            {
                //Set random location for ship head
                shipArr[i].setxLocation((int)(9.0*Math.random()));
                shipArr[i].setyLocation((int)(9.0*Math.random()));

                //Set randon orientations
                double orientation = Math.random();
                if(orientation > 0.5){
                    shipArr[i].setOrientation(true);
                }
                else {
                    shipArr[i].setOrientation(false);
                }

                //Check for overlapping ships
                for(int j=0; j<NUM_SHIPS; j++) {

                }

            } while(checkOverlap(shipGrid.getGrid(), shipArr[i]));

            //Update ship array to true values for ship
            setShip(shipGrid.getGrid(), shipArr[i]);

            System.out.println("Ship Grid after ship placement " + (i+1) + "\n");
            shipGrid.printGrid();
        }
    }

    /**
     * Method used to check if two ships
     * will overlap 
     */
    public boolean checkOverlap(int[][] sGrid, Ship ship) {
        int size = ship.getSize();
        //Get coordinate and subtract one for array index
        int x = (ship.getxLocation());
        int y = (ship.getyLocation());

        //Check grid when ship is vertical
        if(ship.getOrientation()) {

            //Check if ship is out of bounds
            if(((x + size) > 9) || (x < 0)) {
                return true;
            }

            //Check if ship head has neighbors

            //Check right left of head if head is not on edge
            if(y>=1) {
                if(sGrid[x][y-1] == SHIP_GOOD) {
                    return true;
                }
                if(sGrid[x][y+1] == SHIP_GOOD) {
                    return true;
                }
            }
            //Check above head
            if(x>=1) {
                if(sGrid[x-1][y] == SHIP_GOOD) {
                    return true;
                }
            }

            //Check if ship tail has neighbors 
            //Check if tail is on bottom edge
            if(x+size+1 < GRID_LENGTH) {
                //Check under tail if not on edge
                if(sGrid[x+size+1][y] == SHIP_GOOD) {
                    return true;
                }
            }
            //Loop check overlap
            for(int i=0; i<size; i++) {
                //Evaluated to true if there is a ship in the spot
                if(sGrid[x+i][y] == SHIP_GOOD) {
                    return true;
                }
                //If ship is not on the edge check for neighbors

                //Check for neighbors if ship is not on an edge
                if(y>0 && y+1 < GRID_LENGTH) {
                    if(sGrid[x][y+1] == SHIP_GOOD) {
                        return true;
                    }
                    if(sGrid[x][y-1] == SHIP_GOOD) {
                        return true;
                    }

                }else {
                    //Check for neighbors if ship is on left edge
                    if(y==0) {
                        if(sGrid[x][y+1] == SHIP_GOOD) {
                            return true;
                        }
                    }
                    //Check for neightbors if ship is on right edge
                    if((y+1) == GRID_LENGTH) {
                        if(sGrid[x][y-1] == SHIP_GOOD) {
                            return true;
                        }
                    }
                }
            }

        } else { //Check when ship is horizontal

            //Check if ship is out of bounds
            if((y < 0) || ((y+size) > 9)) {
                return true;
            }

            //Check right of head
            if(y>=1) {
                if(sGrid[x][y-1] == SHIP_GOOD) {
                    return true;
                }
                if(sGrid[x][y+1] == SHIP_GOOD) {
                    return true;
                }
            }
            //Check above head
            if(x>=1) {
                if(sGrid[x-1][y] == SHIP_GOOD) {
                    return true;
                }
            }

            //Check right of tail if not on edge
            if(y+size+1 < GRID_WIDTH) {
                if(sGrid[x][y+size+1] == SHIP_GOOD) {
                    return true;
                }
            }

            //Evaluate to true if ship is in the spot
            for(int i=0; i<size; i++) {
                if(sGrid[x][y+i] == SHIP_GOOD) {
                    return true;
                }
                //If ship is not on the edge check for neighbors

                //Check for neighbors if ship is not on an edge
                if(x>0 && x+1 < GRID_WIDTH) {
                    if(sGrid[x+1][y] == SHIP_GOOD) {
                        return true;
                    }
                    if(sGrid[x-1][y] == SHIP_GOOD) {
                        return true;
                    }

                }else {
                    //Check for neighbors if ship is on left edge
                    if(x==0) {
                        if(sGrid[x+1][y] == SHIP_GOOD) {
                            return true;
                        }
                    }
                    //Check for neightbors if ship is on right edge
                    if((x+1) == GRID_WIDTH) {
                        if(sGrid[x-1][y] == SHIP_GOOD) {
                            return true;
                        }
                    }
                }
            }

            //Check if ship tail has neighbors 
            //Check if tail is on bottom edge
            if(y+size+1 < GRID_WIDTH) {
                //Check under tail if not on edge
                if(sGrid[x][y+size+1] == SHIP_GOOD) {
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

    public void setShip(int[][] sGrid, Ship ship) {
        //Evaluated to true if ship is vertical
        if(ship.getOrientation()) {
            for(int i=0; i<ship.getSize(); i++) {
                sGrid[ship.getxLocation()+i][(ship.getyLocation())] = 0;
            }
        } else {
            for(int i=0; i<ship.getSize(); i++) {
                sGrid[(ship.getxLocation())][ship.getyLocation()+i] = 0;
            }
        }
    }
 }