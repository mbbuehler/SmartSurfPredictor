package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.CountryUpdateListener;
import controller.StateUpdateListener;
import model.Notifier;
import model.Spot;

public class AddSpotView extends JDialog
{
	
	private PredictorView view; 
	private Notifier model;
	private ArrayList<Spot> spots;
	private ArrayList<String> countryList,stateList;
	
	//created to hold JList elements before JList intilised 
	private DefaultListModel countrylistModel = new DefaultListModel();
	private DefaultListModel statelistModel = new DefaultListModel();;
	
	private JLabel selectLabel,countryLabel,stateLabel,spotLabel;
	private JList<String> countryJList = new JList<String>(countrylistModel);
	private JList<String> stateJList= new JList<String>(statelistModel);
	
	private JPanel controlPanel, infoPanel;
	
	private List<String> selectedCountry = new ArrayList<String>();
	private List<String> selectedState = new ArrayList<String>();
	
	private JScrollPane countryScrollPane;
	
	//constructor
	public AddSpotView(PredictorView v, Notifier m) 
	{
		this.view = v;
		this.model = m;
		//gets arraylist from spot file reader class
		this.spots = m.getSpot().getSpotsList();
		
		Container cp = this.getContentPane();
	    cp.setLayout(new BorderLayout());
	    
	    controlPanel = new JPanel(new GridLayout(3,1,20,20));
	    
	    selectLabel = new JLabel("Select/Change Your Favourite Surf Spots");
	    selectLabel.setAlignmentX(CENTER_ALIGNMENT);
	    	    
	    //intialise JLabels and set alignment
	    countryLabel = new JLabel("Select Country:");
	    countryLabel.setAlignmentX(CENTER_ALIGNMENT);
	    stateLabel = new JLabel("Select State:");
	    stateLabel.setAlignmentX(CENTER_ALIGNMENT);
	    spotLabel = new JLabel("Select Surf Location:");
	    spotLabel.setAlignmentX(CENTER_ALIGNMENT);
	    
	    // info panel
	    infoPanel = new JPanel();
	    infoPanel.setLayout(new GridLayout(2,2,10,10));
	    
	    //need to loop thru arraylist to add country
	    countryList(spots);
	    //2 rows are always visible
	    countryJList.setVisibleRowCount(2);
	    countryScrollPane = new JScrollPane(countryJList);
	   
	    //by default set to Australia, to make it easy for users,
	    //In the bigger picture, we would use GPS location to detect country
	    countryJList.setSelectedIndex(0);
	    selectedCountry.add(countryJList.getSelectedValue().toString());
	    
	    //creating listener for country JList
	    countryJList.addListSelectionListener(new CountryUpdateListener(view,model,this));
	    
	    //need to loop thru arraylist to add australian states
	    UpdateStates(spots,selectedCountry);
	    //add selection listener for state JList based on country selection 
	    stateJList.addListSelectionListener(new StateUpdateListener(view,model,this));
	    
	    //adding to info panel
	    infoPanel.add(countryLabel);
	    infoPanel.add(countryJList);
	    infoPanel.add(stateLabel);
	    infoPanel.add(stateJList);
	    
	    //adding other JPanels to Control Panel
	    controlPanel.add(selectLabel);
	    controlPanel.add(infoPanel);
	    cp.add(controlPanel);
	    
	    // set the window size by itself
	    pack();
	    setLocationRelativeTo(view);
	    setModal(true);
	}
	
	//find all matching states based on country selected
	public void UpdateStates(ArrayList<Spot> spots,List<String> selectedCountry) 
	{
		//add each state once to list
	    stateList = new ArrayList<String>();
	    
	    //remove old items from default list
	    statelistModel.removeAllElements();
	    	    	
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
			    	for(String n : stateList)
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
			    		stateList.add(stateName);
			    		statelistModel.addElement(stateName);
			    	}
		    	}
	    	}
	    }
		
		//intialise JList
		stateJList.setModel(statelistModel);
	}

	//need to loop thru arraylist to add each country only once
	public void countryList(ArrayList<Spot> spots)
	{
		//when unique region found, added once to countryLIst (arrayList)
	    countryList = new ArrayList<String>();
	    
	    // holds the items and the JList
	    countrylistModel = new DefaultListModel();
	    
	    for (Spot s : spots) 
	    { 		      
	    	String countryName = s.getCountry();
	    	//only adding each country name once to arraylist
	    	boolean countryMatchFound = false;
	    	for(String n : countryList)
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
	    		countryList.add(countryName);
	    		countrylistModel.addElement(countryName);
	    	}
	    }
	    
	    //intialise JList
	    countryJList = new JList(countrylistModel);
	}
	
	public ArrayList<Spot> getSpots() 
	{
		return spots;
	}

	public ArrayList<String> getCountryList() 
	{
		return countryList;
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






}
