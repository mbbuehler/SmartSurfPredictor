package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import model.Notifier;
import model.PlainPrediction;
import model.Spot;
import view.PopUpView;

public class JLabelListener implements MouseListener 
{
	private PopUpView popUpView; 
	private Notifier model; 
	private PlainPrediction prediction;
	

	public JLabelListener(PopUpView p, Notifier m, PlainPrediction plainPrediction) 
	{
		this.popUpView = p;
		this.model = m;
		this.prediction = plainPrediction;
	}

	public void mouseClicked(MouseEvent arg0) 
	{
		popUpView.updateLabels(prediction);
	}

	public void mouseEntered(MouseEvent arg0) 
	{}

	public void mouseExited(MouseEvent arg0) 
	{}

	public void mousePressed(MouseEvent arg0) 
	{}

	public void mouseReleased(MouseEvent arg0) 
	{}

}
