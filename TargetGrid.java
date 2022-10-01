/**
 * Spencer Lefever
 * COSC330 Project 1 Batleship
 * 
 * TargetGrid class for Battleship
 * This is the grid where the player
 * will fire shots at the opponent
 * 
 * Extends the grid class and add a shot object
 * for the user to fire shots
 */

 public class TargetGrid extends Grid {
    
    private Shot shot;

    public TargetGrid(int l, int w, boolean[][] g, Shot s) {
        super(l,w,g);

        shot = s;
    }

    //Shot getter and setter
    public Shot getShot() {
        return shot;
    }

    public void setShot(Shot s) {
        shot = s;
    }

    //Getter and setter for a single cell in the grid
    public void setCell(int x, int y, boolean isHit) {
        boolean[][] grid = this.getGrid();

        grid[x][y] = isHit;

        this.setGrid(grid);

    }
 }