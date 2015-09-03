package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

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
		//Check if all the fields have been selected
		//return true(empty), if users didnt select
		
		//check if country selected
		if(spotView.getSelectedCountry().isEmpty())
		{
			JOptionPane.showMessageDialog(view, " Country not selected !",
		               "Select Country", JOptionPane.ERROR_MESSAGE);
		         return;
		}
		
		//check is state selected
		if(spotView.getSelectedState().isEmpty())
		{
			JOptionPane.showMessageDialog(view, " State not selected !",
		               "Select State", JOptionPane.ERROR_MESSAGE);
		         return;
		}
		
		//check if location selected
		if(spotView.getSelectedLocations().isEmpty())
		{
			JOptionPane.showMessageDialog(view, " Surf Location not selected !",
		               "Select Surf Location", JOptionPane.ERROR_MESSAGE);
		         return;
		}
		
		//write favourite surf details to file
		model.getFavSpot().WriteToFavFile(spotView.FavouriteSpots());
		
		spotView.setVisible(false);
		spotView.dispose();
	}

}
