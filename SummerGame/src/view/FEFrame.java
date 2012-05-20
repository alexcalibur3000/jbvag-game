package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

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
	private static final JPanel mapPanel = new JPanel(); // TODO: This should be a custom JPanel later
	private static final JPanel battlePanel = new JPanel(); // TODO: This should be a custom JPanel later
	private static final JPanel textPanel = new TextboxPanel(); // TODO: This should be a custom JPanel later
	
	/**
	 * Constructs a new Fire Emblem Frame.
	 * 
	 * A fire emblem frame is a jframe with support for things such as message dialogs, battle, and etc
	 */
	public FEFrame() {
		this.setPreferredSize(new Dimension(900, 700)); // TODO: Decide on a size
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		mapPanel.setBounds(0, 0, 900, 700);
		battlePanel.setBounds(100, 150, 700, 400);
		textPanel.setBounds(0, 550, 900, 150);
		
		mapPanel.setBackground(Color.BLUE);
		battlePanel.setBackground(Color.RED);
		textPanel.setBackground(Color.GREEN);
		textPanel.setVisible(false);
		
		lPane.add(mapPanel, 0, 0);
		lPane.add(battlePanel, 1, 0);
		lPane.add(textPanel, 2, 2);
		
		this.add(lPane, BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * This method is not thread safe and should be wrapped inside an invoke later thingy if called from another thread
	 */
	public static void showTextbox() {
		textPanel.setVisible(true);
		
//		try {
//			Thread.sleep(1500);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
//		hideTextbox();
	}
	
	private static void hideTextbox() {
		textPanel.setVisible(false);
	}
}
