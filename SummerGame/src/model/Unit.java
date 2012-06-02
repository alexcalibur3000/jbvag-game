package model;
import main.StartGame;
import view.Sprite;
import view.StaticSprite;

public abstract class Unit {

	// Unit Stats
	String name;
	int Str;
	int Agi;
	int Int;
	int Lvl;
	int HPMax = Str * 2;
	int HP;
	int Move;

	/*------------------------------------------------------------------
	 * Every unit has a sprite, and I have added a constructor such that if you don't assign a sprite yourself, then it will be assigned the default sprite
	 */
	private Sprite sprite;
	private Sprite battleSprite;
	
	public Unit() {
		this(new Sprite("Resources" + StartGame.SEPARATOR + "defaultSprite.gif"), new StaticSprite("Resources" + StartGame.SEPARATOR + "defaultBattleSprite.jpg"));
	}

	public Unit(Sprite sprite, Sprite battleSprite) {
		this.sprite = sprite;
		this.battleSprite = battleSprite;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public Sprite getBattleSprite() {
		return battleSprite;
	}

	/*------------------------------------------------------------------
	 * End Jorge block
	 */

	public abstract int attack(); // All units should have attacks, based off of
									// different modifiers

	public abstract int numAttacks(); // All units will be able to attack a
										// certain amount in a given turn

	public void HPGainLoss(int Gain) {
		// If gain is negative, the unit will be hurt instead.
		this.HP += Gain;
	}
}
