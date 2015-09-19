package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;


//CLASS CREATED BY EESWARI
public class SpotReaderFile 
{
	private Map<Integer, Spot> spotsMap;
	private ArrayList<Spot> spotsList, possibleSelectionSpots;
	
	private ArrayList<String> countryArrayList, stateArrayList, locationArrayList;
	//created to hold JList elements before JList intilised 
	private DefaultListModel countryModel, stateModel, locationModel;
	
	//constructor
	public SpotReaderFile() 
	{
		spotsMap = new HashMap<Integer, Spot>();
		spotsList = new ArrayList<Spot>();
		
		countryArrayList = new ArrayList<String>();
		stateArrayList = new ArrayList<String>();
		locationArrayList = new ArrayList<String>();
				
		countryModel = new DefaultListModel();
		stateModel = new DefaultListModel();
		locationModel = new DefaultListModel();
		
		//reads text files & creates Spot object, adds to hash map
		try 
		{	
			BufferedReader reader;
			String line;
			Spot s;
			reader = new BufferedReader(new FileReader("prg_res/spots.txt"));
			while ((line = reader.readLine()) != null)
		    {
				//for spot file the order is spot ID, country, state, spot name
				//spot info split by ","
				List<String> items = Arrays.asList(line.split(","));

				//for spot file the order is spot ID, country, state, spot name
				//create Spot object
				s = new Spot(items.get(3),items.get(1),items.get(2),Integer.parseInt(items.get(0)));
				//the map key=spotId
				spotsMap.put(Integer.parseInt(items.get(0)), s);
				
				//add to arraylist
				spotsList.add(s);
		    }
		    reader.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
				
	}
	
	//getter method for hash map
	public Map<Integer, Spot> getSpotsMap() 
	{
		return spotsMap;
	}

	public ArrayList<Spot> getSpotsList() 
	{
		return spotsList;
	}

	public ArrayList<String> getCountry() 
	{
		return countryArrayList;
	}

	public ArrayList<String> getState() 
	{
		return stateArrayList;
	}

	public ArrayList<String> getLocation() 
	{
		return locationArrayList;
	}

	public DefaultListModel getCountryModel() 
	{
		return countryModel;
	}

	public DefaultListModel getStateModel() 
	{
		return stateModel;
	}

	public DefaultListModel getLocationModel() 
	{
		return locationModel;
	}

	public ArrayList<Spot> getPossibleSelectionSpots() 
	{
		return possibleSelectionSpots;
	}

	//adds countries once arraylist to be display on GUI
	//add it to defaultmodel which will be used for JList
	public void InitialiseCountryList()
	{	
		for (Spot s : spotsList) 
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
	}
	
	
	//adds states based on country selected to display on GUI
	//add it to defaultmodel which will be used for JList
	public void InitialiseStateList(List<String> selectedCountry)
	{
		stateArrayList = new ArrayList<String>();
			
		//find states based on country
		for (Spot s : spotsList) 
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
		
	}

	//adds location based on country & state selected to display on GUI
	//add it to defaultmodel which will be used for JList
	public void InitialiseLocationList(List<String> selectedCountries, List<String> selectedStates)
	{
		locationArrayList = new ArrayList<String>();
		possibleSelectionSpots = new ArrayList<Spot>();
		boolean found = false;
		
		//find surf location based on countries & states selected
		for (Spot s : spotsList) 
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
			    			possibleSelectionSpots.add(s);
			    			locationArrayList.add(locationName);locationModel.addElement(locationName);
							found = true;
							break;
			    		}
			    	}  	   	
		    	}	
		   		
		   		if(found == true)
		   		{
		   			break;
		   		}
		   	}
		   	
		   	found = false;
		}
		
	}
}
