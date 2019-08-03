import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.*;

public class LoginScreen extends JFrame 
		implements ActionListener {
	private JLabel lblUsername;
	private JTextField txtUsername;
	private JLabel lblPassword;
	private JTextField txtPassword;
	private JButton btnLogin;
	private JPanel panel;
	private String message;

	public LoginScreen() {
		super("Dang nhap");
		DefaultUI();
	}
	
	private void DefaultUI() {
		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		b.add(b1);
		b.add(Box.createHorizontalStrut(10));
		b.add(b2);
		b.add(Box.createHorizontalStrut(10));
		b.add(b3);
		b.add(Box.createHorizontalStrut(10));
		this.add(b, BorderLayout.NORTH);
		
		lblUsername = new JLabel("Ten dang nhap");
		txtUsername = new JTextField(20);
		b1.add(lblUsername);
		b1.add(txtUsername);
		lblPassword = new JLabel("Mat khau");
		txtPassword = new JTextField(20);
		b2.add(lblPassword);
		b2.add(txtPassword);
		
		btnLogin = new JButton("Dang nhap");
		panel = new JPanel();
		panel.add(btnLogin);
		b3.add(panel);
		lblPassword.setPreferredSize(lblUsername.getPreferredSize());
		
		btnLogin.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnLogin)) {
			ArrayList<Account> users = new ArrayList<Account>();
			String username = txtUsername.getText();
			String password = txtPassword.getText();
			try {
				users = FileData.readLoginInfo("data\\Accounts\\accounts.csv");
				Account user = users.stream().filter(x -> 
				x.getUsername().equals(username) && x.getPassword().equals(password))
						.findFirst().orElse(null);
				if(user == null) {
					message = "Ten dang nhap hoac mat khau khong khop.";
	        		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
	        		        JOptionPane.WARNING_MESSAGE);
				}
				else {
					dispose();
					MainScreen mainScreen = new MainScreen(user);
					mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					mainScreen.setSize(700, 600);
					mainScreen.setVisible(true);
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				message = "Du lieu bi loi.";
        		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
        		        JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
