package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import controller.Controller;

public class PasswordChangeWindow extends JPanel{
   private JPasswordField passwordField;
   private JPasswordField passwordField_1;
   private GridBagConstraints c;
   private Controller controller;

   public PasswordChangeWindow(Controller controller) {
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

      passwordField_1 = new JPasswordField();
      passwordField_1.setPreferredSize(new Dimension(130, 22));
      c.gridx = 1;
      c.gridy = 1;
      add(passwordField_1, c);


      JButton btnSavePassword = new JButton("Save password");

      btnSavePassword.addActionListener(new buttonListener());
      
      if (passwordField.getPassword().equals("") || passwordField_1.getPassword().equals("") || passwordField.getPassword().equals(passwordField_1.getPassword()))
         btnSavePassword.setEnabled(false);

      btnSavePassword.setBounds(227, 168, 119, 58);
      c.gridx = 0;
      c.gridy = 3;
      add(btnSavePassword, c);
      
   }
   
   public class buttonListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
         String password = "";
         char[] chars = passwordField.getPassword();
         for (int i = 0; i < passwordField.getPassword().length; i++) {
            password += chars[i];
         }
         controller.changePassword(password);
         passwordField.setText("");
         passwordField_1.setText("");
         System.out.println(password);
      }  
   }

}