import java.awt.Color;
import java.awt.Container;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.ScrollPane;

import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;
import java.awt.Dimension;

public class Exercise extends JFrame {
	
	private JComboBox choiceEx;
	private static final String[] names = {"Jogging", "Running", "Walking", "Gym", "Swimming", "Cycling"};
	JTextField duration = new JTextField(5);
	JTextField calorie = new JTextField(8);
	//JTextField uid = new JTextField();
	java.sql.Timestamp date1 = new java.sql.Timestamp(new java.util.Date().getTime());
	public static int x;
	
	private JTable table;
	private String id;

	    public Exercise() {
	
		super("Exercise");
		
		Register e1 = new Register();
		id=e1.getUnique();
		
		
		setLayout(new GridLayout(1,2));
		GridBagConstraints controlP = new GridBagConstraints();

		JPanel exercisePanel = new JPanel(new GridBagLayout());
		exercisePanel.setBackground(Color.LIGHT_GRAY);
		
		add(exercisePanel);
		controlP.insets = new Insets(0,0,575,190);
		controlP.gridx = 1;
		controlP.gridy = 0;
		JButton dailyrep = new JButton("Print Daily Report");
		dailyrep.setForeground (Color.yellow);
		dailyrep.setPreferredSize(new Dimension(200, 50));
		dailyrep.setFont(new Font("Serif", Font.BOLD, 20));	
		dailyrep.setBackground(Color.gray);
		exercisePanel.add(dailyrep, controlP);
		
		controlP.insets = new Insets(0,0,575,200);
		controlP.gridx = 2;
		controlP.gridy = 0;
		JButton monthlyrep = new JButton("Print Monthly Report");
		monthlyrep.setForeground (Color.yellow);
		monthlyrep.setPreferredSize(new Dimension(250, 50));
		monthlyrep.setFont(new Font("Serif", Font.BOLD, 20));	
		monthlyrep.setBackground(Color.gray);
		exercisePanel.add(monthlyrep, controlP);
		
		controlP.insets = new Insets(0,0,575,50);
		controlP.gridx = 3;
		controlP.gridy = 0;
		JButton logout = new JButton("Log Out");
		logout.setForeground (Color.yellow);
		logout.setPreferredSize(new Dimension(200, 50));
		logout.setFont(new Font("Serif", Font.BOLD, 20));	
		logout.setBackground(Color.gray);
		exercisePanel.add(logout, controlP);
		
		controlP.insets = new Insets(0,-90,475,0);
		controlP.gridx = 1;
		controlP.gridy = 0;
		JLabel title = new JLabel("List of excercises done");
		title.setFont(new Font("roboto", Font.BOLD, 28));	
		exercisePanel.add(title, controlP);
		
		controlP.insets = new Insets(0,-300,375,0);
		controlP.gridx = 1;
		controlP.gridy = 0;
		JLabel chooseEx = new JLabel("Exercise");
		chooseEx.setFont(new Font("roboto", Font.BOLD, 23));	
		exercisePanel.add(chooseEx, controlP);
		
		controlP.insets = new Insets(0,25,375,0);
		controlP.gridx = 1;
		controlP.gridy = 0;
		choiceEx = new JComboBox(names);
		choiceEx.setFont(new Font("roboto", Font.PLAIN, 20));
		choiceEx.setMaximumRowCount(6);
		choiceEx.addItemListener(null);
		choiceEx.setEditable(true);
		exercisePanel.add(choiceEx, controlP);
		
		controlP.insets = new Insets(0,-330,375,0);
		controlP.gridx = 2;
		controlP.gridy = 0;
		JLabel durationT = new JLabel("Duration");
		durationT.setFont(new Font("roboto", Font.CENTER_BASELINE, 23));	
		exercisePanel.add(durationT, controlP);
		controlP.insets = new Insets(0,-100,375,0);
		exercisePanel.add(duration, controlP);
		duration.setFont(new Font("roboto", Font.PLAIN, 20));
		controlP.insets = new Insets(0,50,365,0);
		JLabel siunit = new JLabel("Minutes");
		siunit.setFont(new Font("roboto", Font.CENTER_BASELINE, 10));	
		exercisePanel.add(siunit, controlP);
		
		controlP.insets = new Insets(0,-300,375,0);
		controlP.gridx = 3;
		controlP.gridy = 0;
		JLabel calories = new JLabel("Calories");
		calories.setFont(new Font("roboto", Font.CENTER_BASELINE, 23));	
		exercisePanel.add(calories, controlP);
		controlP.insets = new Insets(0,-50,375,0);
		exercisePanel.add(calorie, controlP);
		calorie.setFont(new Font("roboto", Font.PLAIN, 20));
		controlP.insets = new Insets(0,0,365,-150);
		JLabel siunits = new JLabel("kcal");
		siunits.setFont(new Font("roboto", Font.CENTER_BASELINE, 10));	
		exercisePanel.add(siunits, controlP);
		
		controlP.insets = new Insets(0,-100,250,0);
		controlP.gridx = 1;
		controlP.gridy = 0;
		JButton calculate = new JButton("Calculate");
		calculate.setForeground (Color.yellow);
		calculate.setPreferredSize(new Dimension(150, 50));
		calculate.setFont(new Font("Serif", Font.BOLD, 20));	
		calculate.setBackground(Color.gray);
		exercisePanel.add(calculate, controlP);
		
		controlP.insets = new Insets(0,-70,250,100);
		controlP.gridx = 2;
		controlP.gridy = 0;
		JButton add = new JButton("Add");
		add.setForeground (Color.yellow);
		add.setPreferredSize(new Dimension(100, 50));
		add.setFont(new Font("Serif", Font.BOLD, 20));	
		add.setBackground(Color.gray);
		exercisePanel.add(add, controlP);
		
		controlP.insets = new Insets(0,0,-500,100);
		controlP.gridx = 3;
		controlP.gridy = 0;
		JButton sort = new JButton("Sort by date");
		sort.setForeground (Color.yellow);
		sort.setPreferredSize(new Dimension(150, 50));
		sort.setFont(new Font("Serif", Font.BOLD, 20));	
		sort.setBackground(Color.gray);
		exercisePanel.add(sort, controlP);
		
		controlP.insets = new Insets(230,0,-10,-700);
		controlP.gridx = 1;
		controlP.gridy = 0;
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		table = new JTable();
		scrollPane.setViewportView(table);
		
		table.setRowHeight(30);
		table.setIntercellSpacing(new Dimension(5, 5));
		table.setGridColor(Color.BLACK);
		table.setFillsViewportHeight(true);
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table.setFont(new Font("Arial", Font.PLAIN, 18));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Exercises", "Duration", "Calories"
			}
		));

		exercisePanel.add(scrollPane, controlP);
		
		ButtonHandler handler = new ButtonHandler();
		dailyrep.addActionListener(handler);
		monthlyrep.addActionListener(handler);
		logout.addActionListener(handler);
		choiceEx.addActionListener(handler);
		calculate.addActionListener(handler);
		add.addActionListener(handler);
		sort.addActionListener(handler);
		
		mouseHandle mHandler = new mouseHandle();
		table.addMouseMotionListener(mHandler);
		exercisePanel.addMouseMotionListener(mHandler);
		
	}

	private class ButtonHandler implements ActionListener{

		public void actionPerformed(java.awt.event.ActionEvent event) {
			
			if(event.getActionCommand().equals("Print Daily Report")) {
			
				//JOptionPane.showMessageDialog(null, "Printing daily report");	
				RunMainScreen screen = new RunMainScreen();
				screen.setday();
			}
			
			else if(event.getActionCommand().equals("Print Monthly Report")){
				
				//JOptionPane.showMessageDialog(null, "Printing monthly report");
				RunMainScreen screen = new RunMainScreen();
				screen.setMonthly();
			}
			
			else if(event.getActionCommand().equals("Log Out")){
				
				JOptionPane.showMessageDialog(null, "Logging out");
				RunMainScreen screen = new RunMainScreen();
				screen.setMain();
			}
			
			else if(event.getActionCommand().equals("Sort by date")){
				
				JOptionPane.showMessageDialog(null, "Sorting by date");
				x = 1;
				System.out.println(date1.toString().split(" ")[0]);
				
			}
			
			else if(event.getActionCommand().equals("Calculate")){
				
				JOptionPane.showMessageDialog(null, "Calculating");
				int calories = 0;
				if (choiceEx.getSelectedItem().equals("Jogging")) {
					String x = duration.getText();
					int a=Integer.parseInt(x);
					calories = a * 12;
					String z = Integer.toString(calories);
					calorie.setText(z);
				}
				else if (choiceEx.getSelectedItem().equals("Running")) {
					String x = duration.getText();
					int a=Integer.parseInt(x);
					calories = a * 20;
					String z = Integer.toString(calories);
					calorie.setText(z);
				}
				else if (choiceEx.getSelectedItem().equals("Walking")) {
					String x = duration.getText();
					int a=Integer.parseInt(x);
					calories = a * 5;
					String z = Integer.toString(calories);
					calorie.setText(z);
				}
				else if (choiceEx.getSelectedItem().equals("Gym")) {
					String x = duration.getText();
					int a=Integer.parseInt(x);
					calories = a * 15;
					String z = Integer.toString(calories);
					calorie.setText(z);
				}
				else if (choiceEx.getSelectedItem().equals("Swimming")) {
					String x = duration.getText();
					int a=Integer.parseInt(x);
					calories = a * 35;
					String z = Integer.toString(calories);
					calorie.setText(z);
				}
				else if (choiceEx.getSelectedItem().equals("Cycling")) {
					String x = duration.getText();
					int a=Integer.parseInt(x);
					calories = a * 22;
					String z = Integer.toString(calories);
					calorie.setText(z);
				}
			}
			
			else if(event.getActionCommand().equals("Add")){		
    			try {
    				
    				SignIn s1 = new SignIn();
    				String ids = s1.getU();
    				
    				if (ids != null) {
    					id = ids;
    				}
    				
    			
    				Class.forName("com.mysql.cj.jdbc.Driver");
    				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/maufitness","root","");
    				
					String query = "insert into exercises values(?,?,?,?,?)";
					PreparedStatement ps = con.prepareStatement(query);
					
					ps.setString(1,id);
					ps.setString(2, choiceEx.getSelectedItem().toString());
					ps.setString(3, duration.getText());
					ps.setString(4, calorie.getText());
					ps.setString(5, date1.toString().split(" ")[0]);
					ps.execute();

					JOptionPane.showMessageDialog(null, "Exercise added");
					con.close();
				} 
				
				catch (Exception event1) {
					JOptionPane.showMessageDialog(null, "Exercise not added. Error occured");
				}
				
				
			}

		}

	}
		
		private class mouseHandle implements MouseMotionListener{

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
					try {
						SignIn s1 = new SignIn();
	    				String ids = s1.getU();
	    				
	    				if (ids != null) {
	    					id = ids;
	    				}
	    				
	    				
	    				if (x == 1) {
	    					Class.forName("com.mysql.cj.jdbc.Driver");
		        			Connection con123 =DriverManager.getConnection("jdbc:mysql://localhost:3306/maufitness","root","");
		        			Statement stmt123 = con123.createStatement();
		        			String sql123 = "Select * from exercises where UserID = '"+id+"' and More = '"+date1.toString().split(" ")[0]+"'";
		        			ResultSet rs123 = stmt123.executeQuery(sql123);
		        			
		        			DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
		        			tblModel.setRowCount(0);
		        			
		        			while(rs123.next()) {
		        				
			        				String Name1 = rs123.getString("Name");
			        	            String Duration1 = rs123.getString("Duration");
			        	            String Calories1 = rs123.getString("Calories");
			        	            
			        	            String tbData[] = {Name1, Duration1, Calories1};
			        	            	tblModel.addRow(tbData);	
		        			
		        				
		        			}
		        			con123.close();
	    				}
	    				else {
	    					Class.forName("com.mysql.cj.jdbc.Driver");
		        			Connection con123 =DriverManager.getConnection("jdbc:mysql://localhost:3306/maufitness","root","");
		        			Statement stmt123 = con123.createStatement();
		        			String sql123 = "Select * from exercises where UserID = '"+id+"'";
		        			ResultSet rs123 = stmt123.executeQuery(sql123);
		        			
		        			DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
		        			tblModel.setRowCount(0);

		        			while(rs123.next()) {
		        				
			        				String Name1 = rs123.getString("Name");
			        	            String Duration1 = rs123.getString("Duration");
			        	            String Calories1 = rs123.getString("Calories");
			        	            
			        	            String tbData[] = {Name1, Duration1, Calories1};
			        	            	tblModel.addRow(tbData);	
		        			
		        				
		        			}
		        			con123.close();
	    				}
						
					} catch (Exception e2) {}
				
			}
			
		}

}