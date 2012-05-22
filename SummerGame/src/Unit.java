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

	public abstract int attack(); // All units should have attacks, based off of
									// different modifiers

	public abstract int numAttacks(); // All units will be able to attack a
										// certain amount in a given turn

	public void HPGainLoss(int Gain) {
		// If gain is negative, the unit will be hurt instead.
		this.HP += Gain;
	}
}
