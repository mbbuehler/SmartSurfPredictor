package model;

import java.net.MalformedURLException;
import java.net.URL;

public final class Chart {
	final String link;
	private URL url;

	public Chart(String link) {
		super();
		this.link = link;
		this.url = null;
		try {
			this.url = new URL(link);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public URL getUrl() {
		return url;
	}

}
