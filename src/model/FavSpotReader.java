package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FavSpotReader {
	private static final String path = "user_data/favspots.csv";

	public FavSpotReader() {

	}
	
	
	public static ArrayList<Spot> getFavouriteSpots() {
		ArrayList<Spot> spots = new ArrayList<Spot>();
		
		try 
		{	
			String line;
			Spot s;
			BufferedReader reader = new BufferedReader(new FileReader(path));
			while ((line = reader.readLine()) != null)
		    {
				//for spot file the order is spot ID, country, state, spot name
				//spot info split by ","
				List<String> items = Arrays.asList(line.split(","));

				//for spot file the order is spot ID, country, state, spot name
				//create Spot object
				s = new Spot(items.get(3),items.get(1),items.get(2),Integer.parseInt(items.get(0)));
				spots.add(s);
		    }
		    reader.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return null;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			return null;
		}
		return spots;
	}

}
