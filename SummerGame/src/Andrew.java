public class Andrew extends HeroUnit {

	public Andrew() {
		// Stats
		this.name = "Andrew";
		this.Str = 10;
		this.Agi = 5;
		this.Int = 5;
		this.Lvl = 1;
		this.HP = this.HPMax;
		this.Move = 3;

		// Leveling stats
		this.xpForlevel = 99;
		this.xpToNext = 99;
		this.StrGain = 3;
		this.AgiGain = 2;
		this.IntGain = 1;

	}

	@Override
	public int attack() {
		// Andrew's attack is based off of his strength
		return Str / 2;
	}

	@Override
	public int numAttacks() {
		// Andrew will attack 2 times beginning at 10 Agility, and can attack
		// for a maximum of 3 times starting at 20 Agility
		if (this.Agi >= 20) {
			return 3;
		} else if (this.Agi >= 10) {
			return 2;
		} else {
			return 1;
		}
	}
}
