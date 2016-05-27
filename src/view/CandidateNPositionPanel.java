package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import controller.Controller;

public class CandidateNPositionPanel extends JPanel
{
   private JList list;
   private JComboBox cBox;
   private GridBagConstraints c;
   private JLabel lab;
   private Controller controller;
   
   public CandidateNPositionPanel(Controller controller)
   {
      super(new GridBagLayout());
      this.controller = controller;
      initialize();
      build();
   }

   private void build()
   {
      cBox.setPreferredSize(new Dimension(100,25));
      list.setPreferredSize(new Dimension(200,150));
      c.gridy = 0;
      c.gridx = 0;
      add(lab, c);
      
      c.gridy = 1;
      add(cBox, c);
      
      c.gridheight = 1;
      c.gridx = 1;
      add(list, c); 
   }

   private void initialize()
   {
      list = new JList();
      cBox = new JComboBox();
      c = new GridBagConstraints();
      lab = new JLabel("Select Position");      
   }
}
