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

import javax.swing.JOptionPane;

import model.ForecastResponse.List;

import weka.classifiers.bayes.BayesianLogisticRegression;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.MultilayerPerceptron;
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
		Classifier classifier = new MultilayerPerceptron();// new
															// NaiveBayes();//
															// BayesianLogisticRegression();
		try {
			// load labeled data
			Instances train = new Instances(new BufferedReader(new FileReader(
					new File((trainingSet)))));

			if (train.numInstances() < minNumberOfTrainingInst) {
				// System.exit(0);
				throw new TrainingSetTooSmallException();
			}
			// set class attribute
			train.setClassIndex(train.numAttributes() - 1);

			classifier.buildClassifier(train);
			return classifier;
		} catch (TrainingSetTooSmallException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			String msg = "@PredictionClassifier: Error when loading training set. \nDoes this file exist: "
					+ System.getProperty("user.dir")
					+ "/"
					+ SSPPaths.userDir
					+ "/"
					+ SSPPaths.trainingSetFileName
					+ "? \nPlease rate at least 20 forecasts before running notifier.";
			// System.err.println(msg);
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, msg, "Classifier Exception",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		} catch (Exception e) {
			System.err
					.println("@PredictionClassifier: Could not train ForecastClassifier.");
			e.printStackTrace();
			System.exit(0);
			}
		return null;
	}

	/**
	 * Loads unlabeled forecasts and adds labels to it. prints result to
	 * console.<br>
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
				// if clsLabel is 0: yes
				// if clsLabel is 1: no
				// System.out.println(unlabeled.instance(i));
				double clsLabel = classifier.classifyInstance(unlabeled
						.instance(i));
				//
				labeled.instance(i).setClassValue(clsLabel);

				// System.out.println("checkpoint 2");
				// Get likelihood for decision
				double[] distribution = classifier
						.distributionForInstance(labeled.instance(i));
				Arrays.sort(distribution);

				// System.out.println();
				// System.out.println("label: " + clsLabel);
				// System.out.print("distribution: ");
				// for (double d : distribution)
				// System.out.print(d + " ");
				// System.out.print(" for " + labeled.instance(i).toString());
				// System.out.println("checkpoint 3");
				// estimated likelihood that user accepts forecast
				float score = (float) distribution[1];

				// flag score if no:
				if (clsLabel == 1) {
					score = -1 * score;
				}

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

	public static void setTrainingSet(String trainingSet) {
		PredictionClassifier.trainingSet = trainingSet;
	}

	public static void setUnlabeledPath(String unlabeledPath) {
		PredictionClassifier.unlabeledPath = unlabeledPath;
		}

		}