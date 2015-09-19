package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.PlainPrediction;

import org.junit.Before;
import org.junit.Test;

import util.SamplePredictionLoader;

public class SamplePredictionLoaderTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testLoader() {
		ArrayList<PlainPrediction> list = SamplePredictionLoader
				.getSamplePlainPredictions(10);
		for (PlainPrediction p : list) {
			System.out.println(p.spotName);
			assertTrue(p != null);
		}
		assertTrue("incorrect # of plainpredictions returned: " + list.size(),
				list.size() == 10);
	}

}
