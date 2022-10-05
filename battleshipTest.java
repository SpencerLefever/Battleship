/**
 * Spencer Lefever
 * COSC330 Project 1 Battleship
 * 
 * Main testing class for Battleship game
 * 2 Players will be created and will
 * take turns firing shots until a 
 * winner is determined
 */

 import java.lang.Math;

 public class battleshipTest {
    public static void main(String[] args) {

        final int NUM_SHIPS = 5;
        final int GRID_LENGTH = 10;
        final int GRID_WIDTH = 10;
        
        Player player1 = new Player(GRID_LENGTH, GRID_WIDTH);
        Player player2 = new Player(GRID_LENGTH, GRID_WIDTH);

        Grid oceanGrid1 = player1.getOceanGrid();
            
        Grid targetGrid2 = player2.getTargetGrid();
            

        //Set grids
        player1.setGridRand();
        player2.setGridRand();

        int count = 0;
        //Loop taking turns shooting until someone wins
        do {
            
            //Player 1 fires shot
            Shot player1Shot = new Shot((int)(9.0*Math.random()), (int)(9.0*Math.random()));

            player1.fireShot(player2.getShipGrid(), player2.getOceanGrid(), player1Shot);

            //Player 2 fires shot
            Shot player2Shot = new Shot((int)(9.0*Math.random()), (int)(9.0*Math.random()));

            player2.fireShot(player1.getShipGrid(), player1.getOceanGrid(), player2Shot);

            if(count==4) {
                //Print player 2 target grid for testing
                System.out.println("Player 1 Ocean Grid");
                oceanGrid1.printGrid();
                System.out.println();
    
                //Print player 1 target grid for testing
                System.out.println("Player 2 Target Grid");
                targetGrid2.printGrid();
                System.out.println();
            }
            count++;

        }while(!player1.isWinner(player1.getTargetGrid()) && !player2.isWinner(player2.getTargetGrid()));
        System.out.println("Count: " + count);

        if(player1.isWinner(player1.getTargetGrid())) { 

        }
    }

 }