package model;

public class Prediction {
	private Forecast swellForecast = null;
	private Forecast weatherForecast = null;

	// private Spot spot = null;

	public Prediction(Forecast swellForecast, Forecast weatherForecast) {
		super();
		this.swellForecast = swellForecast;
		this.weatherForecast = weatherForecast;
		// this.spot = spot;
	}



}
