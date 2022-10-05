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
    
    private Shot shot = new Shot(0, 0);

    public TargetGrid(int l, int w) {
        super(l,w);
    }

    TargetGrid(TargetGrid t) {
        super(t);
    }

    //Shot getter and setter
    public Shot getShot() {
        return shot;
    }

    public void setShot(Shot s) {
        shot = s;
    }

    //Set a single shot on the grid overriding the standard setter needed
    public void setShot(int x, int y, int isHit) {
        int [][] grid = this.getGrid();

        grid[x][y] = isHit;

        this.setGrid(grid);

    }
 }