import javax.swing.JFrame;

public class MainProgram {
	public static void main(String[] args) {
//		MainScreen mainScreen = new MainScreen();
//		mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		mainScreen.setSize(700, 600);
//		mainScreen.setVisible(true);
		
		LoginScreen loginScreen = new LoginScreen();
		loginScreen.setSize(450, 100);
		loginScreen.setVisible(true);
		loginScreen.setResizable(false);
		loginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
