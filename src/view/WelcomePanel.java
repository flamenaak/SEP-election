package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;

public class WelcomePanel extends JPanel
{
   private static final long serialVersionUID = 1L;
   
   private JLabel instructions;
   private JButton start, stop, logOut, reset;
   private GridBagConstraints c;
   private Controller controller;

   public WelcomePanel(Controller controller)
   {
      super(new GridBagLayout());
      this.controller = controller;
      initialize();
      build();
      disableButtons();
   }

   private void initialize()
   {
      instructions = new JLabel("Welcome to the admin window");

      start = new JButton("Start election");
      start.addActionListener(new startListener());

      stop = new JButton("Stop election");
      stop.addActionListener(new stopListener());

      logOut = new JButton("Log out");
      logOut.addActionListener(new logOutListener());

      reset = new JButton("Reset values");
      reset.addActionListener(new resetListener());

      c = new GridBagConstraints();
   }

   private void build()
   {
      c.gridwidth = 2;
      c.gridx = 0;
      c.gridy = 0;
      add(instructions, c);

      c.gridwidth = 1;
      c.gridx = 0;
      c.gridy = 1;
      add(start, c);

      c.gridx = 1;
      add(stop, c);

      c.gridwidth = 2;
      c.gridx = 0;
      c.gridy = 2;
      add(reset, c);

      c.gridy = 3;
      add(logOut, c);
   }

   private void disableButtons()
   {
      if (controller.getElection())
      {
         start.setEnabled(false);
         stop.setEnabled(true);
         
      }
      else if(!controller.getElection())
      {
         start.setEnabled(true);
         stop.setEnabled(false);
      }
      repaint();
   }

   public class startListener implements ActionListener
   {
      @Override
      public void actionPerformed(ActionEvent e)
      {
         start.setEnabled(false);
         stop.setEnabled(true);
         controller.startElection();
         System.out.println("changing election state to" + controller.getElection());
         controller.disableTabs("start");
      }
   }

   public class stopListener implements ActionListener
   {
      @Override
      public void actionPerformed(ActionEvent e)
      {
         stop.setEnabled(false);
         start.setEnabled(true);
         controller.endElection();
         System.out.println("changing election state to" + controller.getElection());
         controller.disableTabs("stop");
      }
   }

   public class logOutListener implements ActionListener
   {
      @Override
      public void actionPerformed(ActionEvent e)
      {
         controller.adminLogOut();
      }
   }

   public class resetListener implements ActionListener
   {
      @Override
      public void actionPerformed(ActionEvent e)
      {
         try
         {
            controller.reset();
         }
         catch (Exception e1)
         {
         }
      }
   }
}
