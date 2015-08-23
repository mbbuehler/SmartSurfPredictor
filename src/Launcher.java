import model.Notifier;
import model.PredictionTime;


public class Launcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Notifier().notify(PredictionTime.MORNING);

	}

}
