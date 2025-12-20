package campusComplaints;




import javax.swing.*;

import java.awt.*;

import java.awt.event.*;


public class studentDashBoard extends JFrame {

    studentDashBoard() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JLayeredPane lp = new JLayeredPane();
        lp.setBounds(0, 0, 2000, 1000);
        add(lp);

        // BACKGROUND IMAGE
        ImageIcon i1 = new ImageIcon(
            studentDashBoard.class.getResource(
                "ENTRANCE-qldsgzweof4m7k7ru3dvwxudeqwte00k8mbonf8mns.jpg")
        );
        Image i2 = i1.getImage().getScaledInstance(2000, 1000, Image.SCALE_SMOOTH);
        JLabel bg = new JLabel(new ImageIcon(i2));
        bg.setBounds(0, 0, 2000, 1000);
        lp.add(bg, Integer.valueOf(0));

        // TOP BAR
        JPanel bar = new JPanel();
        bar.setBounds(0, 0, 2000, 50);   // 🔧 smaller height
        bar.setBackground(new Color(0, 191, 255));
        lp.add(bar, Integer.valueOf(1));

        // TEXT
        JLabel greet = new JLabel("WELCOME TO LNMIIT BUS SERVICES", SwingConstants.CENTER);
        greet.setBounds(0, 80, 2000, 50); // 🔧 full width, centered
        greet.setFont(new Font("Tahoma", Font.BOLD, 42));
        lp.add(greet, Integer.valueOf(2));
        setVisible(true);
        
        JButton h = new JButton("HOME");
        JButton v = new JButton("View Buses");
        JButton sb = new JButton("Show Bookings");
        JButton c = new JButton("Contact-us");
        JButton p = new JButton("Profile");
        
        v.addActionListener(new BusViewOpener());
        
        h.setBounds(50, 30, 120, 40);
        v.setBounds(190, 30, 140, 40);
        sb.setBounds(350, 30, 170, 40);
        c.setBounds(540, 30, 140, 40);
        p.setBounds(740, 30, 140, 40);

        
        bar.add(h);
        bar.add(v);
        bar.add(sb);
        bar.add(c);
        bar.add(p);
        Font f = new Font("Tahoma", Font.BOLD, 14);

        h.setFont(f);
        v.setFont(f);
        sb.setFont(f);
        c.setFont(f);
        p.setFont(f);

        h.setFocusPainted(false);
        v.setFocusPainted(false);
        sb.setFocusPainted(false);
        c.setFocusPainted(false);
        p.setFocusPainted(false);
        
        
        JPanel centerBox = new JPanel();
        centerBox.setBounds(400, 250, 1100, 350); // center of screen
        centerBox.setLayout(new CardLayout());
        centerBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        centerBox.setBackground(Color.WHITE);

        lp.add(centerBox, Integer.valueOf(3));
        
        
    }
    
    public class BusViewOpener implements ActionListener
    {

		@Override
		public void actionPerformed(ActionEvent e) {
			new ViewBusesFrame();
			
		}

		
		
    	
    }

    public static void main(String[] args) {
        new studentDashBoard();
    }
}

