package model;

import java.util.Calendar;

import util.SSPBuilder;

public class PredictionFactory {
	private ForecastResponse.List list = null;
	private PredictionTime time = null;
	private SSPBuilder builder;
	private double range = 5400.0; // 1.5h
	private Spot spot;

	public PredictionFactory(ForecastResponse.List list, PredictionTime time,
			Spot spot) {
		this.list = list;
		this.time = time;
		this.spot = spot;
		this.builder = new SSPBuilder();
	}

	/**
	 * TODO: improve timestamp issue
	 * 
	 * @return Prediction or null
	 */
	public Prediction createPrediction() {
		// System.out.println("target: " + getTargetTimestamp(time));
		// System.out.println("targetDate: "
		// + new Date(getTargetTimestamp(time) * 1000));
		// define correct ForecastResponse
		ForecastResponse response = null;
		for (ForecastResponse r : list) {
			// System.out.println("c: " + ++c);
			// System.out.println(r.localTimestamp);
			// System.out.println("timestamp: " + new Date(1000 * r.timestamp));
			long target = getTargetTimestamp(time);
			long timestamp = r.timestamp;
			double  distance = Math.abs(timestamp - target);
			if (distance <= range) {
				// System.out.println("match: " + r.timestamp);
				response = r;
			}
			// System.out.println("difference: " + distance);
			if (response != null) {
				// System.out.println("found.");
				break;
			}
		}
		if (response == null) {
			System.out
					.println("@PredictionFactory. Timestamp does not match in ForecastResponse:");
			System.out.println("targetTimeStamp = " + getTargetTimestamp(time));
			System.out
					.println("probably not matching because of different timezone. Chosing first response.");
			response = list.get(0);
		}
		if (response != null) {
			SwellForecast swellForecast = builder.getSwellForecast(response);
			WeatherForecast weatherForecast = builder
					.getWeatherForecast(response);
			Prediction prediction = new Prediction(swellForecast,
					weatherForecast, spot);
			return prediction;
		}
		return null;

	}


	public long getTargetTimestamp(PredictionTime time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		// System.out.println("current time: " + calendar.getTime());

		// for the next day's morning:
		if (time == PredictionTime.MORNING) {
			calendar.add(Calendar.DATE, 1);
			calendar.set(Calendar.HOUR_OF_DAY, 9);
		}
		// for same day arvo
		else
			calendar.set(Calendar.HOUR_OF_DAY, 15);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		// System.out.println("tomorrow at 0am: " + calendar.getTime());
		// System.out
		// .println("in timestamp: " + calendar.getTimeInMillis() / 1000);
		return calendar.getTimeInMillis() / 1000;
	}

}
