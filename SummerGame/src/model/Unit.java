package model;
import main.StartGame;
import view.Sprite;

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
	
	public Unit() {
		this(new Sprite("Resources" + StartGame.SEPARATOR + "defaultSprite.gif"));
	}

	public Unit(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public Sprite getSprite() {
		return sprite;
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
