package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;

import view.PredictorView;

public class PredictorViewCloseListener implements WindowListener, ActionListener
{

   private PredictorView view;

   // constructor
   public PredictorViewCloseListener(PredictorView view)
   {
      this.view = view;
   }

   public void windowClosed(WindowEvent arg0)
   {
      confirm();
   }

   public void windowClosing(WindowEvent arg0)
   {
      confirm();
   }

   // dialog box to confirm exit application
   private void confirm()
   {
      int returnValue = JOptionPane.showConfirmDialog(view,
            "Exit Application?", "Alert!", JOptionPane.YES_NO_OPTION);

      if (returnValue == JOptionPane.YES_OPTION)
      {
         System.exit(0);
      }
   }

   public void windowActivated(WindowEvent arg0)
   {
   }

   public void windowDeactivated(WindowEvent arg0)
   {
   }

   public void windowDeiconified(WindowEvent arg0)
   {
   }

   public void windowIconified(WindowEvent arg0)
   {
   }

   public void windowOpened(WindowEvent arg0)
   {
   }

   public void actionPerformed(ActionEvent arg0)
   {
      confirm();
   }

}
