import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewBDScreen extends JFrame 
		implements ActionListener {
	private JComboBox<String> cboDSLop;
	private ArrayList<String> dsTenLop;
	private DefaultTableModel tableModel;
	private JTable tableDSDiem;
	private JScrollPane scrollPane;
	private JLabel lblThongKe;
	private JLabel lblSoLuongText;
	private JLabel lblSoLuongValue;
	private JLabel lblPercentPassText;
	private JLabel lblPercentPassValue;
	private JLabel lblPercentFailText;
	private JLabel lblPercentFailValue;
	private String message;
	private JLabel lblThongkeText;
	private Account currentUser;
	private Box b3;

	public ViewBDScreen(Account user) {
		super("Xem bang diem");
		currentUser = user;
		DefaultUI();
	}
	
	private void DefaultUI() {
		Box b =Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		b3 = Box.createVerticalBox();
		Box b3a = Box.createHorizontalBox();
		Box b3c = Box.createHorizontalBox();
		Box b3b = Box.createHorizontalBox();
		Box b3d = Box.createHorizontalBox();
		b.add(b1);
		b.add(Box.createVerticalStrut(10));
		b.add(b2);
		b.add(Box.createVerticalStrut(10));
		b.add(b3);
		this.add(b, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		cboDSLop = new JComboBox<String>();
		b1.add(panel);
		panel.add(cboDSLop);
		dsTenLop = FileData.getFilenameInFolder("data\\BangDiem\\");
		for(String tenLop : dsTenLop) {
			cboDSLop.addItem(tenLop);
		}
		
		tableModel = new DefaultTableModel();
		tableDSDiem = new JTable(tableModel) {
	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
		};
		tableModel.addColumn("STT");
		tableModel.addColumn("MSSV");
		tableModel.addColumn("Ho ten");
		tableModel.addColumn("Diem GK");
		tableModel.addColumn("Diem cuoi ki");
		tableModel.addColumn("Diem khac");
		tableModel.addColumn("Diem tong");
		tableModel.addColumn("Ket qua");
		scrollPane = new JScrollPane(tableDSDiem, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(scrollPane, BorderLayout.CENTER);
		b2.add(scrollPane);
		
		b3.add(b3a);
		b.add(Box.createVerticalStrut(5));
		b3.add(b3b);
		b.add(Box.createVerticalStrut(5));
		b3.add(b3c);
		b.add(Box.createVerticalStrut(5));
		b3.add(b3d);
		b.add(Box.createVerticalStrut(5));

		b3a.add(lblThongkeText = new JLabel("Thong ke"));
		b3b.add(lblSoLuongText = new JLabel("So luong: "));
		b3b.add(lblSoLuongValue = new JLabel(""));
		b3c.add(lblPercentPassText = new JLabel("Ti le dau: "));
		b3c.add(lblPercentPassValue = new JLabel(""));
		b3d.add(lblPercentFailText = new JLabel("Ti le rot: "));
		b3d.add(lblPercentFailValue = new JLabel(""));
		
		lblSoLuongText.setPreferredSize(lblPercentPassText.getPreferredSize());
		lblPercentFailText.setPreferredSize(lblPercentPassText.getPreferredSize());
		
		cboDSLop.addActionListener(this);
		cboDSLop.setSelectedIndex(0);
		tableDSDiem.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent evt) {
	             if (evt.getClickCount() == 2 && currentUser.getLevel() != 1) {
	                 Point pnt = evt.getPoint();
	                 int row = tableDSDiem.rowAtPoint(pnt);
	                 String mssv = (String)tableDSDiem.getValueAt(row, 1);
	                 String hoTen = (String)tableDSDiem.getValueAt(row, 2);
	                 float diemGK = (float) tableDSDiem.getValueAt(row, 3);
	                 float diemCK = (float) tableDSDiem.getValueAt(row, 4);
	                 float diemKhac = (float) tableDSDiem.getValueAt(row, 5);
	                 float diemTong = (float) tableDSDiem.getValueAt(row, 6);
	                 String tenLop =(String)cboDSLop.getSelectedItem();
	                 Diem diem = new Diem(mssv,hoTen, diemGK, diemCK, diemKhac,diemTong);
	                 if(diem != null) {
	                	 EditingScoreScreen esScreen = new EditingScoreScreen(diem, tenLop);
	                	 esScreen.setSize(700, 600);
	                	 esScreen.setVisible(true);
	                	 esScreen.addWindowListener(new java.awt.event.WindowAdapter() {
	                		    @Override
	                		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	                		    	cboDSLop.setSelectedIndex(cboDSLop.getSelectedIndex());
	                		    }
	                		});
	                 }
	             }
	         }
	     });
		
		cboDSLop.setSelectedIndex(0);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cboDSLop) {
			tableModel.setRowCount(0);
			
			if(currentUser.getLevel() == 1) {
				b3.setVisible(false);
				cboDSLop.setVisible(false);
				dsTenLop = FileData.getFilenameInFolder("data\\BangDiem\\");
				for(String tenLop : dsTenLop) {
					String bdPath = "data\\BangDiem\\" + tenLop+".csv";
					File bdFile = new File(bdPath);
					
					if(bdFile.exists()) {
						try {
							BangDiem bd;
							bd = FileData.readBangDiem(bdPath);
							Diem diem = bd.getDSDiem().stream().filter(x -> 
								x.getMSSV().equals(currentUser.getUsername()))
									.findFirst().orElse(null);
							
							if(diem != null) {
								String ketQua = "";
								if(diem.getDiemTong() < 5) {
									ketQua = "Rot";
								}
								else {
									ketQua ="Dau";
								}
								tableModel.addRow(new Object[]
										{1, diem.getMSSV(), diem.getHoTen(), 
										diem.getDiemGK(), diem.getDiemCK(),
										diem.getDiemKhac(), diem.getDiemTong(), ketQua});
								break;
							}
						} catch (Exception e1) {
							e1.printStackTrace();
							message = "Du lieu bi loi.";
			        		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
			        		        JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
			else {
				b3.setVisible(true);
				cboDSLop.setVisible(true);
				String tenLop = (String)cboDSLop.getSelectedItem();
				String bdPath = "data\\BangDiem\\" + tenLop+".csv";
				File bdFile = new File(bdPath);
				
				if(bdFile.exists()) {
					try {
						BangDiem bd = FileData.readBangDiem(bdPath);
						int stt = 1;
						int pass = 0, fail = 0;
						for(Diem diem : bd.getDSDiem()) {
							String ketQua = "";
							if(diem.getDiemTong() < 5) {
								ketQua = "Rot";
								fail++;
							}
							else {
								ketQua ="Dau";
								pass++;
							}
							tableModel.addRow(new Object[]
									{stt++, diem.getMSSV(), diem.getHoTen(), 
									diem.getDiemGK(), diem.getDiemCK(),
									diem.getDiemKhac(), diem.getDiemTong(), ketQua});
						}
						double percentPass = (double)pass/(pass+fail)*100;
						percentPass = (double) Math.round(percentPass * 10) / 10;
						lblSoLuongValue.setText(Integer.toString(pass+fail));
						lblPercentPassValue.setText(Double.toString(percentPass)+"%");
						lblPercentFailValue.setText(Double.toString(100-percentPass)+"%");
					} catch (Exception e1) {
						e1.printStackTrace();
						message = "Du lieu bi loi.";
		        		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
		        		        JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}
	
	
	public void mouseClicked(MouseEvent e) {
	   if (e.getClickCount() == 2 && e.getSource() == tableDSDiem) {
		   JOptionPane.showMessageDialog(new JFrame(), "", "Dialog",
   		        JOptionPane.ERROR_MESSAGE);
	   }
	}
}
