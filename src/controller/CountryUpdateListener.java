package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Notifier;
import view.AddSpotView;
import view.PredictorView;

public class CountryUpdateListener implements ListSelectionListener 
{
	private PredictorView view;
	private Notifier model;
	private AddSpotView spotView;
	
	//Listener class for country Jlist in AddSpotView
	
	public CountryUpdateListener(PredictorView v, Notifier m, AddSpotView s) 
	{
		this.view = v;
		this.model = m;
		this.spotView = s;
	}

	public void valueChanged(ListSelectionEvent e) 
	{
		//code bellow checks if user has finished selecting countries,get selected countries
		if(!e.getValueIsAdjusting())
		{
			// gets values from your jList and added it to a list
			List values = spotView.getCountryJList().getSelectedValuesList();
			spotView.setSelectedCountry(values);
			
			//based on country selection, state needs to be updated
			spotView.UpdateStates(spotView.getSelectedCountry());
		}

	}

}
