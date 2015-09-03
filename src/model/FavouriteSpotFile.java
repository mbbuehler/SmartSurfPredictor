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
import java.util.List;

//CLASS CREATED BY EESWARI
public class FavouriteSpotFile 
{	
	private ArrayList<String> favCountry,favState,favLocation;
	private String filePath ="GuiResources/textFiles/favspots.txt";
	private File f = new File(filePath);
	
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
		favCountry = new ArrayList<String>();
		
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
					
					// add spot details to be pre-selected
					favCountry.add(items.get(1));
			    }
	
				reader.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<String> getFavCountry() 
	{
		return favCountry;
	}

	public ArrayList<String> getFavState() 
	{
		return favState;
	}

	public ArrayList<String> getFavLocation() 
	{
		return favLocation;
	}

	//find users favourite surf spots 
	//based on country, state &location selected
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
