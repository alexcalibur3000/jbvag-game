package model;

/**
 * A path location is different from a normal location in that it stores the
 * predecessor node in a path traversal. Used in the move related methods of the
 * battlemap
 * 
 * @author Jorge
 * 
 */
public class PathLocation extends Location {

	public Location predecessor;
	
	public PathLocation(int row, int col, Location predecessor) {
		super(row, col);
		this.predecessor = predecessor;
	}
	
}
