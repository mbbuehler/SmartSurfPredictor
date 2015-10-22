package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import util.SSPPaths;
import weka.classifiers.Classifier;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;

	/**
 * Classifier for surf forecasts from magicseaweed.
 * 
 * @author marcello
 * 
 */
public class PredictionClassifier {
	// Classifier will not perform well without at least 20 training examples.
	public final static int minNumberOfTrainingInst = 20;

	private static String trainingSet = SSPPaths.userDir + "/"
			+ SSPPaths.trainingSetFileName;
	private static String unlabeledPath = SSPPaths.userDir + "/"
			+ SSPPaths.tmpUnlabeledPredictionFileName;

	// Directly initialize classifier at instantiation. No need to create a
	// constructor for this static class.
	private static Classifier classifier = createClassifier();

	/**
	 * Creates a classifier from a training set (for path see instance variable
	 * "trainingSet".<br>
	 * Modify first line of this method to use a different classifier. Only use
	 * classifiers that can output the distribution for its results.<br>
	 * The last attribute of the training set will be set as output class.
	 * 
	 * @return trained Classifier
	 */
	private static Classifier createClassifier() {
		/*
		 * You could also use new NaiveBayes() new BayesianLogisticRegression()
		 * or others. MultilayerPerceptron performed best in tests.
		 */
		Classifier classifier = new MultilayerPerceptron();
		try {
			// load labeled data
			Instances train = new Instances(new BufferedReader(new FileReader(
					new File((trainingSet)))));

			if (train.numInstances() < minNumberOfTrainingInst) {
				// Show a popup to user and ask him friendly to create more
				// training examples. With too few training examples, the
				// classifier will not perform well.
				throw new TrainingSetTooSmallException();
			}
			// set output class attribute ("yes" / "no")
			train.setClassIndex(train.numAttributes() - 1);
			classifier.buildClassifier(train);
			return classifier;
		} catch (TrainingSetTooSmallException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			// will be called when training set file does not exist (e.g. if the
			// user runs classifier before even creating a training set.)
			String msg = "@PredictionClassifier: Error when loading training set. \nDoes this file exist: "
					+ System.getProperty("user.dir")
					+ "/"
					+ SSPPaths.userDir
					+ "/"
					+ SSPPaths.trainingSetFileName
					+ "? \nPlease rate at least 20 forecasts before running notifier.";
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, msg, "Classifier Exception",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		} catch (Exception e) {
			// An unforeseen error has occurred.
			System.err
					.println("@PredictionClassifier: Could not train ForecastClassifier.");
			e.printStackTrace();
			System.exit(0);
		}
		return null;
	}

	/**
	 * Loads unlabeled forecasts, classifis instances and returns score for each
	 * instance.<br>
	 * Some code copied from weka tutorial:
	 * https://weka.wikispaces.com/Use+WEKA+in+your+Java+code
	 * #Instances-ARFF%20File (30.8.15)
	 * 
	 * @return ArrayList<Float> with scores for all classified instances. Scores
	 *         range from -1 to 1. The higher the score, the higher the
	 *         likelihood that a user likes a forecast.
	 */
	public static ArrayList<Float> ratePredictions() {
		ArrayList<Float> scores = new ArrayList<Float>();
		try {
			// load unlabeled data and set output class
			Instances unlabeled = new Instances(new BufferedReader(
					new FileReader(unlabeledPath)));
			unlabeled.setClassIndex(unlabeled.numAttributes() - 1);

			// create variable for classified instances
			Instances labeled = new Instances(unlabeled);

			for (int i = 0; i < unlabeled.numInstances(); i++) {
				// label all instances
				double clsLabel = classifier.classifyInstance(unlabeled
						.instance(i));
				labeled.instance(i).setClassValue(clsLabel);

				// extract confidence for assigned class
				double[] distribution = classifier
						.distributionForInstance(labeled.instance(i));
				Arrays.sort(distribution);
				float score = (float) distribution[1];
				// reverse score if output class is "no":
				if (clsLabel == 1) {
					score = -1 * score;
				}
				scores.add(score);
			}
			return scores;
		} catch (Exception e) {
			System.err
					.println("Could not classify. Did you already create a trainingSet?");
			e.printStackTrace();
			System.exit(-1);
			return null;
		}
	}
}