package model;

public class Spot 
{
	String name = null;
	String country = null;
	String state = null;
	public int id = -1;
	
	//Eeswari - I changed the ID from long to int
	public Spot(String name, String country, String state, int id) 
	{
		super();
		this.name = name;
		this.country = country;
		this.state = state;
		this.id = id;
	}

	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Spot(" + name + "," + id + ")");
		return builder.toString();
	}
	
	public String getName() 
	{
		return name;
	}

	public String getCountry() 
	{
		return country;
	}

	public String getState() 
	{
		return state;
	}

	//method creates strings, that will be used for multiple files
	public String fileWriterString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(id + "," + country + "," + state + "," + name );
		return builder.toString();
	}
}
