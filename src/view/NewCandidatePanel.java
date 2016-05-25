package view;

import java.awt.GridBagConstraints;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class NewCandidatePanel extends JPanel
{
   
	private static final long serialVersionUID = 1L;
	
	private JComboBox<Object> posComboBox;
	private JLabel position, name;
	private JTextField nameField;

	public NewCandidatePanel()
	{
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
		
		setLayout(null);

		c1.gridx = 0;
		c1.gridy = 0;
		add(name);

		c2.gridx = 1;
		add(position);

		c3.gridx = 0;
		c3.gridy = 1;
		add(nameField);

		c4.gridx = 1;
		add(posComboBox);

	}

	private void initialize()
	{
		setBounds(0, 0, 400, 200);

		posComboBox = new JComboBox<Object>();
		posComboBox.setBounds(45, 99, 118, 22);

		name = new JLabel("Name");
		name.setBounds(252, 70, 33, 16);

		position = new JLabel("Position");
		position.setBounds(77, 70, 44, 16);

		nameField = new JTextField();
		nameField.setBounds(213, 99, 118, 22);
	}
}
