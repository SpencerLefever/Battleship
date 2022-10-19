/**
 * Spencer Lefever
 * COSC330 Project 1 Battleship
 * 
 * Main testing class for Battleship game
 * 2 Players will be created and will
 * take turns firing shots until a 
 * winner is determined
 */

 import java.io.IOError;
import java.lang.Math;

 public class battleshipTest {
    public static void main(String[] args) {

        final int NUM_SHIPS = 5;
        final int GRID_LENGTH = 10;
        final int GRID_WIDTH = 10;

        if(args.length != 3) {
            System.out.println("Error: Role and host name must be passed\nEnter null if role is server");
            System.exit(-1);
        }
        
        Player player1 = new Player(GRID_LENGTH, GRID_WIDTH, args[1], args[2]);
        Player player2 = new Player(GRID_LENGTH, GRID_WIDTH, args[1], args[2]);


        //Set grids
        player1.setGridRand();
        player2.setGridRand();

        //Loop taking turns shooting until someone wins
        do {
            
            //Player 1 fires shot
            Shot player1Shot = new Shot((int)(9.0*Math.random()), (int)(9.0*Math.random()));

            player1.fireShot(player2.getShipGrid(), player1Shot);

            //Player 2 fires shot
            Shot player2Shot = new Shot((int)(9.0*Math.random()), (int)(9.0*Math.random()));

            player2.fireShot(player1.getShipGrid(), player2Shot);

        }while(!player1.isWinner() && !player2.isWinner());
    
        Grid targetGrid1 = player1.getTargetGrid();
        Grid shipGrid1 = player1.getShipGrid();
        
        Grid targetGrid2 = player2.getTargetGrid();
        Grid shipGrid2 = player2.getShipGrid();
        
        if(player1.isWinner()) {
            System.out.println("Player 1 Won");
            //System.out.println("Player 1 Ocean Grid");
            //oceanGrid1.printGrid();
            System.out.println("\nPlayer 1 Target Grid");
            targetGrid1.printGrid();
            System.out.println("\nPlayer 2 Ship Grid");
            shipGrid2.printGrid();
            System.out.println("\n\n\nPlayer 2's Target Grid");
            targetGrid2.printGrid();
            System.out.println("\n\n\nPlayer 1's Ship Grid");
            shipGrid1.printGrid();

        }
        if(player2.isWinner()) {
            System.out.println("Player 2 Won");
            //System.out.println("Player 2 Ocean Grid");
            //oceanGrid2.printGrid();
            System.out.println("\nPlayer 2 Target Grid");
            targetGrid2.printGrid();
            System.out.println("\nPlayer 1 Ship Grid");
            shipGrid1.printGrid();
            System.out.println("\n\n\nPlayer 1's Target Grid");
            targetGrid1.printGrid();
            System.out.println("\n\n\nPlayer 2's Ship Grid");
            shipGrid2.printGrid();

        }
    }
 }