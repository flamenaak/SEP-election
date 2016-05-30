package view;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.Controller;

public class AdminWindow extends JFrame implements ChangeListener, FocusListener
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
      tabPane.addChangeListener(this);
      
   }
   
   public void refreshPanel()
   {   
      removeTabs();
      createGUI();
      build();  
   }
   
   public void removeTabs()
   {
      tabPane.remove(candiPanel);
      tabPane.remove(welcome);
      tabPane.remove(positionPanel);
      tabPane.remove(viewPanel);
      tabPane.remove(passwordPanel);
      tabPane.remove(resultsPanel);
      createGUI();
      build();
   }

   @Override
   public void stateChanged(ChangeEvent e)
   {
      if(e.getSource().equals(candiPanel))
      {
         
         System.out.println(78);
        refreshPanel();
      } 
      
   }

   @Override
   public void focusGained(FocusEvent e)
   {
      if(e.getSource().equals(candiPanel))
      {
         ((NewCandidatePanel) candiPanel).initialize();
         ((NewCandidatePanel) candiPanel).build();
      }
   }

   @Override
   public void focusLost(FocusEvent e)
   {
      // TODO Auto-generated method stub
      
   }
}
