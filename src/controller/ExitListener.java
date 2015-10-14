package controller;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Notifier;


public class ExitListener implements ActionListener
{
   private Window w;

   //Listener to dispose windows
   
   public ExitListener(Window w, Notifier model)
   {
      this.w = w;
   }

   // dispose window
   public void actionPerformed(ActionEvent e)
   {
		w.dispose();
		System.exit(0);
   }

}