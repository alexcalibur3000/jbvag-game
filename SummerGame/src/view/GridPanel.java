package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import main.StartGame;
import model.Unit;

public class GridPanel extends JPanel {

	/**
	 * Useless
	 */
	private static final long serialVersionUID = 1L;

	public static final int GRID_SIZE = 50;
	private final JLayeredPane lPane;

	private final DrawingPanel upper;
	private final DrawingPanel lower;
	private final DrawingPanel top;

	private final Timer timer;
	
	private final Sprite cursor;

	public GridPanel() {
		this.setLayout(new BorderLayout());
		
		cursor = new Sprite("Resources" + StartGame.SEPARATOR + "cursor.gif");
		
		lPane = new JLayeredPane();

		Animator lowerDrawables = new Animator();
		Animator upperDrawables = new Animator();
		Animator topDrawables = new Animator();

		timer = new Timer(500, new AnimationTimer());
		upper = new DrawingPanel(upperDrawables);
		upper.setSize(new Dimension(900, 700));

		lower = new DrawingPanel(lowerDrawables);
		lower.setSize(new Dimension(900, 700));
		
		top = new DrawingPanel(topDrawables);
		top.setSize(new Dimension(900, 700));
		top.addDrawable(cursor);
		
		lPane.add(lower, new Integer(0));
		lPane.add(upper, new Integer(1));
		lPane.add(top, new Integer(2));

		this.add(lPane, BorderLayout.CENTER);
		this.addKeyListener(new CursorMover());
		
	}

	public void startAnimation() {
		timer.start();
	}

	public void addBackgroundDrawable(Drawable d) {
		lower.addDrawable(d);
	}
	
	public void addBackgroundDrawable(Unit d) {
		lower.addDrawable(d.getSprite());
	}

	public void removeBackGroundDrawable(Drawable d) {
		lower.removeDrawable(d);
	}
	
	public void removeBackGroundDrawable(Unit d) {
		lower.removeDrawable(d.getSprite());
	}

	public void addForegroundDrawable(Drawable d) {
		upper.addDrawable(d);
	}
	
	public void addForegroundDrawable(Unit d) {
		upper.addDrawable(d.getSprite());
	}

	public void removeForegroundDrawable(Drawable d) {
		upper.removeDrawable(d);
	}
	
	public void removeForegroundDrawable(Unit d) {
		upper.removeDrawable(d.getSprite());
	}

	private class DrawingPanel extends JPanel {

		/**
		 * Useless
		 */
		private static final long serialVersionUID = 1L;

		private Animator drawables;

		public DrawingPanel(Animator drawables) {
			this.setOpaque(false);
			this.drawables = drawables;
		}

		public void addDrawable(Drawable d) {
			drawables.addDrawable(d);
		}

		public void removeDrawable(Drawable d) {
			drawables.removeDrawable(d.getUUID());
		}
		
		public void advanceAll() {
			drawables.advanceAll();
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
			lower.advanceAll();
			lower.repaint();
			upper.advanceAll();
			upper.repaint();
			top.advanceAll();
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
			if(e == KeyEvent.VK_UP) {
				System.out.println("UP");// handle up
				cursor.setRow(cursor.getRow() - 1);
				top.repaint();
			}
			else if(e == KeyEvent.VK_DOWN) {
				System.out.println("DOWN");// handle down
				cursor.setRow(cursor.getRow() + 1);
				top.repaint();
			}
			else if(e == KeyEvent.VK_RIGHT) {
				System.out.println("RIGHT"); //handle right
				cursor.setCol(cursor.getCol() + 1);
				top.repaint();
			}
			else if(e == KeyEvent.VK_LEFT) {
				System.out.println("LEFT"); // handle left
				cursor.setCol(cursor.getCol() - 1);
				top.repaint();
			}
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
