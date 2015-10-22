package controller;

import java.io.IOException;

import javax.swing.JOptionPane;

import model.ForecastResponse;
import util.APIKey;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;

/**
 * Class responsible for fetching forecasts from magicseaweed.
 * 
 * @author marcello
 * 
 */
public class ForecastController {
	static NetHttpTransport httpTransport = new NetHttpTransport();
	// Result will be in json format.
	static JsonFactory JSON_FACTORY = new JacksonFactory();
	static String base = "http://magicseaweed.com/api/" + APIKey.APIKey + "/";

	private GenericUrl createForecastRequestURL(long spotId) {
		String urlString = base + "forecast/?spot_id=" + spotId;
		GenericUrl url = new GenericUrl(urlString);
		return url;
	}

	/**
	 * Creates HTTP request. Makes sure the request can automatically handle a
	 * json response.
	 * 
	 * @param url
	 * @return HttpRequest, ready to execute
	 */
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

	/**
	 * Executes a HTTP request in order to retrieve forecast for surf spot with
	 * a certain id.<br>
	 * If the request failed (e.g. due to no Internet connection) a dialogue
	 * will be shown and the application exits.
	 * 
	 * @param spotId
	 *            id of spot
	 * @return A List with all forecasts for a certain spot, usually one
	 *         forecast every 3 hours
	 */
	public ForecastResponse.List getForecastResponseList(long spotId) {
		GenericUrl url = createForecastRequestURL(spotId);
		HttpRequest request = createHttpRequest(url);
		try {
			HttpResponse response = request.execute();
			ForecastResponse.List list = response
					.parseAs(ForecastResponse.List.class);
			return list;
		} catch (IOException e) {
			System.out
					.println("Request failed and no Forecasts received. Exiting...");
			JOptionPane
					.showMessageDialog(
							null,
							"Could not connect to magicseaweed. Are you connected to the Internet? Please connect to the Internet and restart the application.",
							"No Internet?",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
			return null;
		}
	}

}
