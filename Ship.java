/**
 * 
 * Ship class conventions
 * 
 * status variable : 
 * 	false=ship is not sunk
 * 	true=ship has been sunk
 * 
 * orientation variable :
 *  true=ship is oriented vertically
 * 	false=ship is oriented horizontally;
 */

public class Ship {
	private int size;
	private String name;
	private boolean status;
	private int xLocation;
	private int yLocation;
	private boolean orientation;
	
	public Ship(int s, String n, boolean st, int xL, int yL, boolean o) {
		size = s;
		name = n;
		status = st;
		xLocation = xL;
		yLocation = yL;
		orientation = o;
	}
	
	public void setSize(int s) {
		size = s;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
	public void setStatus(boolean st) {
		status = st;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setxLocation(int xL){
		xLocation = xL;
	}
	
	public int getxLocation(){
		return xLocation;
	}

	public void setyLocation(int yL){
		yLocation = yL;
	}
	
	public int getyLocation(){
		return yLocation;
	}
	
	
	public void setOrientation(boolean o) {
		orientation = o;
	}
	
	public boolean getOrientation() {
		return orientation;
	}

}
