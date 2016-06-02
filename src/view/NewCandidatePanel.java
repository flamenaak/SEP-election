package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;
import model.Candidate;
import model.Position;

public class NewCandidatePanel extends JPanel
{
   private static final long serialVersionUID = 1L;

   private JComboBox<String> posComboBox;
   private JLabel position, name;
   private JTextField nameField;
   private Controller controller;
   private JButton butt;

   public NewCandidatePanel(Controller controller)
   {
      super(new GridBagLayout());
      this.controller = controller;
      initialize();
      build();
      setVisible(true);
   }

   public void build()
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
      nameField.setPreferredSize(new Dimension(100, 25));
      add(nameField, c3);

      c4.gridx = 1;
      posComboBox.setPreferredSize(new Dimension(100, 25));
      add(posComboBox, c4);
      
      c4.gridy = 2;
      c4.gridx = 0;
      c4.gridwidth = 2;
      butt.addActionListener(new ButtonListener());
      add(butt, c4);
   }

   public void initialize()
   {
      posComboBox = new JComboBox<String>(controller.getPositionsToCombo());
      name = new JLabel("Name");
      position = new JLabel("Position");
      nameField = new JTextField();
      butt = new JButton("Add new candidate");
   }

   public void getNewCombo()
   {
      remove(posComboBox);
      posComboBox = new JComboBox<String>(controller.getPositionsToCombo());
      posComboBox.setPreferredSize(new Dimension(100, 25));
      
      GridBagConstraints c4 = new GridBagConstraints();
      c4.gridx = 1;
      c4.gridy = 1;

      add(posComboBox, c4);
      repaint();
   }

   public class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if (e.getSource().equals(butt))
         {
            String positionName = posComboBox.getSelectedItem().toString();
            String name = nameField.getText();
            Position position = new Position(positionName);
            Candidate candidate = new Candidate(name, position);
            controller.addCandidate(positionName, candidate);
            
            nameField.setText("");
            
            controller.refreshAdmin();
         }      
      }
   }
}
