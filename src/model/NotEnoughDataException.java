package model;

public class NotEnoughDataException extends NullPointerException{

	private static final long serialVersionUID = 1L;
	
	public NotEnoughDataException(){
		super("Not enough data");
	}
	

}
