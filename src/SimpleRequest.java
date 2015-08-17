import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import Model.ForecastResponse;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Key;
public class SimpleRequest {
	static NetHttpTransport httpTransport = new NetHttpTransport();
	static JsonFactory JSON_FACTORY = new JacksonFactory();
	// static GenericUrl url = new GenericUrl(/* insert url */);
	static SSPBuilder builder = new SSPBuilder();

	public static void main(String[] args) {
		new SimpleRequest().sendGet();
	}
	public void sendGet(){
		HttpRequestFactory factory = httpTransport.createRequestFactory(new HttpRequestInitializer() {			
			public void initialize(HttpRequest request) throws IOException {
				request.setParser(new JsonObjectParser(JSON_FACTORY));
				}
		});
		try{
			HttpRequest request = factory.buildGetRequest(url);
			HttpResponse response = request.execute();
			if(response != null){
				ForecastResponse.List list = response
						.parseAs(ForecastResponse.List.class);
				for (ForecastResponse forecastResponse : list) {
					System.out.println(builder
							.getSwellForecast(forecastResponse));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
