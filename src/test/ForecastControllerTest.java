package test;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import model.ForecastResponse;

import org.junit.Before;
import org.junit.Test;

import controller.ForecastController;

public class ForecastControllerTest {
	ForecastController forecastController = null;

	@Before
	public void setup() {
		this.forecastController = new ForecastController();
	}

	@Test
	public void testRequestForecastData() {
		// Make Request for Manly (spotId 526)
		ForecastResponse.List list = forecastController
				.getForecastResponseList(526);
		assertTrue("no List returned", list != null);
		assertTrue(list.size() > 0);
	}

	@Test
	public void testRequestInvalidSpot() {
		ForecastResponse.List list = forecastController
				.getForecastResponseList(-22);
		assertTrue("List is not empty " + list, list.size() == 0);

	}
	private int getRandromInt() {
		Random r = new Random(System.nanoTime());
		return r.nextInt();
	}
}
