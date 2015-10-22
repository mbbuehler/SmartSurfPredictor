package model;

import javax.swing.JOptionPane;

public class TrainingSetTooSmallException extends Exception {

	public TrainingSetTooSmallException() {
		super("Training-Set too small. Please use more training examples.");
		JOptionPane
				.showMessageDialog(
						null,
						"Not enough training examples. Please use at least "
								+ 20
								+ " before training Classifier. Please restart the application.",
						"Not enough training data", JOptionPane.ERROR_MESSAGE);
		System.exit(0);

	}
}
