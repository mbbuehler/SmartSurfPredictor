package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import model.ForecastResponse.List;

import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.J48;
import weka.core.Instances;

import weka.classifiers.Classifier;

public class PredictionClassifier {
	// Testdriver. For local testing only.
	public static void main(String[] args) {
		new PredictionClassifier().createPredictionRating();
	}

	// We will be using various ones. this is just for testing purposes.
	Classifier classifier = null;

	public PredictionClassifier() {
		this.classifier = getClassifier();
					}

	private Classifier getClassifier() {
		Classifier classifier = new NaiveBayes();
		try {
			// load labeled data
			Instances train = new Instances(new BufferedReader(new FileReader(
					"data/labeled_forecasts.arff")));

			// set class attribute
			train.setClassIndex(train.numAttributes() - 1);

			classifier.buildClassifier(train);
			return classifier;
		} catch (IOException e) {
			System.err.println("Error when loading training set.");
		} catch (Exception e) {
			System.err.println("Could not train ForecastClassifier.");
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
	public void createPredictionRating() {
		try {
			// load unlabeled data
			Instances unlabeled = new Instances(new BufferedReader(
					new FileReader("data/unlabeled_forecasts.arff")));
			unlabeled.setClassIndex(unlabeled.numAttributes() - 1);

			// create copy
			Instances labeled = new Instances(unlabeled);

			// label instances
			for (int i = 0; i < unlabeled.numInstances(); i++) {
				// if clsLabel is 0: no
				// if clsLabel is 1: yes
				double clsLabel = classifier.classifyInstance(unlabeled
						.instance(i));
				labeled.instance(i).setClassValue(clsLabel);

				// only get rating if forecast was accepted
				if (isAccepted(clsLabel)) {
					// Get likelihood for decision
					double[] distribution = classifier
							.distributionForInstance(labeled.instance(i));
					Arrays.sort(distribution);
					// estimated likelihood that user accepts forecast
					double likelihood = distribution[1];
					System.out.println("likelihood: " + likelihood);
					System.out.println(labeled.instance(i));
				}

			}

		} catch (Exception e) {
			System.err.println("Could not classify.");
		}

	}

	/**
	 * 
	 * @param clsLabel
	 * @return true if forecast was accepted (label yes <-> 1)
	 */
	private boolean isAccepted(double clsLabel) {
		if (clsLabel > 0.99) {
			return true;
		}
		return false;
	}
}
