package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
      build();
      add(tabPane);
      if(controller.getElection())
         disableTabs("start");
      if(!controller.getElection())
         disableTabs("stop");
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
      setResizable(true);
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

      tabPane.repaint();
      if (controller.getElection() == true)
      {
         candiPanel.setEnabled(false);
         positionPanel.setEnabled(false);
         viewPanel.setEnabled(false);
      }
   }

   public void build()
   {
      tabPane.add("Welcome", welcome);
      tabPane.add("New Candidate", candiPanel);
      tabPane.add("New Position", positionPanel);
      tabPane.add("Registered entities", viewPanel);
      tabPane.add("Change password", passwordPanel);
      tabPane.add("Votes Results", resultsPanel);

      tabPane.setVisible(true);
   }

   public void refreshPanel()
   {
      ((ResultsView) resultsPanel).getNewCombo();
      ((CandidateNPositionPanel) viewPanel).getNewCombo();
      ((NewCandidatePanel) candiPanel).getNewCombo();

   }

   public void removeTabs()
   {
     for(int i =0; i < tabPane.getComponentCount(); i++)
     {
        tabPane.removeAll();
     }
   }

   public void stateChanged(ChangeEvent e)
   {
      if (e.getSource().equals(candiPanel))
      {

         System.out.println(78);
         refreshPanel();
      }

   }

   public void disableTabs(String string)
   {
      switch (string)
      {
         case "start":       
            removeTabs();
            tabPane.add("Welcome", welcome);
            tabPane.add("Change password", passwordPanel);
            tabPane.add("Votes Results", resultsPanel);
            repaint();
            break;
         case "stop":     
            removeTabs();
            tabPane.add("Welcome", welcome);
            tabPane.add("New Candidate", candiPanel);
            tabPane.add("New Position", positionPanel);
            tabPane.add("Registered entities", viewPanel);
            tabPane.add("Change password", passwordPanel);
            tabPane.add("Votes Results", resultsPanel);
            repaint();
            break;
      }
   }
}
