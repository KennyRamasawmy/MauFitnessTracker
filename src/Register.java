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
import java.sql.PreparedStatement;
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
import java.util.UUID;

public class Register extends JFrame {
	
	JPasswordField password = new JPasswordField(15);
	JTextField name = new JTextField(15);
	
private String uid;
	
	JTextField uniqueTextField = new JTextField();
	
    public String getUnique() {
        return uid;
    }
    public void setUnique(String uniqueID) {
        this.uid = this.uniqueTextField.getText();
        this.uid = uniqueID;
    }
	
	public Register() {
		super("Register");
		RunMainScreen e1 = new RunMainScreen();
		uid=e1.getUniqueID();
	
		setLayout(new GridLayout(1,1));
		GridBagConstraints controlPlacement = new GridBagConstraints();
		
		JPanel registerPanel = new JPanel(new GridBagLayout());
		registerPanel.setBackground(Color.LIGHT_GRAY);
		Border borderColor = new LineBorder(new Color(255, 255, 255));
		
		TitledBorder sidePaneltitledBorder2 = BorderFactory.createTitledBorder(borderColor);
		registerPanel.setBorder(new CompoundBorder(sidePaneltitledBorder2,new EmptyBorder(30, 70, 27, 0)));
		
		controlPlacement.insets = new Insets(0,100,25,200);
		controlPlacement.gridx = 0;
		controlPlacement.gridy = 1;
		JLabel title = new JLabel("Register");
		title.setFont(new Font("roboto", Font.BOLD, 28));	
		registerPanel.add(title, controlPlacement);
		
		controlPlacement.insets = new Insets(50,-100,10,10);
		JLabel title1 = new JLabel("Please create an account...");
		title1.setFont(new Font("roboto", Font.BOLD, 15));	
		registerPanel.add(title1, controlPlacement);
		
		controlPlacement.insets = new Insets(0,0,25,250);
		controlPlacement.gridx = 0;
		controlPlacement.gridy = 2;
		JLabel adminName = new JLabel("Enter user ID: ");
		adminName.setFont(new Font("roboto", Font.PLAIN, 14));	
		registerPanel.add(adminName, controlPlacement);
		controlPlacement.insets = new Insets(0,50,25,0);
		registerPanel.add(name, controlPlacement);
		
		controlPlacement.insets = new Insets(0,0,50,250);
		controlPlacement.gridx = 0;
		controlPlacement.gridy = 3;
		JLabel adminPass = new JLabel("Enter Password: ");
		adminPass.setFont(new Font("roboto", Font.PLAIN, 14));	
		registerPanel.add(adminPass, controlPlacement);
		controlPlacement.insets = new Insets(0,50,50,0);
		registerPanel.add(password, controlPlacement);
		
		controlPlacement.insets = new Insets(-80,0,0,100);
		controlPlacement.gridx = 0;
		controlPlacement.gridy = 4;
		JButton log = new JButton("REGISTER");
		log.setForeground (Color.BLUE);
		log.setPreferredSize(new Dimension(150, 30));
		log.setBackground(Color.cyan);
		registerPanel.add(log, controlPlacement);
		
		//button to go to menu
		controlPlacement.insets = new Insets(10,0,0,100);
		controlPlacement.gridx = 0;
		controlPlacement.gridy = 4;
		JButton backButton = new JButton("BACK");
		backButton.setForeground (Color.BLACK);
		backButton.setBackground(Color.CYAN);
		registerPanel.add(backButton, controlPlacement);
		
		JPanel test2 = new JPanel(new GridBagLayout());
		test2.setBackground(Color.GRAY);
	    test2.add(registerPanel);
	    add(test2, controlPlacement);
	    
		ButtonHandler handler = new ButtonHandler();
		log.addActionListener(handler);
		backButton.addActionListener(handler);
		
		
	}
	
	private class ButtonHandler implements ActionListener{


		public void actionPerformed(ActionEvent event) {
			
		
			if(event.getActionCommand().equals("REGISTER")) {
				
				
				
				try {
					
					if (name.getText().equals("") && password.getText().toString().equals("")) {
						JOptionPane.showMessageDialog(null, "Please try again...");
					}
					else {
						Class.forName("com.mysql.cj.jdbc.Driver");
		    			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/maufitness","root","");
		    			Statement stmt = con.createStatement();
		    			String sql = "Select * from account where UserName='" + name.getText() + "' and Password='" + password.getText().toString() + "'";
		    			ResultSet rs = stmt.executeQuery(sql);
		    			
						if(rs.next()) {
							JOptionPane.showMessageDialog(null, "Already a user!");
							con.close();
							
							RunMainScreen screen = new RunMainScreen();
							screen.setExercise();
			
						}
						
						else {
							
							
							
							String query = "insert into account values(?,?,?)";
							PreparedStatement ps = con.prepareStatement(query);
							ps.setString(1, name.getText());
							ps.setString(2, password.getText().toString());
							ps.setString(3,uid);
							ps.execute();
							
							con.close();
						
							JOptionPane.showMessageDialog(null, "New account created. Welcome to MauFitness.");
							
							RunMainScreen screen = new RunMainScreen();
							screen.setExercise();
							
						}
						
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