package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import main.StartGame;
import view.Sprite;
import view.StaticSprite;

public class Unit {

	@XmlAttribute
	protected String name;
	@XmlAttribute
	protected int str;
	@XmlAttribute
	protected int def;
	@XmlAttribute
	protected int spd;
	@XmlAttribute
	protected int health;
	@XmlAttribute
	protected int range;
	@XmlElement
	protected Location loc;
	@XmlAttribute
	protected Team team;
	@XmlAttribute
	protected int moveDist;

	private Sprite sprite;
	private Sprite battleSprite;

	public Unit() {
		this(
				new Sprite("Resources" + StartGame.SEPARATOR
						+ "defaultSprite.gif"), new StaticSprite("Resources"
						+ StartGame.SEPARATOR + "defaultBattleSprite.jpg"));
	}

	public Unit(Sprite sprite, Sprite battleSprite) {
		this.sprite = sprite;
		this.battleSprite = battleSprite;
	}
	
	public Unit(String name, int str, int def, int spd, int health, Location loc, Team team, int moveDist) {
		this();
		this.name = name;
		this.str = str;
		this.def = def;
		this.spd = spd;
		this.health = health;
		this.loc = loc;
		this.team = team;
		this.moveDist = moveDist;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public Sprite getBattleSprite() {
		return battleSprite;
	}
	
	public int getHealth() {
		return health;
	}
	
	public String getName() {
		return name;
	}
	
	public int getStrength() {
		return str;
	}
	
	public int getSpeed() {
		return spd;
	}
	
	public Location getLocation() {
		return loc;
	}

	public void setLocation(Location loc) {
		this.loc = loc;
	}

	/**
	 * Deal damage to the other unit. Health cannot go below 0. Damage dealt is
	 * strength - otherUnit.defense
	 * 
	 * @param toAttack
	 *            The unit you want to do damage to
	 */
	public int attack(Unit toAttack) {
		int startHealth = toAttack.health;
		toAttack.health -= str - toAttack.def;
		if (toAttack.health < 0)
			toAttack.health = 0;
		return startHealth - toAttack.health;
	}
	
	public BattleDescriptor battle(Unit toAttack, boolean canRetaliate) {
		BattleDescriptor bd = new BattleDescriptor(this, toAttack, this.health, toAttack.health);
		bd.registerAttackUnitOne(this.attack(toAttack));
		if(canRetaliate)
			bd.registerAttackUnitTwo(toAttack.attack(this));
		if(this.spd > 1.5 * toAttack.spd)
			bd.registerAttackUnitOne(this.attack(toAttack));
		else if(canRetaliate && toAttack.spd > 1.5 * this.spd)
			bd.registerAttackUnitTwo(toAttack.attack(this));
		return bd;
	}

}
