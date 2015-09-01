package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Notifier;
import view.AddSpotView;
import view.PredictorView;

public class AddSurfSpotListener implements ActionListener 
{
	private PredictorView view;
	private Notifier model;
	private AddSpotView spotView;
	
	public AddSurfSpotListener(PredictorView v, Notifier m, AddSpotView s) 
	{
		this.view = v;
		this.model = m;
		this.spotView = s;
	}

	public void actionPerformed(ActionEvent e) 
	{
	
	}

}
