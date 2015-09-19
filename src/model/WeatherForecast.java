package model;

/**
 * Model Class for MSW Forecast data
 * 
 * @author marcello
 * 
 */
public class WeatherForecast extends Forecast {
	public Wind wind;
	public Weather weather;
	public Chart windChart;
	public Chart pressureChart;


	public WeatherForecast(long issueTimestamp, long localTimestamp) {
		super(issueTimestamp, localTimestamp);
	}

	public WeatherForecast(long issueTimestamp, long localTimestamp, Wind wind,
			Weather weather, Chart windChart, Chart pressureChart) {
		super(issueTimestamp, localTimestamp);
		this.wind = wind;
		this.weather = weather;
		this.windChart = windChart;
		this.pressureChart = pressureChart;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WeatherFC(");
		builder.append("Windspeed: " + wind.speed);
		builder.append(", Temp: " + weather.temperature);
		builder.append(", Weather: " + weather.weather);
		builder.append(")");
		return builder.toString();
	}

}
