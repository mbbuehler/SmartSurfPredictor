package model;

//created by Eeswari for testing 
public class SpotPrediction 
{
	
	private Spot s;
	private PlainPrediction plain;
	private Prediction prediction;
	
	public SpotPrediction(Spot s,PlainPrediction pp, Prediction p) 
	{
		this.s = s;
		this.plain = pp;
		this.prediction=p;
	}

	public PlainPrediction getPlain() {
		return plain;
	}

	public Spot getS() 
	{
		return s;
	}

	public Prediction getPrediction() 
	{
		return prediction;
	}
}
