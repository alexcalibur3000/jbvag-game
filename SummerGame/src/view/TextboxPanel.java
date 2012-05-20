package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import javax.imageio.ImageIO;
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

	/**
	 * Sets the text that the character is saying. Does word wrap and everything
	 * behind the scenes without you having to worry about it
	 * 
	 * @param text
	 *            The full text the character is going to say. It will be split
	 *            up automatically.
	 */
	public void setText(String text) {
		box.setText(text);
	}

	/**
	 * Call this after setting the text in order to advance it.
	 * 
	 * @return true if there is more text, false if there is not
	 */
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
			dummyArea.setBounds(0, 0, TextboxPanel.this.WIDTH,
					TextboxPanel.this.HEIGHT);
		}

		@Override
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(bgImage, 0, 0, null);
			super.paintComponent(g);
		}

		/**
		 * Returns the number of lines in the passed in area (always dummy area
		 * at the moment). It uses the font size of THIS area though, so if the
		 * font size changes, make sure to change the font of the dummy area as
		 * well.
		 * 
		 * @param txtComp
		 *            The JTextArea you are trying to count the lines in
		 * @return How many lines there are
		 */
		private int getLineCountAsSeen(JTextComponent txtComp) {
			int lineCount;
			try {
				int height = txtComp.modelToView(txtComp.getDocument()
						.getEndPosition().getOffset() - 1).y;
				lineCount = height / fontHeight + 1;
			} catch (Exception e) {
				lineCount = 0;
			}
			return lineCount;
		}

		/**
		 * Call initially when you are trying to display a message. Sets the
		 * text, breaks it up, etc. Call once, from there, the actionlistener in
		 * the FEFrame should take care of it by calling advanceText
		 */
		@Override
		public void setText(String s) {
			textQueue.add(s);
			breakAndSetText();
		}

		/**
		 * Will advance the text. Make sure you set the text first.
		 * 
		 * @return true if there is more text, false if there is not
		 */
		public boolean advanceText() {
			return breakAndSetText();
		}

		/**
		 * Will break up the text in the queue to be the appropriate length and
		 * fix the queue up so that subsequent calls on this will work
		 * correctly. This method also sets the text, so calling this will
		 * visibly break the text into pieces and display it
		 * 
		 * @return true if there is more text, false if there is not
		 */
		private boolean breakAndSetText() {
			if (textQueue.isEmpty())
				return false;
			else {
				String s = textQueue.poll().trim();
				dummyArea.setText("");
				int acceptableEndOffset = 0;
				int i;
				for (i = 0; i < s.length(); i++) {
					char c = s.charAt(i);
					if (c == ' ')
						acceptableEndOffset = i;
					dummyArea.append("" + c);
					if (getLineCountAsSeen(dummyArea) > 3)
						break;
				}
				if (i == s.length())
					acceptableEndOffset = i;
				// debug println
				// System.out.println(s.substring(0, acceptableEndOffset));
				super.setText(s.substring(0, acceptableEndOffset));

				if (textQueue.isEmpty()) {
					if (acceptableEndOffset != s.length())
						textQueue.add(s.substring(acceptableEndOffset,
								s.length()));
				} else {
					if (acceptableEndOffset != s.length())
						textQueue.add(s.substring(acceptableEndOffset,
								s.length())
								+ textQueue.poll());
				}
				return true;
			}
		}
	}

}
