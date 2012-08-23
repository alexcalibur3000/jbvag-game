package model;

import javax.xml.bind.annotation.XmlAttribute;

public class Location {

	@XmlAttribute
	public int row;
	@XmlAttribute
	public int col;

	public Location(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public Location() {

	}

	@Override
	public boolean equals(Object o) {
		Location other = (Location) o;
		return (other.row == row && other.col == col);
	}
}
