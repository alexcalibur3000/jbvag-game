package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import main.StartGame;

@XmlRootElement
public class BattleMap {

	@XmlElement
	private MapSquare[][] theMap;

	public BattleMap(String fileName) {

		try {
			File file = new File("Resources" + StartGame.SEPARATOR + fileName);
			JAXBContext jaxbContext = JAXBContext.newInstance(BattleMap.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			BattleMap map = (BattleMap) jaxbUnmarshaller.unmarshal(file);
			theMap = map.theMap;
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	public BattleMap() {

	}

	public boolean addUnitToMap(int row, int col, Unit unit) {
		if (theMap[row][col].isTraversable() && theMap[row][col].setUnit(unit)) {
			unit.setLocation(new Location(row, col));
			return true;
		}
		return false;
	}

	public Unit getUnit(int row, int col) {
		return theMap[row][col].getUnit();
	}

	public Unit getUnit(Location loc) {
		return getUnit(loc.row, loc.col);
	}

	public boolean movePossible(int startRow, int startCol, int endRow,
			int endCol) {
		if (getUnit(startRow, startCol) == null)
			return false;
		else if (getUnit(endRow, endCol) != null)
			return false;
		else {
			if (Math.abs(startRow - endRow) + Math.abs(startCol - endCol) > getUnit(
					startRow, startCol).moveDist)
				return false;
			
			List<Location> shortestPath = shortestPath(startRow, startCol, endRow, endCol);
			if (shortestPath != null && shortestPath.size() <= getUnit(
					startRow, startCol).moveDist)
				return true;
			
			return false;
		}
	}

	public List<Location> shortestPath(int startRow, int startCol, int endRow,
			int endCol) {
		int counter = 1;
		int maxDist = getUnit(startRow, startCol).moveDist;
		Team unitTeam = getUnit(startRow, startCol).team;

		PathLocation start = new PathLocation(startRow, startCol, null);
		List<Location> visited = new ArrayList<Location>();

		Queue<PathLocation> needToVisit = new LinkedList<PathLocation>();

		int numLeftToTraverse = addNeighborLocations(start, visited, needToVisit, unitTeam);
		int nextNumToTraverse = 0;
		
		while (counter <= maxDist && !needToVisit.isEmpty()) {
			PathLocation curr = needToVisit.poll();
			numLeftToTraverse--;
			
			if(curr.row == endRow && curr.col == endCol)
				return buildPathList(curr);
			else {
				visited.add(curr);
				nextNumToTraverse += addNeighborLocations(curr, visited, needToVisit, unitTeam);
			}
			
			if(numLeftToTraverse == 0) {
				numLeftToTraverse = nextNumToTraverse;
				nextNumToTraverse = 0;
				counter++;
			}
		}
		return null;
	}
	
	private List<Location> buildPathList(PathLocation curr) {
		List<Location> li = new ArrayList<Location>();
		while(curr.predecessor != null) {
			li.add(curr);
			curr = (PathLocation) curr.predecessor;
		}
		Collections.reverse(li);
		return li;
	}

	/**
	 * Will skip neighbors that are of the opposite team. Empty neighbors are
	 * fine. Same team is also fine.
	 * 
	 * @param start
	 *            The starting location
	 * @param visited
	 *            List of locations that have already been visited
	 * @param needToVisit
	 *            Queue of locations that we will add to -- new locations that
	 *            need to be visited
	 * @return The number of neighbors that were actually added to the queue
	 */
	private int addNeighborLocations(Location start, List<Location> visited,
			Queue<PathLocation> needToVisit, Team unitTeam) {

		int added = 0;

		PathLocation one = new PathLocation(start.row - 1, start.col, start);
		PathLocation two = new PathLocation(start.row + 1, start.col, start);
		PathLocation three = new PathLocation(start.row, start.col - 1, start);
		PathLocation four = new PathLocation(start.row, start.col + 1, start);

		if (locationIsValid(one) && isTraversable(one)
				&& !visited.contains(one)) {
			if (getUnit(one) == null || getUnit(one).team == unitTeam) {
				needToVisit.add(one);
				added++;
			}
		}
		if (locationIsValid(two) && isTraversable(two)
				&& !visited.contains(two)) {
			if (getUnit(two) == null || getUnit(two).team == unitTeam) {
				needToVisit.add(two);
				added++;
			}
		}
		if (locationIsValid(three) && isTraversable(three)
				&& !visited.contains(three)) {
			if (getUnit(three) == null || getUnit(three).team == unitTeam) {
				needToVisit.add(three);
				added++;
			}
		}
		if (locationIsValid(four) && isTraversable(four)
				&& !visited.contains(four)) {
			if (getUnit(four) == null || getUnit(four).team == unitTeam) {
				needToVisit.add(four);
				added++;
			}
		}
		return added;
	}

	public boolean locationIsValid(Location loc) {
		return (loc.row < theMap[0].length && loc.col < theMap.length
				&& loc.row >= 0 && loc.col >= 0);
	}

	public boolean locationIsValid(int row, int col) {
		return locationIsValid(new Location(row, col));
	}

	public boolean isTraversable(int row, int col) {
		return theMap[row][col].isTraversable();
	}

	public boolean isTraversable(Location loc) {
		return isTraversable(loc.row, loc.col);
	}

	public void writeToXmlTest() {

		theMap[0][0] = new MapSquare(1, 4, 2, true);
		theMap[0][1] = new MapSquare(2, 4, 2, true);
		theMap[1][0] = new MapSquare(3, 4, 2, true);
		theMap[1][1] = new MapSquare(4, 4, 2, true);

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(BattleMap.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(this, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
}
