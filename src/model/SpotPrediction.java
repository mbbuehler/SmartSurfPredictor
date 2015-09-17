package model;

//created by Eeswari for testing 
public class SpotPrediction 
{
	
	private Spot s;
	private PlainPrediction p;
	
	public SpotPrediction(Spot s,PlainPrediction p) 
	{
		this.s = s;
		this.p = p;
	}

	public Spot getS() 
	{
		return s;
	}

	public PlainPrediction getP() 
	{
		return p;
	}
}
