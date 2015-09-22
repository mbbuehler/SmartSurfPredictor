package model;

import java.io.BufferedReader;
import util.SSPPaths;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
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
	private static int minNumberOfTrainingInst = 20;
	private static String trainingSet = SSPPaths.userDir + "/"
			+ SSPPaths.trainingSetFileName;
	private static String unlabeledPath = SSPPaths.userDir + "/"
			+ SSPPaths.tmpUnlabeledPredictionFileName;

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
					new File((trainingSet)))));

			if (train.numInstances() < minNumberOfTrainingInst) {
				System.out.println("Please use at least "
						+ minNumberOfTrainingInst
						+ " before training Classifier.");
				throw new TrainingSetTooSmallException();
			}
			// set class attribute
			train.setClassIndex(train.numAttributes() - 1);

			classifier.buildClassifier(train);
			return classifier;
		} catch (TrainingSetTooSmallException e) {
			System.out.println(e.getMessage());
			System.exit(0);
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

			// System.out.println("unlabeled isntances: "
			// + unlabeled.numInstances());

			// label all instances
			for (int i = 0; i < unlabeled.numInstances(); i++) {
				// System.out.println("checkpoint 1");
				// if clsLabel is 0: no
				// if clsLabel is 1: yes
				// System.out.println(unlabeled.instance(i));
				double clsLabel = classifier.classifyInstance(unlabeled
						.instance(i));
				//
				// System.out.println(clsLabel);
				labeled.instance(i).setClassValue(clsLabel);

				// System.out.println("checkpoint 2");
				// Get likelihood for decision
				double[] distribution = classifier
						.distributionForInstance(labeled.instance(i));
				Arrays.sort(distribution);

				// System.out.println("checkpoint 3");
				// estimated likelihood that user accepts forecast
				float score = (float) distribution[1];

				// System.out.println("checkpoint 4");
				scores.add(score);

				// System.out.println("likelihood: " + likelihood);
				// System.out.println(labeled.instance(i));

			}
			return scores;

		} catch (Exception e) {
			System.err
					.println("Could not classify. Did you already create a trainingSet?");
			e.printStackTrace();
			return null;
		}

		}

	}