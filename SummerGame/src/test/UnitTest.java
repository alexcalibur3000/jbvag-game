package test;

import static org.junit.Assert.assertEquals;
import model.Location;
import model.Team;
import model.Unit;

import org.junit.Before;
import org.junit.Test;

public class UnitTest {

	Unit theUnit;
	Unit enemyUnit;
	
	@Before
	public void setUp() {
		theUnit = new Unit("Jorge", 2, 1, 6, 10, new Location(3, 3), Team.BLUE, 5);
		enemyUnit = new Unit("Rohit", 2, 0, 3, 10, new Location(4, 4), Team.RED, 5);
	}
	
	@Test
	public void testAttack() {
		theUnit.attack(enemyUnit);
		assertEquals(8, enemyUnit.getHealth());
		assertEquals(10, theUnit.getHealth());
		
		theUnit.attack(enemyUnit);
		theUnit.attack(enemyUnit);
		theUnit.attack(enemyUnit);
		theUnit.attack(enemyUnit);
		assertEquals(0, enemyUnit.getHealth());
		
		
		theUnit.attack(enemyUnit);
		// does not go below 0
		assertEquals(0, enemyUnit.getHealth());
	}
	
	public void reverseTestAttack() {
		enemyUnit.attack(theUnit);
		assertEquals(9, theUnit.getHealth());
	}
	
	public void battleTest() {
		theUnit.battle(enemyUnit, true);
		assertEquals(6, enemyUnit.getHealth());
		assertEquals(9, theUnit.getHealth());
		theUnit.battle(enemyUnit, false);
		assertEquals(2, enemyUnit.getHealth());
		assertEquals(9, theUnit.getHealth());
	}
	
	public void battleTest2() {
		enemyUnit.battle(theUnit, true);
		assertEquals(6, enemyUnit.getHealth());
		assertEquals(9, theUnit.getHealth());
	}

}
