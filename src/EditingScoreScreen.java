import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.*;

public class EditingScoreScreen extends JFrame implements ActionListener{

	private JLabel lblGK;
	private JTextField txtGK;
	private JLabel lblCK;
	private JTextField txtCK;
	private JLabel lblKhac;
	private JTextField txtKhac;
	private JLabel lblTong;
	private JTextField txtTong;
	private String currentLop;
	private Diem currentDiem;
	private JButton btnSave;
	private String message;
	private JLabel lblHoTenValue;
	private JLabel lblHoTenText;
	private JLabel lblMSSVValue;
	private JLabel lblMSSVText;

	public EditingScoreScreen(Diem diem, String tenLop) {
		super("Chinh sua diem");
		currentDiem = diem;
		currentLop = tenLop;
		DefaultUI();
		UpdateTextValue();
		lblMSSVValue.setText(diem.getMSSV());
		lblHoTenValue.setText(diem.getHoTen());
	}

	private void UpdateTextValue() {
		txtGK.setText(Float.toString(currentDiem.getDiemGK()));
		txtCK.setText(Float.toString(currentDiem.getDiemCK()));
		txtKhac.setText(Float.toString(currentDiem.getDiemKhac()));
		txtTong.setText(Float.toString(currentDiem.getDiemTong()));
	}

	private void DefaultUI() {
		Box b = Box.createVerticalBox();
		Box a1 = Box.createHorizontalBox();
		Box a2 = Box.createHorizontalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		Box b5 = Box.createHorizontalBox();
		b.add(a1);
		b.add(Box.createVerticalStrut(5));
		b.add(a2);
		b.add(Box.createVerticalStrut(5));
		b.add(b1);
		b.add(Box.createVerticalStrut(5));
		b.add(b2);
		b.add(Box.createVerticalStrut(5));
		b.add(b3);
		b.add(Box.createVerticalStrut(5));
		b.add(b4);
		b.add(Box.createVerticalStrut(5));
		b.add(b5);
		b.add(Box.createVerticalStrut(5));
		this.add(b, BorderLayout.NORTH);
		
		b1.add(lblGK = new JLabel("Diem GK"));
		b1.add(txtGK = new JTextField(20));
		b2.add(lblCK = new JLabel("Diem CK"));
		b2.add(txtCK = new JTextField(20));
		b3.add(lblKhac = new JLabel("Diem khac"));
		b3.add(txtKhac = new JTextField(20));
		b4.add(lblTong = new JLabel("Diem tong"));
		b4.add(txtTong = new JTextField(20));
		b5.add(btnSave = new JButton("Cap nhat"));
		
		a1.add(lblMSSVText = new JLabel("MSSV: "));
		a1.add(lblMSSVValue = new JLabel(""));
		a2.add(lblHoTenText = new JLabel("Ho ten: "));
		a2.add(lblHoTenValue = new JLabel(""));
		
		lblGK.setPreferredSize(lblKhac.getPreferredSize());
		lblCK.setPreferredSize(lblKhac.getPreferredSize());
		lblTong.setPreferredSize(lblKhac.getPreferredSize());
		lblMSSVText.setPreferredSize(lblKhac.getPreferredSize());
		lblHoTenText.setPreferredSize(lblKhac.getPreferredSize());
		
		btnSave.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSave) {
			if(isExistEmptyField()) {
				message = "Khong duoc de trong.";
        		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
        		        JOptionPane.ERROR_MESSAGE);
        		return;
			}
			
			if(!isValidValueInput()) {
				message = "Input khong hop le.";
        		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
        		        JOptionPane.ERROR_MESSAGE);
        		return;
			}
			

			String path = "data\\BangDiem\\"+currentLop+".csv";
			try {
				BangDiem bangDiem = FileData.readBangDiem(path);
				Diem diem = bangDiem.getDSDiem().stream().filter(x -> 
						x.getMSSV().equals(currentDiem.getMSSV()))
						.findFirst().orElse(null);
				if(diem != null) {
					diem.setDiemGK(Float.parseFloat(txtGK.getText()));
					diem.setDiemCK(Float.parseFloat(txtCK.getText()));
					diem.setDiemKhac(Float.parseFloat(txtKhac.getText()));
					diem.setDiemTong(Float.parseFloat(txtTong.getText()));
					FileData.writeData("data\\BangDiem\\", currentLop, false, bangDiem.getDSDiem());
					message = "Cap nhat thanh cong.";
	        		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
	        		        JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					message = "Du lieu nguoi dung khong ton tai.";
	        		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
	        		        JOptionPane.ERROR_MESSAGE);
				}
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				message = "File du lieu bi loi.";
        		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
        		        JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private boolean isExistEmptyField() {
		return (txtGK.getText() == null || txtGK.getText().isEmpty()
				|| txtCK.getText() == null || txtCK.getText().isEmpty()
				|| txtKhac.getText() == null || txtKhac.getText().isEmpty()
				|| txtTong.getText() == null || txtTong.getText().isEmpty());
	}
	
	private boolean isValidValueInput() {
		try {
			Float.parseFloat(txtGK.getText());
			Float.parseFloat(txtCK.getText());
			Float.parseFloat(txtKhac.getText());
			Float.parseFloat(txtTong.getText());
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return true;
		}
	}
}
