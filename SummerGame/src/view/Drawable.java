package view;

import java.awt.image.BufferedImage;
import java.util.UUID;

public abstract class Drawable {
	
	private final UUID uuid;
	protected int row;
	protected int col;
	
	public Drawable() {
		this.uuid = UUID.randomUUID();
	}
	
	public abstract BufferedImage getCurrentImage();
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	
	public abstract void advanceImage();
	
	public UUID getUUID() {
		return uuid;
	}
	
}
