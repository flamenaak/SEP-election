package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class ResultsView extends JPanel
{
   private JList list;
   private JComboBox cBox;
   private JButton refresh;
   private GridBagConstraints c;
   private JPanel sub;
   private JLabel lab;
   
   public ResultsView()
   {
      super(new GridBagLayout());
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
      
      sub.add(refresh);
      
      c.gridx = 0;
      c.gridy = 2;
      add(sub, c);
      
      
   }

   private void initialize()
   {
      list = new JList();
      cBox = new JComboBox();
      refresh = new JButton("Refresh");
      c = new GridBagConstraints();
      sub = new JPanel(new GridBagLayout());
      lab = new JLabel("Select Position");
      
   }
}
