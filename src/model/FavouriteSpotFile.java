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

//CLASS CREATED BY EESWARI
public class FavouriteSpotFile 
{	
	private ArrayList<Integer> preSelectCountryIndex,preSelectStateIndex,preSelectLocationIndex;
	private List<String> selectedCountryNames, selectedStateNames, selectedLocationsNames;
	private String filePath ="GuiResources/textFiles/favspots.txt";
	private File f = new File(filePath);
	private SpotReaderFile spot;

	public FavouriteSpotFile(SpotReaderFile s) 
	{
		this.spot = s;
	}

	
	public void WriteToFavFile(ArrayList<Spot> favSpots)
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
			// true to append
            // false to overwrite.
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
	
	//read fav spot details - country state location
	//so it can pre-selected incase want to check their selection
	public void CountrySelection() 
	{
		preSelectCountryIndex = new ArrayList<Integer>();
		selectedCountryNames = new ArrayList<String>();
		selectedStateNames = new ArrayList<String>();
		selectedLocationsNames  = new ArrayList<String>();
		
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
					
					// add index values for country pre-selection
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
	

	
	//read fav spot details - state
	//so it can pre-selected incase want to check their selection
	public void StateSelection() 
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
					//for spot file the order is spot ID, country, state, spot name
					//spot info split by ","
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
			//close file
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
	
	
	//read fav spot details - state
	//so it can pre-selected incase want to check their selection
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
			//close file
			reader.close();
				
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	
	//converts arraylist to array
	
	
	public int[] getSelectedCountry() 
	{
		int[] countryIndex = new int[preSelectCountryIndex.size()];
		
		for (int i = 0; i < countryIndex.length; i++) 
		{
			countryIndex[i] = preSelectCountryIndex.get(i);
		}
		return countryIndex;
	}
	
	
	
	
	//converts arraylist to array

	
	public int[] getSelectedState() 
	{
		int[] stateIndex = new int[preSelectStateIndex.size()];
		
		for (int i = 0; i < stateIndex.length; i++) 
		{
			stateIndex[i] = preSelectStateIndex.get(i);
		}
		
		return stateIndex;
	}

	
	
	
	
	
	public int[] getSelectedLocation() 
	{
		int[] locIndex = new int[preSelectLocationIndex.size()];
		
		for (int i = 0; i < locIndex.length; i++) 
		{
			locIndex[i] = preSelectLocationIndex.get(i);
		}
		
		return locIndex;
	}

	//find users favourite surf spots 
	//based on country, state &location selected
	
	
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


	public ArrayList<Spot> FavouriteSpots(ArrayList<Spot> spotsSelected, List<String> selectedCountry, List<String> selectedState, List<String> selectedLocations)
	{
		 ArrayList<Spot> favSpot = new ArrayList<Spot>();
		//find fav surf location based on countries & states selected
		for (Spot s : spotsSelected) 
		{ 		      
		 	String locationName = s.getName();
			 	
		   	//if country names match, add state once to list
		   	for(String c : selectedCountry )
		   	{	
		    	if(s.getCountry().equals(c))
		    	{	
			    	for(String n : selectedState)
			    	{
			    		//based on country & state, select location
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
