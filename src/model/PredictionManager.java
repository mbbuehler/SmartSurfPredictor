package model;

import java.util.ArrayList;
import java.util.HashMap;

import weka.classifiers.Classifier;

import controller.ForecastController;

public class PredictionManager {

	private ForecastController controller = null;

	public PredictionManager(ForecastController controller) {
		super();
		this.controller = controller;
	}

	private ArrayList<Prediction> getPredictions(ArrayList<Spot> spots,
			PredictionTime predictionTime) {
		ArrayList<Prediction> predictions = new ArrayList<Prediction>();
		for(Spot spot:spots){
			Prediction p = getPrediction(spot.getId(), predictionTime);
			predictions.add(p);
		}
		return predictions;
	}

	private boolean prepareUnlabeledFile(ArrayList<Prediction> predictions) {
		try{
			// Write unlabeled predictions. They will be classified by the weka
			// classifier
			PredictionWriter writer = new PredictionWriter(
					"classifier_data/unlabeled_predictions.arff", false);
			writer.writePredictions(predictions);
			writer.close();
			return true;
		} catch (Exception e) {
			System.err
					.println("Could not prepare File with unlabeled Predictions:");
			e.printStackTrace();
			return false;
		}
	}

	public HashMap<Spot, PlainPrediction> getFavouritePredictions(
			PredictionTime predictionTime) {
		// Fetch Spots the user is interested in
		ArrayList<Spot> spots = FavSpotReader.getFavouriteSpots();
		// Fetch the predictions for the spots at the right time of the day
		ArrayList<Prediction> predictions = getPredictions(spots,
				predictionTime);
		// Write the unlabeled predictions to arff file
		boolean succeeded = prepareUnlabeledFile(predictions);
		if (!succeeded) {
			System.err
					.println("Classifier Data was not properly prepared. Cannot classify");
			return null;
		}
		// classify predictions and receive arrayList with scores
		ArrayList<Float> scores = PredictionClassifier.getRatedPredictions();

		HashMap<Spot, PlainPrediction> map = new HashMap<Spot, PlainPrediction>();
		// add scores to accepted predictions and fill HashMap
		for (int i = 0; i < predictions.size(); ++i) {
			// declare new variable names for all needed elements -> better
			// readability
			Spot currentSpot = spots.get(i);
			Prediction currentPrediction = predictions.get(i);
			Float currentScore = scores.get(i);

			if (isAccepted(currentScore)) {
				currentPrediction.setScore(currentScore);
				// Extract only the needed data and pack into a PlainPrediction
				PlainPrediction p = new PlainPrediction(currentPrediction);
				map.put(currentSpot, p);
			}
		}
		return map;
	}

	/**
	 * Makes Call to MSW API and returns Prediction
	 * 
	 * @param spotId
	 * @return Prediction for Spot with id spotId at time predictionTime
	 */
	private Prediction getPrediction(long spotId, PredictionTime predictionTime) {
		// make request and retrieve list
		ForecastResponse.List list = controller.getForecastResponseList(spotId);
		// create factory and retrieve Prediction
		Prediction prediction = new PredictionFactory(list, predictionTime)
				.createPrediction();
		return prediction;
	}


	/**
	 * TODO: Adjust parameter
	 * 
	 * @param predictionRating
	 * @return boolean
	 */
	private boolean isAccepted(float predictionRating) {
		return (predictionRating > 0.5) ? true : false;
	}
}
