package view;

import java.util.ArrayList;
import java.util.List;

public class Animator {
	
	private final List<Sprite> sprites;
	
	public Animator() {
		sprites = new ArrayList<Sprite>();
	}
	
	public void addSprite(String fileName) {
		sprites.add(new Sprite(fileName));
	}
	
	
}
