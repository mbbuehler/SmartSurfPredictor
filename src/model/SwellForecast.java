package model;

/**
 * Model Class for MSW Forecast data
 * 
 * @author marcello
 * 
 */
public class SwellForecast extends Forecast {
	public long fadedRating;
	public long solidRating;
	public Surf surf;
	public Swell primarySwell;
	public Swell secondarySwell;
	public Swell tertiarySwell;
	public Chart periodChart;
	public Chart swellChart;
	public Chart sstChart;

	public SwellForecast(long issueTimestamp, long localTimestamp) {
		super(issueTimestamp, localTimestamp);
	}

	public SwellForecast(long issueTimestamp, long localTimestamp,
			long fadedRating, long solidRating, Surf surf, Swell primarySwell,
			Swell secondarySwell, Swell tertiarySwell, Chart periodChart,
			Chart swellChart, Chart sstChart) {
		this(issueTimestamp, localTimestamp);
		this.fadedRating = fadedRating;
		this.solidRating = solidRating;
		this.surf = surf;
		this.primarySwell = primarySwell;
		this.secondarySwell = secondarySwell;
		this.tertiarySwell = tertiarySwell;
		this.periodChart = periodChart;
		this.swellChart = swellChart;
		this.sstChart = sstChart;
	}

	/**
	 * just for visualizing and checking that the Class has the correct
	 * attributes
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("timestamp: " + this.getLocalTimestamp());
		builder.append(" | solidRating: " + this.solidRating);
		builder.append(" | faded Rating: " + this.fadedRating);
		return builder.toString();
	}

	}
