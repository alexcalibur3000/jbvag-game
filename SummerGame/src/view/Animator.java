package view;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class Animator implements Iterable<Drawable> {
	
	// make a list of players instead I think
	private final Map<UUID, Drawable> sprites;
	
	public Animator() {
		sprites = new HashMap<UUID, Drawable>();
	}
	
	public void addSprite(Sprite sprite) {
		sprites.put(sprite.getUUID(), sprite);
	}

	@Override
	public Iterator<Drawable> iterator() {
		return sprites.values().iterator();
	}
	
	public void removeDrawable(UUID id) {
		sprites.remove(id);
	}
	
	public void advanceAll() {
		for(Drawable d : this) {
			d.advanceImage();
		}
	}
	
	public void drawAll(Graphics g) {
		for(Drawable d : this) {
			// insert drawing code here
		}
	}
}
