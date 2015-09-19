package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Notifier;
import view.FeedbackView;
import view.PredictorView;

//Class created by Eeswari
public class MainForecastButtonListener implements ActionListener 
{
	private PredictorView view; 
	private Notifier model;
	
	public MainForecastButtonListener(PredictorView v, Notifier m) 
	{
		this.view = v;
		this.model = m;
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		new FeedbackView(view,model).setVisible(true);
	}

}
