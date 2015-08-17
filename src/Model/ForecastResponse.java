package Model;

import java.util.ArrayList;

import Model.Forecast;
import Model.NotEnoughDataException;
import Model.Surf;
import Model.SwellForecast;
import Model.Swell;

import com.google.api.client.util.Key;

/**
 * 
 * Model Class that stores all data from a MSW Forecast request for a specific
 * location
 * 
 * @author marcello
 * 
 */
public class ForecastResponse {
	public static class List extends ArrayList<ForecastResponse> {
	}

	@Key
	public long timestamp;
	@Key
	public long localTimestamp;
	@Key
	public long issueTimestamp;
	@Key
	public long fadedRating;
	@Key
	public long solidRating;
	@Key
	public Swell swell;
	@Key
	public Wind wind;
	@Key
	public Condition condition;
	@Key
	public Charts charts;

	public ForecastResponse() {
		super();
	}

	public ForecastResponse(long timestamp, long localTimestamp,
			long issueTimestamp,
			long fadedRating, long solidRating, Swell swell, Wind wind,
			Condition condition, Charts charts) {
		this.timestamp = timestamp;
		this.localTimestamp = localTimestamp;
		this.issueTimestamp = issueTimestamp;
		this.fadedRating = fadedRating;
		this.solidRating = solidRating;
		this.swell = swell;
		this.wind = wind;
		this.condition = condition;
		this.charts = charts;
	}

	public static class Swell {
		@Key
		public long minBreakingHeight;
		@Key
		public double absMinBreakingHeight;
		@Key
		public long maxBreakingHeight;
		@Key
		public double absMaxBreakingHeight;
		@Key
		public String unit;
		@Key
		public Components components;

		public Swell() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Swell(long minBreakingHeight, double absMinBreakingHeight,
				long maxBreakingHeight, double absMaxBreakingHeight,
				String unit, Components components) {
			this.minBreakingHeight = minBreakingHeight;
			this.absMinBreakingHeight = absMinBreakingHeight;
			this.maxBreakingHeight = maxBreakingHeight;
			this.absMaxBreakingHeight = absMaxBreakingHeight;
			this.unit = unit;
			this.components = components;
		}

		public static class Components {
			@Key
			public Combined combined;
			@Key
			public Primary primary;
			@Key
			public Secondary secondary;
			@Key
			public Tertiary tertiary;

			public Components() {
				super();
				// TODO Auto-generated constructor stub
			}

			public Components(Combined combined, Primary primary,
					Secondary secondary, Tertiary tertiary) {
				this.combined = combined;
				this.primary = primary;
				this.secondary = secondary;
				this.tertiary = tertiary;
			}

			public static class Combined {
				@Key
				public long height;
				@Key
				public long period;
				@Key
				public double direction;
				@Key
				public String compassDirection;

				public Combined() {
					super();
					// TODO Auto-generated constructor stub
				}

				public Combined(long height, long period, double direction,
						String compassDirection) {
					this.height = height;
					this.period = period;
					this.direction = direction;
					this.compassDirection = compassDirection;
				}
			}

			public static class Primary {
				@Key
				public double height;
				@Key
				public long period;
				@Key
				public double direction;
				@Key
				public String compassDirection;

				public Primary() {
					super();
					// TODO Auto-generated constructor stub
				}

				public Primary(double height, long period, double direction,
						String compassDirection) {
					this.height = height;
					this.period = period;
					this.direction = direction;
					this.compassDirection = compassDirection;
				}
			}

			public static class Secondary {
				@Key
				public double height;
				@Key
				public long period;
				@Key
				public double direction;
				@Key
				public String compassDirection;

				public Secondary() {
					super();
					// TODO Auto-generated constructor stub
				}

				public Secondary(double height, long period, double direction,
						String compassDirection) {
					this.height = height;
					this.period = period;
					this.direction = direction;
					this.compassDirection = compassDirection;
				}
			}

			public static class Tertiary {
				@Key
				public double height;
				@Key
				public long period;
				@Key
				public double direction;
				@Key
				public String compassDirection;

				public Tertiary() {
					super();
					// TODO Auto-generated constructor stub
				}

				public Tertiary(double height, long period, double direction,
						String compassDirection) {
					this.height = height;
					this.period = period;
					this.direction = direction;
					this.compassDirection = compassDirection;
				}
			}
		}
	}

	public static class Wind {
		@Key
		public long speed;
		@Key
		public long direction;
		@Key
		public String compassDirection;
		@Key
		public long chill;
		@Key
		public long gusts;
		@Key
		public String unit;

		public Wind() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Wind(long speed, long direction, String compassDirection,
				long chill, long gusts, String unit) {
			this.speed = speed;
			this.direction = direction;
			this.compassDirection = compassDirection;
			this.chill = chill;
			this.gusts = gusts;
			this.unit = unit;
		}
	}

	public static class Condition {
		@Key
		public long pressure;
		@Key
		public long temperature;
		@Key
		public long weather;
		@Key
		public String unitPressure;
		@Key
		public String unit;

		public Condition() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Condition(long pressure, long temperature, long weather,
				String unitPressure, String unit) {
			this.pressure = pressure;
			this.temperature = temperature;
			this.weather = weather;
			this.unitPressure = unitPressure;
			this.unit = unit;
		}
	}

	public static class Charts {
		@Key
		public String swell;
		@Key
		public String period;
		@Key
		public String wind;
		@Key
		public String pressure;
		@Key
		public String sst;

		public Charts() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Charts(String swell, String period, String wind,
				String pressure, String sst) {
			this.swell = swell;
			this.period = period;
			this.wind = wind;
			this.pressure = pressure;
			this.sst = sst;
		}
	}
}
