package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteFavouriteSpotFile 
{	
	public void WriteToFavFile(ArrayList<Spot> favSpots)
	{
		File f = new File("GuiResources/textFiles/favspots.txt");
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
}
