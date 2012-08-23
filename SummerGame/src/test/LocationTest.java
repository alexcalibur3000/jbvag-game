package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import model.Location;

import org.junit.Test;

public class LocationTest {

	@Test
	public void testEquality() {
		Location one = new Location(1, 1);
		Location two = new Location(1, 2);
		Location three = new Location(1, 1);
		
		assertEquals(one, three);
		assertFalse(one.equals(two));
		assertFalse(two.equals(three));
	}

}
