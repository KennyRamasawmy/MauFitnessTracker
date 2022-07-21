import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
	
	public MainFrame() {
		
		super("MainFrame");
		setLayout(new GridLayout(1,2));
		GridBagConstraints controlP = new GridBagConstraints();
		 
		JPanel west = new JPanel(new GridBagLayout());
		
		controlP.insets = new Insets(0,0,0,0);

		controlP.gridx = 0;
		controlP.gridy = 2;
		JLabel image = new JLabel(new ImageIcon("cardiochallenge2.jpg"));
		image.setPreferredSize(new Dimension(300, 800));
		west.add(image , controlP); 
		
		add(west);
		
		JPanel east = new JPanel(new GridBagLayout());
		east.setBackground(Color.LIGHT_GRAY);
		 
		controlP.insets = new Insets(0,0,75,0);
		controlP.gridx = 0;
		controlP.gridy = 1;
		JLabel text1 = new JLabel("Welcome To MauFitness");
		text1.setFont(new Font("Roboto", Font.BOLD, 35));
		text1.setForeground (Color.DARK_GRAY);
		east.add(text1, controlP);
		
		controlP.insets = new Insets(0,0,75,0);
		controlP.gridx = 0;
		controlP.gridy = 2;
		JButton adminButton = new JButton("Sign In");
		adminButton.setForeground (Color.WHITE);
		adminButton.setPreferredSize(new Dimension(200, 50));
		adminButton.setFont(new Font("Roboto", Font.BOLD, 30));
		adminButton.setBackground(Color.gray);
		east.add(adminButton, controlP);
		
		controlP.insets = new Insets(0,0,75,0);
		controlP.gridx = 0;
		controlP.gridy = 3;
		JButton customerButton = new JButton("Register");
		customerButton.setForeground (Color.WHITE);
		customerButton.setPreferredSize(new Dimension(200, 50));
		customerButton.setFont(new Font("Roboto", Font.BOLD, 30));
		customerButton.setBackground(Color.gray);
		east.add(customerButton, controlP);
		
		add(east);
		
		ButtonHandler handler = new ButtonHandler();
		adminButton.addActionListener(handler);
		customerButton.addActionListener(handler);
		
	}
	
	private class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			
			if(event.getActionCommand().equals("Sign In")) {
				JOptionPane.showMessageDialog(null, "Log In selected!");
				RunMainScreen screen = new RunMainScreen();
				screen.setLogin();
			}
			
			else if(event.getActionCommand().equals("Register")) {
				
				JOptionPane.showMessageDialog(null, "Sign Up selected!");
				RunMainScreen screen = new RunMainScreen();
				screen.setRegister();
			}
			
		}
	}
}
