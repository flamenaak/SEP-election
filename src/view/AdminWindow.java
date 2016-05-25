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
   private JTabbedPane tabPane;
   private TextField name;
   private JLabel nameLab, passLab;
   private GridBagConstraints c;
   private JButton butt;
   private JPanel candiPanel;
   // private Controller controller;

   public AdminWindow(/* Controller controller */)
   {
      super();
      // this.controller = controller;
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
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
   }

   private void createComponents()
   {
      tabPane = new JTabbedPane();
      candiPanel = new JPanel();
   }

   private void createGUI()
   {
      tabPane.add(candiPanel, "Add candidate");
      candiPanel.setOpaque(false);
      tabPane.addTab("Firzt", new JPanel());
      tabPane.addTab("Firzt", new JPanel());

	  setResizable(false);
	  
	  candiPanel = new NewCandidatePanel();
      candiPanel.setOpaque(false);
      
      tabPane.add("1st", candiPanel);
      tabPane.add("3rd", new Label("dsa"));
      
      tabPane.setVisible(true);
      add(tabPane);
      
      tabPane.repaint();
   }

   public void actionPerformed(ActionEvent action)
   {
   }

}
