package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

import model.Andrew;
import model.BattleDescriptor;

public class FEFrame extends JFrame {

	/**
	 * Useless
	 */
	private static final long serialVersionUID = 1L;

	// instance variables
	private static final JLayeredPane lPane = new JLayeredPane();
	private static final GridPanel mapPanel = new GridPanel(); // TODO: This
																// should be
																// a custom
																// JPanel
																// later
	private static final BattlePanel battlePanel = new BattlePanel(); // TODO:
																		// This
																		// should
	// be a custom
	// JPanel later
	private static final TextboxPanel textPanel = new TextboxPanel(900, 150);

	/**
	 * Constructs a new Fire Emblem Frame.
	 * 
	 * A fire emblem frame is a JFrame with support for things such as message
	 * dialogs, battle, and etc
	 */
	public FEFrame() {
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		lPane.setPreferredSize(new Dimension(900, 700));

		// The dimensions of the content pane on windows is 894x672 with the
		// 900x700 window size
		mapPanel.setBounds(0, 0, 900, 700);
		battlePanel.setBounds(100, 150, 700, 400);
		textPanel.setBounds(0, 550, 900, 150);

		// mapPanel.setBackground(Color.BLUE);
		mapPanel.addForegroundDrawable(new Andrew());
		battlePanel.setBackground(Color.RED);
		battlePanel.setVisible(false);
		textPanel.setVisible(false);

		lPane.add(mapPanel, new Integer(0));
		lPane.add(battlePanel, new Integer(1));
		lPane.add(textPanel, new Integer(2));

		this.setContentPane(lPane);
		this.pack();
		this.setVisible(true);

		mapPanel.startAnimation();
		focusGrid();
	}

	/**
	 * Shows the message, broken up and everything. Is thread safe because it
	 * checks to make sure you are on EDT. Efficient and doesn't make a thread
	 * if it doesn't have to
	 * 
	 * @param message
	 *            The message you want to display.
	 */
	public static void showTextbox(final String message) {
		if (!SwingUtilities.isEventDispatchThread()) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					textPanel.setText(message);
					textPanel.setVisible(true);
					textPanel.requestFocusInWindow();
				}
			});
		} else {
			textPanel.setText(message);
			textPanel.setVisible(true);
			textPanel.requestFocusInWindow();
		}
	}

	public static void hideTextbox() {
		if (!SwingUtilities.isEventDispatchThread()) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					textPanel.setVisible(false);
				}
			});
		} else {
			textPanel.setVisible(false);
		}
	}

	public static void focusGrid() {
		if (!SwingUtilities.isEventDispatchThread()) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					mapPanel.requestFocusInWindow();
				}
			});
		} else {
			mapPanel.requestFocusInWindow();
		}
	}

	public static void showBattle(BattleDescriptor descriptor) {
		battlePanel.setBattleDescriptor(descriptor);
		if (!SwingUtilities.isEventDispatchThread()) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					battlePanel.setVisible(true);
					battlePanel.requestFocusInWindow();
					battlePanel.startBattle();
				}
			});
		} else {
			battlePanel.setVisible(true);
			battlePanel.requestFocusInWindow();
			battlePanel.startBattle();
		}
	}

	public static void hideBattle() {
		if (!SwingUtilities.isEventDispatchThread()) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					battlePanel.setVisible(false);
				}
			});
		} else {
			battlePanel.setVisible(false);
		}
	}

}
