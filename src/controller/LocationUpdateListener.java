package controller;

import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Notifier;
import view.AddSpotView;
import view.PredictorView;

public class LocationUpdateListener implements ListSelectionListener 
{
	private PredictorView view;
	private Notifier model;
	private AddSpotView spotView;
	
	//listener for Location Jlist selection in AddSpotView
	
	public LocationUpdateListener(PredictorView v, Notifier m, AddSpotView a) 
	{
		this.view = v;
		this.model = m;
		this.spotView = a;
	}

	public void valueChanged(ListSelectionEvent e) 
	{
		//code bellow checks if user has finished selecting locations
		//get selected countries
		if(!e.getValueIsAdjusting())
		{
			// gets values from jList and added it 
			List<String> values = spotView.getLocationJList().getSelectedValuesList();
			spotView.setSelectedLocations(values);
		}

	}

}
