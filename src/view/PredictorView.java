package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import controller.AddSpotListener;
import controller.ExitListener;
import controller.MainForecastButtonListener;
import controller.PredictorViewCloseListener;
import controller.ShowPopupListener;
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
	
	private JButton showPopupButton;

	public PredictorView()
    {		
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

		showPopupButton = new JButton("show Forecasts");
		exitPanel.add(showPopupButton);
		showPopupButton.addActionListener(new ShowPopupListener(this, model));
        	
		box.add(welcomePanel);
        box.add(setLocation);
        box.add(forecastFeedback);
        box.add(exitPanel);
        
        //constantly check time, to notify user twice a day surf conditions
        feedbackScheduler();
 
        add(box,BorderLayout.CENTER);
        setBounds(100, 100, 800, 600);
        setVisible(true);

	   }
		
		
		//constantly check time
		private void feedbackScheduler()
		{
			ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	    	Runnable thread = new Runnable() 
	    	{
	    	    public void run() 
	    	    {
	    	        checkTime();
	    	    }
	    	};
	    	scheduler.scheduleAtFixedRate(thread, 0, 1, TimeUnit.MINUTES);
		}
	

		//to notify user twice a day surf conditions if 9AM or 3PM
		private void checkTime() 
		{
			// create a calendar
	        Calendar cal = Calendar.getInstance();
	        // get the current time
	        Date time = cal.getTime();
	        SimpleDateFormat setTime = new SimpleDateFormat("HH:mm");
	    	String current = setTime.format(time); 
	    	
	    	//check time 9AM,3PM
	    	String morningString = "09:00";
	    	String afternoonString = "15:00";
	    	
	    	//create feedback pop up according to time
	    	if(current.equals(morningString))
	    	{
	    		new PopUpView(this,model,PredictionTime.MORNING).setVisible(true);
	    	}
	    	
	    	if(current.equals(afternoonString))
	    	{
	    		new PopUpView(this,model,PredictionTime.AFTERNOON).setVisible(true);
	    	}
	    
		}

}



