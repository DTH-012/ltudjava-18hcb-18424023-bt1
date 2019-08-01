import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewClassScreen extends JFrame 
	implements ActionListener {

	private JComboBox<String> cboDSLop;
	private JTable tableDSLop;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private ArrayList<String> dsTenLop;
	private ArrayList<String> dsTenLopTheoMH;
	private String message;

	public ViewClassScreen() {
		super("Xem danh sach lop");
		DefaultUI();
		dsTenLop = new ArrayList<String>();
		dsTenLopTheoMH = new ArrayList<String>();
	}
	
	private void DefaultUI() {
		Box b =Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		b.add(b1);
		b.add(Box.createVerticalStrut(10));
		b.add(b2);
		this.add(b, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		cboDSLop = new JComboBox<String>();
		b1.add(panel);
		panel.add(cboDSLop);
		dsTenLop = FileData.getFilenameInFolder("data\\LopHoc\\");
		dsTenLopTheoMH = FileData.getFilenameInFolder("data\\LopHoc\\TheoMH\\");
		for(String tenLop : dsTenLop) {
			cboDSLop.addItem(tenLop);
		}
		for(String tenLop : dsTenLopTheoMH) {
			cboDSLop.addItem(tenLop);
		}
		
		tableModel = new DefaultTableModel();
		tableDSLop = new JTable(tableModel);
		tableModel.addColumn("STT");
		tableModel.addColumn("MSSV");
		tableModel.addColumn("Ho ten");
		tableModel.addColumn("Gioi tinh");
		tableModel.addColumn("CMND");
		scrollPane = new JScrollPane(tableDSLop, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(scrollPane, BorderLayout.CENTER);
		
		cboDSLop.addActionListener(this);
		cboDSLop.setSelectedIndex(0);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(cboDSLop)) {
			tableModel.setRowCount(0);
			String tenLop = (String)cboDSLop.getSelectedItem();
			String lhPath = "data\\LopHoc\\" + tenLop+".csv";
			String lhTheoMhPath = "data\\LopHoc\\TheoMH\\" + tenLop+".csv";
			File lhFile = new File(lhPath);
			File lhTheoMhFile = new File(lhTheoMhPath);
			
			if(lhFile.exists()) {
				try {
					LopHoc lh = FileData.readDSSV(lhPath);
					int stt = 1;
					for(SinhVien sv : lh.getDSSV()) {
						tableModel.addRow(new Object[]
								{stt++, sv.getMSSV(), sv.getHoTen(), sv.getGioiTinh(), sv.getCMND()});
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					message = "Du lieu bi loi.";
	        		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
	        		        JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(lhTheoMhFile.exists()) {
				try {
					LopHoc lh = FileData.readDSSV(lhTheoMhPath);
					int stt = 1;
					for(SinhVien sv : lh.getDSSV()) {
						tableModel.addRow(new Object[]
								{stt++, sv.getMSSV(), sv.getHoTen(), sv.getGioiTinh(), sv.getCMND()});
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					message = "Du lieu bi loi.";
	        		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
	        		        JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				message = "Du lieu bi loi.";
        		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
        		        JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
