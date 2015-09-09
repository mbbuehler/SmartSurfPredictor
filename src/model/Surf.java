package model;

public final class Surf {
	public final int minBreakingHeight;
    public final double absMinBreakingHeight;
	public final int maxBreakingHeight;
    public final double absMaxBreakingHeight;
    public final String unit;
    

	public Surf(int minBreakingHeight, double absMinBreakingHeight,
			int maxBreakingHeight, double absMaxBreakingHeight, String unit) {
		super();
		this.minBreakingHeight = minBreakingHeight;
		this.absMinBreakingHeight = absMinBreakingHeight;
		this.maxBreakingHeight = maxBreakingHeight;
		this.absMaxBreakingHeight = absMaxBreakingHeight;
		this.unit = unit;
	}


}
