package view;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GridPanel extends JPanel {

	/**
	 * Useless
	 */
	private static final long serialVersionUID = 1L;

	public static final int GRID_SIZE = 50;
	private final JLayeredPane lPane;

	private final Animator lowerDrawables;
	private final Animator upperDrawables;
	private final Animator topDrawables;

	private final JPanel upper;
	private final JPanel lower;
	private final JPanel top;

	private final Timer timer;

	public GridPanel() {
		lPane = new JLayeredPane();
		lPane.setBounds(0, 900, 0, 700);

		lowerDrawables = new Animator();
		upperDrawables = new Animator();
		topDrawables = new Animator();

		timer = new Timer(500, new AnimationTimer());

		upper = new DrawingPanel(upperDrawables);
		upper.setBounds(0, 900, 0, 700);

		lower = new DrawingPanel(lowerDrawables);
		lower.setBounds(0, 900, 0, 700);

		top = new DrawingPanel(topDrawables);
		top.setBounds(0, 900, 0, 700);

		lPane.add(lower, new Integer(0));
		lPane.add(upper, new Integer(1));
		lPane.add(top, new Integer(2));

		this.add(lPane);
		
		this.addKeyListener(new CursorMover());
	}

	public void startAnimation() {
		timer.start();
	}

	public void addBackgroundDrawable(Drawable d) {
		lowerDrawables.addDrawable(d);
	}

	public void removeBackGroundDrawable(Drawable d) {
		lowerDrawables.removeDrawable(d.getUUID());
	}

	public void addForegroundDrawable(Drawable d) {
		upperDrawables.addDrawable(d);
	}

	public void removeForegroundDrawable(Drawable d) {
		upperDrawables.removeDrawable(d.getUUID());
	}

	private class DrawingPanel extends JPanel {

		/**
		 * Useless
		 */
		private static final long serialVersionUID = 1L;

		private Animator drawables;

		public DrawingPanel(Animator drawables) {
			this.setBounds(0, 900, 0, 700);
			this.setOpaque(false);
			this.drawables = drawables;
		}

		public void addDrawable(Drawable d) {
			drawables.addDrawable(d);
		}

		public void removeDrawable(Drawable d) {
			drawables.removeDrawable(d.getUUID());
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			drawables.drawAll(g);
		}

	}

	private class AnimationTimer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			lowerDrawables.advanceAll();
			lower.repaint();
			upperDrawables.advanceAll();
			upper.repaint();
			topDrawables.advanceAll();
			top.repaint();
		}

	}
	
	private class CursorMover implements KeyListener {

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			int e = arg0.getKeyCode();
			if(e == KeyEvent.VK_UP)
				System.out.println("UP");// handle up
			else if(e == KeyEvent.VK_DOWN)
				System.out.println("DOWN");// handle down
			else if(e == KeyEvent.VK_RIGHT)
				System.out.println("RIGHT"); //handle right
			else if(e == KeyEvent.VK_LEFT)
				System.out.println("LEFT"); // handle left
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
