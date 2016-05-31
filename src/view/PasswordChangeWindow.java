package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import controller.Controller;

public class PasswordChangeWindow extends JPanel implements KeyListener
{
   private JPasswordField passwordField;
   private JPasswordField passwordField_1;
   private GridBagConstraints c;
   private Controller controller;
   private JButton btnSavePassword;

   public PasswordChangeWindow(Controller controller)
   {
      super(new GridBagLayout());
      this.controller = controller;
      c = new GridBagConstraints();

      JLabel lblNewLabel = new JLabel("Enter new password");
      c.gridx = 0;
      c.gridy = 0;
      add(lblNewLabel, c);

      JLabel lblNewLabel_1 = new JLabel("Confirm new password");
      c.gridy = 1;
      add(lblNewLabel_1, c);

      passwordField = new JPasswordField();
      passwordField.setPreferredSize(new Dimension(130, 22));
      c.gridx = 1;
      c.gridy = 0;
      add(passwordField);
      passwordField.addKeyListener(this);

      passwordField_1 = new JPasswordField();
      passwordField_1.setPreferredSize(new Dimension(130, 22));
      c.gridx = 1;
      c.gridy = 1;
      add(passwordField_1, c);
      passwordField_1.addKeyListener(this);

      btnSavePassword = new JButton("Save password");

      btnSavePassword.addActionListener(new buttonListener());

      btnSavePassword.setBounds(227, 168, 119, 58);
      c.gridx = 0;
      c.gridy = 3;
      add(btnSavePassword, c);

   }

   public class buttonListener implements ActionListener
   {
      @Override
      public void actionPerformed(ActionEvent e)
      {
         String password = "";
         char[] chars = passwordField.getPassword();
         for (int i = 0; i < passwordField.getPassword().length; i++)
         {
            password += chars[i];
         }
         controller.changePassword(password);
         passwordField.setText("");
         passwordField_1.setText("");
      }
   }

   @Override
   public void keyPressed(KeyEvent arg0)
   {
   }

   @Override
   public void keyReleased(KeyEvent arg0)
   {

      char[] pass = passwordField.getPassword();
      char[] pass2 = passwordField_1.getPassword();
      if (Arrays.equals(pass, pass2))
      {
         btnSavePassword.setEnabled(true);
      }
      else
      {
         btnSavePassword.setEnabled(false);
      }
   }

   @Override
   public void keyTyped(KeyEvent arg0)
   {
   }

}