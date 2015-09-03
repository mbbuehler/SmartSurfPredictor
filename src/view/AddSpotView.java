package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.AddSurfSpotListener;
import controller.CountryUpdateListener;
import controller.ExitListener;
import controller.LocationUpdateListener;
import controller.StateUpdateListener;
import model.Notifier;
import model.Spot;

public class AddSpotView extends JDialog
{
	
	private PredictorView view; 
	private Notifier model;
	private ArrayList<Spot> allSpots;
	private ArrayList<String> countryArrayList,stateArrayList,locationArrayList;
	
	//created to hold JList elements before JList intilised 
	private DefaultListModel countryModel = new DefaultListModel();
	private DefaultListModel stateModel = new DefaultListModel();
	private DefaultListModel locationModel = new DefaultListModel();
	
	private JLabel selectLabel,countryLabel,stateLabel,spotLabel;
	
	private JList<String> countryJList = new JList<String>(countryModel);
	private JList<String> stateJList= new JList<String>(stateModel);
	private JList<String> locationJList= new JList<String>(locationModel);
	
	private JPanel controlPanel, infoPanel;
	
	private List<String> selectedCountry = new ArrayList<String>();
	private List<String> selectedState = new ArrayList<String>();
	private List<String> selectedLocations = new ArrayList<String>();
	
	private JScrollPane countryScroll,stateScroll,locationScroll;
	
	private ArrayList<Spot> spotsSelected,favSpot;
	
	private JButton submitButton = new JButton("Submit");
	private JButton cancel = new JButton("CANCEL");

	
	//constructor
	public AddSpotView(PredictorView v, Notifier m) 
	{
		this.view = v;
		this.model = m;
		//gets arraylist from spot file reader class
		this.allSpots = m.getSpot().getSpotsList();
		
		Container cp = this.getContentPane();
	    cp.setLayout(new BorderLayout());
	    
	    //need to loop thru arraylist to add country
	    countryList();
	    //set number of row to be visible & scroll panel
	    countryJList.setVisibleRowCount(3);
	    countryScroll = new JScrollPane(countryJList);
	   
	    //by default set to Australia, to make it easy for users,
	    //In the bigger picture, we would use GPS location to detect country
	    countryJList.setSelectedIndex(0);
	    selectedCountry.add(countryJList.getSelectedValue().toString());
	    //creating listener for country JList
	    countryJList.addListSelectionListener(new CountryUpdateListener(view,model,this));
	    
	    //need to loop thru arraylist to add australian states
	    UpdateStates(selectedCountry);
	    //set number of row to be visible & scroll panel
	    stateJList.setVisibleRowCount(3);
	    stateScroll = new JScrollPane(stateJList);
	    //add selection listener for state JList based on country selection 
	    stateJList.addListSelectionListener(new StateUpdateListener(view,model,this));
	    
	    //set locations based on countroes & states selected
	    UpdateSurfLocations(selectedCountry,selectedState);
	    //set number of row to be visible & scroll panel
	    locationJList.setVisibleRowCount(5);
	    locationScroll = new JScrollPane(locationJList);
	    //add selection listener for state JList based on country selection 
	    locationJList.addListSelectionListener(new LocationUpdateListener(view,model,this));
	    
	    //pre-selected Spot
	    PreSelectedSpot();
	    
	    
	    //add listeners for Submit & Cancel button button
	    submitButton.addActionListener(new AddSurfSpotListener(v, model, this));
	    cancel.addActionListener(new ExitListener(this,model));
	    
	    
	    
	    //adding panel
	    IntialiseJLablesPanels();
	    cp.add(controlPanel);
	    
	    // set the window size by itself
	    pack();
	    setLocationRelativeTo(view);
	    setModal(true);
	}
	
	//checks if user has previous selected spots
	private void PreSelectedSpot() 
	{
		//check for pre-selections
		model.getFavSpot().CountrySelection();
		
		//updated states based on user preselection of countries 
		UpdateStates(model.getFavSpot().getSelectedCountryNames());
		model.getFavSpot().StateSelection();
		
		//updated locations based on user preselection of countries & states
		UpdateSurfLocations(model.getFavSpot().getSelectedCountryNames(), model.getFavSpot().getSelectedStateNames());
		model.getFavSpot().LocationSelection();
		
		//preselect country, state, location J-List
		countryJList.setSelectedIndices(model.getFavSpot().getSelectedCountry());
		stateJList.setSelectedIndices(model.getFavSpot().getSelectedState());
		locationJList.setSelectedIndices(model.getFavSpot().getSelectedLocation());
	}

	public void UpdateSurfLocations(List<String> selectedCountries ,List<String> selectedStates) 
	{
		//remove old items from default model
		locationModel.removeAllElements();
		
		//intialise location list
		model.getSpot().InitialiseLocationList(selectedCountries,selectedStates);
		
		locationArrayList = model.getSpot().getLocation();
		spotsSelected = model.getSpot().getPossibleSelectionSpots();
		
		//set new values   	
	    locationModel = model.getSpot().getLocationModel();
		
		//set JList
		locationJList.setModel(locationModel);
	}

	//find all matching states based on country selected
	public void UpdateStates(List<String> selectedCountry) 
	{	
		//remove old items from default list
	    stateModel.removeAllElements();

		//intialise country list
		model.getSpot().InitialiseStateList(selectedCountry);
		
		//add each state only once to list
		stateArrayList = model.getSpot().getState();
			    
	    //set new values   	
	    stateModel = model.getSpot().getStateModel();
	    
		//intialise JList
		stateJList.setModel(stateModel);
	}

	//need to loop thru arraylist to add each country only once
	public void countryList()
	{
		//intialise country list
		model.getSpot().InitialiseCountryList();
		
		//when unique region found, added once to countryLIst (arrayList)
	    countryArrayList = model.getSpot().getCountry();
	 
	    // holds the items and the JList
	    countryModel = model.getSpot().getCountryModel();
	    
	    //intialise JList
	    countryJList = new JList(model.getSpot().getCountryModel());
	}
	
	//find users favourite surf spots
	public ArrayList<Spot> FavouriteSpots()
	{
		favSpot = model.getFavSpot().FavouriteSpots(spotsSelected, selectedCountry, selectedState, selectedLocations);
		return favSpot;
	}
	
	
	public ArrayList<Spot> getSpots() 
	{
		return allSpots;
	}

	public ArrayList<String> getCountryList() 
	{
		return countryArrayList;
	}

	public JList getCountryJList() 
	{
		return countryJList;
	}

	public JList getStateJList() 
	{
		return stateJList;
	}
	
	public void setStateJList(JList<String> stateJList) {
		this.stateJList = stateJList;
	}

	public JList<String> getLocationJList() {
		return locationJList;
	}

	public void setLocationJList(JList<String> locationJList) {
		this.locationJList = locationJList;
	}

	public List<String> getSelectedState() 
	{
		return selectedState;
	}

	public void setSelectedState(List<String> selectedState) 
	{
		this.selectedState = selectedState;
	}

	public List<String> getSelectedLocations() {
		return selectedLocations;
	}

	public void setSelectedLocations(List<String> selectedLocations) {
		this.selectedLocations = selectedLocations;
	}

	public List<String> getSelectedCountry() 
	{
		return selectedCountry;
	}

	public void setSelectedCountry(List<String> selectedCountry) 
	{
		this.selectedCountry = selectedCountry;
	}
	
	public JPanel getInfoPanel() 
	{
		return infoPanel;
	}
	

	public JLabel getStateLabel() 
	{
		return stateLabel;
	}

	public JLabel getSpotLabel() 
	{
		return spotLabel;
	}
	
	//method intilalise JPanels & JLabels
	private void IntialiseJLablesPanels() 
	{
		controlPanel = new JPanel(new GridLayout(3,1,20,20));
	    
	    // info panel
	    infoPanel = new JPanel();
	    infoPanel.setLayout(new GridLayout(4,2,10,10));
		
		selectLabel = new JLabel("Select/Change Your Favourite Surf Spots");
	    selectLabel.setAlignmentX(CENTER_ALIGNMENT);
	    	    
	    //intialise JLabels and set alignment
	    countryLabel = new JLabel("Select Country:");
	    countryLabel.setAlignmentX(CENTER_ALIGNMENT);
	    stateLabel = new JLabel("Select State:");
	    stateLabel.setAlignmentX(CENTER_ALIGNMENT);
	    spotLabel = new JLabel("Select Surf Location:");
	    spotLabel.setAlignmentX(CENTER_ALIGNMENT);
	    
	    infoPanel.add(countryLabel);
	    infoPanel.add(countryScroll);
	    infoPanel.add(stateLabel);
	    infoPanel.add(stateScroll);
	    infoPanel.add(spotLabel);
	    infoPanel.add(locationScroll);
	    // adding submit & cancel button
	    infoPanel.add(submitButton);
	    infoPanel.add(cancel);
	    
	    //adding other JPanels to Control Panel
	    controlPanel.add(selectLabel);
	    controlPanel.add(infoPanel);
		
	}




}
