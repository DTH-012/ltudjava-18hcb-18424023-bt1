import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MainScreen extends JFrame implements ActionListener{
	private JButton btnImport;
	private JButton btnAdd;
	private JButton btnViewSchedule;
	private JButton btnViewClass;
	private JButton btnViewResult;
	private DefaultTableModel tableModel;
	private JTable table;
	private JScrollPane scrollPane;
	private BangDiem bangDiem;
	private LopHoc lopHoc;
	private ThoiKhoaBieu TKB;

	public MainScreen()
	{
		super("Quan ly sinh vien");
		bangDiem = new BangDiem();
		lopHoc = new LopHoc();
		TKB = new ThoiKhoaBieu();
		DefaultUI();
	}
	
	private void DefaultUI() {
		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		b.add(b1);
		b.add(Box.createVerticalStrut(10));
		this.add(b, BorderLayout.NORTH);
		JPanel panel = new JPanel();
		b1.add(panel);
		panel.add(btnImport = new JButton("Import"));
		panel.add(btnAdd = new JButton("Them SV"));
		panel.add(btnViewClass = new JButton("Xem danh sach lop"));
		panel.add(btnViewSchedule = new JButton("Xem TKB"));
		panel.add(btnViewResult = new JButton("Xem bang diem"));
		ArrayList<String> columnNames = new ArrayList<String>();
		columnNames.add("a");
		columnNames.add("a");
		CreateTable(columnNames);

		btnImport.addActionListener(this);
		btnAdd.addActionListener(this);
		
	}
	
	private void CreateTable(ArrayList<String> columnNames) {
		if(columnNames != null && !columnNames.isEmpty()) {
			if(scrollPane != null) {
				this.remove(scrollPane);
			}
			
			tableModel = new DefaultTableModel();
			table = new JTable(tableModel);
			for(String columnName : columnNames) {
				tableModel.addColumn(columnName);
			}
			
			scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			this.add(scrollPane, BorderLayout.CENTER);
		}
	}
	
	public void actionPerformed(ActionEvent e) {

        //Handle open button action.
        if (e.getSource() == btnImport) {
        	ImportScreen importScreen = new ImportScreen();
        	importScreen.setSize(700, 600);
        	importScreen.setVisible(true);
        }
        else if(e.getSource() == btnAdd) {
    		ArrayList<String> classList = getClassList();
    		if(classList.isEmpty()) {
    			String message = "Chua import lop hoc nao ca!";
        		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
        		        JOptionPane.WARNING_MESSAGE);
        		return;
    		}
        	AddingForm addingForm = new AddingForm(classList);
        	addingForm.setSize(700, 600);
        	addingForm.setVisible(true);
        }
    }
	
	private ArrayList<String> getClassList() {
		ArrayList<String> classList = new ArrayList<String>();
		classList = FileData.getFilenameInFolder("data\\LopHoc\\");
		
		return classList;
	}
}
