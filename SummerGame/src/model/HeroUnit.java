package model;

public abstract class HeroUnit extends Unit {

	//Leveling Statistics
	int xpForlevel;
	int xpToNext;
	int StrGain;
	int AgiGain;
	int IntGain;
	
	public void levelUp(){
		this.xpForlevel = this.xpForlevel*2;
		this.xpToNext = this.xpForlevel;
		this.Str += this.StrGain;
		this.Agi += this.AgiGain;
		this.Int += this.IntGain;
		this.HPMax = this.Str*2;
		this.HP += this.StrGain*2;
	}
	
	public void gainXP(int xp){
		int tempXPvalue = this.xpToNext;
		this.xpToNext -= xp;
		if(this.xpToNext <= 0){
			this.levelUp();
			gainXP(xp-tempXPvalue);
		}
	}
}
