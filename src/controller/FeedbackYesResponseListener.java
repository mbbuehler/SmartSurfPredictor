package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Notifier;
import model.PlainPrediction;
import model.PredictionStatus;
import view.FeedbackView;
import view.PopUpView;

public class FeedbackYesResponseListener implements ActionListener 
{
	private FeedbackView feedbackView=null; 
	private Notifier model = null;
	private PopUpView popView = null;
	private PlainPrediction p = null;
	//listener for Yes button in FeedbackView and gets the next forecast 

	public FeedbackYesResponseListener(FeedbackView v, Notifier m) 
	{
		this.feedbackView = v;
		this.model = m;
	}
	
	public FeedbackYesResponseListener(PopUpView v, Notifier m, PlainPrediction p) 
	{
		this.popView = v;
		this.model = m;
		this.p=p;
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		//if user doesnt like the forecast, its rejected, and gets the next forecast
		if(popView == null)
		{
			feedbackView.nextPredictionFeedback(PredictionStatus.ACCEPTED);
		}
		else
		{	
			p.setStatus(PredictionStatus.ACCEPTED);
			p.setHasBeenRated(true);
			//When user has given feedback, disable the button
			popView.getLikePrediction().setText("Thank you for your feedback.");
			popView.getNoButton().setVisible(false);
			popView.getYesButton().setVisible(false);
			popView.nextPrediction(p);
		}
	}

}
