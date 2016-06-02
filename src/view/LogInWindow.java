package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import controller.Controller;

public class LogInWindow implements ActionListener, KeyListener
{
   private JFrame logInFrame;
   private JPanel leftPanel, rightPanel;
   private TextField name;
   private JPasswordField password;
   private JLabel nameLab, passLab;
   private GridBagConstraints c;
   private JButton butt;
   private Controller controller;

   public LogInWindow(Controller controller)
   {
      this.controller = controller;
      this.createComponents();
      this.createGUI();
      assignActionListener();
   }

   private void assignActionListener()
   {
      butt.addActionListener(this);
      password.addKeyListener(this);
   }

   private void createComponents()
   {
      logInFrame = new JFrame();
      logInFrame.setLayout(new GridBagLayout());
      leftPanel = new JPanel(new GridBagLayout());
      rightPanel = new JPanel(new GridBagLayout());
      name = new TextField();
      name.setColumns(20);
      password = new JPasswordField();
      password.setColumns(14);
      nameLab = new JLabel("Name");
      passLab = new JLabel("Password");
      c = new GridBagConstraints();
      butt = new JButton("Log In");
   }

   private void createGUI()
   {
      Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
      logInFrame.setSize(500, 300);
      int x = (int) ((s.getWidth() - logInFrame.getWidth()) / 2);
      int y = (int) ((s.getHeight() - logInFrame.getHeight()) / 2);

      logInFrame.setLocation(x, y);
      logInFrame.setVisible(true);
      logInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //building and setting frame
      
      c.gridwidth = 4;
      c.gridx = 0;
      c.gridy = 2;
      leftPanel.add(nameLab, c);
      rightPanel.add(name, c);
      c.gridwidth = 4;
      c.gridx = 0;
      c.gridy = 4;
      leftPanel.add(passLab, c);
      rightPanel.add(password, c);
      c.gridx = 0;
      c.gridy = 0;
      c.gridwidth = 1;
      logInFrame.add(leftPanel, c);
      c.gridx = 1;
      logInFrame.add(rightPanel, c);
      c.gridy = 1;
      logInFrame.add(butt, c);
   }

   public void actionPerformed(ActionEvent action)
   {
      if(action.getSource().equals(butt))
      {
         controller.logIn(name.getText(), this.password.getPassword());
      }
   }

   public void close()
   {
      logInFrame.setVisible(false);     
   }
   
   public void open()
   {
      logInFrame.setVisible(true);
   }
   
   public void clearFields()
   {
      name.setText("");
      password.setText("");
   }
   
   @Override
   public void keyPressed(KeyEvent arg0)
   {      
   }

   @Override
   public void keyReleased(KeyEvent e)
   {
      if(e.getKeyCode() == KeyEvent.VK_ENTER)
      {
         controller.logIn(name.getText(), this.password.getPassword());
      }      
   }

   @Override
   public void keyTyped(KeyEvent arg0)
   {
   }

}
