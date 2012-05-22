package view;

import java.awt.image.BufferedImage;
import java.util.UUID;

public abstract class Drawable {
	
	private final UUID uuid;
	
	public Drawable() {
		this.uuid = UUID.randomUUID();
	}
	
	public abstract BufferedImage getCurrentImage();
	
	public abstract int getRow();
	
	public abstract int getCol();
	
	public abstract void setRow(int row);
	
	public abstract void setCol(int col);
	
	public abstract void advanceImage();
	
	public UUID getUUID() {
		return uuid;
	}
	
}
