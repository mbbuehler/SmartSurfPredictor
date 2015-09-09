package test;
import model.PredictionFactory;
import model.PredictionTime;

import org.junit.Before;
import org.junit.Test;


public class PredictionFactoryTest {
	PredictionFactory factory = null;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetTargetTimestamp() {
		PredictionFactory f = new PredictionFactory(null,
				PredictionTime.MORNING);
		System.out.println("timestamp:"
				+ f.getTargetTimestamp(PredictionTime.MORNING));
	}

}
