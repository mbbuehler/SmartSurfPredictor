package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Notifier;
import view.PredictorView;

public class FeedbackButtonListener implements ActionListener
{
	private PredictorView view; 
	private Notifier model;

	public FeedbackButtonListener(PredictorView v, Notifier m) 
	{
		this.view = v;
		this.model = m;
	}

	public void actionPerformed(ActionEvent e) 
	{
		//check if spots selected like program listener class
		
		//else display view
		
	}

}
