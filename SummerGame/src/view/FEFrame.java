package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class FEFrame extends JFrame {

	/**
	 * Useless
	 */
	private static final long serialVersionUID = 1L;

	// instance variables
	private static final JLayeredPane lPane = new JLayeredPane();
	private static final JPanel mapPanel = new JPanel(); // TODO: This should be
															// a custom JPanel
															// later
	private static final JPanel battlePanel = new JPanel(); // TODO: This should
															// be a custom
															// JPanel later
	private static final TextboxPanel textPanel = new TextboxPanel(894, 122);

	/**
	 * Constructs a new Fire Emblem Frame.
	 * 
	 * A fire emblem frame is a jframe with support for things such as message
	 * dialogs, battle, and etc
	 */
	public FEFrame() {
		this.setPreferredSize(new Dimension(900, 700)); // TODO: Decide on a
														// size
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// The dimensions of the content pane on windows is 894x672 with the
		// 900x700 window size
		mapPanel.setBounds(0, 0, 894, 672);
		battlePanel.setBounds(97, 136, 700, 400);
		textPanel.setBounds(0, 550, 900, 150);

		mapPanel.setBackground(Color.BLUE);
		battlePanel.setBackground(Color.RED);
		textPanel.setVisible(false);
		
		// this code make the textbox advance on any keypress
		textPanel.addKeyListener(new TextboxListener());

		lPane.add(mapPanel, 0, new Integer(0));
		lPane.add(battlePanel, new Integer(1));
		lPane.add(textPanel, new Integer(2));

		this.add(lPane);
		this.setLayeredPane(lPane);
		this.pack();
		this.setVisible(true);
	}

	/**
	 * This method is not thread safe and should be wrapped inside an invoke
	 * later thingy if called from another thread
	 */
	public static void showTextbox(String message) {
		textPanel
				.setText(message);
		textPanel.setVisible(true);
		textPanel.requestFocusInWindow();
	}

	private static void hideTextbox() {
		textPanel.setVisible(false);
	}

	private static class TextboxListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub

	//		System.out.println("HELLO");
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			if (!textPanel.advanceText())
				hideTextbox();
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

	//		System.out.println("HELLO");
		}
	}
}
