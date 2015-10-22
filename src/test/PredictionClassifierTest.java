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
			assertTrue(r >= -1 && r <= 1);
		}

	}

	// @Test
	// public void testRating() {
	// PredictionClassifier.setTrainingSet("test_data/labeled_test.arff");
	// PredictionClassifier.setUnlabeledPath("test_data/unlabeled_test.arff");
	// PredictionClassifier.ratePredictions();
	// }

}
