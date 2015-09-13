package test;

import model.ForecastResponse;
import model.Prediction;
import model.PredictionFactory;
import model.PredictionTime;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import controller.ForecastController;

public class PredictionFactoryTest {
	PredictionFactory factory = null;
	ForecastController controller = null;

	@Before
	public void setUp() throws Exception {
		controller = new ForecastController();
	}

	@Test
	public void testFindMorningForecast() {
		int spotId = 526; // Manly
		ForecastResponse.List list = controller.getForecastResponseList(spotId);
		Prediction p = new PredictionFactory(list, PredictionTime.MORNING)
				.createPrediction();
		assertTrue(p != null);
	}

	@Test
	public void testGetTargetTimestamp() {
		PredictionFactory f = new PredictionFactory(null,
				PredictionTime.MORNING);
		System.out.println("timestamp:"
				+ f.getTargetTimestamp(PredictionTime.MORNING));
	}

}
