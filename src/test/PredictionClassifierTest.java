package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.PredictionClassifier;

import org.junit.Before;
import org.junit.Test;

public class PredictionClassifierTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRatePredictions() {
		ArrayList<Float> ratings = PredictionClassifier.ratePredictions();
		assertTrue(ratings != null);
		assertTrue(ratings.size() > 0);
		for (Float r : ratings) {
			assertTrue(r >= 0 && r <= 1);
		}

	}

}
