package model;

public final class Swell {
	public final float height;
	public final int period;
    public final double direction;
	public final CompassDirection compassDirection;
	public final String unit;

	public Swell(float height, int period, double direction,
			String compassDirection, String unit) {
        this.height = height;
        this.period = period;
        this.direction = direction;
		this.compassDirection = CompassDirection
				.getCompassDirection(compassDirection);
		this.unit = unit;
    }
}
