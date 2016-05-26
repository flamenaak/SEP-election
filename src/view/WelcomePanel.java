package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomePanel extends JPanel
{
   private JLabel instructions;
   private JButton start, stop, logOut, chameleon;
   private GridBagConstraints c;
   
   public WelcomePanel()
   {
      super(new GridBagLayout());
      initialize();
      build();
   }

   private void initialize()
   {
      instructions = new JLabel("Welcome to the admin window");
      start = new JButton("Start election");
      stop = new JButton("Stop election");
      logOut = new JButton("Log out");
      chameleon = new JButton("This button has to be set to start or stop by the status of election");
      
      c = new GridBagConstraints();   
   }
   
   private void build()
   {
      c.gridx = 0;
      c.gridy = 0;
      add(instructions, c);
      
      c.gridx = 0;
      c.gridy = 1;
      add(chameleon, c);
      
      c.gridy = 2;
      add(logOut, c);
   }
}
