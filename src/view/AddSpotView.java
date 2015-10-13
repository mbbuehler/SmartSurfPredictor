package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

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
	
	//created to hold JList elements before JList intilised 
	private DefaultListModel stateModel = new DefaultListModel();
	private DefaultListModel locationModel = new DefaultListModel();
	
	private JLabel selectLabel,countryLabel,stateLabel,spotLabel;
	
	private JList<String> countryJList;
	private JList<String> stateJList= new JList<String>(stateModel);
	private JList<String> locationJList= new JList<String>(locationModel);
	
	private JPanel controlPanel, infoPanel,footerPanel;
	
	private List<String> selectedCountry = new ArrayList<String>();
	private List<String> selectedState = new ArrayList<String>();
	private List<String> selectedLocations = new ArrayList<String>();
	
	private JScrollPane countryScroll,stateScroll,locationScroll;
	
	private ArrayList<Spot> spotsSelected,favSpot;
	
	private JButton submitButton = new JButton("Submit");
	private JButton cancel = new JButton("Back");

	
	public AddSpotView(PredictorView v, Notifier m) 
	{
		this.view = v;
		this.model = m;
		//gets spot list file reader class
		this.allSpots = m.getSpot().getSpotsList();
		
		Container cp = this.getContentPane();
	    cp.setLayout(new BorderLayout());
	    
	    //gets country,state,location info for JList.
	    //set number of row to be visible & scroll panel. add listener for each JList
	    countryList();
	    countryJList.setVisibleRowCount(3);
	    countryScroll = new JScrollPane(countryJList);
	    //by default set to Australia, to make it easy for users,creating listener for country JList
	    //In the bigger picture, we would use GPS location to detect country
	    countryJList.setSelectedIndex(0);
	    selectedCountry.add(countryJList.getSelectedValue().toString());
	    countryJList.addListSelectionListener(new CountryUpdateListener(view,model,this));
	   
	    UpdateStates(selectedCountry);
	    stateJList.setVisibleRowCount(3);
	    stateScroll = new JScrollPane(stateJList);
	    stateJList.addListSelectionListener(new StateUpdateListener(view,model,this));
	    
	    updateSurfLocations(selectedCountry,selectedState);
	    locationJList.setVisibleRowCount(5);
	    locationScroll = new JScrollPane(locationJList);
	    locationJList.addListSelectionListener(new LocationUpdateListener(view,model,this));
	    
	    //pre-selected Spot
	    preSelectedSpot();
	    
	    //listeners for buttons
	    submitButton.addActionListener(new AddSurfSpotListener(v, model, this));
	    cancel.addActionListener(new ExitListener(this,model));
	    
	    //adding panel
	    intialiseJLablesPanels();
	    cp.add(controlPanel);
	    
	    // set the window size by itself
	    pack();
	    setLocationRelativeTo(view);
	    setModal(true);
	}
	
	//checks if user has previously selected spots
	private void preSelectedSpot() 
	{
		//check for pre-selections
		model.getFavSpot().countrySelectionIndex();
		
		//updated states based on user preselection of countries 
		UpdateStates(model.getFavSpot().getSelectedCountryNames());
		model.getFavSpot().stateSelectionIndex();
		
		//updated locations based on user preselection of countries & states
		updateSurfLocations(model.getFavSpot().getSelectedCountryNames(), model.getFavSpot().getSelectedStateNames());
		model.getFavSpot().LocationSelection();
		
		//preselect country, state, location J-List
		countryJList.setSelectedIndices(model.getFavSpot().getSelectedCountry());
		stateJList.setSelectedIndices(model.getFavSpot().getSelectedState());
		locationJList.setSelectedIndices(model.getFavSpot().getSelectedLocation());
	}

	//find all matching locations based on country & states selected
	public void updateSurfLocations(List<String> selectedCountries ,List<String> selectedStates) 
	{
		//remove old items from default model
		locationModel.removeAllElements();
		
		//intialise location list
		model.getSpot().initialiseLocationList(selectedCountries,selectedStates);
		
		spotsSelected = model.getSpot().getAllPossibleSelectionSpots();
		
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
		model.getSpot().initialiseStateList(selectedCountry);
				    
	    //set new values   	
	    stateModel = model.getSpot().getStateModel();
	    
		//intialise JList
		stateJList.setModel(stateModel);
	}

	//add countries to JList
	public void countryList()
	{
		//intialise country list
		model.getSpot().initialiseCountryList();
		
		//intialise JList
	    countryJList = new JList(model.getSpot().getCountryModel());
	}
	
	//find users favorite surf spots
	public ArrayList<Spot> findUsersfavouriteSpots()
	{
		favSpot = model.getFavSpot().finUsersFavouriteSpots(spotsSelected, selectedCountry, selectedState, selectedLocations);
		return favSpot;
	}
	
	
	public ArrayList<Spot> getSpots() 
	{
		return allSpots;
	}


	public JList getCountryJList() 
	{
		return countryJList;
	}

	public JList getStateJList() 
	{
		return stateJList;
	}
	
	public void setStateJList(JList<String> stateJList) 
	{
		this.stateJList = stateJList;
	}

	public JList<String> getLocationJList() 
	{
		return locationJList;
	}

	public void setLocationJList(JList<String> locationJList) 
	{
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

	public List<String> getSelectedLocations() 
	{
		return selectedLocations;
	}

	public void setSelectedLocations(List<String> selectedLocations) 
	{
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
	private void intialiseJLablesPanels() 
	{
		controlPanel = new JPanel(new GridLayout(4,1,20,20));
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
	    // adding button
	    infoPanel.add(submitButton);
	    infoPanel.add(cancel);
	    
	    //footer
	    footerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	  	footerPanel.add(new JLabel("Acknowledgement: Data kindly provided by Magicseaweed."));
	  	    
	    //adding all major JPanels to Control Panel
	    controlPanel.add(selectLabel);
	    controlPanel.add(infoPanel);
	    controlPanel.add(footerPanel);
	}




}
