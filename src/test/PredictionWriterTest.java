package test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;

import model.PlainPrediction;
import model.Prediction;
import model.PredictionStatus;
import model.PredictionWriter;
import model.Spot;
import model.Surf;
import model.Swell;
import model.SwellForecast;
import model.Weather;
import model.WeatherForecast;
import model.Wind;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

public class PredictionWriterTest {
	ArrayList<Prediction> predictions = new ArrayList<Prediction>();
	ArrayList<PlainPrediction> plainPredictionsUnlabeled = new ArrayList<PlainPrediction>();
	ArrayList<PlainPrediction> plainPredictionsLabeled = new ArrayList<PlainPrediction>();

	@Before
	public void setUp() throws Exception {
		Spot spot = new Spot("Manly", null, null, 529);

		Swell primarySwell1 = new Swell(8, 9, 0.0, "N", "FT");
		Surf surf1 = new Surf(2, 2, 3, 3, "FT");
		SwellForecast swellForecast1 = new SwellForecast(0, 0, 2, 1, surf1,
				primarySwell1, null, null, null, null, null);

		Wind wind1 = new Wind(4, 0, "N", 0, 0, "C");
		Weather weather1 = new Weather(1000, 24, 10, "MB", "C");
		WeatherForecast weatherForecast1 = new WeatherForecast(0, 0, wind1,
				weather1, null, null);

		Prediction p1 = new Prediction(swellForecast1, weatherForecast1, spot);
				
		Swell primarySwell2 = new Swell(14, 11, 0.0, "N", "FT");
		Surf surf2 = new Surf(4, 4, 6, 6, "FT");
		SwellForecast swellForecast2 = new SwellForecast(0, 0, 5, 4, surf2,
				primarySwell2, null, null, null, null, null);
				
		Wind wind2 = new Wind(4, 0, "N", 0, 0, "C");
		Weather weather2 = new Weather(1000, 24, 10, "MB", "C");
		WeatherForecast weatherForecast2 = new WeatherForecast(0, 0, wind2,
				weather2, null, null);
				
		Prediction p2 = new Prediction(swellForecast2, weatherForecast2, spot);

		this.predictions.add(p1);
		this.predictions.add(p2);

		this.plainPredictionsUnlabeled.add(new PlainPrediction(p1));
		this.plainPredictionsUnlabeled.add(new PlainPrediction(p2));
				
		PlainPrediction pLabeled1 = new PlainPrediction(p1);
		pLabeled1.setStatus(PredictionStatus.ACCEPTED);

		PlainPrediction pLabeled2 = new PlainPrediction(p2);
		pLabeled2.setStatus(PredictionStatus.REJECTED);

		this.plainPredictionsLabeled.add(pLabeled1);
		this.plainPredictionsLabeled.add(pLabeled2);
	
			}

	@Test
	public void testWriteToFileUnlabeled() {
		String path = "test_data/unlabeled_test.arff";
		PredictionWriter writer = new PredictionWriter(path);
		writer.writeToFile(this.plainPredictionsUnlabeled.get(0));
		writer.writeToFile(plainPredictionsUnlabeled);

		assertTrue(containsHeader(path));

	}

	@Test
	public void testWriteToFileLabeled() {
		String path = "test_data/labeled_test.arff";
		PredictionWriter writer = new PredictionWriter(path);

		writer.writeToFile(plainPredictionsUnlabeled);
		writer.writeToFile(this.plainPredictionsLabeled.get(0));

		assertTrue(containsHeader(path));

	}

	private boolean containsHeader(String path) {
		boolean containsRelationHeader = false, containsAttributeHeader = false, containsDataHeader = false;
		File file = new File(path);
		try {
			String fileString = FileUtils.readFileToString(file);
			containsRelationHeader = fileString.contains(
					"@relation");
			containsAttributeHeader = fileString.contains("@attribute");
			containsDataHeader = fileString.contains("@data");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return containsRelationHeader && containsAttributeHeader
				&& containsDataHeader;
	}

		}
