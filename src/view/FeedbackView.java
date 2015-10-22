package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
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

import model.Notifier;
import model.PlainPrediction;
import model.PredictionStatus;
import model.PredictionWriter;
import util.SamplePredictionLoader;
import controller.BackListener;
import controller.FeedbackNoResponseListener;
import controller.FeedbackYesResponseListener;



public class FeedbackView extends JDialog
{
	private PredictorView view; 
	private Notifier model;
	
	private JButton cancel = new JButton("Back");
	private JButton yesButton = new JButton("Yes");
	private JButton noButton = new JButton("No");
	
	private JLabel infoLabel;
	private JLabel minHeightLabel,minHeightupdateLabel;
	private JLabel maxHeightLabel,maxHeightupdateLabel;
	private JLabel waveRatingLabel;
	private JPanel waveRatingPanel;
	private JLabel primarySwellHeigtLabel, primarySwellHeigtUpdateLabel;
	private JLabel swellPeriodLabel,swellPeriodUpdateLabel;
	private JLabel primarySwellDirectionLabel,primarySwellDirectionUpdateLabel;
	private JLabel speedLabel,speedUpdateLabel;
	private JLabel windDirectionLabel,windDirectionUpdateLabel;
	private JLabel weatherLabel,weatherUpdateLabel;
	private JLabel temperatureLabel,temperatureUpdateLabel;
	private JLabel likePrediction;
	private JLabel footerLabel;
	
	private ArrayList<PlainPrediction> recommends;
	
	private JPanel controlPanel,infoUpdatePanel,questionPanel,answerPanel,cancelPanel;
	
	private int i=0;
	
	private PredictionWriter writer;
	
	public FeedbackView(PredictorView v, Notifier m) 
	{
		this.view = v;
		this.model = m;
		//gets feedbacks for user to respond
		this.recommends = SamplePredictionLoader.getSamplePlainPredictions(21);
		
		Container cp = this.getContentPane();
	    cp.setLayout(new BorderLayout());
	    
	    //intialise all JLabels,adds to panels
	    intialiseJLablesPanels();
	    cp.add(controlPanel);
	    
	    //intialise 1st feedback
	    updateForcastDetails(recommends.get(i));
	    	    
	    // set the window size by itself & title
        this.setTitle("Quick Feedback");
	    pack();
	    setLocationRelativeTo(view);
	    setModal(true);
	}
	
	
	//method intilalise JPanels & JLabels
	private void intialiseJLablesPanels() 
	{
		controlPanel = new JPanel(new GridLayout(0,1,0,0));
		questionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		answerPanel = new JPanel(new GridLayout(1,2,2,0));
		cancelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		infoUpdatePanel = new JPanel(new GridLayout(0,2,0,0));
			
		infoLabel = new JLabel("Surf Prediction Feedback");
		infoLabel.setAlignmentX(CENTER_ALIGNMENT);
			    	    
	    //intialise JLabels and set alignment				
		minHeightLabel = new JLabel("Minumum Height (ft):");
		minHeightLabel.setAlignmentX(CENTER_ALIGNMENT);
		minHeightupdateLabel = new JLabel("--");
		minHeightupdateLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		maxHeightLabel = new JLabel("Maximum Height (ft):");
		maxHeightLabel.setAlignmentX(CENTER_ALIGNMENT);
		maxHeightupdateLabel = new JLabel("--");
		maxHeightupdateLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		waveRatingLabel = new JLabel("Wave Ratings:");
		waveRatingLabel.setAlignmentX(CENTER_ALIGNMENT);
		waveRatingPanel = new JPanel(new GridLayout(1,0,0,0));
		
		primarySwellHeigtLabel = new JLabel("Primary Swell Height (ft):");
		primarySwellHeigtLabel.setAlignmentX(CENTER_ALIGNMENT);
		primarySwellHeigtUpdateLabel = new JLabel("--");
		primarySwellHeigtUpdateLabel.setAlignmentX(CENTER_ALIGNMENT);
				
		swellPeriodLabel = new JLabel("Primary Swell Rate (seconds): ");
		swellPeriodLabel.setAlignmentX(CENTER_ALIGNMENT);
		swellPeriodUpdateLabel = new JLabel("--");
		swellPeriodUpdateLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		primarySwellDirectionLabel = new JLabel("Primary Swell Direction:");
		primarySwellDirectionLabel.setAlignmentX(CENTER_ALIGNMENT);
		primarySwellDirectionUpdateLabel = new JLabel("--");
		primarySwellDirectionUpdateLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		speedLabel = new JLabel("Wind Speed (mph):");
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
				
		temperatureLabel = new JLabel("Temperature (celsius):");
		temperatureLabel.setAlignmentX(CENTER_ALIGNMENT);
		temperatureUpdateLabel = new JLabel("--");
		temperatureUpdateLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		infoUpdatePanel.add(infoLabel);
		infoUpdatePanel.add(new JLabel(""));
		
		infoUpdatePanel.add(minHeightLabel);  
		infoUpdatePanel.add(minHeightupdateLabel); //yes
		infoUpdatePanel.add(maxHeightLabel);  
		infoUpdatePanel.add(maxHeightupdateLabel);// yes
		
		//star rating
		infoUpdatePanel.add(waveRatingLabel);
		infoUpdatePanel.add(waveRatingPanel);
		
		
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
		questionPanel.add(likePrediction);
		infoUpdatePanel.add(questionPanel);
		answerPanel.add(yesButton);
		answerPanel.add(noButton);
		infoUpdatePanel.add(answerPanel);
		yesButton.addActionListener(new FeedbackYesResponseListener(this,model));
		noButton.addActionListener(new FeedbackNoResponseListener(this,model));
		
		cancelPanel.add(cancel);
		cancel.addActionListener(new BackListener(this,model));
		//footer
		footerLabel = new JLabel("Acknowledgement: Data kindly provided by Magicseaweed.");
		footerLabel.setBackground(Color.CYAN);
		footerLabel.setOpaque(true);
		cancelPanel.add(footerLabel);
				
	    //adding other JPanels to Control Panel
	    controlPanel.add(infoUpdatePanel);
	    controlPanel.add(cancelPanel);
	}
	
	//update wave Rating
	public void waveRating(PlainPrediction p)
	{
		waveRatingPanel.removeAll();
		
		int fadedRating = p.fadedRating;
		int solidRating = p.solidRating;
		int diff;
		
		String solidPath = "/stars/fullStar.jpg";
		String fadedPath = "/stars/halfStar.jpg";
		
		//checks the wave rating, add the right type of stars to guide user
		if(fadedRating>solidRating)
		{
			diff = fadedRating - solidRating;
			for(int j=0; j<solidRating;j++)
			{
				waveRatingPanel.add(new JLabel(new ImageIcon(getClass().getResource(solidPath))));
			}
			
			for(int j=0; j<diff;j++)
			{
				waveRatingPanel.add(new JLabel(new ImageIcon(getClass().getResource(fadedPath))));
			}
		}
		
		if(solidRating>fadedRating || solidRating == fadedRating)
		{
			for(int j=0; j<solidRating;j++)
			{
				waveRatingPanel.add(new JLabel(new ImageIcon(getClass().getResource(solidPath))));
			}
		}
	}
		

	//updates the forecast display accordingly
	public void updateForcastDetails(PlainPrediction p)
	{		
		//updates all JLabels for next forecast to rate
		minHeightupdateLabel.setText("" + p.minBreakHeight);
		maxHeightupdateLabel.setText("" + p.maxBreakHeight);
			
		waveRating(p);
			
		primarySwellHeigtUpdateLabel.setText("" + p.primarySwellHeight);
		swellPeriodUpdateLabel.setText("" + p.primarySwellPeriod);
		primarySwellDirectionUpdateLabel.setText("" + p.primarySwellDirection);
		speedUpdateLabel.setText("" + p.windSpeed);
		windDirectionUpdateLabel.setText("" + p.windDirection);
		
		//gets the right weather image for each forecast
		String weatherFileName = "/weather/" + p.weather + ".png";
			BufferedImage logo ; 
			try 
		     {
				 logo = ImageIO.read(getClass().getResource(weatherFileName));
				 weatherUpdateLabel.setIcon(new ImageIcon(logo));
		     } 
		     catch (IOException e) 
		     {
				e.printStackTrace();
		     }
		     
			
		temperatureUpdateLabel.setText("" + p.temperature);
	}


	//gets the next forecast  
	public void nextPredictionFeedback(PredictionStatus status) 
	{
		//update status for previous feedback
		updateFeedbackResponse(status);		
		
		i=i+1;
		//gets next feedback for user to respond
		if(i<recommends.size())
		{	
	      	updateForcastDetails(recommends.get(i));
	    }
		
		//if user gives feedback for everything
		if(i==recommends.size())
		{
			this.dispose();
		}	
	}
	
	
	//this method will update, yes or no - whether user like the forecast or not
	//and write the result to trainee_file 
	public void updateFeedbackResponse(PredictionStatus status) 
	{
		PlainPrediction current = recommends.get(i);
		current.setStatus(status);
		PredictionWriter writer = new PredictionWriter("user_data/labeled_predictions.arff");
		writer.writeToFile(current);
	}

	}
	
	
	
