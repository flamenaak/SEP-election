package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;


public class NewCandidatePanel extends JPanel
{
   private static final long serialVersionUID = 1L;
   
   private JComboBox<Object> posComboBox;
   private JLabel position, name;
   private JTextField nameField;
   private Controller controller;

   public NewCandidatePanel(Controller controller)
   {
      super(new GridBagLayout());
      this.controller = controller;
      initialize();
      build();
      setVisible(true);
   }

   private void build()
   {
      GridBagConstraints c1 = new GridBagConstraints();
      GridBagConstraints c2 = new GridBagConstraints();
      GridBagConstraints c3 = new GridBagConstraints();
      GridBagConstraints c4 = new GridBagConstraints();
      

      c1.gridx = 0;
      c1.gridy = 0;
      add(name, c1);

      c2.gridx = 1;
      add(position, c2);

      c3.gridx = 0;
      c3.gridy = 1;
      nameField.setPreferredSize(new Dimension(100,25));
      add(nameField, c3);

      c4.gridx = 1;
      posComboBox.setPreferredSize(new Dimension(100,25));
      add(posComboBox, c4);

   }

   private void initialize()
   {
      //setBounds(0, 0, 400, 200);
      posComboBox = new JComboBox<Object>();      
      name = new JLabel("Name");
      position = new JLabel("Position");
      nameField = new JTextField();

   }
}
