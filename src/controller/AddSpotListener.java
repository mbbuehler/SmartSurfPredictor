package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Notifier;
import view.AddSpotView;
import view.PredictorView;

public class AddSpotListener implements ActionListener
{
	private PredictorView view; 
	private Notifier model;
	
	//button listener for main page-PredictorView
	public AddSpotListener(PredictorView v, Notifier m) 
	{
		this.view = v;
		this.model = m;
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		new AddSpotView(view,model).setVisible(true);
	}

}
