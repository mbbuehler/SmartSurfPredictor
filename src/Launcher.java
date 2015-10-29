import java.io.File;
import util.SSPPaths;
import view.PredictorView;

/**
 * Entry point for application. Launches GUI.
 * 
 * @author marcello
 * 
 */
public class Launcher 
{
	/**
	 * Initialises application and launches GUI
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		System.out.println(">>> SmartSurfPredictor started <<<");
		initApplication();
		System.out.println("> Application initialised.");

		// Launches welcome screen
		new PredictorView();
	}

	/**
	 * Create needed folder structure for saving user data
	 */
	private static void initApplication() {
		// Initialize file structure
		String dir = SSPPaths.userDir;
		// create folder where user data will be stored
		File file = new File(dir);
		file.mkdir();

	}
}
