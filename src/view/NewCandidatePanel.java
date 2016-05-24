package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewCandidatePanel extends JPanel
{
   private JPanel main;
   private JComboBox posComboBox;
   private JLabel position, name;
   private JTextField nameField;
   private GridBagConstraints c;

   public NewCandidatePanel()
   {
      initialize();
      build();
      main.setVisible(true);
   }

   private void build()
   {
      c.gridx = 0;
      c.gridy = 0;
      main.add(name, c);
      
      c.gridx = 1;
      main.add(position, c);
      
      c.gridx = 0;
      c.gridy = 1;
      main.add(nameField, c);
      
      c.gridx = 1;
      main.add(posComboBox, c); 
      
   }

   private void initialize()
   {
      main = new JPanel(new GridBagLayout());
      posComboBox = new JComboBox();
      name = new JLabel("Name");
      position = new JLabel("Position");
      nameField = new JTextField();
      c = new GridBagConstraints();
   }

}
