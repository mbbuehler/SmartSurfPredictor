package model;

public enum Unit {
	FT, MPH, MB;

	public static Unit getUnit(String unit) {
		if (unit.equalsIgnoreCase("ft")) {
			return FT;
		} else if (unit.equalsIgnoreCase("MPH")) {
			return MPH;
		} else if (unit.equalsIgnoreCase("MB")) {
			return MB;
		} else
			System.err.println("Unit not found");
		return null;
	}

}
