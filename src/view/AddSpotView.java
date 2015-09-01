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
	private ArrayList<String> countryArrayList,stateArrayList;
	
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
	
	private ArrayList<Spot> spotsSelected;
	
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
	    countryList(allSpots);
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
	    UpdateStates(allSpots,selectedCountry);
	    //set number of row to be visible & scroll panel
	    stateJList.setVisibleRowCount(3);
	    stateScroll = new JScrollPane(stateJList);
	    //add selection listener for state JList based on country selection 
	    stateJList.addListSelectionListener(new StateUpdateListener(view,model,this));
	    
	    //set locations based on countroes & states selected
	    UpdateSurfLocations(allSpots,selectedCountry,selectedState);
	    //set number of row to be visible & scroll panel
	    locationJList.setVisibleRowCount(5);
	    locationScroll = new JScrollPane(locationJList);
	    //add selection listener for state JList based on country selection 
	    locationJList.addListSelectionListener(new LocationUpdateListener(view,model,this));
	    
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
	
	public void UpdateSurfLocations(ArrayList<Spot> spots,List<String> selectedCountries ,List<String> selectedStates) 
	{
		//remove old items from default model
		locationModel.removeAllElements();
		
		spotsSelected = new ArrayList<Spot>();
		
		//find surf location based on countries & states selected
		for (Spot s : spots) 
	    { 		      
	    	String locationName = s.getName();
	    	
	    	//if country names match, add state once to list
	    	for(String c : selectedCountries )
	    	{
		    	if(s.getCountry().equals(c))
		    	{	
			    	for(String n : selectedStates)
			    	{
			    		//based on country & state, select location
			    		if(s.getState().equals(n))
			    		{
			    			//add to spot arrayList
			    			spotsSelected.add(s);
			    			locationModel.addElement(locationName);
			    		}
			    	}
			    	   	
		    	}
	    	}
	    }
		
		//set JList
		locationJList.setModel(locationModel);
	}

	

	//find all matching states based on country selected
	public void UpdateStates(ArrayList<Spot> spots,List<String> selectedCountry) 
	{
		//add each state only once to list
	    stateArrayList = new ArrayList<String>();
	    
	    //remove old items from default list
	    stateModel.removeAllElements();
	    	    	
	    //find states based on country
		for (Spot s : spots) 
	    { 		      
	    	String stateName = s.getState();
	    	//only adding each state name once to arraylist
	    	boolean stateMatchFound = false;
	    	
	    	//if country names match, add state once to list
	    	for(String c : selectedCountry )
	    	{
		    	if(s.getCountry().equals(c))
		    	{	
			    	for(String n : stateArrayList)
			    	{
			    		if(n.equals(stateName))
			    		{
			    			stateMatchFound = true;
			    			break;
			    		}
			    	}
			    	
			    	//If boolean is false, state is unique, add it to list
			    	//adds state to Combo box
			    	if (stateMatchFound == false)
			    	{
			    		stateArrayList.add(stateName);
			    		stateModel.addElement(stateName);
			    	}
		    	}
	    	}
	    }
		
		//intialise JList
		stateJList.setModel(stateModel);
	}

	//need to loop thru arraylist to add each country only once
	public void countryList(ArrayList<Spot> spots)
	{
		//when unique region found, added once to countryLIst (arrayList)
	    countryArrayList = new ArrayList<String>();
	    
	    // holds the items and the JList
	    countryModel = new DefaultListModel();
	    
	    for (Spot s : spots) 
	    { 		      
	    	String countryName = s.getCountry();
	    	//only adding each country name once to arraylist
	    	boolean countryMatchFound = false;
	    	for(String n : countryArrayList)
	    	{
	    		if(n.equals(countryName))
	    		{
	    			countryMatchFound = true;
	    			break;
	    		}
	    	}
	    	//If boolean is false, country is unique, add it to list
	    	//adds country to countryCombo box
	    	if (countryMatchFound == false)
	    	{
	    		countryArrayList.add(countryName);
	    		countryModel.addElement(countryName);
	    	}
	    }
	    
	    //intialise JList
	    countryJList = new JList(countryModel);
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
