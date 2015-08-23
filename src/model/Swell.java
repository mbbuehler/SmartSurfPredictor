package model;

public final class Swell {
	public final double height;
    public final long period;
    public final double direction;
	public final CompassDirection compassDirection;
	public final String unit;

	public Swell(double height, long period, double direction,
			String compassDirection, String unit) {
        this.height = height;
        this.period = period;
        this.direction = direction;
		this.compassDirection = CompassDirection
				.getCompassDirection(compassDirection);
		this.unit = unit;
    }
}
