package model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Writes data files (arff) for weka Classifiers. This is a specialized Writer
 * for the SmartSurfPredictor Classifier. Check out constructor javadoc for more
 * information.
 * 
 * @author marcello
 * 
 */
public class PredictionWriter extends PrintWriter {
	boolean labeled;
	
	/**
	 * <p>
	 * Creates an arff file for Predictions.
	 * </p>
	 * set flag labeled:<li>true = write labeled data (= write training set) <li>
	 * false = write unlabeled data
	 * 
	 * @param path
	 *            path to file, e.g. "data/predictions_unlabeled.arff"
	 * @param labeled
	 *            true (write training set) or false (write unlabeled
	 *            predictions)
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public PredictionWriter(String path, boolean labeled)
			throws FileNotFoundException,
			UnsupportedEncodingException 
	{
		super(new FileOutputStream(path, true));
		this.labeled = labeled;
		
		// this.
	}

	/**
	 * Write first element of arff file: relation name
	 * 
	 * @param name
	 *            the name of the relation
	 */
	private void writeRelationName(String name) {
		this.write("@relation " + name);
	}

	/**
	 * Writes list with predictions to arff file. <br>
	 * Writes the \at relation, the \at attribute and the \at data section. Do
	 * NOT use this method for appending labeled predictions to training set or
	 * you will get the header information multiple times.<li>
	 * header: e.g. "@relation X" <li>attributes: e.g.
	 * "@attribute speed numeric" <li>data: data with all data from Prediction
	 * list
	 * 
	 * @param list
	 */
	public void writePredictions(ArrayList<Prediction> list) 
	{
		writeRelationName("predictions");
		this.println("");
		this.println("");
		writeAttributeList();
		this.println("");
		this.println("");
		this.println("@data");
		for (Prediction p : list) 
		{
			addLabeledPrediction(p);
		}
	}

	/**
	 * Adds a single labeled prediction to training set. Writes neither the \at
	 * relation nor the \at attribute part. Use this method for APPENDING data
	 * to arff file.
	 * 
	 * @param p
	 *            the prediction that should be added. Make sure it is labeled
	 */
	public void addLabeledPrediction(Prediction p)
	{
		StringBuilder builder = new StringBuilder();

		int minBreakHeight = p.getSwellForecast().surf.minBreakingHeight;
		builder.append(minBreakHeight);
		builder.append(",");
		int maxBreakHeight = p.getSwellForecast().surf.maxBreakingHeight;
		builder.append(maxBreakHeight);
		builder.append(",");
		int fadedRating = p.getSwellForecast().fadedRating;
		builder.append(fadedRating);
		builder.append(",");
		int solidRating = p.getSwellForecast().solidRating;
		builder.append(solidRating);
		builder.append(",");
		float primarySwellHeight = p.getSwellForecast().primarySwell.height;
		builder.append(primarySwellHeight);
		builder.append(",");
		int primarySwellPeriod = p.getSwellForecast().primarySwell.period;
		builder.append(primarySwellPeriod);
		builder.append(",");
		CompassDirection primarySwellDirection = p.getSwellForecast().primarySwell.compassDirection;
		builder.append(primarySwellDirection);
		builder.append(",");
		int windSpeed = p.getWeatherForecast().wind.speed;
		builder.append(windSpeed);
		builder.append(",");
		CompassDirection windDirection = p.getWeatherForecast().wind.compassDirection;
		builder.append(windDirection);
		builder.append(",");
		int weather = p.getWeatherForecast().weather.weather;
		builder.append(weather);
		builder.append(",");
		float temperature = p.getWeatherForecast().weather.temperature;
		builder.append(temperature);
		// for target class
		builder.append(",");
		if (labeled) {
			if (p.status == PredictionStatus.ACCEPTED) {
				builder.append("yes");
			} else if (p.status == PredictionStatus.REJECTED) {
				builder.append("no");
			} else {
				System.err.println("Error: Invalid PredictionStatus: "
						+ p.status);
				// builder.append("?");
			}
		} else {
			builder.append("?");
		}
		this.println(builder.toString());
	}

	/**
	 * Add a numberic attribute
	 * 
	 * @param attributeName
	 *            the name of the attribute
	 */
	private void addNumeric(String attributeName) 
	{
		this.println("@attribute " + attributeName + " numeric");
	}

	/**
	 * Add a non-numeric attribute.
	 * 
	 * @param attributeName
	 *            the name of the attribute
	 * @param values
	 *            a string containing the comma-separated discrete values for
	 *            the attribute
	 */
	private void addAttribute(String attributeName, String values) 
	{
		this.println("@attribute " + attributeName + " {"
				+ values.substring(1, values.length() - 1) + "}");
	}

	/**
	 * Writes second element of arff file: All attributes that will be used by
	 * the classifier.
	 */
	private void writeAttributeList() 
	{
		StringBuilder builder = new StringBuilder();

		addNumeric("minBreakHeight");
		addNumeric("maxBreakHeight");
		addNumeric("fadedRating");
		addNumeric("solidRating");
		addNumeric("primarySwellHeight");
		addNumeric("primarySwellPeriod");
		addAttribute("compassDirection",
				Arrays.toString(CompassDirection.values()));
		addNumeric("windSpeed");
		addAttribute("windDirection",
				Arrays.toString(CompassDirection.values()));
		addNumeric("weather");
		addNumeric("temperature");
		builder.append("@attribute show {yes,no}");
		
		this.print(builder.toString());
		
	}



}
