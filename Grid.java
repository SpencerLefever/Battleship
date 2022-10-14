/**
 * Spencer Lefever
 * COSC330 Project 1 Battleship
 * 
 * Battleship Game Grid class
 * for the player grids in game
 * 
 * A 2D int array of -1 is initalized
 * 
 * In the future the JButtons associated with 
 * the grid will disable after a shot if fired
 * 
 * Target Grid Key
 *  -1: Null/No Shot fired
 *   0: Miss/Shot fired
 *   1: Hit/Shot fired 
 * 
 * Ocean/Ship Grid Key
 *  -1: No ship
 *   0: Ship Good
 *   1: Ship Hit
 * 
 */

 public class Grid {

    //Length and width variables
    private int length;
    private int width;

    private int[][] grid;
    
    public Grid(int l, int w) {
        length = l;
        width = w;
        grid = new int[l][w];
        //Set all grid values to false
        for(int i=0; i<l; i++) {
            for(int j=0; j<w; j++) {
                grid[i][j] = -1;
            }
        }
    }
    
    Grid(Grid g) {
        this.length = g.length;
        this.width = g.width;
        for(int i=0; i<g.length; i++) {
            for(int j=0; j<g.width; j++) {
                this.grid[i][j] = g.grid[i][j];
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
    public int[][] getGrid() {
        return grid;
    }

    //This is used to set changes in the grid after a shot
    public void setGrid(int[][] g) {
        for(int i=0; i<length; i++) {
            for(int j=0; j<width; j++) {
                this.grid[i][j] = g[i][j];
            }
        }
    }

    //Getter and setter for a single cell in the grid
    public void setCell(int x, int y, int isHit) {
        grid[x][y] = isHit;
    }

    /**
     * Method to get a single cell from the grid
     */
    public int getCell(int x, int y) {
        return grid[x][y];
    }

    public void printGrid() {

        //Print grid header
        System.out.println(" 1 2 3 4 5 6 7 8 9 10");
        for(int i=0; i< length; i++) {
            System.out.print(i+1+" ");
            for(int j=0; j<width; j++) {

                //System.out.print(grid[i][j]);
                if(grid[i][j] == -1) {
                    System.out.print("- ");
                }
                else if (grid[i][j] == 0) {
                    System.out.print("o ");
                }
                else if (grid[i][j] == 1) {
                    System.out.print("x ");
                }

            }
            System.out.println();
        }
    }


 }