package view;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.ExitListener;
import controller.PredictorViewCloseListener;


public class PredictorView extends JFrame
{
	private JLabel welcome = new JLabel("Welcome");
	private JButton setLocation = new JButton("Add/Change Surf Location");
	private JButton forecastFeedback = new JButton("Forecast Feedback");
	private JButton exit = new JButton("exit");
	// constructor
	public PredictorView()
    {
      Container contentPane = (JPanel) getContentPane();
      setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
      
      contentPane.add(welcome,BorderLayout.NORTH);
      contentPane.add(setLocation, BorderLayout.CENTER);
      contentPane.add(forecastFeedback, BorderLayout.CENTER);
      contentPane.add(exit,BorderLayout.SOUTH);
      
      //listener for closing the window
      this.addWindowListener(new PredictorViewCloseListener(this));
      
      //adding exit  button listener
      exit.addActionListener(new ExitListener(this));

      setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
      setBounds(100, 100, 800, 600);
      setVisible(true);

	   }
}



