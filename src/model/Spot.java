package model;

public class Spot 
{
	String name = null;
	String country = null;
	String state = null;
	int id = -1;
	String idName = null;
	
	//Eeswari - I changed the ID from long to int, 
	//is there any particular reason why you need it as long ?
	//I'm adding one more variable, for GUI purposes
	public Spot(String name, String country, String state, int id) 
	{
		super();
		this.name = name;
		this.country = country;
		this.state = state;
		this.id = id;
		this.idName = id + ":" +name;
	}

	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Spot(" + name + "," + id + ")");
		return builder.toString();
	}
	
	// Methods bellow created by Eeswari
	// variable getters
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

	public long getId() 
	{
		return id;
	}
	
	public String getIdName() 
	{
		return idName;
	}
	
	//method creates strings, that will be used for multiple files
	public String fileWriterString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(id + "," + country + "," + state + "," + name );
		return builder.toString();
	}
}
