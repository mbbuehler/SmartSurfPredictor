package model;

public abstract class Forecast {
	private long issueTimestamp;
	private long localTimestamp;
	public Forecast(long issueTimestamp, long localTimestamp) {
		super();
		this.issueTimestamp = issueTimestamp;
		this.localTimestamp = localTimestamp;
	}
	public long getIssueTimestamp() {
		return issueTimestamp;
	}
	public long getLocalTimestamp() {
		return localTimestamp;
	}

}
