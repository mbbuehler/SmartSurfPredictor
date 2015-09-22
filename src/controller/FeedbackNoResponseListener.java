package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Notifier;
import model.PlainPrediction;
import model.PredictionStatus;
import view.FeedbackView;
import view.PopUpView;

public class FeedbackNoResponseListener implements ActionListener 
{
	private FeedbackView feedbackView = null;
	private PopUpView popView = null;
	private Notifier model=null;
	private PlainPrediction p=null;
	//listener for No button in FeedbackView and gets the next forecast 
	
	public FeedbackNoResponseListener(FeedbackView v, Notifier m) 
	{
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
		//if user doesnt like the forecast, its rejected, and gets the next forecast
		if(popView == null)
		{
			feedbackView.nextPredictionFeedback(PredictionStatus.REJECTED);
		}
		else
		{
			p.setStatus(PredictionStatus.REJECTED);
			popView.nextPrediction(p);
		}
	}

}
