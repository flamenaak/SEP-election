package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;
import model.Position;

public class NewPositionPanel extends JPanel implements ActionListener
{
   private Controller cont;
   private JLabel label;
   private JTextField tField;
   private JButton save;
   private GridBagConstraints c;
   private JPanel subPanel;
   private Position position;
   
   public NewPositionPanel(Controller cont)
   {
      super(new GridBagLayout());
      this.cont = cont;
      initialize();
      build();
   }
   
   public void initialize()
   {
      label = new JLabel("Enter name of the position");
      tField= new JTextField();
      save = new JButton("Save");
      c = new GridBagConstraints();
      subPanel = new JPanel();
   }
   
   public void build()
   {
      c.gridx = 0;
      c.gridy = 0;
      add(label,c);
      
      c.gridy = 1;
      tField.setPreferredSize(new Dimension(100,25));
      add(tField,c);
      
      c.gridy = 0;
      subPanel.add(save, c);
      
      c.gridx = 0;
      c.gridy = 2;
      add(subPanel, c);
      
      save.addActionListener(this);
   }
   
   
   

   @Override
   public void actionPerformed(ActionEvent action)
   {
      if (action.getSource().equals(save))
      {
         position = new Position(tField.getText());
         cont.addPosition(position);
         System.out.println(position.getPositionName());
         tField.setText("");
      }
      
   }
   
}
