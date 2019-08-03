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
	private Account currentUser;
	private JButton btnChangePwd;
	private JButton btnLogout;

	public MainScreen(Account user)
	{
		super("Quan ly sinh vien");
		bangDiem = new BangDiem();
		lopHoc = new LopHoc();
		TKB = new ThoiKhoaBieu();
		currentUser = user;
		DefaultUI();
	}
	
	private void DefaultUI() {
		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		b.add(b1);
		b.add(Box.createVerticalStrut(10));
		b.add(b2);
		b.add(Box.createVerticalStrut(10));
		this.add(b, BorderLayout.NORTH);
		JPanel panel1 = new JPanel();
		b1.add(panel1);
		panel1.add(btnImport = new JButton("Import"));
		panel1.add(btnAdd = new JButton("Them SV"));
		panel1.add(btnViewClass = new JButton("Xem danh sach lop"));
		panel1.add(btnViewSchedule = new JButton("Xem TKB"));
		panel1.add(btnViewResult = new JButton("Xem bang diem"));
		
		JPanel panel2 = new JPanel();
		b2.add(panel2);
		panel2.add(btnChangePwd = new JButton("Doi mat khau"));
		panel2.add(btnLogout = new JButton ("Dang xuat"));
		
		btnImport.addActionListener(this);
		btnAdd.addActionListener(this);
		btnViewClass.addActionListener(this);
		btnViewSchedule.addActionListener(this);
		btnChangePwd.addActionListener(this);
		btnLogout.addActionListener(this);
		
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
        else if(e.getSource() == btnViewClass) {
        	ArrayList<String> classList = getClassList();
    		if(classList.isEmpty()) {
    			String message = "Chua import lop hoc nao ca!";
        		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
        		        JOptionPane.WARNING_MESSAGE);
        		return;
    		}
    		ViewClassScreen viewClassScreen = new ViewClassScreen();
    		viewClassScreen.setSize(700, 600);
    		viewClassScreen.setVisible(true);
        }
        else if(e.getSource() == btnViewSchedule) {
        	ArrayList<String> classList = getClassList();
    		if(classList.isEmpty()) {
    			String message = "Chua import tkb lop hoc nao ca!";
        		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
        		        JOptionPane.WARNING_MESSAGE);
        		return;
    		}
    		ViewTKBScreen viewTKBScreen = new ViewTKBScreen();
    		viewTKBScreen.setSize(700, 600);
    		viewTKBScreen.setVisible(true);
        }
        else if(e.getSource() == btnLogout) {
        	java.awt.Window win[] = java.awt.Window.getWindows(); 
        	for(int i=0;i<win.length;i++){ 
        		win[i].dispose(); 
        	}
        	
        	LoginScreen loginScreen = new LoginScreen();
    		loginScreen.setSize(450, 100);
    		loginScreen.setVisible(true);
    		loginScreen.setResizable(false);
    		loginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
	
	private ArrayList<String> getClassList() {
		ArrayList<String> classList = new ArrayList<String>();
		classList = FileData.getFilenameInFolder("data\\LopHoc\\");
		
		return classList;
	}
}
