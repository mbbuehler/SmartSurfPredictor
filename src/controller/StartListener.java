package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Notifier;
import view.AddSpotView;
import view.PredictorView;

public class StartListener implements ActionListener
{
	private PredictorView view; 
	private Notifier model;
	
	//button listener for main page-PredictorView
	public StartListener(PredictorView v, Notifier m) 
	{
		this.view = v;
		this.model = m;
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		//when user start to setup, change setup boolean true 
		view.setStartSetupDone(true);
		new AddSpotView(view,model).setVisible(true);
	}

}
