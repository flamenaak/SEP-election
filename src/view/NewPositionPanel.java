package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;

public class NewPositionPanel extends JPanel implements ActionListener
{
   private Controller cont;
   private JLabel label;
   private JTextField tField;
   private JButton save, back;
   private GridBagConstraints c;
   
   public NewPositionPanel()//Controller cont)
   {
      super(new GridBagLayout());
      this.cont = cont;
      
      
   }
   
   public void initialize()
   {
      label = new JLabel("Enter name of the position");
      tField= new JTextField();
      save = new JButton("Save");
      back = new JButton("Back");
      c = new GridBagConstraints();
   }
   
   public void build()
   {
      c.gridx = 0;
      c.gridy = 0;
      add(label,c);
      
      c.gridy = 1;
      add(tField,c);
      
      c.gridy = 2;
      add(save, c);
      
      c.gridx = 1;
      add(back, c);
      
   }
   
   
   

   @Override
   public void actionPerformed(ActionEvent e)
   {
      // TODO Auto-generated method stub
      
   }
   
}
