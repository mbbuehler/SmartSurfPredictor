package model;

public final class Wind {
	public final int speed;
    public final long direction;
	public final CompassDirection compassDirection;
	public final int chill;
	public final int gusts;
    public final String unit;

	public Wind(int speed, long direction, String compassDirection, int chill,
			int gusts, String unit) {
        this.speed = speed;
        this.direction = direction;
		this.compassDirection = CompassDirection
				.getCompassDirection(compassDirection);
		this.chill = chill;
        this.gusts = gusts;
        this.unit = unit;
    }
}
