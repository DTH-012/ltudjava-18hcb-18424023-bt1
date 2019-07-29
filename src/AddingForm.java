import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class AddingForm extends JFrame implements ActionListener {
	private JLabel lblMSSV;
	private JTextField txtMSSV;
	private JLabel lblHoTen;
	private JTextField txtHoTen;
	private JLabel lblGioiTinh;
	private JComboBox<String> cboGioiTinh;
	private JLabel lblCMND;
	private JTextField txtCMND;
	private JLabel lblLop;
	private JComboBox<String> cboLop;
	private JButton btnAdd;
	private ArrayList<String> dsLop;
	private FileData fileData;
	private LopHoc lopHoc;
	private String message;

	public AddingForm (ArrayList<String> classList) {
		super("Them sinh vien");
		String tenLop = classList.get(0);
    	fileData = new FileData();
		try {
			lopHoc = fileData.readDSSV("data\\LopHoc\\"+tenLop+".csv");
		} catch (Exception e) {
			message = "Du lieu lop hoc bi loi.";
    		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
    		        JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		dsLop = classList;
		DefaultUI();
	}
	
	private void DefaultUI() {
		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		Box b5 = Box.createHorizontalBox();
		Box b6 = Box.createHorizontalBox();
		b.add(b1);
		b.add(Box.createVerticalStrut(5));
		b.add(b2);
		b.add(Box.createVerticalStrut(5));
		b.add(b3);
		b.add(Box.createVerticalStrut(5));
		b.add(b4);
		b.add(Box.createVerticalStrut(5));
		b.add(b5);
		b.add(Box.createVerticalStrut(10));
		b.add(b6);
		this.add(b, BorderLayout.NORTH);
		
		cboGioiTinh = new JComboBox<String>();
		cboGioiTinh.addItem("Nam");
		cboGioiTinh.addItem("Nu");
		
		b1.add(lblMSSV = new JLabel("MSSV"));
		b1.add(txtMSSV = new JTextField(20));
		b2.add(lblHoTen = new JLabel("Ho ten"));
		b2.add(txtHoTen = new JTextField(20));
		b3.add(lblGioiTinh = new JLabel("Gioi tinh"));
		b3.add(cboGioiTinh);
		b4.add(lblCMND = new JLabel("CMND"));
		b4.add(txtCMND = new JTextField(20));
		b5.add(lblLop = new JLabel("Lop"));
		b5.add(cboLop = new JComboBox<String>());
		b.add(btnAdd = new JButton("Them"));
		
		for(String lop : dsLop) {
			cboLop.addItem(lop);
		}
		
		lblMSSV.setPreferredSize(lblGioiTinh.getPreferredSize());
		lblHoTen.setPreferredSize(lblGioiTinh.getPreferredSize());
		lblCMND.setPreferredSize(lblGioiTinh.getPreferredSize());
		lblLop.setPreferredSize(lblGioiTinh.getPreferredSize());
		
		btnAdd.addActionListener(this);
		cboLop.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {

        //Handle open button action.
        if (e.getSource() == btnAdd) {
        	boolean valid = validateEmptyForm();
        	if(!valid) {
        		message = "Khong duoc de trong.";
        		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
        		        JOptionPane.WARNING_MESSAGE);
        		return;
        	}
        	
    		String mssv = txtMSSV.getText();
    		String hoTen = txtHoTen.getText();
    		String cmnd = txtCMND.getText();
    		String tenLop = (String)cboLop.getSelectedItem();
    		String gioiTinh = (String)cboGioiTinh.getSelectedItem();
        	boolean existMSSV = isExistMSSV(mssv);
        	if(existMSSV) {
        		message = "MSSV da ton tai.";
        		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
        		        JOptionPane.WARNING_MESSAGE);
        		return;
        	}
        	else {
        		SinhVien sv = new SinhVien(mssv, hoTen, gioiTinh, cmnd);
        		ArrayList<SinhVien> tempClass = new ArrayList<SinhVien>();
        		tempClass.add(sv);
        		try {
					fileData.writeData("data\\LopHoc\\", tenLop, true, tempClass);
	        		message = "Them thanh cong.";
	        		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
	        		        JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
	        		message = "Them du lieu bi loi.";
	        		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
	        		        JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
        	}
        }
        else if(e.getSource() == cboLop) {
        	String tenLop = (String)cboLop.getSelectedItem();
        	try {
    			lopHoc = fileData.readDSSV("data\\LopHoc\\"+tenLop+"csv");
    		} catch (Exception ex) {
    			message = "Du lieu lop hoc bi loi.";
        		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
        		        JOptionPane.ERROR_MESSAGE);
    			ex.printStackTrace();
    		}
        }
    }

	private boolean isExistMSSV(String mssv) {
		SinhVien sv = lopHoc.getDSSV().stream().filter(x -> x.getMSSV().equals(mssv)).findFirst().orElse(null);
		if(sv != null) {
			return true;
		}
		return false;
	}

	private boolean validateEmptyForm() {
		String mssv = txtMSSV.getText();
		String hoTen = txtHoTen.getText();
		String cmnd = txtCMND.getText();
		
		if(mssv == null || mssv.isEmpty() || hoTen == null || hoTen.isEmpty()
				|| cmnd == null || cmnd.isEmpty()) return false;
			
		return true;
	}
}
