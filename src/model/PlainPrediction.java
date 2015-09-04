package model;

/**
 * PlainPrediction is a simple container for all the data that should be
 * displayed in the GUI.
 * 
 * @author marcello
 * 
 */
public class PlainPrediction {

	public final PredictionTime time;
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

	public PlainPrediction(PredictionTime time, int minBreakHeight,
			int maxBreakHeight, int fadedRating, int solidRating,
			float primarySwellHeight, int primarySwellPeriod,
			CompassDirection primarySwellDirection, int windSpeed,
			CompassDirection windDirection, int weather, float temperature) {
		super();
		this.time = time;
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
	}


}
