package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.ExitListener;
import model.Notifier;

public class FeedbackView extends JDialog
{
	private PredictorView view; 
	private Notifier model;
	
	private JButton cancel = new JButton("CANCEL");
	private JButton yesButton = new JButton("Yes");
	private JButton noButton = new JButton("No");
	
	private JLabel infoLabel, spotName,spotNameUpdate;
	private JLabel surfHeightLabel,surfHeightupdateLabel;
	private JLabel primarySwellHeigtLabel, primarySwellHeigtUpdateLabel;
	private JLabel swellRateLabel,swellRateUpdateLabel;
	private JLabel speedLabel,speedUpdateLabel;
	private JLabel directionLabel,directionUpdateLabel;
	private JLabel weatherLabel,weatherUpdateLabel;
	private JLabel temperatureLabel,temperatureUpdateLabel;
	
	private JPanel controlPanel,infoUpdatePanel;
	
	public FeedbackView(PredictorView v, Notifier m) 
	{
		this.view = v;
		this.model = m;
		
		Container cp = this.getContentPane();
	    cp.setLayout(new BorderLayout());
	    
	    //cancel button listener
	    cancel.addActionListener(new ExitListener(this,model));
	    
	    //adding panel
	    IntialiseJLablesPanels();
	    cp.add(controlPanel);
	    
	    // set the window size by itself
	    pack();
	    setLocationRelativeTo(view);
	    setModal(true);
	}
	
	//method intilalise JPanels & JLabels
	private void IntialiseJLablesPanels() 
	{
		controlPanel = new JPanel(new GridLayout(3,1,10,10));
	    
	    // info panel
		infoUpdatePanel = new JPanel();
		infoUpdatePanel.setLayout(new GridLayout(0,2,10,10));
			
		infoLabel = new JLabel("Surf Prediction Feedback");
		infoLabel.setAlignmentX(CENTER_ALIGNMENT);
			    	    
	    //intialise JLabels and set alignment
		spotName = new JLabel("Surf Spot:");
		spotName.setAlignmentX(CENTER_ALIGNMENT);
		spotNameUpdate = new JLabel("--");
		spotNameUpdate.setAlignmentX(CENTER_ALIGNMENT);
		
		surfHeightLabel = new JLabel("Surf Height:");
		surfHeightLabel.setAlignmentX(CENTER_ALIGNMENT);
		surfHeightupdateLabel = new JLabel("--");
		surfHeightupdateLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		primarySwellHeigtLabel = new JLabel("Primary Swell Height:");
		primarySwellHeigtLabel.setAlignmentX(CENTER_ALIGNMENT);
		primarySwellHeigtUpdateLabel = new JLabel("--");
		primarySwellHeigtUpdateLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		swellRateLabel = new JLabel("Primary Swell Rate:");
		swellRateLabel.setAlignmentX(CENTER_ALIGNMENT);
		swellRateUpdateLabel = new JLabel("--");
		swellRateUpdateLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		speedLabel = new JLabel("Wind Speed:");
		speedLabel.setAlignmentX(CENTER_ALIGNMENT);
		speedUpdateLabel = new JLabel("--");
		speedUpdateLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		directionLabel = new JLabel("Wind Direction:");
		directionLabel.setAlignmentX(CENTER_ALIGNMENT);
		directionUpdateLabel = new JLabel("--");
		directionUpdateLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		weatherLabel = new JLabel("Weather:");
		weatherLabel.setAlignmentX(CENTER_ALIGNMENT);
		weatherUpdateLabel = new JLabel("--");
		weatherUpdateLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		temperatureLabel = new JLabel("Temperature:");
		temperatureLabel.setAlignmentX(CENTER_ALIGNMENT);
		temperatureUpdateLabel = new JLabel("--");
		temperatureUpdateLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		infoUpdatePanel.add(spotName);
		infoUpdatePanel.add(spotNameUpdate);
		infoUpdatePanel.add(surfHeightLabel);
		infoUpdatePanel.add(surfHeightupdateLabel);
		infoUpdatePanel.add(primarySwellHeigtLabel);
		infoUpdatePanel.add(primarySwellHeigtUpdateLabel);
		infoUpdatePanel.add(swellRateLabel);
		infoUpdatePanel.add(swellRateUpdateLabel);
		
		infoUpdatePanel.add(speedLabel);
		infoUpdatePanel.add(speedUpdateLabel);
		infoUpdatePanel.add(directionLabel);
		infoUpdatePanel.add(directionUpdateLabel);
		
		infoUpdatePanel.add(weatherLabel);
		infoUpdatePanel.add(weatherUpdateLabel);
		infoUpdatePanel.add(temperatureLabel);
		infoUpdatePanel.add(temperatureUpdateLabel);
		
	    // adding submit & cancel button
	    infoUpdatePanel.add(yesButton);
	    infoUpdatePanel.add(noButton);
	    
		    
	    //adding other JPanels to Control Panel
	    controlPanel.add(infoLabel);
	    controlPanel.add(infoUpdatePanel);
	    controlPanel.add(cancel);
			
		}

	
	
	//setters for variables
		
	public void setSpotNameUpdate(JLabel spotNameUpdate) 
	{
		this.spotNameUpdate = spotNameUpdate;
	}
	

	public void setSurfHeightupdateLabel(JLabel surfHeightupdateLabel) 
	{
		this.surfHeightupdateLabel = surfHeightupdateLabel;
	}
	

	public void setPrimarySwellHeigtUpdateLabel(JLabel primarySwellHeigtUpdateLabel) 
	{
		this.primarySwellHeigtUpdateLabel = primarySwellHeigtUpdateLabel;
	}
	

	public void setSwellRateUpdateLabel(JLabel swellRateUpdateLabel) 
	{
		this.swellRateUpdateLabel = swellRateUpdateLabel;
	}
	

	public void setSpeedUpdateLabel(JLabel speedUpdateLabel) 
	{
		this.speedUpdateLabel = speedUpdateLabel;
	}
	

	public void setDirectionUpdateLabel(JLabel directionUpdateLabel) 
	{
		this.directionUpdateLabel = directionUpdateLabel;
	}
	

	public void setWeatherUpdateLabel(JLabel weatherUpdateLabel) 
	{
		this.weatherUpdateLabel = weatherUpdateLabel;
	}
	

	public void setTemperatureUpdateLabel(JLabel temperatureUpdateLabel) 
	{
		this.temperatureUpdateLabel = temperatureUpdateLabel;
	}
	
	
}
