package model;

public class Spot {
	String name = null;
	String country = null;
	String state = null;
	long id = -1;

	public Spot(String name, String country, String state, long id) {
		super();
		this.name = name;
		this.country = country;
		this.state = state;
		this.id = id;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Spot(" + name + "," + id + ")");
		return builder.toString();
	}

}
