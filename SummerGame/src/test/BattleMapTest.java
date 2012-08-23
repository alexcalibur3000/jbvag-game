package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import model.BattleMap;
import model.Unit;

import org.junit.Before;
import org.junit.Test;

public class BattleMapTest {

	
	BattleMap theMap;
	
	@Before
	public void setUp() {
		theMap = new BattleMap("defaultworld.xml");
	}
	
	@Test
	public void testLoad() {
		assertEquals("Joe", theMap.getUnit(0, 0).getName());
		assertEquals(1, theMap.getUnit(0, 0).getStrength());
		assertEquals("Steve", theMap.getUnit(0, 1).getName());
		assertEquals("Robert", theMap.getUnit(1, 0).getName());
		assertNull(theMap.getUnit(1, 1));
		assertTrue(theMap.isTraversable(0, 0));
	}
	
	@Test
	public void testAddUnit() {
		assertTrue(theMap.addUnitToMap(1, 1, new Unit()));
		assertFalse(theMap.addUnitToMap(1, 1, new Unit()));
		assertFalse(theMap.addUnitToMap(0, 0, new Unit()));
	}
	
	// TODO: Test shortest path extensively
	
	@Test
	public void testMovePossible() {
		assertTrue(theMap.movePossible(1, 0, 1, 1));
		assertFalse(theMap.movePossible(1, 1, 0, 1));
		assertFalse(theMap.movePossible(0, 0, 0, 1));
		assertFalse(theMap.movePossible(0, 0, 1, 1));
	}

}
