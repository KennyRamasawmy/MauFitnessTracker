import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class dailyReport extends JFrame {
	
	JDateChooser dateChooserStart = new JDateChooser();
	JDateChooser dateChooserEnd = new JDateChooser();
	
	public dailyReport() {
		
		super("Exercise");
		setLayout(new GridLayout(1,2));
		GridBagConstraints controlPlacement = new GridBagConstraints();

		JPanel dailypanel = new JPanel(new GridBagLayout());
		dailypanel.setBackground(Color.lightGray);
		add(dailypanel);


	
		//Top Bar
		//Print Daily Report Button
		controlPlacement.insets = new Insets(0,200,400,50);
		controlPlacement.gridx = 1;
		controlPlacement.gridy = 0;
		JButton dailyrep = new JButton("Print Monthly Report");
		dailyrep.setForeground (Color.DARK_GRAY);
		dailyrep.setPreferredSize(new Dimension(200, 50));
		dailyrep.setFont(new Font("Serif", Font.BOLD, 20));	
		dailyrep.setBackground(Color.gray);
		dailypanel.add(dailyrep, controlPlacement);
		
	
		//Add Exercise Button	
		controlPlacement.insets = new Insets(0,100,400,50);
		controlPlacement.gridx = 2;
		controlPlacement.gridy = 0;
		JButton AddExercise = new JButton("Add Exercise");
		AddExercise.setForeground (Color.DARK_GRAY);
		AddExercise.setPreferredSize(new Dimension(250, 50));
		AddExercise.setFont(new Font("Serif", Font.BOLD, 20));	
		AddExercise.setBackground(Color.gray);
		dailypanel.add(AddExercise, controlPlacement);
		
		//Log Out Button Button
		controlPlacement.insets = new Insets(0,0,400,120);
		controlPlacement.gridx = 3;
		controlPlacement.gridy = 0;
		JButton logout = new JButton("Log Out");
		logout.setForeground(Color.DARK_GRAY);
		logout.setPreferredSize(new Dimension(150, 50));
		logout.setFont(new Font("Serif", Font.BOLD, 20));
		logout.setBackground(Color.gray);
		dailypanel.add(logout, controlPlacement);

		
		//Start Date
		controlPlacement.insets = new Insets(50,250,100,0);
		controlPlacement.gridx = 1;
		controlPlacement.gridy = 0;
		JLabel startdate = new JLabel("Choose Start Date");
		startdate.setFont(new Font("Arial", Font.PLAIN, 22));
		dailypanel.add(startdate, controlPlacement);
		
		controlPlacement.insets = new Insets(50,200,200,0);
		controlPlacement.gridx = 1;
		controlPlacement.gridy = 0;
		dateChooserStart.getJCalendar().setPreferredSize(new Dimension(300,300));
		dateChooserStart.setPreferredSize(new Dimension(200,50));
		dateChooserStart.setDate(null);
		dateChooserStart.setDateFormatString("yyyy-MM-dd");
		dailypanel.add(dateChooserStart, controlPlacement);

		controlPlacement.insets = new Insets(200,100,10,200);
		controlPlacement.gridx = 2;
		controlPlacement.gridy = 0;
		JButton exportbtn = new JButton("Export Report");
		exportbtn.setPreferredSize(new Dimension(200, 40));
		exportbtn.setFont(new Font("Arial", Font.PLAIN, 16));
		exportbtn.setBackground(Color.gray);
		dailypanel.add(exportbtn, controlPlacement);
			
		ButtonHandler Handler = new ButtonHandler();
		dailyrep.addActionListener(Handler);
		AddExercise.addActionListener(Handler);
		logout.addActionListener(Handler);
		exportbtn.addActionListener(Handler);
		
	}
	private class ButtonHandler implements ActionListener{

		public void actionPerformed(ActionEvent event) {
			
			if(event.getActionCommand().equals("Print Monthly Report")) {
			
				RunMainScreen screen = new RunMainScreen();
				screen.setMonthly();
				
			}
			
			else if(event.getActionCommand().equals("Export Report")) {
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String startingdate  = sdf.format(dateChooserStart.getDate());
				
				
				try {
					
					Register e1 = new Register();
					String id=e1.getUnique();
					
					SignIn s1 = new SignIn();
    				String ids = s1.getU();
    				
    				if (ids != null) {
    					id = ids;
    				}
    				
					Class.forName("com.mysql.cj.jdbc.Driver");
        			Connection con12 =DriverManager.getConnection("jdbc:mysql://localhost:3306/maufitness","root","");
        			Statement stmt12 = con12.createStatement();
        			String sql12 = "Select * from exercises where More = '"+startingdate+"' and UserID = '"+id+"'";
        			System.out.println(id);
        			ResultSet rs12 = stmt12.executeQuery(sql12);
        			
        			
        			FileWriter myWriter = new FileWriter("C:\\Users\\kenny\\Desktop\\MaufitnessReportDaily.txt");
        			myWriter.write("Exercise   \t" + "Duration \t" + "Calories \t" + "Date   \n");

        			while(rs12.next()) {
        				String Name12 = rs12.getString("Name") + "        ";
        	            String Duration12 = rs12.getString("Duration") + "          ";
        	            String Calories12 = rs12.getString("Calories") + "           ";
        	            String Date12 = rs12.getString("More") + "      ";
        	            System.out.println(Name12 + " " + Duration12 + " " + Calories12 + " " + Date12);
        	            
		        	        try{
		        	        	myWriter.write(Name12 + " " + Duration12 + " " + Calories12 + " " + Date12 + "\n");  
		        	        } catch(SecurityException se){
								
								System.out.println("No Permission");
							}
        	            } 
        			myWriter.close();
        			con12.close();
        			
        			JOptionPane.showMessageDialog(null, "Print Successful");
				} 
				catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Print not successful");
				}
				
			}
			
			else if(event.getActionCommand().equals("Add Exercise")) {
				
				RunMainScreen screen = new RunMainScreen();
				screen.setExercise();
				
			}
			
			else if(event.getActionCommand().equals("Log Out")) {
				
				RunMainScreen screen = new RunMainScreen();
				screen.setMain();
				
			}
		}
	}
}