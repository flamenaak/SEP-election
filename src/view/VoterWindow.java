package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.Controller;
import model.Election;
import model.Position;

public class VoterWindow extends JFrame implements ActionListener
{
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

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(300, 200);
      setLayout(new FlowLayout());

      int x = (int) ((s.getWidth() - getWidth()) / 2);
      int y = (int) ((s.getHeight() - getHeight()) / 2);
      setLocation(x, y);
      
      voter = new JPanel();
      ok = new JButton("OK");
      logOut = new JButton("Log out");
      
      candidateBox = new JComboBox<String>();
      positionBox = new JComboBox<String>(controller.getPositionsToCombo());
      
      positionBox.setSelectedItem(null);
      candidateBox.setSelectedItem(null);
      
      candidateBox.setEditable(false);
      positionBox.setEditable(false);
      
      candidateBox.setPreferredSize(new Dimension(100, 25));
      positionBox.setPreferredSize(new Dimension(100, 25));
      
      
      
      JPanel np = new JPanel();
      
      np.add(positionBox);
      np.add(candidateBox);
      voter.add(ok);
      voter.add(logOut);

      ok.addActionListener(this);
      logOut.addActionListener(this);
      candidateBox.addActionListener(this);
      positionBox.addActionListener(this);
      
      add(np);
      add(voter);
      setVisible(true);
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      if(e.getSource().equals(positionBox))
      {
         String positionName = (String) positionBox.getSelectedItem();
         position = dbm.getPosition(positionName);
      }
      else if(e.getSource().equals(candidateBox))
      {
         String name = (String) candidateBox.getSelectedItem();
         String pos = (String) positionBox.getSelectedItem();
         controller.addVote(name, pos);
      }
      else if(e.getSource().equals(logOut))
      {
         controller.voterLogOut();
      }
      else if(e.getSource().equals(ok))
      {         
         JOptionPane.showMessageDialog(new JFrame(), "Thanks for voting");
         controller.okPressed();
      }
   }
   
   
   
   
  
}
