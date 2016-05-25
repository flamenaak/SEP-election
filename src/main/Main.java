package main;

import javax.swing.JFrame;

import view.NewPositionPanel;

public class Main
{

   public static void main(String[] args)
   {

    // VoterWindow vw = new VoterWindow();
     //vw.setComponents();
      //AdminWindow win = new AdminWindow();
      JFrame frame = new JFrame();
      NewPositionPanel pan = new NewPositionPanel();
      frame.setVisible(true);
      frame.add(pan);
      
   }

}
