package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import controller.Controller;

public class CandidateNPositionPanel extends JPanel implements ActionListener
{
   private JList list;
   private JComboBox cBox;
   private GridBagConstraints c;
   private JLabel lab;
   private Controller controller;
   private JButton deletePos;
   private JButton deleteCand;
   
   
   public CandidateNPositionPanel(Controller controller)
   {
      super(new GridBagLayout());
      this.controller = controller;
      initialize();
      build();
   }

   private void build()
   {
      try
      {
         cBox = new JComboBox(controller.getPositionsToCombo());
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      
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
      
      add(deletePos);
      add(deleteCand);
   }

   private void initialize()
   {
      list = new JList();
      cBox = new JComboBox();
      c = new GridBagConstraints();
      lab = new JLabel("Select Position");  
      deletePos = new JButton("Delete Position");
      deleteCand = new JButton("Delete Candidate");
   }

   @Override
   public void actionPerformed(ActionEvent action)
   {
      if(action.getSource().equals(cBox))
      {
         //add the list of candidates to the Jlist according what he picked in the drop down
        list = new JList(controller.getCandidatesToCombo(controller.getPosition(cBox.getSelectedItem().toString())));
      }
      if (action.getSource().equals(deletePos))
      {
         //delete position according, which one he selected
         controller.deletePosition(cBox.getSelectedItem().toString());
      }
      if (action.getSource().equals(deleteCand))
      {
         //delete candidate according which one he selected
         controller.deleteCandidate(cBox.getSelectedItem().toString(), list.getSelectedValue().toString());
      }
   }
}
