package model;

public enum CompassDirection {
	N, NE, E, SE, S, SW, W, NW;

	public String toString() {
		return this.name();
	}

	public static CompassDirection getCompassDirection(String direction) {
		try {
			if (direction.equals("N")) {
				return N;
			} else if (direction.equals("NE")) {
				return NE;
			} else if (direction.equals("E")) {
				return E;
			} else if (direction.equals("SE")) {
				return SE;
			} else if (direction.equals("S")) {
				return S;
			} else if (direction.equals("SW")) {
				return SW;
			} else if (direction.equals("W")) {
				return W;
			} else if (direction.equals("NW")) {
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
