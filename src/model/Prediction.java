package model;

public class Prediction {
	private SwellForecast swellForecast = null;
	private WeatherForecast weatherForecast = null;

	// private Spot spot = null;

	public Prediction(SwellForecast swellForecast,
			WeatherForecast weatherForecast) {
		super();
		this.swellForecast = swellForecast;
		this.weatherForecast = weatherForecast;
		// this.spot = spot;
	}



}
