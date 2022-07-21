import java.util.UUID;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class RunMainScreen {
	
	private static Exercise exercise;
	private static MainFrame mainframe;
	private static SignIn signin; 
	private static Register register;
	private static dailyReport dayreport;
	private static Report report;
	private static String uniqueID= UUID.randomUUID().toString();
	JTextField uniqueIDTextField = new JTextField();
	
    public String getUniqueID() {
        return uniqueID;
    }
    public void setUniqueID(String uniqueID) {
        this.uniqueID = this.uniqueIDTextField.getText();
        this.uniqueID = uniqueID;
    }

	public static void main(String[] args) {
		
		mainframe = new MainFrame();
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setSize(1000,800);
		mainframe.setVisible(true);
		
		signin = new SignIn();
		signin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		signin.setSize(575,500);
		signin.setVisible(false);
		
		register = new Register();
		register.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		register.setSize(575,500);
		register.setVisible(false);
		
		exercise = new Exercise();
		exercise.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		exercise.setSize(1200,725); 
		exercise.setVisible(false);
		
		report = new Report();
		report.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		report.setSize(1000,500); 
		report.setVisible(false);
		
		dayreport = new dailyReport();
		dayreport.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dayreport.setSize(1000,500); 
		dayreport.setVisible(false);
		
		}
	
	public void setLogin() {
		mainframe.setVisible(false);
		signin.setVisible(true);
		register.setVisible(false);
		exercise.setVisible(false);
		report.setVisible(false);
		dayreport.setVisible(false);
	}
	
	
	public void setRegister() {		
		mainframe.setVisible(false);
		register.setVisible(true);
		signin.setVisible(false);
		exercise.setVisible(false);
		report.setVisible(false);
		dayreport.setVisible(false);
	}
	
	public void setMain() {
		mainframe.setVisible(true);
		signin.setVisible(false);
		register.setVisible(false);
		exercise.setVisible(false);
		report.setVisible(false);
		dayreport.setVisible(false);
	}
	
	public void setExercise() {
		mainframe.setVisible(false);
		signin.setVisible(false);
		register.setVisible(false);
		exercise.setVisible(true);
		report.setVisible(false);
		dayreport.setVisible(false);
	}
	
	public void setMonthly() {
		mainframe.setVisible(false);
		signin.setVisible(false);
		register.setVisible(false);
		exercise.setVisible(false);
		report.setVisible(true);
		dayreport.setVisible(false);
	}
	
	public void setday() {
		mainframe.setVisible(false);
		signin.setVisible(false);
		register.setVisible(false);
		exercise.setVisible(false);
		report.setVisible(false);
		dayreport.setVisible(true);
	}
	
}
