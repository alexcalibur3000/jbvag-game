package model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BattleDescriptor {

	private final Unit unitOne;
	private final Unit unitTwo;
	private final Queue<Action> actionQueue;
	private final int unitOneStartHealth;
	private final int unitTwoStartHealth;

	/**
	 * Remember the order you pass these in, it is important for the rest of the
	 * methods. unitOne will be on the left, unitTwo will be on the right.
	 * 
	 * @param unitOne
	 *            The unit on the left
	 * @param unitTwo
	 *            The unit on the right
	 */
	public BattleDescriptor(Unit unitOne, Unit unitTwo, int unitOneStartHealth,
			int unitTwoStartHealth) {

		this.unitOne = unitOne;
		this.unitTwo = unitTwo;
		actionQueue = new LinkedList<Action>();
		this.unitOneStartHealth = unitOneStartHealth;
		this.unitTwoStartHealth = unitTwoStartHealth;
		
		actionQueue.add(new Action(BattleEvent.WAIT, -1));
	}

	/**
	 * This method means that unit one attacks unit two
	 * 
	 * @param damage
	 *            The amount of damage done to unit two
	 */
	public void registerAttackUnitOne(int damage) {
		actionQueue.add(new Action(BattleEvent.LEFTATTACK, damage));
	}

	/**
	 * This method means that unit two attacks unit one
	 * 
	 * @param damage
	 *            The amount of damage done to unit one
	 */
	public void registerAttackUnitTwo(int damage) {
		actionQueue.add(new Action(BattleEvent.RIGHTATTACK, damage));
	}

	public void registerEndOfBattle() {
		actionQueue.add(new Action(BattleEvent.END, -1));
	}

	public Unit getUnitOne() {
		return unitOne;
	}

	public Unit getUnitTwo() {
		return unitTwo;
	}

	public int getUnitOneStartHealth() {
		return unitOneStartHealth;
	}

	public int getUnitTwoStartHealth() {
		return unitTwoStartHealth;
	}

	public Queue<Action> getActionQueue() {
		return actionQueue;
	}

	public class Action {

		public BattleEvent event;
		public int damage;

		public Action(BattleEvent event, int damage) {
			this.event = event;
			this.damage = damage;
		}

	}

	public enum BattleEvent {
		WAIT, LEFTATTACK, RIGHTATTACK, END;
	}

}
