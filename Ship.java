public class Ship {
	private int size;
	private String name;
	private boolean status;
	private int[][] location;
	private boolean orientation;
	
	public Ship(int s, String n, boolean st, int[][] l, boolean o) {
		size = s;
		name = n;
		status = st;
		location = l;
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
	
	public void setLocation(int[][] l){
		location = l;
	}
	
	public int[][] getLocation(){
		return location;
	}
	
	public void setOrientation(boolean o) {
		orientation = o;
	}
	
	public boolean getOrientation() {
		return orientation;
	}

}
