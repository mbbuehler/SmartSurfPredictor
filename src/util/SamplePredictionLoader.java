package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import model.CompassDirection;
import model.PlainPrediction;
import model.PredictionStatus;
import model.Spot;
import model.SpotReaderFile;

/**
 * Loads sample predictions needed for initial setup of application
 * 
 * @author marcello
 * 
 */
public class SamplePredictionLoader {

	private static ArrayList<Spot> spots = new SpotReaderFile().getSpotsList();

	public static ArrayList<PlainPrediction> getSamplePlainPredictions(int count) {
		ArrayList<PlainPrediction> allPlainPredictions = new ArrayList<PlainPrediction>();

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					SamplePredictionLoader.class.getResourceAsStream("/"
							+ SSPPaths.unlabeledSamplePredictionsFileName)));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] s = line.split(",");
				String spotName = findSpotName(Integer.parseInt(s[0]));
				PlainPrediction p = new PlainPrediction(spotName,
						Integer.parseInt(s[0]), Integer.parseInt(s[1]),
						Integer.parseInt(s[2]), Integer.parseInt(s[3]),
						Integer.parseInt(s[4]), Float.parseFloat(s[5]),
						Integer.parseInt(s[6]),
						CompassDirection.getCompassDirection(s[7]),
						Integer.parseInt(s[8]),
						CompassDirection.getCompassDirection(s[9]),
						Integer.parseInt(s[10]), Float.parseFloat(s[11]),
						(float) -1.0,
 PredictionStatus.UNLABELED,
						System.currentTimeMillis());
				allPlainPredictions.add(p);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ArrayList<PlainPrediction> result = new ArrayList<PlainPrediction>();
		int i = 0;
		while (i < count) {
			int r = Math.abs(new Random(System.nanoTime()).nextInt()
					% allPlainPredictions.size());
			if (!result.contains(allPlainPredictions.get(r))) {
				result.add(allPlainPredictions.get(r));
				++i;
			}

		}

		return result;
	}

	private static String findSpotName(int spotId) {
		for (Spot s : spots) {
			if (s.id == spotId) {
				return s.getName();
			}
		}
		return "unknown";
	}
}
