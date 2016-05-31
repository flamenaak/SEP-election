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
import model.Position;

public class CandidateNPositionPanel extends JPanel implements ActionListener
{
   private JList<String> list;
   private JComboBox<String> cBox;
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
      
      
      c.gridy = 0;
      c.gridx = 0;
      add(lab, c);
      
      c.gridy = 1;
      cBox.addActionListener(this);
      add(cBox, c);
      
      list.setPreferredSize(new Dimension(200,150));

      try {
      list.setListData(controller.getCandidatesToCombo(new Position(cBox.getSelectedItem().toString())));
      } catch (NullPointerException e) {
      }
     
      c.gridheight = 1;
      c.gridx = 1;
      add(list, c); 
      
      deletePos.addActionListener(this);
      deleteCand.addActionListener(this);
      
      c.gridy = 2;
      c.gridx = 0;
      add(deletePos, c);
      
      c.gridx = 1;
      add(deleteCand, c);
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
   
   public void getNewCombo()
   {
      remove(cBox);
      cBox = new JComboBox<String>(controller.getPositionsToCombo());
      cBox.setPreferredSize(new Dimension(100,25));
      cBox.addActionListener(this);
      GridBagConstraints c4 = new GridBagConstraints();
      c4.gridx = 0;
      c4.gridy = 1;

      add(cBox, c4);
      repaint();
      
      list.setListData((String[]) controller.getResultsToCombo(new Position(cBox.getSelectedItem().toString())));
      list.repaint();
   }

   @Override
   public void actionPerformed(ActionEvent action)
   {
      if(action.getSource().equals(cBox))
      {
         //add the list of candidates to the Jlist according what he picked in the drop down
        list.setListData(controller.getCandidatesToCombo(new Position(cBox.getSelectedItem().toString())));
        list.repaint();
      }
      else if (action.getSource().equals(deletePos))
      {
         //delete position according, which one he selected
         controller.deletePosition(cBox.getSelectedItem().toString());
         controller.refreshAdmin();
      }
      else if (action.getSource().equals(deleteCand))
      {
         //delete candidate according which one he selected
         controller.deleteCandidate(list.getSelectedValue().toString(), cBox.getSelectedItem().toString());
         System.out.println(list.getSelectedValue().toString());
         list.setListData(controller.getCandidatesToCombo(new Position(cBox.getSelectedItem().toString())));
         list.repaint();
      }
   }
}
