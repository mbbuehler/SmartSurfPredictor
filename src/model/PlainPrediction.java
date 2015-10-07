package model;

/**
 * PlainPrediction is a simple container for all the data that should be
 * displayed in the GUI.
 * 
 * @author marcello
 * 
 */
public class PlainPrediction {

	public final String spotName;
	public final int spotId;
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
	private PredictionStatus status;
	private boolean hasBeenRated = false;

	public PlainPrediction(String spotName, int spotId, int minBreakHeight,
			int maxBreakHeight, int fadedRating, int solidRating,
			float primarySwellHeight, int primarySwellPeriod,
			CompassDirection primarySwellDirection, int windSpeed,
			CompassDirection windDirection, int weather, float temperature,
 float score, PredictionStatus status)
	{
		super();
		this.spotName = spotName;
		this.spotId = spotId;
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
		this.status = status;
	}

	public PlainPrediction(Prediction p)
	{
		this.spotName = p.spot.name;
		this.spotId = p.spot.id;
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
		this.status = p.getStatus();
	}

	public PredictionStatus getStatus() {
		return status;
	}

	public void setStatus(PredictionStatus status) {
		this.status = status;
	}

	public String getSpotName() 
	{
		return spotName;
	}

	public int getSpotId() 
	{
		return spotId;
	}

	public boolean isHasBeenRated() {
		return hasBeenRated;
	}

	public void setHasBeenRated(boolean hasBeenRated) {
		this.hasBeenRated = hasBeenRated;
	}

}
