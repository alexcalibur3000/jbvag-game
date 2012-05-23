package view;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class Animator implements Iterable<Drawable> {
	
	private final Map<UUID, Drawable> sprites;
	
	public Animator() {
		sprites = new HashMap<UUID, Drawable>();
	}
	
	public void addDrawable(Drawable sprite) {
		sprites.put(sprite.getUUID(), sprite);
	}
	
	// TODO: add alternate constructors

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
			// TODO: insert drawing code here
		}
	}
}
