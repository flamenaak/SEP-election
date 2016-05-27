package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.Controller;
import model.Election;
import model.Position;

public class VoterWindow 
{
   private static JFrame mainFrame;
   private static JComboBox<String> candidateBox;
   private static JComboBox<String> positionBox;
   private static Position position;
   private static Election election;
   private JPanel comboBoxPanel;
   private JPanel voter;
   private JButton ok;
   private JButton logOut;
   private static Controller controller;
   
   public VoterWindow(Controller cont)
   {
      controller = cont;
      setComponents();
   }
   
   private void setComponents()
   {

      Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
      mainFrame = new JFrame();

      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      mainFrame.setSize(300, 200);
      mainFrame.setLayout(new FlowLayout());

      int x = (int) ((s.getWidth() - mainFrame.getWidth()) / 2);
      int y = (int) ((s.getHeight() - mainFrame.getHeight()) / 2);
      mainFrame.setLocation(x, y);
      
      voter = new JPanel();
      ok = new JButton("OK");
      logOut = new JButton("Log out");
      
      candidateBox = new JComboBox<String>(controller.getCandidates());
      positionBox = new JComboBox<String>(controller.getPositions()); 
      
      candidateBox.setEditable(false);
      positionBox.setEditable(false);
      
      candidateBox.setPreferredSize(new Dimension(100, 25));
      positionBox.setPreferredSize(new Dimension(100, 25));
      
      candidateBox.addActionListener(new candidateBox());
      positionBox.addActionListener(new positionBox());
      
      JPanel np = new JPanel();
      
      np.add(positionBox);
      np.add(candidateBox);
      voter.add(ok);
      voter.add(logOut);

      ok.addActionListener(new okPressed());
      logOut.addActionListener(new logOutPressed());
      
      mainFrame.add(np);
      mainFrame.add(voter);
      mainFrame.setVisible(true);
   }

   public static class okPressed implements ActionListener
   {
      @Override
      public void actionPerformed(ActionEvent arg0)
      {
         controller.okPressed();
         JOptionPane.showMessageDialog(mainFrame, "Thanks for voting");
         controller.logOut();
      }          
   }
   
   public static class logOutPressed implements ActionListener
   {
      @Override
      public void actionPerformed(ActionEvent arg0)
      {
         controller.logOut(); 
      }
   }
   
   public static class candidateBox implements ActionListener
   {

      @Override
      public void actionPerformed(ActionEvent arg0)
      {
         int candidateIndex = candidateBox.getSelectedIndex();
         controller.vote(candidateIndex);
      }
      
   }
   
   public static class positionBox implements ActionListener
   {

      @Override
      public void actionPerformed(ActionEvent arg0)
      {
         int positionIndex = positionBox.getSelectedIndex();
         position = election.getPosition(positionIndex);
      }
      
   }
   
   
   
   
  
}
