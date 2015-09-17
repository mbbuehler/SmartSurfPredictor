package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.ExitListener;
import controller.FeedbackNoResponseListener;
import controller.FeedbackYesResponseListener;
import model.Notifier;
import model.PlainPrediction;
import model.SpotPrediction;



public class FeedbackView extends JDialog
{
	private PredictorView view; 
	private Notifier model;
	
	private JButton cancel = new JButton("CANCEL");
	private JButton yesButton = new JButton("Yes");
	private JButton noButton = new JButton("No");
	
	private JLabel infoLabel, spotName,spotNameUpdate;
	private JLabel minHeightLabel,minHeightupdateLabel;
	private JLabel maxHeightLabel,maxHeightupdateLabel;
	private JLabel primarySwellHeigtLabel, primarySwellHeigtUpdateLabel;
	private JLabel swellPeriodLabel,swellPeriodUpdateLabel;
	private JLabel primarySwellDirectionLabel,primarySwellDirectionUpdateLabel;
	private JLabel speedLabel,speedUpdateLabel;
	private JLabel windDirectionLabel,windDirectionUpdateLabel;
	private JLabel weatherLabel,weatherUpdateLabel;
	private JLabel temperatureLabel,temperatureUpdateLabel;
	private JLabel likePrediction;
	
	private ArrayList<SpotPrediction> recommends;
	
	private JPanel controlPanel,infoUpdatePanel,opinionPanel;
	
	private int i=0;
	
	public FeedbackView(PredictorView v, Notifier m) 
	{
		this.view = v;
		this.model = m;
		this.recommends = model.getPredictionManager().getRandomUnlabeledPlainPredictions();
		
		Container cp = this.getContentPane();
	    cp.setLayout(new BorderLayout());
	    
	    //cancel button listener
	    cancel.addActionListener(new ExitListener(this,model));
	    
	    IntialiseJLablesPanels();
	    cp.add(controlPanel);
	    
	    // set the window size by itself
	    pack();
	    setLocationRelativeTo(view);
	    setModal(true);
	    
	    NextForcast(recommends.get(i));
	 	    
	}
	
	//method intilalise JPanels & JLabels
	private void IntialiseJLablesPanels() 
	{
		controlPanel = new JPanel(new GridLayout(0,1,5,5));
		opinionPanel = new JPanel(new GridLayout(1,2,5,5));
		infoUpdatePanel = new JPanel(new GridLayout(0,2,10,5));
			
		infoLabel = new JLabel("Surf Prediction Feedback");
		infoLabel.setAlignmentX(CENTER_ALIGNMENT);
			    	    
	    //intialise JLabels and set alignment
		spotName = new JLabel("Surf Spot:");
		spotName.setAlignmentX(CENTER_ALIGNMENT);
		spotNameUpdate = new JLabel("--");
		spotNameUpdate.setAlignmentX(CENTER_ALIGNMENT);
				
		minHeightLabel = new JLabel("Minumum Height:");
		minHeightLabel.setAlignmentX(CENTER_ALIGNMENT);
		minHeightupdateLabel = new JLabel("--");
		minHeightupdateLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		maxHeightLabel = new JLabel("Maximum Height:");
		maxHeightLabel.setAlignmentX(CENTER_ALIGNMENT);
		maxHeightupdateLabel = new JLabel("--");
		maxHeightupdateLabel.setAlignmentX(CENTER_ALIGNMENT);
				
		primarySwellHeigtLabel = new JLabel("Primary Swell Height:");
		primarySwellHeigtLabel.setAlignmentX(CENTER_ALIGNMENT);
		primarySwellHeigtUpdateLabel = new JLabel("--");
		primarySwellHeigtUpdateLabel.setAlignmentX(CENTER_ALIGNMENT);
				
		swellPeriodLabel = new JLabel("Primary Swell Rate:");
		swellPeriodLabel.setAlignmentX(CENTER_ALIGNMENT);
		swellPeriodUpdateLabel = new JLabel("--");
		swellPeriodUpdateLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		primarySwellDirectionLabel = new JLabel("Primary Swell Direction:");
		primarySwellDirectionLabel.setAlignmentX(CENTER_ALIGNMENT);
		primarySwellDirectionUpdateLabel = new JLabel("--");
		primarySwellDirectionUpdateLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		speedLabel = new JLabel("Wind Speed:");
		speedLabel.setAlignmentX(CENTER_ALIGNMENT);
		speedUpdateLabel = new JLabel("--");
		speedUpdateLabel.setAlignmentX(CENTER_ALIGNMENT);
				
		windDirectionLabel = new JLabel("Wind Direction:");
		windDirectionLabel.setAlignmentX(CENTER_ALIGNMENT);
		windDirectionUpdateLabel = new JLabel("--");
		windDirectionUpdateLabel.setAlignmentX(CENTER_ALIGNMENT);
				
		weatherLabel = new JLabel("Weather:");
		weatherLabel.setAlignmentX(CENTER_ALIGNMENT);
		weatherUpdateLabel = new JLabel();
		weatherUpdateLabel.setAlignmentX(CENTER_ALIGNMENT);
				
		temperatureLabel = new JLabel("Temperature:");
		temperatureLabel.setAlignmentX(CENTER_ALIGNMENT);
		temperatureUpdateLabel = new JLabel("--");
		temperatureUpdateLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		// adding submit & cancel button
		opinionPanel.add(yesButton);
		opinionPanel.add(noButton);
		yesButton.addActionListener(new FeedbackYesResponseListener(this,model));
		noButton.addActionListener(new FeedbackNoResponseListener(this,model));
		
		infoUpdatePanel.add(spotName);
		infoUpdatePanel.add(spotNameUpdate);
		infoUpdatePanel.add(minHeightLabel);  
		infoUpdatePanel.add(minHeightupdateLabel); //yes
		infoUpdatePanel.add(maxHeightLabel);  //add star rating
		infoUpdatePanel.add(maxHeightupdateLabel);// yes
		infoUpdatePanel.add(primarySwellHeigtLabel);
		infoUpdatePanel.add(primarySwellHeigtUpdateLabel); //  yes
		infoUpdatePanel.add(swellPeriodLabel);
		infoUpdatePanel.add(swellPeriodUpdateLabel); // yes
		
		infoUpdatePanel.add(primarySwellDirectionLabel);
		infoUpdatePanel.add(primarySwellDirectionUpdateLabel); // yes
		infoUpdatePanel.add(speedLabel);
		infoUpdatePanel.add(speedUpdateLabel); //  yes
		infoUpdatePanel.add(windDirectionLabel);
		infoUpdatePanel.add(windDirectionUpdateLabel); // yes
		
		infoUpdatePanel.add(weatherLabel);
		infoUpdatePanel.add(weatherUpdateLabel); // yes
		infoUpdatePanel.add(temperatureLabel);
		infoUpdatePanel.add(temperatureUpdateLabel); // yes
		
		likePrediction = new JLabel("Do you like this prediction ?");
	    
	    //adding other JPanels to Control Panel
	    controlPanel.add(infoLabel);
	    controlPanel.add(infoUpdatePanel);
	    controlPanel.add(likePrediction);
	    controlPanel.add(opinionPanel);
	    controlPanel.add(cancel);
	    		    
	}

	
	public void NextForcast(SpotPrediction sp)
	{		
			System.out.println("Name = "+sp.getS().getName());
			
			spotNameUpdate.setText(sp.getS().getName()+","+sp.getS().getState()+","+sp.getS().getCountry());
			minHeightupdateLabel.setText(""+sp.getP().getMinBreakHeight()); 
			maxHeightupdateLabel.setText(""+sp.getP().getMaxBreakHeight());
			primarySwellHeigtUpdateLabel.setText(""+sp.getP().getPrimarySwellHeight());
			swellPeriodUpdateLabel.setText(""+sp.getP().getPrimarySwellPeriod()); 
			primarySwellDirectionUpdateLabel.setText(""+sp.getP().getPrimarySwellDirection()); 
			speedUpdateLabel.setText(""+sp.getP().getWindSpeed()); 
			windDirectionUpdateLabel.setText(""+sp.getP().getWindDirection()); 
			
			String weatherFileName = "/weather/"+sp.getP().getWeather()+".png";
			BufferedImage logo ; 
			try 
		     {
				System.out.println("half  "+sp.getP().getWeather());
				System.out.println("path "+weatherFileName);
				 logo = ImageIO.read(getClass().getResource(weatherFileName));
				 weatherUpdateLabel.setIcon(new ImageIcon(logo));
		     } 
		     catch (IOException e) 
		     {
				e.printStackTrace();
		     }
		     
			
		   temperatureUpdateLabel.setText(""+sp.getP().getTemperature()); 	
	}


	public void increaseI() 
	{
		i=i+1;
		if(i<recommends.size())
		{	
	      	NextForcast(recommends.get(i));
	    }
	}
	
	
	
}
