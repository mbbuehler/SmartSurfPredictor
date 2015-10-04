package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.ExitListener;
import controller.FeedbackNoResponseListener;
import controller.FeedbackYesResponseListener;
import controller.JLabelListener;
import model.Notifier;
import model.PlainPrediction;
import model.PredictionStatus;
import model.PredictionTime;
import model.PredictionWriter;
import model.Spot;

public class PopUpView extends JDialog
{
	private JPanel mainPanel,logoPanel,spotPanel,displayPanel,exitPanel;
	private JButton exitButton;
	
	private PredictorView view; 
	private Notifier model;
	private HashMap<Spot, PlainPrediction> ratedPredictionsMap;
	private ArrayList<JLabel> nameLabels = new ArrayList<JLabel>();
	private ArrayList<PlainPrediction> prediction = new ArrayList<PlainPrediction>();
	
	//Variables for forecast display
	private JButton yesButton = new JButton("Yes");
	private JButton noButton = new JButton("No");
	private JLabel infoLabel, spotName,spotNameUpdate;
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
	private JLabel recommendationLabel,recommendationUpdateLabel;
	private JLabel likePrediction;
	private JPanel infoUpdatePanel,questionPanel,answerPanel;
		
	//testing material to remove - PredicView,TestingListener

	public PopUpView(PredictorView v, Notifier m, PredictionTime time) 
	{
		this.view = v;
		this.model = m;
		
		//getting rated forecast
		ratedPredictionsMap = model.getPredictionManager().getRatedPredictions(time);
		
		//adds logo
		addLogo();
		
		exitPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    exitButton = new JButton("Exit");
	    exitButton.addActionListener(new ExitListener(this,model));
		exitPanel.add(exitButton);
		
		mainPanel = new JPanel(new BorderLayout());
		spotPanel = new JPanel(new GridLayout(0,1,0,0));
		displayPanel = new JPanel(new GridLayout(0,1,0,0));
		//initalise all jLabels for forecast display
		intialiseLabels();
		
		//display spot list
		displaySpotNames();
		spotPanel.add(exitPanel);
		
		mainPanel.add(spotPanel,BorderLayout.WEST);
		mainPanel.add(displayPanel,BorderLayout.CENTER);
		mainPanel.add(logoPanel,BorderLayout.NORTH);
		add(mainPanel);
		
		// set the window size 
		this.setSize(500,500);
		this.setUndecorated(true);
		this.setVisible(true);
		this.toFront();
		this.setAlwaysOnTop(true);
	    this.requestFocus();
	}
	
	//creates JLabels,adds listener to display details
	public void displaySpotNames()
	{
		spotPanel.add(new JLabel("Click for forecast"));
		int i = 0;
		
		for(Map.Entry<Spot, PlainPrediction> p: ratedPredictionsMap.entrySet())
		{
			prediction.add(p.getValue());
			
			//create JLabel, add listener, add it to display
			nameLabels.add(new JLabel(prediction.get(i).getSpotName()));
			nameLabels.get(i).addMouseListener(new JLabelListener(this,model,prediction.get(i)));
			spotPanel.add(nameLabels.get(i));
			i++;
		}
		//set by default to first spot
		updateLabels(prediction.get(0));
	}
	
	
	public void updateLabels(PlainPrediction p)
	{
		displayForecast(p);
		
		for(JLabel j:nameLabels)
		{
			j.setForeground(Color.BLACK);
			
			if(j.getText().equals(p.getSpotName()))
			{
				j.setForeground(Color.blue);
			}
		}

	}
	
	//updates user response to last forest,write to trainee_file, get next forecast
	public void nextPrediction(PlainPrediction old)
	{
		//update status for previous feedback
		writeResponse(old);
		
		boolean found = false;
		for(PlainPrediction plain:prediction)
		{
			if(old.getSpotId() == plain.getSpotId())
			{
				//get next forecast based on old forecast
				found = true;
				continue;
			}
			if(found == true)
			{
				//gets next forecast
				updateLabels(plain);
				break;
			}
		}
	}
	
	//this method write the result to trainee_file 
	public void writeResponse(PlainPrediction p) 
	{
		PredictionWriter writer = new PredictionWriter("user_data/labeled_predictions.arff");
		writer.writeToFile(p);
	}
	

	//display forecast 
	public void displayForecast(PlainPrediction p)
	{
		//removes all old button forecast listeners and new once are created
		for(ActionListener act : yesButton.getActionListeners()) 
		{
			yesButton.removeActionListener(act);
		}
		for(ActionListener act : noButton.getActionListeners()) 
		{
			noButton.removeActionListener(act);
		}
		
		//updates all JLabels,images 
		spotNameUpdate.setText(" "+p.spotName);
		minHeightupdateLabel.setText(" " + p.minBreakHeight);
		maxHeightupdateLabel.setText(" " + p.maxBreakHeight);
					
		waveRating(p);
					
		primarySwellHeigtUpdateLabel.setText(" " + p.primarySwellHeight);
		swellPeriodUpdateLabel.setText(" " + p.primarySwellPeriod);
		primarySwellDirectionUpdateLabel.setText(" " + p.primarySwellDirection);
		speedUpdateLabel.setText(" " + p.windSpeed);
		windDirectionUpdateLabel.setText(" " + p.windDirection);
		
		weatherImageUpdate(p);
		
		temperatureUpdateLabel.setText(" " + p.temperature);
		
		recommendationImageUpdate(p);
		
		yesButton.addActionListener(new FeedbackYesResponseListener(this,model,p));
		noButton.addActionListener(new FeedbackNoResponseListener(this,model,p));
		
	}
	
	//intialise all jlabels for display
	public void intialiseLabels()
	{
		questionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		answerPanel = new JPanel(new GridLayout(1,2,2,0));
		infoUpdatePanel = new JPanel(new GridLayout(0,2,0,0));
			
		infoLabel = new JLabel("Surf Prediction Feedback");
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
			    	    
	    //intialise JLabels and set alignment
		spotName = new JLabel("Surf Spot: ");
		spotName.setHorizontalAlignment(SwingConstants.RIGHT);
		spotNameUpdate = new JLabel("--");
		//spotNameUpdate.setAlignmentX(CENTER_ALIGNMENT);
				
		minHeightLabel = new JLabel("Minumum Height (ft): ");
		minHeightLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		minHeightupdateLabel = new JLabel("--");
		
		maxHeightLabel = new JLabel("Maximum Height (ft): ");
		maxHeightLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		maxHeightupdateLabel = new JLabel("--");
		
		waveRatingLabel = new JLabel("Wave Ratings: ");
		waveRatingLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		waveRatingPanel = new JPanel(new GridLayout(1,0,0,0));
		
		primarySwellHeigtLabel = new JLabel("Swell Height (ft): ");
		primarySwellHeigtLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		primarySwellHeigtUpdateLabel = new JLabel("--");
				
		swellPeriodLabel = new JLabel("Swell Rate (seconds): ");
		swellPeriodLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		swellPeriodUpdateLabel = new JLabel("--");
		
		primarySwellDirectionLabel = new JLabel("Swell Direction: ");
		primarySwellDirectionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		primarySwellDirectionUpdateLabel = new JLabel("--");
		
		speedLabel = new JLabel("Wind Speed (mph): ");
		speedLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		speedUpdateLabel = new JLabel("--");
				
		windDirectionLabel = new JLabel("Wind Direction: ");
		windDirectionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		windDirectionUpdateLabel = new JLabel("--");
				
		weatherLabel = new JLabel("Weather: ");
		weatherLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		weatherUpdateLabel = new JLabel();
						
		temperatureLabel = new JLabel("Temperature (celsius): ");
		temperatureLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		temperatureUpdateLabel = new JLabel("--");
		
		recommendationLabel = new JLabel("Surfing condition are: ");
		recommendationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		recommendationUpdateLabel = new JLabel();
		
		infoUpdatePanel.add(infoLabel);
		infoUpdatePanel.add(new JLabel(""));
		
		infoUpdatePanel.add(spotName);
		infoUpdatePanel.add(spotNameUpdate);
		infoUpdatePanel.add(minHeightLabel);  
		infoUpdatePanel.add(minHeightupdateLabel); 
		infoUpdatePanel.add(maxHeightLabel);  
		infoUpdatePanel.add(maxHeightupdateLabel);
		
		//star rating
		infoUpdatePanel.add(waveRatingLabel);
		infoUpdatePanel.add(waveRatingPanel);
		
		
		infoUpdatePanel.add(primarySwellHeigtLabel);
		infoUpdatePanel.add(primarySwellHeigtUpdateLabel); 
		infoUpdatePanel.add(swellPeriodLabel);
		infoUpdatePanel.add(swellPeriodUpdateLabel); 
		
		infoUpdatePanel.add(primarySwellDirectionLabel);
		infoUpdatePanel.add(primarySwellDirectionUpdateLabel); 
		infoUpdatePanel.add(speedLabel);
		infoUpdatePanel.add(speedUpdateLabel); 
		infoUpdatePanel.add(windDirectionLabel);
		infoUpdatePanel.add(windDirectionUpdateLabel);
		
		infoUpdatePanel.add(weatherLabel);
		infoUpdatePanel.add(weatherUpdateLabel); 
		infoUpdatePanel.add(temperatureLabel);
		infoUpdatePanel.add(temperatureUpdateLabel);
		infoUpdatePanel.add(recommendationLabel);
		infoUpdatePanel.add(recommendationUpdateLabel); 
		
		likePrediction = new JLabel("Do you like this prediction ?");
		likePrediction.setHorizontalAlignment(SwingConstants.RIGHT);
		questionPanel.add(likePrediction);
		infoUpdatePanel.add(questionPanel);
		answerPanel.add(yesButton);
		answerPanel.add(noButton);
		infoUpdatePanel.add(answerPanel);
								
	    //adding other JPanels to display
		displayPanel.add(infoUpdatePanel);
		
	}
	
	//update recommendation image, if a user should surf or not
	public void recommendationImageUpdate(PlainPrediction p)
	{
		//gets the right recommendation image for each forcast
		String path;
		if(p.getStatus() == PredictionStatus.ACCEPTED)
		{
			path = "/response/good.jpg";
		}
		else if (p.getStatus() == PredictionStatus.REJECTED)
		{
			path = "/response/bad.jpg";
		}
		else
		{
			path = "/response/notsure.jpg";
		}
		
		
		BufferedImage logo ; 
		try 
		{
			logo = ImageIO.read(getClass().getResource(path));
			recommendationUpdateLabel.setIcon(new ImageIcon(logo));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
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
	
	
	//update weather image
	public void weatherImageUpdate(PlainPrediction p)
	{
		//gets the right weather image for each forcast
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
	}
	
	//add logo for view
 	public void addLogo()
	 {
		BufferedImage logo;
		JLabel picLabel;
		JLabel welcome = new JLabel("Smart Surf Predictor");
		welcome.setAlignmentX(CENTER_ALIGNMENT);
		//adds logo and JLabel
		try 
		{
			logo = ImageIO.read(getClass().getResource("/images/surfSmall.jpg"));
			picLabel = new JLabel(new ImageIcon(logo)); 
			picLabel.setAlignmentX(CENTER_ALIGNMENT);
			logoPanel = new JPanel();
			logoPanel.add(picLabel);
			logoPanel.add(welcome);
		}
	    catch (IOException e) 
	    {
	    	e.printStackTrace();
	    }
	 }

 	
}
