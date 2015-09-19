package model;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import controller.ForecastController;

public class Notifier {

	private PredictionManager predictionManager = null;
	//variable created by Eeswari
	private SpotReaderFile spot = new SpotReaderFile();
	private FavouriteSpotFile favSpot = new FavouriteSpotFile(spot);

	public Notifier() {
		super();
		ForecastController controller = new ForecastController();
		this.predictionManager = new PredictionManager(controller);
	}

	
	public void notify(PredictionTime predictionTime) 
	{
		HashMap<Spot, PlainPrediction> ratedPredictions = predictionManager
				.getRatedPredictions(predictionTime);
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

	public FavouriteSpotFile getFavSpot() {
		return favSpot;
	}

	public PredictionManager getPredictionManager() 
	{
		return predictionManager;
	}


}
