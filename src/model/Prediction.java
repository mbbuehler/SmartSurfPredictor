package model;

public class Prediction {
	private SwellForecast swellForecast = null;
	private WeatherForecast weatherForecast = null;
	public PredictionStatus status = PredictionStatus.UNLABELED;
	private float score = -1;

	// private Spot spot = null;

	public Prediction(SwellForecast swellForecast,
			WeatherForecast weatherForecast) 
	{
		super();
		this.swellForecast = swellForecast;
		this.weatherForecast = weatherForecast;
		// this.spot = spot;
	}

	public float getScore() 
	{
		return score;
	}

	public void setScore(float score) 
	{
		this.score = score;
	}

	public SwellForecast getSwellForecast() 
	{
		return swellForecast;
	}

	public WeatherForecast getWeatherForecast() 
	{
		return weatherForecast;
	}

	public String toString()
	{
		return "Prediction(" + swellForecast + "," + weatherForecast + ","
				+ "Score: " + score + ")";
	}
	

	public PredictionStatus getStatus() 
	{
		return status;
	}

	public void setStatus(PredictionStatus status) 
	{
		this.status = status;
	}


}
