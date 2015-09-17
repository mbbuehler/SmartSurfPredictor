package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import model.CompassDirection;
import model.Prediction;
import model.PredictionStatus;
import model.PredictionWriter;
import model.Surf;
import model.Swell;
import model.SwellForecast;
import model.Unit;
import model.Weather;
import model.WeatherForecast;
import model.Wind;

import org.junit.Before;
import org.junit.Test;

public class PredictionWriterTest {
	ArrayList<Prediction> predictions = new ArrayList<Prediction>();

	@Before
	public void setUp() throws Exception {

		Swell primarySwell1 = new Swell(8, 9, 0.0, "N", "FT");
		Surf surf1 = new Surf(2, 2, 3, 3, "FT");
		SwellForecast swellForecast1 = new SwellForecast(0, 0, 2, 1, surf1,
				primarySwell1, null, null, null, null, null);
		
		Wind wind1 = new Wind(4, 0, "N", 0, 0, "C");
		Weather weather1 = new Weather(1000, 24, 10, "MB", "C");
		WeatherForecast weatherForecast1 = new WeatherForecast(0, 0, wind1,
				weather1, null, null);
		
		Prediction p1 = new Prediction(swellForecast1, weatherForecast1);
		
		Swell primarySwell2 = new Swell(14, 11, 0.0, "N", "FT");
		Surf surf2 = new Surf(4, 4, 6, 6, "FT");
		SwellForecast swellForecast2 = new SwellForecast(0, 0, 5, 4, surf2,
				primarySwell2, null, null, null, null, null);
		
		Wind wind2 = new Wind(4, 0, "N", 0, 0, "C");
		Weather weather2 = new Weather(1000, 24, 10, "MB", "C");
		WeatherForecast weatherForecast2 = new WeatherForecast(0, 0, wind2,
				weather2, null, null);
		
		Prediction p2 = new Prediction(swellForecast2, weatherForecast2);

		this.predictions.add(p1);
		this.predictions.add(p2);
	}

	@Test
	public void testWriteUnlabeledPredictions() {
		try {
			PredictionWriter writer = new PredictionWriter(
					"tmp/unlabeled_test.arff", false);
			writer.writePredictions(predictions);
			writer.close();
			assert (true);
		} catch (Exception e) {
			fail();
		}

	}

	@Test
	public void testWriteLabeledPredictions() {
		predictions.get(0).status = PredictionStatus.ACCEPTED;
		predictions.get(1).status = PredictionStatus.REJECTED;
		try {
			PredictionWriter writer = new PredictionWriter(
					"tmp/labeled_test.arff", true);
			writer.writePredictions(predictions);
			writer.addLabeledPrediction(predictions.get(0));
			writer.addLabeledPrediction(predictions.get(1));

			writer.close();
			assert (true);
		} catch (Exception e) {
			fail();
		}

	}

}
