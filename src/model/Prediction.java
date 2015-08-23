package model;

public class Prediction {
	private Forecast swellForecast = null;
	private Forecast weatherForecast = null;
	private float score = -1;

	// private Spot spot = null;

	public Prediction(Forecast swellForecast, Forecast weatherForecast) {
		super();
		this.swellForecast = swellForecast;
		this.weatherForecast = weatherForecast;
		// this.spot = spot;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public Forecast getSwellForecast() {
		return swellForecast;
	}

	public Forecast getWeatherForecast() {
		return weatherForecast;
	}

	public String toString(){
		return "Prediction(" + swellForecast + "," + weatherForecast + ","
				+ score + ")";
	}


}
