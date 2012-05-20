package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import main.StartGame;

public class TextboxPanel extends JPanel {

	public static final String FILENAME = "Resources" + StartGame.SEPARATOR + "textpanel_background.png";
	
	private BufferedImage bgImage;
	
	public TextboxPanel() {
		try {
			bgImage = ImageIO.read(new File(FILENAME));
		} catch (IOException e) {
			System.err.println("Error reading the background file for the textbox");
			e.printStackTrace();
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(bgImage, 0, 0, null);
	}
	
}
