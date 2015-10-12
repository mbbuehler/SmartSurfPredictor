package util;

import java.util.ArrayList;
import java.util.Random;

import controller.ForecastController;

import model.FavouriteSpotFile;
import model.PlainPrediction;
import model.Prediction;
import model.PredictionManager;
import model.PredictionTime;
import model.PredictionWriter;
import model.Spot;
import model.SpotReaderFile;

public class UnlabeledForecastSaver {

	public UnlabeledForecastSaver() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		new UnlabeledForecastSaver().getRandomUnlabeledPlainPredictions(20);
	}

	public void getRandomUnlabeledPlainPredictions(int number) {
		PredictionWriter writer = new PredictionWriter(
				"prg_res/unlabeled_sample_predictions.csv");
		ArrayList<Spot> spots = new SpotReaderFile().getSpotsList();

		int i = 0;
		while (i < number) {
			int r = Math.abs(new Random(System.nanoTime()).nextInt()
					% spots.size());
			Spot spot = spots.get(r);
			PredictionTime randomTime = PredictionTime.AFTERNOON;
			Prediction p = new PredictionManager(new ForecastController())
					.getPrediction(spot, randomTime);
			if (p != null) {
				// if spot actually exists and we received a prediction
				writer.writeToFile(new PlainPrediction(p));
				System.out.println("Spot: " + spot.getName());
			}
			++i;
		}
		}
	}
