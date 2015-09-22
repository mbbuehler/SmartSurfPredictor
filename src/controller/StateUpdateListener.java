package controller;

import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Notifier;
import view.AddSpotView;
import view.PredictorView;

public class StateUpdateListener implements ListSelectionListener 
{
	private PredictorView view;
	private Notifier model;
	private AddSpotView spotView;

	//listener class for State JList in AddSpotView, to check user selection
	
	public StateUpdateListener(PredictorView v, Notifier m, AddSpotView s) 
	{
		this.view = v;
		this.model = m;
		this.spotView = s;
	}

	public void valueChanged(ListSelectionEvent e) 
	{
		//code bellow checks if user has finished selecting state
		//get selected locations
		if(!e.getValueIsAdjusting())
		{
			// gets values from your jList and added it to a list
			List<String> values = spotView.getStateJList().getSelectedValuesList();
			spotView.setSelectedState(values);
			
			//based on country & state selection, locations needs to be updated
			spotView.updateSurfLocations(spotView.getSelectedCountry(),spotView.getSelectedState());
		}
	}

}
