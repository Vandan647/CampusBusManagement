package campusComplaints;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
	
	 ImageIcon i1 = new ImageIcon(
	            Login.class.getResource("loginicon.jpg")
	        );
	 JLabel i = new JLabel(i1);
	 JLabel l1 = new JLabel("username:");
	 JLabel l2 = new JLabel("password:");
	 JLabel l3 = new JLabel("Role:");
	 JLabel l0 = new JLabel("LOGIN");
	 JRadioButton b1 = new JRadioButton("Student");
	 JRadioButton b2 = new JRadioButton("Authority");
	 JRadioButton b3 = new JRadioButton("Admin");

	 ButtonGroup btn = new ButtonGroup();
	 JTextField f1 = new JTextField();
	 JPasswordField f2 = new JPasswordField();
	 
	 JButton LOGIN = new JButton("LOGIN");
	 JButton REGISTER = new JButton("REGISTER");
	 
	 
	Login()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setVisible(true);
		this.setLayout(null);
		this.setLocation(500,300);
		this.setSize(1000,500);
		add(i);
		i.setSize(500,500);
		add(l1);
		add(l2);
		add(l3);
		l1.setSize(100,20);l2.setSize(100,20);l3.setSize(100,20);
		l3.setLocation(500,104);
		l1.setLocation(500,200);
		l2.setLocation(500,300);
		btn.add(b1);
		 btn.add(b2);
		 btn.add(b3);
		 add(b1);add(b2);add(b3);
		 b1.setBounds(550,105,100,20);
		 b2.setBounds(650,105,100,20);
		 b3.setBounds(750,105,100,20);
	 add(f1);add(f2);
	 f1.setBounds(600,200,200,20);
	 f2.setBounds(600,300,200,20);
	 add(LOGIN); add(REGISTER);
	 LOGIN.setBounds(500, 400, 100, 40);
	 REGISTER.setBounds(620, 400, 100, 40);
	 add(l0);
	 l0.setBounds(650,20,100,60);
    
		
	}
   
	public static void main(String [] args)
	{
		new Login();
	}
}
