package main;

import javax.swing.JFrame;

import view.ResultsView;

public class Main
{

   public static void main(String[] args)
   {

    // VoterWindow vw = new VoterWindow();
     //vw.setComponents();
      //AdminWindow win = new AdminWindow();
      JFrame frame = new JFrame();
      //NewPositionPanel pan = new NewPositionPanel();
        ResultsView pan = new ResultsView();   
      frame.add(pan);
      frame.setVisible(true); 
   }

}
