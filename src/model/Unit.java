package model;

public enum Unit {
	FT, MPH, MB, C;

	public static Unit getUnit(String unit) {
		if (unit.equalsIgnoreCase("ft")) {
			return FT;
		} else if (unit.equalsIgnoreCase("MPH")) {
			return MPH;
		} else if (unit.equalsIgnoreCase("MB")) {
			return MB;
		} else if (unit.equalsIgnoreCase("C")) {
			return C;
		} else {
			System.err.println("Unit not found: " + unit);
			return null;
		}

	}
	}
