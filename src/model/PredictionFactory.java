package model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;

import util.SSPBuilder;

public class PredictionFactory {
	private ForecastResponse.List list = null;
	private PredictionTime time = null;
	private SSPBuilder builder;

	public PredictionFactory(ForecastResponse.List list, PredictionTime time) {
		this.list = list;
		this.time = time;
		this.builder = new SSPBuilder();
	}

	/**
	 * TODO: improve timestamp issue
	 * 
	 * @return Prediction or null
	 */
	public Prediction createPrediction() {
		// define correct ForecastResponse
		ForecastResponse response = null;
		for (ForecastResponse r : list) {
			if (r.localTimestamp == getTargetTimestamp(time)) {
				response = r;
			}
		}
		if (response != null) {
			Forecast swellForecast = builder.getSwellForecast(response);
			Forecast weatherForecast = builder.getWeatherForecast(response);
			Prediction prediction = new Prediction(swellForecast,
					weatherForecast);
			return prediction;
		} else {
			System.err.println("Timestamp does not match in ForecastResponse.");
			return null;
		}
	}

	public long getTargetTimestamp(PredictionTime time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		// System.out.println("current time: " + calendar.getTime());
		calendar.add(Calendar.DATE, 1);
		// for some reason one hour moved.
		if (time == PredictionTime.MORNING)
			calendar.set(Calendar.HOUR_OF_DAY, 10);
		else
			calendar.set(Calendar.HOUR_OF_DAY, 16);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		// System.out.println("tomorrow at 0am: " + calendar.getTime());
		// System.out
		// .println("in timestamp: " + calendar.getTimeInMillis() / 1000);
		return calendar.getTimeInMillis();
	}

}
