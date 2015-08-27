import model.Notifier;
import model.PredictionTime;
import view.PredictorView;


public class Launcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		new Notifier().notify(PredictionTime.MORNING);
		// creates PredictorViewview
		new PredictorView();

	}

}
