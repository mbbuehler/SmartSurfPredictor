package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Notifier;
import model.PredictionStatus;
import view.FeedbackView;

public class FeedbackNoResponseListener implements ActionListener 
{
	private FeedbackView view; 
	private Notifier model;

	public FeedbackNoResponseListener(FeedbackView v, Notifier m) 
	{
		this.view = v;
		this.model = m;
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		view.NextPredictionFeedback(PredictionStatus.REJECTED);

	}

}
