import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.sql.Statement;

public class SignIn extends JFrame {
	
	JPasswordField password = new JPasswordField(15);
	JTextField name = new JTextField(15);
	private static String Uids;
	
	JTextField uTextField = new JTextField();
	
    public String getU() {
        return Uids;
    }
    public void setU(String uniqueID) {
        this.Uids = this.uTextField.getText();
        this.Uids = uniqueID;
    }
	
	public SignIn() {
		
		super("SignIn");
		setLayout(new GridLayout(1,1));
		GridBagConstraints controlP = new GridBagConstraints();
		
		JPanel loginPanel = new JPanel(new GridBagLayout());
		loginPanel.setBackground(Color.LIGHT_GRAY);
		Border borderColor = new LineBorder(new Color(255, 255, 255));
		
		TitledBorder sidePaneltitledBorder2 = BorderFactory.createTitledBorder(borderColor);
		loginPanel.setBorder(new CompoundBorder(sidePaneltitledBorder2,new EmptyBorder(30, 70, 27, 0)));
		
		controlP.insets = new Insets(0,150,25,250);
		controlP.gridx = 0;
		controlP.gridy = 1;
		JLabel title = new JLabel("Login");
		title.setFont(new Font("roboto", Font.BOLD, 30));	
		loginPanel.add(title, controlP);
		
		controlP.insets = new Insets(50,-100,10,10);
		JLabel title1 = new JLabel("Please sign in to access...");
		title1.setFont(new Font("roboto", Font.BOLD, 15));	
		loginPanel.add(title1, controlP);
		
		controlP.insets = new Insets(0,0,25,250);
		controlP.gridx = 0;
		controlP.gridy = 2;
		JLabel adminName = new JLabel("Enter user ID: ");
		adminName.setFont(new Font("roboto", Font.PLAIN, 14));	
		loginPanel.add(adminName, controlP);
		controlP.insets = new Insets(0,50,25,0);
		loginPanel.add(name, controlP);
		
		controlP.insets = new Insets(0,0,50,250);
		controlP.gridx = 0;
		controlP.gridy = 3;
		JLabel adminPass = new JLabel("Enter Password: ");
		adminPass.setFont(new Font("roboto", Font.PLAIN, 14));	
		loginPanel.add(adminPass, controlP);
		controlP.insets = new Insets(0,50,50,0);
		loginPanel.add(password, controlP);
		
		controlP.insets = new Insets(-80,0,0,100);
		controlP.gridx = 0;
		controlP.gridy = 4;
		JButton log = new JButton("LOGIN");
		log.setForeground (Color.BLUE);
		log.setPreferredSize(new Dimension(150, 30));
		log.setBackground(Color.cyan);
		loginPanel.add(log, controlP);
		
		controlP.insets = new Insets(10,0,0,100);
		controlP.gridx = 0;
		controlP.gridy = 4;
		JButton backButton = new JButton("BACK");
		backButton.setForeground (Color.BLACK);
		backButton.setBackground(Color.CYAN);
		loginPanel.add(backButton, controlP);
		
		JPanel test2 = new JPanel(new GridBagLayout());
		test2.setBackground(Color.GRAY);
	    test2.add(loginPanel);
	    add(test2, controlP);
	    
		ButtonHandler handler = new ButtonHandler();
		log.addActionListener(handler);
		backButton.addActionListener(handler);
		
	}
	private class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getActionCommand().equals("LOGIN")) {
			
				try {
						Class.forName("com.mysql.cj.jdbc.Driver");
		    			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/maufitness","root","");
		    			Statement stmt = con.createStatement();
		    			            
		    			String sql = "Select * from account where UserName='" + name.getText() + "' and Password='" + password.getText().toString() + "'";
		    			ResultSet rs = stmt.executeQuery(sql);
		    			
						if(rs.next()) {
						JOptionPane.showMessageDialog(null, "Login Successful");
							Uids = rs.getString(3); 
							RunMainScreen screen = new RunMainScreen();
							screen.setExercise();
							
						}
						
						else {
							
							JOptionPane.showMessageDialog(null, "Login Failed");
						
						}
						
				}
				catch (Exception event1) {
					System.out.println(event1);
				}
				
			}
			else if(event.getActionCommand().equals("BACK")){
				RunMainScreen screen = new RunMainScreen();
				screen.setMain();
			}

		}
	}	
	
}
