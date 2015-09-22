package model;

public class TrainingSetTooSmallException extends Exception {

	public TrainingSetTooSmallException() {
		super("Training-Set too small. Please use more training examples.");

	}
}
