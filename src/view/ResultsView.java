package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import controller.Controller;
import model.Position;

public class ResultsView extends JPanel implements ActionListener
{
   private JList list;
   private JComboBox cBox;
   private JButton refresh;
   private GridBagConstraints c;
   private JPanel sub;
   private JLabel lab;
   private Controller controller;
   
   public ResultsView(Controller cont)
   {
      super(new GridBagLayout());
      controller = cont;
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
      
      cBox.addActionListener(this);
      c.gridy = 1;
      add(cBox, c);
      
      c.gridheight = 1;
      c.gridx = 1;
      try
      {
         list.setListData(controller.getResultsToCombo(new Position(cBox.getSelectedItem().toString())));
      }
      catch(Exception e)
      {
         
      }
      add(list, c);
      
      refresh.addActionListener(this);
      sub.add(refresh);
      
      c.gridx = 0;
      c.gridy = 2;
      add(sub, c);     
   }

   private void initialize()
   {
      list = new JList();
      cBox = new JComboBox(controller.getPositionsToCombo());
      refresh = new JButton("Refresh");
      c = new GridBagConstraints();
      sub = new JPanel(new GridBagLayout());
      lab = new JLabel("Select Position");
   }

   public void getNewCombo()
   {
      remove(cBox);
      cBox = new JComboBox<String>(controller.getPositionsToCombo());
      cBox.setPreferredSize(new Dimension(100,25));
      GridBagConstraints c4 = new GridBagConstraints();
      cBox.addActionListener(this);
      c4.gridx = 0;
      c4.gridy = 1;

      add(cBox, c4);
      repaint();
      
      try
      {
         list.setListData(controller.getResultsToCombo(new Position(cBox.getSelectedItem().toString())));
      }
      catch(Exception e)
      {
         
      }
      list.repaint();
   }
   
   @Override
   public void actionPerformed(ActionEvent e)
   {
      if(e.getSource().equals(refresh))
      {
         removeAll();
         initialize();
         build();
      }
      else if(e.getSource().equals(cBox))
      {
         try
         {
            list.setListData(controller.getResultsToCombo(new Position(cBox.getSelectedItem().toString())));
            list.repaint();
         }
         catch (Exception e1)
         {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }
      }
   }
   
   
}
