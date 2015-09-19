package util;

import model.Chart;
import model.Forecast;
import model.ForecastResponse;
import model.Spot;
import model.Surf;
import model.Swell;
import model.SwellForecast;
import model.Weather;
import model.WeatherForecast;
import model.Wind;


/**
 * 
 * Creates Forecast Class objects from MSW json response
 * 
 * @author marcello
 * 
 */
public class SSPBuilder {

	/**
	 * Converts data from ForecastResponse r to an instance of Class
	 * {@link WeatherForecast}. Some Attributes of {@link WeatherForecast} might
	 * be null.
	 * 
	 * @param r
	 * @return {@link WeatherForecast}
	 */
	public WeatherForecast getWeatherForecast(ForecastResponse r) {
		model.Wind wind = null;
		model.Weather weather = null;
		model.Chart windChart = null;
		model.Chart pressureChart = null;
		try{
			wind = new model.Wind((int) r.wind.speed, r.wind.direction,
					r.wind.compassDirection, (int) r.wind.chill,
					(int) r.wind.gusts,
					r.wind.unit);
		} catch (NullPointerException e) {
			System.out.println("Nullpointer when creating Wind.");
			// e.printStackTrace();
		}
		try {
			weather = new model.Weather(r.condition.pressure,
					r.condition.temperature, r.condition.weather,
					r.condition.unitPressure, r.condition.unit);
		} catch (NullPointerException e) {
			System.out.println("Nullpointer when creating Weather.");
			// e.printStackTrace();
		}
		try {
			windChart = new model.Chart(r.charts.wind);
		} catch (NullPointerException e) {
			System.out.println("Nullpointer when creating WindChart.");
			// e.printStackTrace();
		}
		try {
			pressureChart = new model.Chart(r.charts.pressure);
		} catch (NullPointerException e) {
			System.out.println("Nullpointer when creating PressureChart.");
			// e.printStackTrace();
		}
		WeatherForecast weatherForecast = new WeatherForecast(r.issueTimestamp,

				r.localTimestamp, wind, weather, windChart, pressureChart);
		return weatherForecast;
	}

	/**
	 * Converts data from ForecastResponse r to an instance of Class
	 * {@link SwellForecast}. Some Attributes of {@link SwellForecast} might be
	 * null.
	 * 
	 * @param r
	 *            response from MSWeed
	 * @return {@link SwellForecast}
	 */
	public SwellForecast getSwellForecast(ForecastResponse r) {
		Surf surf = null;
		model.Swell primarySwell = null;
		model.Swell secondarySwell = null;
		model.Swell tertiarySwell = null;
		model.Chart periodChart = null;
		model.Chart swellChart = null;
		model.Chart sstChart = null;

		try {
			surf = new Surf((int) r.swell.minBreakingHeight,
					(int) r.swell.absMinBreakingHeight,
					(int) r.swell.maxBreakingHeight,
					(int) r.swell.absMaxBreakingHeight, r.swell.unit);
		} catch (NullPointerException e) {
			// System.out.println("Nullpointer when creating surf.");
			// e.printStackTrace();
		}
		try {
			primarySwell = new model.Swell(
					(float) r.swell.components.primary.height,
					(int) r.swell.components.primary.period,
					r.swell.components.primary.direction,
					r.swell.components.primary.compassDirection, r.swell.unit);
		} catch (NullPointerException e) {
			System.out.println("Nullpointer when creating primarySwell.");
			// e.printStackTrace();
		}
		try {
			secondarySwell = new model.Swell(
					(int) r.swell.components.secondary.height,
					(int)
					r.swell.components.secondary.period,
					r.swell.components.secondary.direction,
					r.swell.components.secondary.compassDirection, r.swell.unit);
		} catch (NullPointerException e) {
			// System.out.println("Nullpointer when creating secondarySwell.");
			// e.printStackTrace();
		}
		try {
			tertiarySwell = new model.Swell(
					(int) r.swell.components.tertiary.height,
					(int) r.swell.components.tertiary.period,
					r.swell.components.tertiary.direction,
					r.swell.components.tertiary.compassDirection, r.swell.unit);
		} catch (NullPointerException e) {
			// System.out.println("Nullpointer when creating tertiarySwell.");
			// e.getMessage();
		}
		try {
			periodChart = new model.Chart(r.charts.period);
			swellChart = new model.Chart(r.charts.swell);
			sstChart = new model.Chart(r.charts.sst);
		} catch (NullPointerException e) {
			// System.out.println("Nullpointer when creating Charts.");
		}
		SwellForecast swellForecast = new SwellForecast(r.issueTimestamp,
				r.localTimestamp, (int) r.fadedRating, (int) r.solidRating,
				surf,
				primarySwell, secondarySwell, tertiarySwell, periodChart,
				swellChart, sstChart);
		return swellForecast;

			}

}
