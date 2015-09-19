package test;

import model.ForecastResponse;
import model.Prediction;
import model.PredictionFactory;
import model.PredictionTime;
import model.Spot;


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
		Spot spot = new Spot("Manly", null, null, 526);
		ForecastResponse.List list = controller
				.getForecastResponseList(spot.id);
		Prediction p = new PredictionFactory(list, PredictionTime.MORNING, spot)
				.createPrediction();
		assertTrue(p != null);
	}

	@Test
	public void testGetTargetTimestamp() {
		PredictionFactory f = new PredictionFactory(null,
				PredictionTime.MORNING, null);
		System.out.println("timestamp:"
				+ f.getTargetTimestamp(PredictionTime.MORNING));
	}

}
