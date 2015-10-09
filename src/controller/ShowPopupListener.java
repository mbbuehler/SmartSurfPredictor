package controller;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import model.Notifier;
import model.PredictionTime;

import view.PopUpView;
import view.PredictorView;

public class ShowPopupListener implements ActionListener {
	private PredictorView view;
	private Notifier model;

	public ShowPopupListener(PredictorView v, Notifier m) {
		this.view = v;
		this.model = m;
	}
	public void actionPerformed(ActionEvent e) 
	{
		Date dt = new Date();
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(dt);
		int hours = cal.get(Calendar.HOUR);
		// System.out.println("it is " + hours + " o'clock.");
		PredictionTime time = (hours <= 12) ? PredictionTime.MORNING
				: PredictionTime.AFTERNOON;
		new PopUpView(view, model, time).setVisible(true);
		
	}


}
