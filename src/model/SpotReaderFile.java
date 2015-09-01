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


//CLASS CREATED BY EESWARI
public class SpotReaderFile 
{
	private Map<Integer, Spot> spotsMap;
	private ArrayList<Spot> spotsList;
	
	//constructor
	public SpotReaderFile() 
	{
		spotsMap = new HashMap<Integer, Spot>();
		spotsList = new ArrayList<Spot>();
		
		//reads text files & creates Spot object, adds to hash map
		try 
		{	
			BufferedReader reader;
			String line;
			Spot s;
			reader = new BufferedReader(new FileReader("GuiResources/textFiles/spots.txt"));
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



}
