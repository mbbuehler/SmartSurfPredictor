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

import weka.classifiers.bayes.BayesianLogisticRegression;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.SimpleLogistic;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;

import weka.classifiers.Classifier;

public class PredictionClassifier {
	private static String trainingSet = "user_data/labeled_predictions.arff";
	private static String unlabeledPath = "user_data/unlabeled_predictions.arff";

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
		Classifier classifier = new NaiveBayes();// BayesianLogisticRegression();
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
			e.printStackTrace();
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

}