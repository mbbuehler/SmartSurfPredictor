package model;

/**
 * PlainPrediction is a simple container for all the data that should be
 * displayed in the GUI.
 * 
 * @author marcello
 * 
 */
public class PlainPrediction {

	public final int minBreakHeight;
	public final int maxBreakHeight;
	public final int fadedRating;
	public final int solidRating;
	public final float primarySwellHeight;
	public final int primarySwellPeriod;
	public final CompassDirection primarySwellDirection;
	public final int windSpeed;
	public final CompassDirection windDirection;
	public final int weather;
	public final float temperature;
	public final float score;

	public PlainPrediction(int minBreakHeight,
			int maxBreakHeight, int fadedRating, int solidRating,
			float primarySwellHeight, int primarySwellPeriod,
			CompassDirection primarySwellDirection, int windSpeed,
			CompassDirection windDirection, int weather, float temperature,
			float score) 
	{
		super();
		this.minBreakHeight = minBreakHeight;
		this.maxBreakHeight = maxBreakHeight;
		this.fadedRating = fadedRating;
		this.solidRating = solidRating;
		this.primarySwellHeight = primarySwellHeight;
		this.primarySwellPeriod = primarySwellPeriod;
		this.primarySwellDirection = primarySwellDirection;
		this.windSpeed = windSpeed;
		this.windDirection = windDirection;
		this.weather = weather;
		this.temperature = temperature;
		this.score = score;
	}

	public PlainPrediction(Prediction p) 
	{
		this.minBreakHeight = p.getSwellForecast().surf.minBreakingHeight;
		this.maxBreakHeight = p.getSwellForecast().surf.maxBreakingHeight;
		this.fadedRating = p.getSwellForecast().fadedRating;
		this.solidRating = p.getSwellForecast().solidRating;
		this.primarySwellHeight = p.getSwellForecast().primarySwell.height;
		this.primarySwellPeriod = p.getSwellForecast().primarySwell.period;
		this.primarySwellDirection = p.getSwellForecast().primarySwell.compassDirection;
		this.windSpeed = p.getWeatherForecast().wind.speed;
		this.windDirection = p.getWeatherForecast().wind.compassDirection;
		this.weather = p.getWeatherForecast().weather.weather;
		this.temperature = p.getWeatherForecast().weather.temperature;
		this.score = p.getScore();
	}

	public int getMinBreakHeight() 
	{
		return minBreakHeight;
	}

	public int getMaxBreakHeight() 
	{
		return maxBreakHeight;
	}

	public int getFadedRating() 
	{
		return fadedRating;
	}

	public int getSolidRating() 
	{
		return solidRating;
	}

	public float getPrimarySwellHeight() 
	{
		return primarySwellHeight;
	}

	public int getPrimarySwellPeriod() 
	{
		return primarySwellPeriod;
	}

	public CompassDirection getPrimarySwellDirection() 
	{
		return primarySwellDirection;
	}

	public int getWindSpeed() 
	{
		return windSpeed;
	}

	public CompassDirection getWindDirection() 
	{
		return windDirection;
	}

	public int getWeather() 
	{
		return weather;
	}

	public float getTemperature() 
	{
		return temperature;
	}

	public float getScore() 
	{
		return score;
	}


}
