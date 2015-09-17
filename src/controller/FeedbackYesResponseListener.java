package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Notifier;
import model.PredictionStatus;
import model.PredictionTime;
import view.FeedbackView;

public class FeedbackYesResponseListener implements ActionListener 
{
	private FeedbackView view; 
	private Notifier model;

	public FeedbackYesResponseListener(FeedbackView v, Notifier m) 
	{
		this.view = v;
		this.model = m;
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		view.NextPredictionFeedback(PredictionStatus.ACCEPTED);
	}

}
