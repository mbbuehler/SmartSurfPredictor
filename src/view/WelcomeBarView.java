package view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomeBarView extends JPanel
{
	private BufferedImage logo;
	private JLabel picLabel;
	private JLabel welcome = new JLabel("Welcome to Smart Surf Predictor");
	private JPanel introPanel;
	
	 public WelcomeBarView(PredictorView view)
	 {
		 welcome.setAlignmentX(CENTER_ALIGNMENT);
		try {
			logo = ImageIO.read(getClass().getResource("/images/surf.jpg"));
		}
	     catch (IOException e) 
	     {
			e.printStackTrace();
	     }
	        
	     picLabel = new JLabel(new ImageIcon(logo));
	     picLabel.setAlignmentX(CENTER_ALIGNMENT);
	     
	     introPanel = new JPanel();
	     introPanel.add(picLabel);
	     introPanel.add(welcome);
	     add(introPanel);
	 }

}
