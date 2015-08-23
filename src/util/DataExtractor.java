package util;

import java.io.IOException;
import java.util.ArrayList;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.json.JsonObjectParser;

import model.Forecast;
import model.ForecastResponse;
import model.Prediction;

public class DataExtractor {
	private ForecastResponse.List forecastResponseList = null;
	private SSPBuilder builder = null;

	public DataExtractor(ForecastResponse.List forecastResponseList) {
		super();
		this.forecastResponseList = forecastResponseList;
		builder = new SSPBuilder();
	}

	/**
	 * 
	 * @return ArrayList with all SwellForecasts
	 */
	public ArrayList<Forecast> getAllSwellForecasts() {
		ArrayList<Forecast> swellForecasts = new ArrayList<Forecast>();
		for (ForecastResponse response : this.forecastResponseList) {
			swellForecasts.add(builder.getSwellForecast(response));
		}
		return swellForecasts;
	}

	public Prediction getPrediction() {
		return null;
	}

}
