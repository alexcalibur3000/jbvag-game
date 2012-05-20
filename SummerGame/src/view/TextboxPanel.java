package view;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

import main.StartGame;

public class TextboxPanel extends JPanel {

	/**
	 * Useless
	 */
	private static final long serialVersionUID = 1L;

	public static final String FILENAME = "Resources" + StartGame.SEPARATOR
			+ "textpanel_background.png";

	private BufferedImage bgImage;
	private final TextContainer box;
	
	private final int WIDTH;
	private final int HEIGHT;

	public TextboxPanel(int width, int height) {
		this.HEIGHT = height;
		this.WIDTH = width;
		try {
			bgImage = ImageIO.read(new File(FILENAME));
		} catch (IOException e) {
			System.err
					.println("Error reading the background file for the textbox");
			e.printStackTrace();
		}
		this.setLayout(null);
		box = new TextContainer();
		box.setBounds(0, 0, WIDTH, HEIGHT);
		this.add(box);
	}

//	@Override
//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		Graphics2D g2 = (Graphics2D) g;
//		g2.drawImage(bgImage, 0, 0, null);
//	}

	/**
	 * Sets the text that the character is saying. Should, but does not yet, do
	 * word wrap and everything behind the scenes without you having to worry
	 * about it
	 * 
	 * @param text
	 *            The full text the character is going to say. It will be split
	 *            up automatically.
	 */
	public void setText(String text) {
		box.setText(text);
	}
	
	public boolean advanceText() {
		return box.advanceText();
	}
	
	private class TextContainer extends JTextArea {
		
		/**
		 * Useless
		 */
		private static final long serialVersionUID = 1L;
		
		private final JTextArea dummyArea;
		private final Queue<String> textQueue;
		
	    private final int fontHeight;
		
		public TextContainer() {
			
			textQueue = new LinkedList<String>();
			
			fontHeight = getRowHeight();
			
			this.setEditable(false);
			this.setLineWrap(true);
			this.setWrapStyleWord(true);
			this.setOpaque(false);
			
			dummyArea = new JTextArea();
			dummyArea.setLineWrap(true);
			dummyArea.setWrapStyleWord(true);
			dummyArea.setBounds(0, 0, TextboxPanel.this.WIDTH, TextboxPanel.this.HEIGHT);
		}
		
		@Override
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(bgImage, 0, 0, null);
			super.paintComponent(g);
		}
		
		private int getLineCountAsSeen(JTextComponent txtComp) {
		    int lineCount;
		    try {
		    	int height = txtComp.modelToView(txtComp.getDocument().getEndPosition().getOffset() - 1).y;
		    	lineCount = height / fontHeight + 1;
		    } catch (Exception e) { 
		    	lineCount = 0;
		    }      
		    return lineCount;
		}
		
		@Override
		public void setText(String s) {
			textQueue.add(s);
			breakAndSetText();
		}
		
		/**
		 * Make sure you actually set the text first.
		 */
		public boolean advanceText() {
			return breakAndSetText();
		}
		
		private boolean breakAndSetText() {
			if(textQueue.isEmpty())
				return false;
			else {
				String s = textQueue.poll().trim();
				dummyArea.setText("");
				int acceptableEndOffset = 0;
				int i;
				for(i = 0; i < s.length(); i++) {
					char c = s.charAt(i);
					if (c == ' ')
						acceptableEndOffset = i;
					dummyArea.append("" + c);
					if(getLineCountAsSeen(dummyArea) > 3)
						break;
				}
				if(i == s.length())
					acceptableEndOffset = i;
				System.out.println(s.substring(0, acceptableEndOffset));
				super.setText(s.substring(0, acceptableEndOffset));
				
				if(textQueue.isEmpty()) {
					if(acceptableEndOffset != s.length())
						textQueue.add(s.substring(acceptableEndOffset, s.length()));
				}
				else {
					if(acceptableEndOffset != s.length())
						textQueue.add(s.substring(acceptableEndOffset, s.length()) + textQueue.poll());
				}
				

				return true;
//				// TODO: REMOVE
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				breakAndSetText();
				
				
				
			}
		}
	}

}
