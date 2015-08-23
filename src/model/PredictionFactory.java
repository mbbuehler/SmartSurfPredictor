package model;

public class PredictionFactory {
	private ForecastResponse.List list = null;
	private PredictionTime time = null;

	public PredictionFactory(ForecastResponse.List list, PredictionTime time) {
		this.list = list;
		this.time = time;
	}

	public Prediction createPrediction() {
		return null;
	}

}
