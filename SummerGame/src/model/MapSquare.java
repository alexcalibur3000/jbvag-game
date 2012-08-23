package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class MapSquare {

	@XmlAttribute
	private int strBonus;

	@XmlAttribute
	private int speedBonus;

	@XmlAttribute
	private int defBonus;

	@XmlElement
	private Unit currUnit;
	
	@XmlAttribute
	private boolean traversable;

	public MapSquare(int strBonus, int speedBonus, int defBonus, boolean traversable) {
		this.strBonus = strBonus;
		this.speedBonus = speedBonus;
		this.defBonus = defBonus;
		currUnit = new Unit("Doesn't matter", 1, 2, 3, 4, new Location(1, 1), Team.BLUE, 5);
		this.traversable = traversable;
	}

	/**
	 * This constructor exists to satisfy jaxb
	 */
	public MapSquare() {

	}

	public boolean setUnit(Unit unit) {
		if (currUnit != null)
			return false;
		currUnit = unit;
		return true;
	}
	
	public Unit getUnit() {
		return currUnit;
	}

	/**
	 * @return If the square is traversable
	 */
	public boolean isTraversable() {
		return traversable;
	}

}
