import java.io.File;
import util.SSPPaths;
import view.PredictorView;

public class Launcher 
{
	public static void main(String[] args) 
	{
		initApplication();

		// creates PredictorView
		new PredictorView();

		// bug at jar export. TODO: fix it
		// cleanUp();
	}

	private static void initApplication() {
		// Initialize file structure
		String dir = SSPPaths.userDir;
		// create folder where user data will be stored
		File file = new File(dir);
		file.mkdir();

	}

	private static void cleanUp() {
		File unlabeled = new File(SSPPaths.userDir + "/"
				+ SSPPaths.tmpUnlabeledPredictionFileName);
		unlabeled.delete();
	}

}
