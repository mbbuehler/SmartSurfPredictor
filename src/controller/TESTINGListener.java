package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Notifier;
import model.PredictionTime;
import view.FeedbackView;
import view.PopUpView;
import view.PredictorView;

//Class created by Eeswari
public class TESTINGListener implements ActionListener 
{
	private PredictorView view; 
	private Notifier model;
	private PredictionTime time;
	
	//testing
	
	public TESTINGListener(PredictorView v, Notifier m,PredictionTime time) 
	{
		this.view = v;
		this.model = m;
		this.time=time;
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		//open feedback view
		PopUpView p = new PopUpView(view,model,time);
		p.setUndecorated(true);
		p.setVisible(true);
	}

}
