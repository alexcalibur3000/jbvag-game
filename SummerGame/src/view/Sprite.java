package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class Sprite extends Drawable {

	protected static Map<String, Sprite> spriteMap = new HashMap<String, Sprite>();
	private List<BufferedImage> theImages;
	protected int gifLength;
	
	protected int index;
	private int row;
	private int col;

	protected Sprite() {
		index = 0;
		row = 0;
		col = 0;
	}
	
	/**
	 * Argument should be a gif or you're gonna have a bad time
	 * 
	 * @param filename
	 *            The path to a .gif file
	 */
	public Sprite(String filename) {

		this(); // initialize things using the other constructor

		if (spriteMap.containsKey(filename)) {
			this.theImages = spriteMap.get(filename).theImages;
			this.gifLength = theImages.size();
		} else {
			ImageInputStream stream;
			try {
				stream = ImageIO.createImageInputStream(new File(filename));

				Iterator<ImageReader> readers = ImageIO.getImageReaders(stream);

				ImageReader reader = (ImageReader) readers.next();

				reader.setInput(stream);

				int n = reader.getNumImages(true);

				theImages = new ArrayList<BufferedImage>();

				// TODO: try removing index 0 to see if that fixes gifs
				for (int i = 0; i < n; i++) {
					BufferedImage image = reader.read(i);
					theImages.add(image);
				}
				stream.close();

				// if index 0 ends up breaking things again
				// theImages.remove(0);

				gifLength = theImages.size();
			} catch (Exception e) {
				System.err.println("Error loading .gif");
				e.printStackTrace();
			}
			spriteMap.put(filename, this);
		}
	}

	public int getNumFrames() {
		return gifLength;
	}

	public int getCurrentIndex() {
		return index;
	}

	public void setCurrentIndex(int newIndex) {
		index = newIndex;
	}

	public void advanceImage() {
		index = (index + 1) % gifLength;
	}

	public BufferedImage getCurrentImage() {
		return theImages.get(index);
	}

	@Override
	public int getRow() {
		return row;
	}

	@Override
	public int getCol() {
		return col;
	}

	@Override
	public void setRow(int row) {
		this.row = row;
	}

	@Override
	public void setCol(int col) {
		this.col = col;
	}

}
