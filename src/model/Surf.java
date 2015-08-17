package model;

public final class Surf {
    public final long minBreakingHeight;
    public final double absMinBreakingHeight;
    public final long maxBreakingHeight;
    public final double absMaxBreakingHeight;
    public final String unit;
    

    public Surf(long minBreakingHeight, double absMinBreakingHeight,
			long maxBreakingHeight, double absMaxBreakingHeight, String unit) {
		super();
		this.minBreakingHeight = minBreakingHeight;
		this.absMinBreakingHeight = absMinBreakingHeight;
		this.maxBreakingHeight = maxBreakingHeight;
		this.absMaxBreakingHeight = absMaxBreakingHeight;
		this.unit = unit;
	}


}
