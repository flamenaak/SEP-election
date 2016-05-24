package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Election;
import model.Position;

public class VoterWindow
{
   private JFrame mainFrame;
   private JComboBox<String> candidateBox;
   private JComboBox<String> positionBox;
   private Position position;
   private Election election;
   private JPanel comboBoxPanel;
   private JPanel voter;
   private JButton ok;
   private JButton back;
   
   
   public void setComponents()
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
      ok = new JButton("ok");
      back = new JButton("back");
      
      candidateBox = new JComboBox<String>(); /*after merging, it might need a bit change --position.toStringArray()*/
      positionBox = new JComboBox<String>(); /*need a to string.. or a method in election which returns a string of positions*/
      candidateBox.setEditable(false);
      positionBox.setEditable(false);
      
      //comboBoxPanel.add(positionBox);
      //comboBoxPanel.add(candidateBox);
      voter.add(positionBox);
      voter.add(candidateBox);
      voter.add(ok);
      voter.add(back);

      mainFrame.add(comboBoxPanel);
      mainFrame.add(positionBox);
      mainFrame.add(voter);
      mainFrame.setVisible(true);
   }
}
