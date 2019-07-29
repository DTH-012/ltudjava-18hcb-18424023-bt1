import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class ImportScreen extends JFrame 
				implements ActionListener{
	private JButton btnImport;
	private JTextField txtURL;
	private JLabel lblURL;
	private JRadioButton radLop;
	private JRadioButton radTKB;
	private JRadioButton radBangDiem;
	private JButton btnSave;
	private JFileChooser fc;
	private FileData fileData;
	private BangDiem bangDiem;
	private LopHoc lopHoc;
	private ThoiKhoaBieu TKB;
	private File fileChoosen;

	public ImportScreen() {
		super("Import");
		DefaultUI();
		this.lopHoc = new LopHoc();
		this.TKB = new ThoiKhoaBieu();
		this.bangDiem = new BangDiem();
	}

	private void DefaultUI() {
        fc = new JFileChooser();
		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		b.add(b1);
		b.add(Box.createVerticalStrut(5));
		b.add(b2);
		b.add(Box.createVerticalStrut(5));
		b.add(b3);
		b.add(Box.createVerticalStrut(10));
		this.add(b, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		b1.add(panel);
		radLop = new JRadioButton("Lop");
		radLop.setSelected(true);
		radTKB = new JRadioButton("TKB");
		radBangDiem = new JRadioButton("Bang diem");
		ButtonGroup importGroup = new ButtonGroup();
		importGroup.add(radLop);
		importGroup.add(radTKB);
		importGroup.add(radBangDiem);
		panel.add(radLop);
		panel.add(radTKB);
		panel.add(radBangDiem);

		b2.add(lblURL = new JLabel("URL"));
		b2.add(txtURL = new JTextField(20));
		txtURL.setEditable(false);
		b2.add(btnImport = new JButton("Import File"));
		btnImport.addActionListener(this);
		
		b3.add(btnSave = new JButton("Save"));
		btnSave.addActionListener(this);
		
		
	}
	
	public void actionPerformed(ActionEvent e) {

        //Handle open button action.
        if (e.getSource() == btnImport) {
            int returnVal = fc.showOpenDialog(ImportScreen.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                fileChoosen = fc.getSelectedFile();
                txtURL.setText(fileChoosen.getPath());
            }
        }
        else if (e.getSource() == btnSave) {
        	fileData = new FileData();
        	try {
        		lopHoc = fileData.readDSSV(fileChoosen.getPath());
        		fileData.writeData("data\\LopHoc\\", lopHoc.getTenLop(), false, lopHoc.getDSSV());

        		String message = "Import thanh cong.";
        		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
        		        JOptionPane.INFORMATION_MESSAGE);
        	}
        	catch(Exception ex){
        		String message = "Du lieu bi loi.";
        		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
        		        JOptionPane.ERROR_MESSAGE);
        	}
        }
    }
}
