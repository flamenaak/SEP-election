package main;

import controller.Controller;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import model.Candidate;
import model.Election;
import model.Position;
import view.AdminWindow;
import view.LogInWindow;
import view.NewCandidatePanel;
import view.VoterWindow;
import javax.swing.JFrame;
import view.NewPositionPanel;

public class Main
{
   public static void main(String[] args)
   {
      Controller con = new Controller();
	   AdminWindow win = new AdminWindow(con);
   }
}
