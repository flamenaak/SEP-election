package view;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controller.Controller;

public class AdminWindow extends JFrame
{

   private JTabbedPane tabPane;
   private GridBagConstraints c;

   private JPanel candiPanel, positionPanel, viewPanel, resultsPanel, passwordPanel, welcome;
    private Controller controller;

   public AdminWindow(Controller controller)
   {
      super();
      this.controller = controller;
      this.createComponents();
      this.createGUI();
      this.setFrame();
   }

   private void setFrame()
   {
      Dimension s = Toolkit.getDefaultToolkit().getScreenSize();

      setSize(500, 300);
      int x = (int) ((s.getWidth() - getWidth()) / 2);
      int y = (int) ((s.getHeight() - getHeight()) / 2);

      setLocation(x, y);
      setVisible(true);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
   }

   private void createComponents()
   {
      tabPane = new JTabbedPane();
      candiPanel = new JPanel();
   }
   
   public void close()
   {
      this.setVisible(false);
   }

   private void createGUI()
   {
	  setResizable(false);
      // candiPanel, positionPanel, viewPanel, resultsPanel, passwordPanel;

      candiPanel = new NewCandidatePanel(controller);
      candiPanel.setOpaque(false);

      positionPanel = new NewPositionPanel(controller);
      positionPanel.setOpaque(false);
      
      viewPanel = new CandidateNPositionPanel(controller);
      viewPanel.setOpaque(false);
      
      passwordPanel = new PasswordChangeWindow(controller);
      passwordPanel.setOpaque(false);
      
      resultsPanel = new ResultsView(controller);
      resultsPanel.setOpaque(false);
      
      welcome = new WelcomePanel(controller);
      welcome.setOpaque(false);
      
      tabPane.add("Welcome", welcome);
      tabPane.add("New Candidate", candiPanel);
      tabPane.add("New Position", positionPanel);
      tabPane.add("Registered entities", viewPanel);
      tabPane.add("Change password", passwordPanel);
      tabPane.add("Votes Results", resultsPanel);
      
      tabPane.setVisible(true);
      add(tabPane);

      tabPane.repaint();
   }
}
