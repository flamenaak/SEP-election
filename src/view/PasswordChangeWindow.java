package view;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PasswordChangeWindow extends JPanel{
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	public PasswordChangeWindow() {
	setBounds(100, 100, 617, 342);
	setLayout(null);
	
	JLabel lblNewLabel = new JLabel("Enter new password");
	lblNewLabel.setBounds(165, 51, 130, 16);
	add(lblNewLabel);
	
	JLabel lblNewLabel_1 = new JLabel("Confirm new password");
	lblNewLabel_1.setBounds(152, 112, 143, 16);
	add(lblNewLabel_1);
	
	passwordField = new JPasswordField();
	passwordField.setBounds(307, 48, 130, 22);
	add(passwordField);
	
	passwordField_1 = new JPasswordField();
	passwordField_1.setBounds(307, 109, 130, 22);
	add(passwordField_1);
	
	JButton btnSavePassword = new JButton("Save password");
	
	if (passwordField.getPassword().equals("") || passwordField_1.getPassword().equals("") || passwordField.getPassword().equals(passwordField_1.getPassword()))
		btnSavePassword.setEnabled(false);
		
	btnSavePassword.setBounds(227, 168, 119, 58);
	add(btnSavePassword);
	}
	public static void main(String[] args) {
		PasswordChangeWindow window = new PasswordChangeWindow();
		window.setVisible(true);
	}	
}