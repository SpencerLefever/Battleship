/**
 * Spencer Lefever
 * COSC330 Project 1 Battleship
 * 
 * Battleship Game Grid class
 * for the player grids in game
 * 
 * A 2D boolean array of falses are initalized
 * 
 * In the future the JButtons associated with 
 * the grid will disable after a shot if fired
 * 
 */

 public class Grid {

    //Length and width variables
    private int length;
    private int width;

    private boolean[][] grid;
    
    public Grid(int l, int w, boolean[][] g) {
        length = l;
        width = w;
        boolean[][] grid = new boolean[l][w];
        //Set all grid values to false
        for(int i=0; i<l; i++) {
            for(int j=0; j<w; j++) {
                grid[l][w] = false;
            }
        }
    }

    //Length getter and setter
    public int getLength() {
        return length;
    }

    public void setLength(int l) {
        length = l;
    }

    //Width getter and setter
    public int getWidth() {
        return width;
    }

    public void setWidth(int w) {
        width = w;
    }

    //Grid arr getter and setter
    public boolean[][] getGrid() {
        return grid;
    }

    //This is used to set changes in the grid after a shot
    public void setGrid(boolean[][] g) {
        for(int i=0; i<length; i++) {
            for(int j=0; j<width; j++) {
                this.grid[i][j] = g[i][j];
            }
        }
    }


 }