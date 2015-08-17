import Model.Forecast;
import Model.ForecastResponse;
import Model.Surf;
import Model.SwellForecast;
import Model.Swell;


/**
 * 
 * Creates Forecast Class objects from MSW json response
 * 
 * @author marcello
 * 
 */
public class SSPBuilder {

	public Forecast getSwellForecast(ForecastResponse r) {
		Surf surf = null;
		Model.Swell primarySwell = null;
		Model.Swell secondarySwell = null;
		Model.Swell tertiarySwell = null;
		Model.Chart periodChart = null;
		Model.Chart swellChart = null;
		Model.Chart sstChart = null;

		try {
			surf = new Surf(r.swell.minBreakingHeight,
					r.swell.absMinBreakingHeight, r.swell.maxBreakingHeight,
					r.swell.absMaxBreakingHeight, r.swell.unit);
		} catch (NullPointerException e) {
			System.out.println("Nullpointer when creating surf.");
			// e.printStackTrace();
		}
		try {
			primarySwell = new Model.Swell(r.swell.components.primary.height,
					r.swell.components.primary.period,
					r.swell.components.primary.direction,
					r.swell.components.primary.compassDirection, r.swell.unit);
		} catch (NullPointerException e) {
			System.out.println("Nullpointer when creating primarySwell.");
			// e.printStackTrace();
		}
		try {
			secondarySwell = new Model.Swell(
					r.swell.components.secondary.height,
					r.swell.components.secondary.period,
					r.swell.components.secondary.direction,
					r.swell.components.secondary.compassDirection, r.swell.unit);
		} catch (NullPointerException e) {
			System.out.println("Nullpointer when creating secondarySwell.");
			// e.printStackTrace();
		}
		try {
			tertiarySwell = new Model.Swell(r.swell.components.tertiary.height,
					r.swell.components.tertiary.period,
					r.swell.components.tertiary.direction,
					r.swell.components.tertiary.compassDirection, r.swell.unit);
		} catch (NullPointerException e) {
			System.out.println("Nullpointer when creating tertiarySwell.");
			// e.getMessage();
		}
		try {
			periodChart = new Model.Chart(r.charts.period);
			swellChart = new Model.Chart(r.charts.swell);
			sstChart = new Model.Chart(r.charts.sst);
		} catch (NullPointerException e) {
			System.out.println("Nullpointer when creating Charts.");
		}
		Forecast swellForecast = new SwellForecast(r.issueTimestamp,
				r.localTimestamp, r.fadedRating, r.solidRating, surf,
				primarySwell, secondarySwell, tertiarySwell, periodChart,
				swellChart, sstChart);
		return swellForecast;

			}
}
