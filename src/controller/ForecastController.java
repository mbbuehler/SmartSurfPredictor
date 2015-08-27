package controller;

import java.io.IOException;

import model.ForecastResponse;
		
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;

import ams.main.APIKey;

public class ForecastController {
	static NetHttpTransport httpTransport = new NetHttpTransport();
	static JsonFactory JSON_FACTORY = new JacksonFactory();
	static String base = "http://magicseaweed.com/api/" + APIKey.APIKey + "/";

	private GenericUrl createForecastRequestURL(long spotId) {
		String urlString = base + "forecast/?spot_id=" + spotId;
		GenericUrl url = new GenericUrl(urlString);
		return url;
	}

	private HttpRequest createHttpRequest(GenericUrl url) {
		HttpRequestFactory factory = httpTransport
				.createRequestFactory(new HttpRequestInitializer() {
					public void initialize(HttpRequest request)
							throws IOException {
						request.setParser(new JsonObjectParser(JSON_FACTORY));
					}
				});
		try {
			HttpRequest request = factory.buildGetRequest(url);
			return request;
		} catch (IOException e) {
			System.err
					.println("HttpRequest could not be created. Returning null.");
			return null;
		}
	}

	public ForecastResponse.List getForecastResponseList(long spotId) {
		GenericUrl url = createForecastRequestURL(spotId);
		HttpRequest request = createHttpRequest(url);
		try {
			HttpResponse response = request.execute();
			ForecastResponse.List list = response
					.parseAs(ForecastResponse.List.class);
			return list;
		} catch (IOException e) {
			System.err
					.println("Request failed. No Forecasts received. Returned null.");
			return null;
		}
	}


}
