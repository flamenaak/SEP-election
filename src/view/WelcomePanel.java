package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;

public class WelcomePanel extends JPanel
{
	private JLabel instructions;
	private JButton start, stop, logOut, chameleon, reset;
	private GridBagConstraints c;
	private Controller controller;

	public WelcomePanel(Controller controller) 
	{
		super(new GridBagLayout());
		this.controller = controller;
		initialize();
		build();
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
		
		chameleon = new JButton("This button has to be set to start or stop by the status of election");
		
		reset = new JButton("Reset values");
		reset.addActionListener(new resetListener());
		
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
		add(reset, c);

		c.gridy = 3;
		add(logOut, c);
	}

	public class startListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			controller.startElection();
		}  
	}

	public class stopListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			controller.endElection();
		}
	}
	
	public class logOutListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			controller.adminLogOut();
		}
	}
	
	public class resetListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				controller.reset();
				controller.refreshAdmin();
			} catch (Exception e1) {
			}
		}
	}
	
}
