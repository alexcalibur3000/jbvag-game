package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.Queue;

import javax.swing.JPanel;
import javax.swing.Timer;

import model.BattleDescriptor;
import model.BattleDescriptor.Action;
import model.BattleDescriptor.BattleEvent;

public class BattlePanel extends JPanel {

	/**
	 * Useless.
	 */
	private static final long serialVersionUID = 1L;

	private BattleDescriptor descriptor;
	private Queue<Action> actionQueue;
	private int leftHealth;
	private int rightHealth;

	private Timer timer;

	public void setBattleDescriptor(BattleDescriptor descriptor) {
		this.descriptor = descriptor;
		actionQueue = descriptor.getActionStack();
		leftHealth = descriptor.getUnitOneStartHealth();
		rightHealth = descriptor.getUnitTwoStartHealth();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		if (leftHealth > 0) {
			g2.drawImage(descriptor.getUnitOne().getBattleSprite()
					.getCurrentImage(), 20, 20, null);
		}
		g2.drawString("" + leftHealth, 50, 50);

		if (rightHealth > 0) {
			AffineTransform orig = g2.getTransform();
			g2.translate(350, 200);
			g2.scale(-1.0, 1.0);
			g2.translate(-350, -200);
			g2.drawImage(descriptor.getUnitTwo().getBattleSprite()
					.getCurrentImage(), 20, 20, null);
			g2.setTransform(orig);
		}
		g2.drawString("" + rightHealth, 620, 50);
	}

	public void startBattle() {
		timer = new Timer(1000, new AnimationTimer());
		timer.start();
	}

	private class AnimationTimer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Action action = actionQueue.poll();
			System.out.println("DEBUG: " + action.event + " " + action.damage);
			if (action.event == BattleEvent.LEFTATTACK) {
				rightHealth -= action.damage;
				repaint();
			}
			else if (action.event == BattleEvent.RIGHTATTACK) {
				leftHealth -= action.damage;
				repaint();
			}
			else if (action.event == BattleEvent.END) {
				timer.stop();
				System.out.println("here");
				FEFrame.hideBattle();
				FEFrame.focusGrid();
				repaint();
			}
		}
	}
}
