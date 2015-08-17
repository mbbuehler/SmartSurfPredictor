package Model;

public final class Swell {
	public final double height;
    public final long period;
    public final double direction;
    public final String compassDirection;
	public final String unit;

	public Swell(double height, long period, double direction,
			String compassDirection, String unit) {
        this.height = height;
        this.period = period;
        this.direction = direction;
        this.compassDirection = compassDirection;
		this.unit = unit;
    }
}
