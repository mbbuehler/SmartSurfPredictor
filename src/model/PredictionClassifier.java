package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import model.ForecastResponse.List;

import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;

import weka.classifiers.Classifier;

public class PredictionClassifier {
	private static String trainingSet = "classifier_data/labeled_predictions.arff";
	private static String unlabeledPath = "classifier_data/unlabeled_predictions.arff";

	// We will be trying various ones. this is just for testing purposes.
	private static Classifier classifier = createClassifier();

	// public PredictionClassifier() {
	// this.classifier = getClassifier();
	// }

	/**
	 * Modify this method to change classifier
	 * 
	 * @return
	 */
	private static Classifier createClassifier() {
		Classifier classifier = new NaiveBayes();
		try {
			// load labeled data
			Instances train = new Instances(new BufferedReader(new FileReader(
					trainingSet)));

			// set class attribute
			train.setClassIndex(train.numAttributes() - 1);

			classifier.buildClassifier(train);
			return classifier;
		} catch (IOException e) {
			System.err
					.println("@PredictionClassifier: Error when loading training set.");
		} catch (Exception e) {
			System.err
					.println("@PredictionClassifier: Could not train ForecastClassifier.");
			}
			return null;
	}

	/**
	 * Loads unlabeled forecasts from file "data/unlabeled_forecasts.arff" and
	 * adds labels to it. prints result to console.<br>
	 * Some code copied from
	 * https://weka.wikispaces.com/Use+WEKA+in+your+Java+code
	 * #Instances-ARFF%20File (30.8.15)
	 * 
	 * @param prediction
	 */
	public static ArrayList<Float> ratePredictions() {
		ArrayList<Float> scores = new ArrayList<Float>();
		try {
			// load unlabeled data
			Instances unlabeled = new Instances(new BufferedReader(
					new FileReader(unlabeledPath)));
			unlabeled.setClassIndex(unlabeled.numAttributes() - 1);

			// create copy
			Instances labeled = new Instances(unlabeled);

			// label all instances
			for (int i = 0; i < unlabeled.numInstances(); i++) {
				// if clsLabel is 0: no
				// if clsLabel is 1: yes
				double clsLabel = classifier.classifyInstance(unlabeled
						.instance(i));
				//
				labeled.instance(i).setClassValue(clsLabel);

				// Get likelihood for decision
				double[] distribution = classifier
						.distributionForInstance(labeled.instance(i));
				Arrays.sort(distribution);
				// estimated likelihood that user accepts forecast
				float score = (float) distribution[1];
				scores.add(score);

				// System.out.println("likelihood: " + likelihood);
				// System.out.println(labeled.instance(i));

			}
			return scores;

		} catch (Exception e) {
			System.err.println("Could not classify.");
			e.printStackTrace();
			return null;
		}

		}

	// private PlainPrediction createPlainPrediction(Instance i, float score) {
	// // System.out.println("instance: ");
	// // System.out.println(i);
	// // double val = 0;
	// // for (int k = 0; k < i.numAttributes(); ++k) {
	// // if (i.attribute(k).isNumeric()) {
	// // System.out.print(i.value(k));
	// // } else {
	// // System.out.print(i.stringValue(k));
	// // }
	// // System.out.print(",");
	// // // System.out.print(i.value(k) + ",");
	// // // System.out.println(i.stringValue(6));
	// // }
	// System.out.println("");
	// int minBreakHeight = (int) i.value(0);
	// int maxBreakHeight = (int) i.value(1);
	// int fadedRating = (int) i.value(2);
	// int solidRating = (int) i.value(3);
	// float primarySwellHeight = (float) i.value(4);
	// int primarySwellPeriod = (int) i.value(5);
	// CompassDirection primarySwellDirection = CompassDirection
	// .getCompassDirection(i.stringValue(6));
	// int windSpeed = (int) i.value(7);
	// CompassDirection windDirection = CompassDirection.getCompassDirection(i
	// .stringValue(8));
	// int weather = (int) i.value(9);
	// float temperature = (float) i.value(10);
	// PlainPrediction p = new PlainPrediction(minBreakHeight, maxBreakHeight,
	// fadedRating, solidRating, primarySwellHeight,
	// primarySwellPeriod, primarySwellDirection, windSpeed,
	// windDirection, weather, temperature, score);
	// return p;
	// }

}