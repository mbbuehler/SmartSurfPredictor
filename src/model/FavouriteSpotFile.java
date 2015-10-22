package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

import util.SSPPaths;

//Author: Eeswari
public class FavouriteSpotFile 
{	
	private ArrayList<Integer> preSelectCountryIndex,preSelectStateIndex,preSelectLocationIndex;
	private List<String> selectedCountryNames, selectedStateNames, selectedLocationsNames;
	private static final String filePath = SSPPaths.userDir + "/"
			+ SSPPaths.favSpotFileName;
	private File f = new File(filePath);
	private SpotReaderFile spot;

	public FavouriteSpotFile(SpotReaderFile s) 
	{
		this.spot = s;
	}
	
	public FavouriteSpotFile() 
	{
	}
	
	//creates spot objects based on favspot file & returns list
	public static ArrayList<Spot> getFavouriteSpots() 
	{
		ArrayList<Spot> favSpots = new ArrayList<Spot>();
		
		try 
		{	
			String line;
			Spot s;
			BufferedReader reader = new BufferedReader(new FileReader(new File(
					filePath)));
			while ((line = reader.readLine()) != null)
		    {
				//for spot file the order is spot ID, country, state, spot name - spot info split by ","
				List<String> items = Arrays.asList(line.split(","));

				//create Spot object
				s = new Spot(items.get(3),items.get(1),items.get(2),Integer.parseInt(items.get(0)));
				favSpots.add(s);
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
		
		return favSpots;
	}


	//writes to Favorite file if user selects different spots
	public void writeToFavFile(ArrayList<Spot> favSpots)
	{
		BufferedWriter out;
		
		//if file doesnt exist, create file
		if(!f.exists())
		{
			try 
			{
				f.createNewFile();
			} 
			catch (IOException e) 
			{

				e.printStackTrace();
			}
		}
		
		//Open file & write
		try 
	    {
	    	out = new BufferedWriter (new FileWriter(f, false));
	    	for(Spot s:favSpots)
			{
				out.write(s.fileWriterString());
				out.newLine();
			}
	    	out.close();
	    } 
	    catch (IOException e) 
	    {
			e.printStackTrace();
		}
	}

	
	//gets index number for selected countries, in-order to highlight it in JList Selection
	public void countrySelectionIndex() 
	{
		preSelectCountryIndex = new ArrayList<Integer>();
		selectedCountryNames = new ArrayList<String>();
		selectedStateNames = new ArrayList<String>();
		selectedLocationsNames  = new ArrayList<String>();
		
		//reads favspot file & adds spot details
		if(f.exists())
		{
			try 
			{	
				BufferedReader reader;
				String line;
				reader = new BufferedReader(new FileReader(filePath));
				
				while ((line = reader.readLine()) != null)
				{
					//for spot file the order is spot ID, country, state, spot name - spot info split by ","
					List<String> items = Arrays.asList(line.split(","));
					
					// add index values for country pre-selected
					for(String c :spot.getCountry())
					{	
						if(c.equals(items.get(1)))
						{
							preSelectCountryIndex.add(spot.getCountry().indexOf(c));
							break;
						}
					}
					
					selectedCountryNames.add(items.get(1));
					selectedStateNames.add(items.get(2));
					selectedLocationsNames.add(items.get(3));
			    }
				
				//close file
				reader.close();
				
				//removes duplicates
				preSelectCountryIndex = new ArrayList<Integer>(new LinkedHashSet<Integer>(preSelectCountryIndex));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	
	//gets index number of state Selected, in-order to highlight it in JList Selection
	public void stateSelectionIndex() 
	{
		preSelectStateIndex  = new ArrayList<Integer>();
		
		//reads fav spot files & adds spot details
		if(f.exists())
		{
			try 
			{	
				BufferedReader reader;
				String line;
				reader = new BufferedReader(new FileReader(filePath));
				
				while ((line = reader.readLine()) != null)
				{
					//for spot file the order is spot ID, country, state, spot name -info split by ","
					List<String> items = Arrays.asList(line.split(","));
					
					//add index values for pre-selected states
					for(String s :spot.getState())
					{
						//checks if the states match
						if(s.equals(items.get(2)))
						{
							preSelectStateIndex.add(spot.getState().indexOf(s));
							break;
						}
					}
				}
				reader.close();
			
				//removes duplicates
				preSelectStateIndex = new ArrayList<Integer>(new LinkedHashSet<Integer>(preSelectStateIndex));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	
	//gets index number for selected locations, in-order to highlight it in JList Selection
	public void LocationSelection() 
	{
		preSelectLocationIndex  = new ArrayList<Integer>();
			
		//reads fav spot files & adds spot details
		if(f.exists())
		{
			try 
			{	
				BufferedReader reader;
				String line;
				reader = new BufferedReader(new FileReader(filePath));
					
				while ((line = reader.readLine()) != null)
				{
					//for spot file the order is spot ID, country, state, spot name
					//spot info split by ","
					List<String> items = Arrays.asList(line.split(","));
						
					//add index values for pre-selected states
					for(String l :spot.getLocation())
					{
						//checks if the states match
						if(l.equals(items.get(3)))
						{
							preSelectLocationIndex.add(spot.getLocation().indexOf(l));
							break;
						}
					}
				}
				reader.close();
				
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	

	//converts country arraylist to array
	public int[] getSelectedCountry() 
	{
		int[] countryIndex = new int[preSelectCountryIndex.size()];
		
		for (int i = 0; i < countryIndex.length; i++) 
		{
			countryIndex[i] = preSelectCountryIndex.get(i);
		}
		return countryIndex;
	}

	//converts state arraylist to array
	public int[] getSelectedState() 
	{
		int[] stateIndex = new int[preSelectStateIndex.size()];
		
		for (int i = 0; i < stateIndex.length; i++) 
		{
			stateIndex[i] = preSelectStateIndex.get(i);
		}
		
		return stateIndex;
	}

	//converts location arraylist to array
	public int[] getSelectedLocation() 
	{
		int[] locIndex = new int[preSelectLocationIndex.size()];
		
		for (int i = 0; i < locIndex.length; i++) 
		{
			locIndex[i] = preSelectLocationIndex.get(i);
		}
		
		return locIndex;
	}
	
	
	public List<String> getSelectedCountryNames() 
	{
		return selectedCountryNames;
	}

	public List<String> getSelectedStateNames() 
	{
		return selectedStateNames;
	}


	public List<String> getSelectedLocationsNames() 
	{
		return selectedLocationsNames;
	}

	
	//returns list of spot selected based on AddSpotView JList selection 
	public ArrayList<Spot> finUsersFavouriteSpots(ArrayList<Spot> spotsSelected, List<String> selectedCountry, List<String> selectedState, List<String> selectedLocations)
	{
		 ArrayList<Spot> favSpot = new ArrayList<Spot>();
		//find favorite surf spot based on countries,states,location selected
		for (Spot s : spotsSelected) 
		{ 		      
		 	String locationName = s.getName();
			 	
		   	//check if country,state & location matches, then add that spot
		 	for(String c : selectedCountry )
		   	{	
		    	if(s.getCountry().equals(c))
		    	{	
			    	for(String n : selectedState)
			    	{
			    		if(s.getState().equals(n))
			    		{
			    			//Search for matching locations
			    			for(String l : selectedLocations)
			    			{
			    				if(locationName.equals(l))
					    		{
			    					favSpot.add(s);
					    		}
			    			}
			    		}
			    	}
				    	   	
		    	}
		   	}
		}
		return favSpot;
	}


}
