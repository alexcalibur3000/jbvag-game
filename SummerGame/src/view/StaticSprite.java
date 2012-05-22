package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class StaticSprite extends Sprite {

	private BufferedImage finalImage;
	
	public StaticSprite(String filename) {
		
		gifLength = 1;
		
		if(spriteMap.containsKey(filename))
			finalImage = spriteMap.get(filename).getCurrentImage();
		else {
			try {
				finalImage = ImageIO.read(new File(filename));
				spriteMap.put(filename, this);
			} catch (IOException e) {
				System.err.println("Error while reading a static image");
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public BufferedImage getCurrentImage() {
		return finalImage;
	}
	
	@Override
	public void advanceImage() {
		// does nothing in a static image
	}
	
	@Override
	public void setCurrentIndex(int newIndex) {
		// does nothing in a static image
	}
	
	@Override
	public int getCurrentIndex() {
		return 0; // always 0 in a static image
	}

}
