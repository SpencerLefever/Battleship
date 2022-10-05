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

        }while(player1.isWinner(player1.getTargetGrid()) && player2.isWinner(player2.getTargetGrid()));
    
        Grid targetGrid1 = player1.getTargetGrid();
        Grid shipGrid1 = player1.getShipGrid();
        Grid oceanGrid1 = player1.getOceanGrid();
        
        Grid targetGrid2 = player2.getTargetGrid();
        Grid shipGrid2 = player2.getShipGrid();
        Grid oceanGrid2 = player2.getOceanGrid();

        if(player1.isWinner(player1.getTargetGrid())) {
            System.out.println("Player 1 lost");
            System.out.println("Player 1 Ocean Grid");
            oceanGrid1.printGrid();
            System.out.println("\nPlayer 1 Target Grid");
            targetGrid1.printGrid();
            System.out.println("\nPlayer 2 Ship Grid");
            shipGrid2.printGrid();
        }
        if(player2.isWinner(player2.getTargetGrid())) {
            System.out.println("Player 2 lost");
            System.out.println("Player 2 Ocean Grid");
            oceanGrid2.printGrid();
            System.out.println("\nPlayer 2 Target Grid");
            targetGrid2.printGrid();
            System.out.println("\nPlayer 1 Ship Grid");
            shipGrid1.printGrid();

        }
    }
 }