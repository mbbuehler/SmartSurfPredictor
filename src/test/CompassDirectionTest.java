package test;
import static org.junit.Assert.*;

import model.CompassDirection;

import org.junit.Test;


public class CompassDirectionTest {

	@Test
	public void testCorrectMapping() {
		CompassDirection dir = CompassDirection.getCompassDirection("NW");
		assertTrue("wrong CompassDirection", dir.equals(CompassDirection.NW));
	}

	@Test
	public void testInvalidMapping() {
		CompassDirection dir = CompassDirection.getCompassDirection("NWE");
		assertTrue(dir == null);
	}

}
