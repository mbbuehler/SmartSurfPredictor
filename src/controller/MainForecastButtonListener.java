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
	
	//listener for forecast button in Main Page, PredictorView
	
	public MainForecastButtonListener(PredictorView v, Notifier m) 
	{
		this.view = v;
		this.model = m;
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		//open feedback view
		new FeedbackView(view,model).setVisible(true);
	}

}
