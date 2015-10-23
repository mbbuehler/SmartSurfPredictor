package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Notifier;
import model.PlainPrediction;
import model.PredictionStatus;
import view.FeedbackView;
import view.PopUpView;
import view.PredictorView;

public class FeedbackNoResponseListener implements ActionListener 
{
	private FeedbackView feedbackView = null;
	private PopUpView popView = null;
	private Notifier model=null;
	private PlainPrediction p=null;
	private PredictorView predictorView = null; 
	
	//listener for No button in FeedbackView and gets the next forecast 
	public FeedbackNoResponseListener(PredictorView view,FeedbackView v, Notifier m) 
	{
		this.predictorView=view;
		this.feedbackView = v;
		this.model = m;
	}
	
	public FeedbackNoResponseListener(PopUpView v, Notifier m,PlainPrediction p) 
	{
		this.popView = v;
		this.model = m;
		this.p=p;
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		//if user doesn't like the forecast, its rejected, and gets the next forecast
		if(popView == null)
		{
			feedbackView.nextPredictionFeedback(PredictionStatus.REJECTED);
		
			//when user has completed the app setup up for the first time
			//check if user has completed setup
			//if yes - change boolean to false
			if(predictorView.isStartSetupDone() == true)
			{
				predictorView.setStartSetupDone(false);
			}
		}
		else
		{
			p.setStatus(PredictionStatus.REJECTED);
			p.setHasBeenRated(true);
			//When user has given feedback, disable the button
			popView.getLikePrediction().setText("Thank you for your feedback.");
			popView.getNoButton().setVisible(false);
			popView.getYesButton().setVisible(false);
			popView.nextPrediction(p);
		}
		
		
		
	}

}
