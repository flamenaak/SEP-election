package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javafx.collections.SetChangeListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;

public class AdminWindow extends JFrame implements ActionListener
{
   private JFrame frame;
   private JTabbedPane tabPane;
   private TextField name;
   private JLabel nameLab, passLab;
   private GridBagConstraints c;
   private JButton butt;
   private JPanel candiPanel, positionPanel, viewPanel, resultsPanel, passwordPanel, welcome;
   // private Controller controller;

   public AdminWindow(/* Controller controller */)
   {
      // this.controller = controller;
      this.createComponents();
      this.createGUI();
      this.setFrame();
   }

   private void setFrame()
   {
      Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
      frame.setSize(500, 300);
      int x = (int) ((s.getWidth() - frame.getWidth()) / 2);
      int y = (int) ((s.getHeight() - frame.getHeight()) / 2);

      frame.setLocation(x, y);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   }

   private void createComponents()
   {
      frame = new JFrame();
      tabPane = new JTabbedPane();
      candiPanel = new JPanel();
   }

   private void createGUI()
   {
      setResizable(false);
      // candiPanel, positionPanel, viewPanel, resultsPanel, passwordPanel;

      candiPanel = new NewCandidatePanel();
      candiPanel.setOpaque(false);

      positionPanel = new NewPositionPanel();
      positionPanel.setOpaque(false);
      
      viewPanel = new CandidateNPositionPanel();
      viewPanel.setOpaque(false);
      
      passwordPanel = new PasswordChangeWindow();
      passwordPanel.setOpaque(false);
      
      resultsPanel = new ResultsView();
      resultsPanel.setOpaque(false);
      
      welcome = new WelcomePanel();
      welcome.setOpaque(false);
      
      tabPane.add("Welcome", welcome);
      tabPane.add("New Candidate", candiPanel);
      tabPane.add("New Position", positionPanel);
      tabPane.add("Registered entities", viewPanel);
      tabPane.add("Change password", passwordPanel);
      tabPane.add("Votes Results", resultsPanel);
      

      tabPane.setVisible(true);
      frame.add(tabPane);

      tabPane.repaint();
   }

   public void actionPerformed(ActionEvent action)
   {
   }

}
