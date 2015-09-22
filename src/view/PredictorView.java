package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import controller.AddSpotListener;
import controller.ExitListener;
import controller.MainForecastButtonListener;
import controller.TESTINGListener;
import controller.PredictorViewCloseListener;
import model.Notifier;
import model.PredictionTime;


public class PredictorView extends JFrame
{
	private Notifier model = new Notifier();
		
	private WelcomeBarView welcomePanel = new WelcomeBarView(this);
	
	private JButton setLocation = new JButton("Add/Change Surf Location");
	private JButton forecastFeedback = new JButton("Forecast Feedback");
	
	private JButton exitButton;
	private JPanel exitPanel;
	
	public PredictorView()
    {
		model.notify(PredictionTime.MORNING);
		
		JPanel box = new SSPPanel(new GridLayout(0, 1, 10, 10));
        
		//add listener for buttons & align button
		setLocation.setAlignmentX(CENTER_ALIGNMENT);
        setLocation.addActionListener(new AddSpotListener(this,model));
        

        forecastFeedback.setAlignmentX(CENTER_ALIGNMENT);
        forecastFeedback.addActionListener(new MainForecastButtonListener(this,model));
        
        exitButton = new JButton("exit");
		exitPanel = new SSPPanel(new FlowLayout(FlowLayout.LEFT));
        exitPanel.add(exitButton);
        exitButton.addActionListener(new ExitListener(this,model));
        	
		box.add(welcomePanel);
        box.add(setLocation);
        box.add(forecastFeedback);
        box.add(exitPanel);
        
        //testing -REMOVE- I should check time and set PredictionTime.MORNING or PredictionTime.evening
        JButton pop = new JButton("POP UP");
        pop.addActionListener(new TESTINGListener(this,model,PredictionTime.MORNING));
        box.add(pop);
        //testing -REMOVE
        
        add(box,BorderLayout.CENTER);
        
        setUndecorated(true);
        setBounds(100, 100, 800, 600);
        setVisible(true);

	   }

}



