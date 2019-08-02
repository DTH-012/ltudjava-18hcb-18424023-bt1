import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewTKBScreen extends JFrame 
	implements ActionListener {

	private JComboBox<String> cboDSLop;
	private JTable tableDSMonHoc;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private ArrayList<String> dsTenLop;
	private String message;

	public ViewTKBScreen() {
		super("Xem thoi khoa bieu");
		DefaultUI();
		dsTenLop = new ArrayList<String>();
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
		dsTenLop = FileData.getFilenameInFolder("data\\TKB\\");
		for(String tenLop : dsTenLop) {
			cboDSLop.addItem(tenLop);
		}
		
		tableModel = new DefaultTableModel();
		tableDSMonHoc = new JTable(tableModel);
		tableModel.addColumn("STT");
		tableModel.addColumn("Ma mon");
		tableModel.addColumn("Ten mon");
		tableModel.addColumn("Phong hoc");
		scrollPane = new JScrollPane(tableDSMonHoc, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(scrollPane, BorderLayout.CENTER);
		
		cboDSLop.addActionListener(this);
		cboDSLop.setSelectedIndex(0);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(cboDSLop)) {
			tableModel.setRowCount(0);
			String tenLop = (String)cboDSLop.getSelectedItem();
			String tkbPath = "data\\TKB\\" + tenLop+".csv";
			File tkbFile = new File(tkbPath);
			
			if(tkbFile.exists()) {
				try {
					ThoiKhoaBieu tkb = FileData.readTKB(tkbPath);
					int stt = 1;
					for(MonHoc mh : tkb.getDSMH()) {
						tableModel.addRow(new Object[]
								{stt++, mh.getMaMH(), mh.getTenMH(), mh.getPhongHoc()});
					}
				} catch (Exception e1) {
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
