package model;

public final class Wind {
    public final long speed;
    public final long direction;
	public final CompassDirection compassDirection;
    public final long chill;
    public final long gusts;
    public final String unit;

    public Wind(long speed, long direction, String compassDirection, long chill, long gusts, String unit){
        this.speed = speed;
        this.direction = direction;
		this.compassDirection = CompassDirection
				.getCompassDirection(compassDirection);
		this.chill = chill;
        this.gusts = gusts;
        this.unit = unit;
    }
}
