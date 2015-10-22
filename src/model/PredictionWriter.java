package model;
	
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
public class PredictionWriter {
	private File file = null;

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
	public PredictionWriter(String path) {
		this.file = new File(path);
		}

	/**
	 * Write first element of arff file: relation name
	 * 
	 * @param name
	 *            the name of the relation
	 */
	private String getRelationNameString(String name) {
		return "@relation " + name;
	}

	private String getHeaderString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getRelationNameString("predictions"));
		// add new line (system independent)
		builder.append(System.getProperty("line.separator"));
		builder.append(System.getProperty("line.separator"));

		builder.append(getAttributeListString());

		builder.append(System.getProperty("line.separator"));
		builder.append(System.getProperty("line.separator"));

		builder.append("@data");

			builder.append(System.getProperty("line.separator"));

		return builder.toString();
	}

	/**
	 * Adds a single labeled prediction to training set. Writes neither the \at
	 * relation nor the \at attribute part. Use this method for APPENDING data
	 * to arff file.
	 * 
	 * @param p
	 *            the prediction that should be added. Make sure it is labeled
	 */

	/**
	 * Get a numeric attribute
	 * 
	 * @param attributeName
	 *            the name of the attribute
	 */
	private String getNumericString(String attributeName) {
		return "@attribute " + attributeName + " numeric"
				+ System.getProperty("line.separator");
		}

	/**
	 * Get a non-numeric attribute.
	 * 
	 * @param attributeName
	 *            the name of the attribute
	 * @param values
	 *            a string containing the comma-separated discrete values for
	 *            the attribute
	 */
	private String getAttributeString(String attributeName, String values) {
		return "@attribute " + attributeName + " {"
				+ values.substring(1, values.length() - 1) + "}"
				+ System.getProperty("line.separator");
		}

	/**
	 * Writes second element of arff file: All attributes that will be used by
	 * the classifier.
	 * 
	 * @return String with attribute List
	 */
	private String getAttributeListString() {
		StringBuilder builder = new StringBuilder();

		builder.append(getNumericString("spotId"));
		builder.append(getNumericString("minBreakHeight"));
		builder.append(getNumericString("maxBreakHeight"));
		builder.append(getNumericString("fadedRating"));
		builder.append(getNumericString("solidRating"));
		builder.append(getNumericString("primarySwellHeight"));
		builder.append(getNumericString("primarySwellPeriod"));
		builder.append(getAttributeString("compassDirection",
				Arrays.toString(CompassDirection.values())));
		builder.append(getNumericString("windSpeed"));
		builder.append(getAttributeString("windDirection",
				Arrays.toString(CompassDirection.values())));
		builder.append(getNumericString("weather"));
		builder.append(getNumericString("temperature"));
		builder.append("@attribute show {yes,no}");

		return builder.toString();
		}

	private BufferedWriter createWriter() throws IOException {
		BufferedWriter writer = null;
		// Filewriter append true, otherwise file will be overwritten
		writer = new BufferedWriter(new FileWriter(this.file, true));
		return writer;
		}

	private String getSingleAttributeValueString(String value) {
		return value + ",";
		}

	private String getSingleAttributeValueString(int value) {
		return value + ",";
	}

	private String getSingleAttributeValueString(float value) {
		return value + ",";
	}

	private String getInstanceString(PlainPrediction p) {
		StringBuilder builder = new StringBuilder();
		try {
			builder.append(p.spotId + ",");
			builder.append(getSingleAttributeValueString(p.minBreakHeight));
			builder.append(getSingleAttributeValueString(p.maxBreakHeight));
			builder.append(getSingleAttributeValueString(p.fadedRating));
			builder.append(getSingleAttributeValueString(p.solidRating));
			builder.append(getSingleAttributeValueString(p.primarySwellHeight));
			builder.append(getSingleAttributeValueString(p.primarySwellPeriod));
			builder.append(getSingleAttributeValueString(p.primarySwellDirection
					.toString()));
			builder.append(getSingleAttributeValueString(p.windSpeed));
			builder.append(getSingleAttributeValueString(p.windDirection
					.toString()));
			builder.append(getSingleAttributeValueString(p.weather));
			builder.append(getSingleAttributeValueString(p.temperature));

			if (p.getStatus() == PredictionStatus.ACCEPTED) {
				builder.append("yes");
			} else if (p.getStatus() == PredictionStatus.REJECTED) {
				builder.append("no");
			} else if (p.getStatus() == PredictionStatus.UNLABELED) {
				builder.append("?");
			} else {
				System.err.println("Error: Invalid PredictionStatus: "
						+ p.getStatus());
			}
			builder.append(System.getProperty("line.separator"));
		} catch (Exception e) {
			// If p contains an invalid or empty value, just skip it.
			// System.out.println("Could not write " + p);
			return "";
		}
		return builder.toString();
	}

	/**
	 * Writes a single PlainPrediction to arff file. <br>
	 * If File does not exist: writes header (\at relation, \at attribute list).
	 * Otherwise only appends instance data<li>
	 * header: e.g. "@relation X" <li>attributes: e.g.
	 * "@attribute speed numeric" <li>data: data with all data from Prediction
	 * list
	 * 
	 * @param p
	 *            PlainPrediction to be written
	 */
	public void writeToFile(PlainPrediction p) {
		StringBuilder builder = new StringBuilder();
		if (!this.file.exists()) {
			builder.append(getHeaderString());
		}
			builder.append(getInstanceString(p));
		try {
			BufferedWriter writer = createWriter();
			writer.append(builder.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Writes list with PlainPredictions to arff file. <br>
	 * If File does not exist: writes header (\at relation, \at attribute list).
	 * Otherwise only appends instance data<li>
	 * header: e.g. "@relation X" <li>attributes: e.g.
	 * "@attribute speed numeric" <li>data: data with all data from Prediction
	 * list
	 * 
	 * @param plainPredictions
	 *            ArrayList of PlainPredictions to be written
	 */
	public void writeToFile(ArrayList<PlainPrediction> plainPredictions) {
		StringBuilder builder = new StringBuilder();
		if (!this.file.exists()) {
			builder.append(getHeaderString());
		}
		for (PlainPrediction p : plainPredictions) {
			builder.append(getInstanceString(p));
		}
		try {
			BufferedWriter writer = createWriter();
			writer.append(builder.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}

		}
