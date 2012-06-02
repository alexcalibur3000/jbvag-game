package model;

import java.util.Stack;

public class BattleDescriptor {

	private Unit unitOne;
	private Unit unitTwo;
	private Stack<Action> actionStack;

	/**
	 * Remember the order you pass these in, it is important for the rest of the
	 * methods. unitOne will be on the left, unitTwo will be on the right.
	 * 
	 * @param unitOne
	 *            The unit on the left
	 * @param unitTwo
	 *            The unit on the right
	 */
	public BattleDescriptor(Unit unitOne, Unit unitTwo) {
		this.unitOne = unitOne;
		this.unitTwo = unitTwo;
		actionStack = new Stack<Action>();
	}

	public void registerAttackUnitOne(int damage) {
		actionStack.add(new Action(BattleEvent.LEFTATTACK, damage));
	}

	public void registerAttackUnitTwo(int damage) {
		actionStack.add(new Action(BattleEvent.RIGHTATTACK, damage));
	}

	public void registerDeathUnitOne() {
		actionStack.add(new Action(BattleEvent.LEFTDEATH, -1));
	}

	public void registerDeathUnitTwo() {
		actionStack.add(new Action(BattleEvent.RIGHTDEATH, -1));
	}

	public void registerEndOfBattle() {
		actionStack.add(new Action(BattleEvent.END, -1));
	}

	public Unit getUnitOne() {
		return unitOne;
	}

	public Unit getUnitTwo() {
		return unitTwo;
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
		LEFTATTACK, RIGHTATTACK, LEFTDEATH, RIGHTDEATH, END;
	}

}
