package model;

public enum CompassDirection {
	N, NE, E, SE, S, SW, W, NW;

	public String toString() {
		return this.name();
	}

	public static CompassDirection getCompassDirection(String direction) {
		// Reduce direction categories to 8 values.
		String shortDirection = direction.length() > 2 ? direction
				.substring(direction
				.length() - 2) : direction;
		// System.out.println(shortDirection);
		try {
			if (shortDirection.equals("N")) {
				return N;
			} else if (shortDirection.equals("NE")) {
				return NE;
			} else if (shortDirection.equals("E")) {
				return E;
			} else if (shortDirection.equals("SE")) {
				return SE;
			} else if (shortDirection.equals("S")) {
				return S;
			} else if (shortDirection.equals("SW")) {
				return SW;
			} else if (shortDirection.equals("W")) {
				return W;
			} else if (shortDirection.equals("NW")) {
				return NW;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.err.println("CompassDirection could not be matched");
				return null;
		}
			}
		}
