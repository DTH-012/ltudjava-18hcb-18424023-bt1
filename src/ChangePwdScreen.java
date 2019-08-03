import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class ChangePwdScreen extends JFrame implements ActionListener {
	private Account currentUser;
	private JLabel lblOldPwd;
	private JTextField txtOldPwd;
	private JLabel lblNewPwd;
	private JTextField txtNewPwd;
	private JLabel lblReNewPwd;
	private JTextField txtReNewPwd;
	private JButton btnUpdatePwd;
	private String message;

	public ChangePwdScreen(Account user) {
		super("Doi mat khau");
		currentUser = user;
		DefaultUI();
	}
	
	
	private void DefaultUI() {
		Box b= Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		
		b.add(b1);
		b1.add(lblOldPwd = new JLabel("Mat khau cu"));
		b1.add(txtOldPwd = new JTextField(20));
		b.add(Box.createHorizontalStrut(5));
		
		b.add(b2);
		b2.add(lblNewPwd = new JLabel("Mat khau moi"));
		b2.add(txtNewPwd = new JTextField(20));
		b.add(Box.createHorizontalStrut(5));

		b.add(b3);
		b3.add(lblReNewPwd = new JLabel("Nhap lai mat khau"));
		b3.add(txtReNewPwd = new JTextField(20));
		b.add(Box.createHorizontalStrut(10));

		b.add(b4);
		JPanel panel = new JPanel();
		panel.add(btnUpdatePwd = new JButton("Cap nhat"));
		b4.add(panel);
		b.add(Box.createHorizontalStrut(10));
		
		this.add(b, BorderLayout.NORTH);
		
		lblOldPwd.setPreferredSize(lblReNewPwd.getPreferredSize());
		lblNewPwd.setPreferredSize(lblReNewPwd.getPreferredSize());
		
		btnUpdatePwd.addActionListener(this);
	}


	public void actionPerformed(ActionEvent e) {
		if(e.getSource() ==  btnUpdatePwd) {
			int validationResult = validateInput();
			if(validationResult == 1) {
				String newPwd = txtNewPwd.getText();
				try {
					ArrayList<Account> users = FileData.readUsersInfo("data\\Accounts\\accounts.csv");
					Account user = users.stream().filter(x -> x.getUsername().equals(currentUser.getUsername())).findFirst().orElse(null);
					if(user == null) {
						message = "Du lieu nguoi dung khong ton tai.";
		        		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
		        		        JOptionPane.ERROR_MESSAGE);
					}
					else {
						user.setPassword(newPwd);
						try {
							FileData.writeData("data\\Accounts\\","accounts",false, users);
							currentUser.setPassword(newPwd);
							message = "Cap nhat thanh cong";
			        		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
			        		        JOptionPane.INFORMATION_MESSAGE);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							message = "File du lieu dang duoc su dung.";
			        		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
			        		        JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					message = "Du lieu bi loi.";
	        		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
	        		        JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				switch(validationResult) {
					case -1:
						message = "Khong duoc de trong.";
						break;
					case -2:
						message = "Mat khau cu khong dung.";
						break;
					case -3:
						message = "Mat khau moi khong khop nhau";
						break;
					case -4:
						message = "Mat khau moi khong duoc trung voi mat khau hien tai.";
						break;
					default:
						
				}
        		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
        		        JOptionPane.ERROR_MESSAGE);
			}
		}
	}


	private int validateInput() {
		String oldPwd = txtOldPwd.getText();
		String newPwd = txtNewPwd.getText();
		String reNewPwd = txtReNewPwd.getText();
		
		if(oldPwd == null || oldPwd .isEmpty() 
				|| newPwd == null || newPwd .isEmpty()
				|| reNewPwd == null || reNewPwd .isEmpty()) {
			return -1;
		}
		
		if(!oldPwd.equals(currentUser.getPassword())) {
			return -2;
		}
		
		if(!newPwd.equals(reNewPwd)) {
			return -3;
		}
		
		if(newPwd.equals(currentUser.getPassword())) {
			return -4;
		}
		
		return 1;
	}
}
