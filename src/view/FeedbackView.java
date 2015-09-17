package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import model.Prediction;
import model.PredictionStatus;
import model.PredictionWriter;
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
	
	private JPanel controlPanel,infoUpdatePanel,questionPanel,answerPanel,cancelPanel;
	
	private int i=0;
	
	private PredictionWriter writer;
	
	public FeedbackView(PredictorView v, Notifier m) 
	{
		this.view = v;
		this.model = m;
		this.recommends = model.getPredictionManager().getRandomUnlabeledPlainPredictions();
		
		Container cp = this.getContentPane();
	    cp.setLayout(new BorderLayout());
	    
	    IntialiseJLablesPanels();
	    cp.add(controlPanel);
	    
	    UpdateForcastDetails(recommends.get(i));
	    
	    // set the window size by itself
	    pack();
	    setLocationRelativeTo(view);
	    setModal(true);
	}
	
	
	//method intilalise JPanels & JLabels
	private void IntialiseJLablesPanels() 
	{
		controlPanel = new JPanel(new GridLayout(0,1,0,0));
		questionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		answerPanel = new JPanel(new GridLayout(1,2,2,0));
		cancelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		infoUpdatePanel = new JPanel(new GridLayout(0,2,0,0));
			
		infoLabel = new JLabel("Surf Prediction Feedback");
		infoLabel.setAlignmentX(CENTER_ALIGNMENT);
			    	    
	    //intialise JLabels and set alignment
		spotName = new JLabel("Surf Spot:");
		spotName.setAlignmentX(CENTER_ALIGNMENT);
		spotNameUpdate = new JLabel("--");
		spotNameUpdate.setAlignmentX(CENTER_ALIGNMENT);
				
		minHeightLabel = new JLabel("Minumum Height (ft):");
		minHeightLabel.setAlignmentX(CENTER_ALIGNMENT);
		minHeightupdateLabel = new JLabel("--");
		minHeightupdateLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		maxHeightLabel = new JLabel("Maximum Height (ft):");
		maxHeightLabel.setAlignmentX(CENTER_ALIGNMENT);
		maxHeightupdateLabel = new JLabel("--");
		maxHeightupdateLabel.setAlignmentX(CENTER_ALIGNMENT);
				
		primarySwellHeigtLabel = new JLabel("Primary Swell Height (ft):");
		primarySwellHeigtLabel.setAlignmentX(CENTER_ALIGNMENT);
		primarySwellHeigtUpdateLabel = new JLabel("--");
		primarySwellHeigtUpdateLabel.setAlignmentX(CENTER_ALIGNMENT);
				
		swellPeriodLabel = new JLabel("Primary Swell Rate (seconds):");
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
		questionPanel.add(likePrediction);
		infoUpdatePanel.add(questionPanel);
		answerPanel.add(yesButton);
		answerPanel.add(noButton);
		infoUpdatePanel.add(answerPanel);
		yesButton.addActionListener(new FeedbackYesResponseListener(this,model));
		noButton.addActionListener(new FeedbackNoResponseListener(this,model));
		
		cancelPanel.add(cancel);
		cancel.addActionListener(new ExitListener(this,model));
		
	    //adding other JPanels to Control Panel
	    controlPanel.add(infoUpdatePanel);
	    controlPanel.add(cancelPanel);
	    		    
	}

	//updates the forecast display accordingly
	public void UpdateForcastDetails(SpotPrediction sp)
	{		
			spotNameUpdate.setText(sp.getS().getName()+", "+sp.getS().getState()+", "+sp.getS().getCountry());
			minHeightupdateLabel.setText(""+sp.getPlain().getMinBreakHeight()); 
			maxHeightupdateLabel.setText(""+sp.getPlain().getMaxBreakHeight());
			primarySwellHeigtUpdateLabel.setText(""+sp.getPlain().getPrimarySwellHeight());
			swellPeriodUpdateLabel.setText(""+sp.getPlain().getPrimarySwellPeriod()); 
			primarySwellDirectionUpdateLabel.setText(""+sp.getPlain().getPrimarySwellDirection()); 
			speedUpdateLabel.setText(""+sp.getPlain().getWindSpeed()); 
			windDirectionUpdateLabel.setText(""+sp.getPlain().getWindDirection()); 
			
			String weatherFileName = "/weather/"+sp.getPlain().getWeather()+".png";
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
		     
			
		   temperatureUpdateLabel.setText(""+sp.getPlain().getTemperature()); 	
	}


	//gets the next forecast  
	public void NextPredictionFeedback(PredictionStatus status) 
	{
		//update status for previous feedback
		UpdateFeedbackResponse(status);		
		
		i=i+1;
		if(i<recommends.size())
		{	
	      	UpdateForcastDetails(recommends.get(i));
	    }
		
		//if user gives feedback for everything
		if(i==recommends.size())
		{
			this.dispose();
		}	
	}
	
	
	//this method will update, yes or no - whether user like the forecast or not
	//and write the result to trainee_file - will belater used for machinean learning
	public void UpdateFeedbackResponse(PredictionStatus status) 
	{
		recommends.get(i).getPrediction().setStatus(status);
		ArrayList<Prediction> list = new ArrayList<Prediction>();
		Writer(list);
	}


	public void Writer(ArrayList<Prediction> list) 
	{
		try 
		{
			writer = new PredictionWriter("user_data/predictions_labeled.arff", true);
			
			if(i==0)
			{
				list.add(recommends.get(i).getPrediction());
				writer.writePredictions(list);
			}
			else if(i<recommends.size())
			{
				//writer.addLabeledPrediction(recommends.get(i).getPrediction());
			}
			
			writer.close();
			assert (true);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		

	}
	
	
	
}
