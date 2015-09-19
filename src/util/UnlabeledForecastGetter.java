package util;

import java.util.ArrayList;
import java.util.Random;

import controller.ForecastController;

import model.FavSpotReader;
import model.FavouriteSpotFile;
import model.PlainPrediction;
import model.Prediction;
import model.PredictionManager;
import model.PredictionTime;
import model.PredictionWriter;
import model.Spot;

public class UnlabeledForecastGetter {

	public UnlabeledForecastGetter() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		new UnlabeledForecastGetter().getRandomUnlabeledPlainPredictions(10);
	}

	public void getRandomUnlabeledPlainPredictions(int number) {
		PredictionWriter writer = new PredictionWriter(
				"prg_res/unlabeled_sample_predictions.arff");
		ArrayList<Spot> spots = FavouriteSpotFile.getFavouriteSpots();
		int r = Math
				.abs(new Random(System.nanoTime()).nextInt() % spots.size());
		System.out.println("size: " + spots.size());
		System.out.println("r: " + r);

		int i = 0;
		while (i < number) {
			Spot spot = spots.get(r);
			PredictionTime randomTime = PredictionTime.AFTERNOON;
			Prediction p = new PredictionManager(new ForecastController())
					.getPrediction(spot, randomTime);
			if (p != null) {
				// if spot actually exists and we received a prediction
				writer.writeToFile(new PlainPrediction(p));
			}
			++i;
		}
		}
	}
