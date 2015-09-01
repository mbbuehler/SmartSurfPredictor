package model;

import java.awt.Window;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

import controller.ForecastController;

public class Notifier {
	private PredictionManager predictionManager = null;
	//variable created by Eeswari
	private SpotReaderFile spot = new SpotReaderFile();
	private WriteFavouriteSpotFile writeFavSpot = new WriteFavouriteSpotFile();

	public Notifier() {
		super();
		ForecastController controller = new ForecastController();
		this.predictionManager = new PredictionManager(controller);
	}

	public void notify(PredictionTime predictionTime) 
	{

		HashMap<Spot, Prediction> ratedPredictions = predictionManager.getFavouritePredictions(predictionTime);
		Iterator it = ratedPredictions.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			StringBuilder builder = new StringBuilder();
			builder.append(pair.getKey() + " = " + pair.getValue());
			it.remove();
			System.out.println(builder.toString());
		}

	}
	
	//getter for variable
	public SpotReaderFile getSpot() 
	{
		return spot;
	}

	public WriteFavouriteSpotFile getWriteFavSpot() {
		return writeFavSpot;
	}


}
