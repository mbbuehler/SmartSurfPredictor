package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ExitListener;
import controller.PredictorViewCloseListener;


public class PredictorView extends JFrame
{
	private BufferedImage logo;
	private JLabel picLabel;
	private JLabel welcome = new JLabel("Welcome to Smart Surf Predictor");
	private JPanel introPanel;

	private JButton setLocation = new JButton("Add/Change Surf Location");
	private JButton forecastFeedback = new JButton("Forecast Feedback");
	
	private JButton exit = new JButton("exit");
	private JPanel exitPanel;
	
	
	// constructor
	public PredictorView()
    {
		//Box box = Box.createVerticalBox();
		JPanel box = new JPanel(new GridLayout(0,1,10,10));
        //box.setBorder(new EmptyBorder(10,10,10,10));
		
        welcome.setAlignmentX(CENTER_ALIGNMENT);
        setLocation.setAlignmentX(CENTER_ALIGNMENT);
        forecastFeedback.setAlignmentX(CENTER_ALIGNMENT);
        
        exitPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        exitPanel.add(exit);
        //adding exit  button listener
        exit.addActionListener(new ExitListener(this));
        
        try 
        {
			logo = ImageIO.read(getClass().getResource("/images/surf.jpg"));
			picLabel = new JLabel(new ImageIcon(logo));
			picLabel.setAlignmentX(CENTER_ALIGNMENT);
			introPanel = new JPanel();
			introPanel.add(picLabel);
			introPanel.add(welcome);
		} 
        catch (IOException e) 
        {
			e.printStackTrace();
		}
                
        box.add(introPanel);
        box.add(setLocation);
        box.add(forecastFeedback);
        box.add(exitPanel);
    
        //add(box);
        add(box,BorderLayout.CENTER);
        
        //listener for closing the window
        this.addWindowListener(new PredictorViewCloseListener(this));
      
        setBounds(100, 100, 800, 600);
        setVisible(true);

	   }
}



