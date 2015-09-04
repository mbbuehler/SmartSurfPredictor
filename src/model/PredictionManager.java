package model;

import java.util.ArrayList;
import java.util.HashMap;

import controller.ForecastController;

public class PredictionManager {

	private ForecastController controller = null;

	public PredictionManager(ForecastController controller) {
		super();
		this.controller = controller;
	}

	private boolean prepareClassifierData(PredictionTime predictionTime) {
		ArrayList<Spot> spots = getSpots();
		ArrayList<Prediction> predictions = new ArrayList<Prediction>();
		for(Spot spot:spots){
			Prediction p = getPrediction(spot.getId(), predictionTime);
			predictions.add(p);
		}
		try{
			// Write unlabeled predictions. They will be classified by the weka
			// classifier
			PredictionWriter writer = new PredictionWriter(
					"data/predictions_unlabeled.arff", false);
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

	/**
	 * 
	 * 
	 * Reads
	 * 
	 * @param predictionTime
	 * @return
	 */
	public HashMap<Spot, Prediction> getFavouritePredictions(PredictionTime predictionTime) {
		// Prepare File that will be read by the classifier
		boolean succeeded = prepareClassifierData(predictionTime);

		// TODO: call classifier and get data back as PlainPrediction Class
		// TODO: fill Hashmap, get scores and return

		HashMap<Spot, Prediction> acceptedPredictions = new HashMap<Spot, Prediction>();
		
		ArrayList<Spot> spots = getSpots();
		for(Spot spot:spots){
			Prediction prediction = getPrediction(spot.id, predictionTime);
			float rating = getPredictionRating(prediction);
			// TODO: set predicitonrating
			if (isAccepted(rating)) {
				acceptedPredictions.put(spot, prediction);
			}
		}
		return acceptedPredictions;

	}
	
	private ArrayList<Spot> getSpots() {
		// TODO: implement reading and returning spots
		ArrayList<Spot> spots = new ArrayList<Spot>();
		for (int i = 255; i < 257; ++i) {
			Spot spot = new Spot("testname", "testcountry", "teststate", i);
			spots.add(spot);
		}
		return spots;
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

	private float getPredictionRating(Prediction prediction) {
		// TODO: implement
		return 1;
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
