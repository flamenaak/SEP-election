package main;

<<<<<<< HEAD
import controller.Controller;
import model.Candidate;
import model.Election;
import model.Position;
import view.VoterWindow;
=======
import javax.swing.JFrame;

import view.NewPositionPanel;
>>>>>>> refs/remotes/origin/develop

public class Main
{

   public static void main(String[] args)
<<<<<<< HEAD
   { 
     VoterWindow vw = new VoterWindow();
     vw.setComponents();

=======
   {

    // VoterWindow vw = new VoterWindow();
     //vw.setComponents();
      //AdminWindow win = new AdminWindow();
      JFrame frame = new JFrame();
      NewPositionPanel pan = new NewPositionPanel();
        //ResultsView pan = new ResultsView();   
      frame.add(pan);
      frame.setVisible(true); 
>>>>>>> refs/remotes/origin/develop
   }

}
