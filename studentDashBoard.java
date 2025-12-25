package campusComplaints;




import javax.swing.*;

import java.awt.*;

import java.awt.event.*;
import java.sql.*;
public class studentDashBoard extends JFrame {
	
                String RollNumber;

            	Connection conn;
                Statement stmt;
                ResultSet rs;
    studentDashBoard(String roll) {
                this.RollNumber=roll;
                
                connectDB() ;
                
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
        CardLayout cardLayout = new CardLayout();
        centerBox.setLayout(cardLayout);
        centerBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        centerBox.setBackground(Color.WHITE);

        lp.add(centerBox, Integer.valueOf(3));
        
     // HOME PANEL
        JPanel homePanel = new JPanel();
        homePanel.setBackground(Color.WHITE);
        JLabel hompePage = new JLabel("🏠 HOME PAGE", SwingConstants.CENTER);
        hompePage.setFont(new Font("Tahoma", Font.BOLD, 32));
        homePanel.add(hompePage);
      
        String aboutText =
                "<html><div style='text-align: center; width: 800px;'>"
              + "<b>About the App:</b><br><br>"
              + "With this app, you can view the availability of seats and book seats "
              + "in the college bus from the convenience of your phone, making the process "
              + "faster and easier than ever before.<br><br>"
              + "Say goodbye to long lines and the unpredictability of your seat with the "
              + "<b>LNMIIT Bus Booking App</b>."
              + "</div></html>";
        JLabel aboutLabel = new JLabel(aboutText, SwingConstants.CENTER);
        aboutLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        homePanel.add(aboutLabel);
        
     // PROFILE PANEL
       
        JPanel profilePanel = new JPanel();
       
        profilePanel.setBackground(Color.WHITE);
        JLabel pe = new JLabel("👤 PROFILE", SwingConstants.CENTER);
        pe.setFont(new Font("Tahoma", Font.BOLD, 32));
        profilePanel.add(pe);
       
        JLabel profileInfo = new JLabel(loadProfileHTML(), SwingConstants.CENTER);
        profileInfo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        

        // CENTER ALIGNMENT
        JPanel centerProfile = new JPanel(new GridBagLayout());
        centerProfile.setBackground(Color.WHITE);
        centerProfile.add(profileInfo);

        profilePanel.add(centerProfile, BorderLayout.CENTER);

        
     // CONTACT US PANEL
        JPanel contactusPanel = new JPanel();
        contactusPanel.setBackground(Color.WHITE);
        contactusPanel.setLayout(new BorderLayout(20, 20));

        
        JLabel cu = new JLabel("☎ CONTACT US", SwingConstants.CENTER);
        cu.setFont(new Font("Tahoma", Font.BOLD, 32));
        contactusPanel.add(cu, BorderLayout.NORTH);

        
        String contactText =
                "<html><div style='text-align:center; width:700px;'>"
              + "<b>LNMIIT Bus Services</b><br><br>"
              + "📍 The LNM Institute of Information Technology<br>"
              + "Rupa ki Nangal, Post-Sumel, Via Jamdoli<br>"
              + "Jaipur – 302031, Rajasthan<br><br>"
              + "📞 Phone: +91-141-2688XXX<br>"
              + "✉ Email: busservices@lnmiit.ac.in<br><br>"
              + "🕘 Office Hours: 9:00 AM – 5:00 PM (Mon–Fri)"
              + "</div></html>";

        JLabel contactInfo = new JLabel(contactText, SwingConstants.CENTER);
        contactInfo.setFont(new Font("Tahoma", Font.PLAIN, 18));

       
        JPanel centerContact = new JPanel(new GridBagLayout());
        centerContact.setBackground(Color.WHITE);
        centerContact.add(contactInfo);

        contactusPanel.add(centerContact, BorderLayout.CENTER);

        
     // SHOW BOOKINGS PANEL
        JPanel bookingPanel = new JPanel();
        bookingPanel.setBackground(Color.WHITE);
        bookingPanel.setLayout(new BorderLayout(20, 20));

       
        JLabel sbTitle = new JLabel(" BOOKINGS", SwingConstants.CENTER);
        sbTitle.setFont(new Font("Tahoma", Font.BOLD, 32));
        bookingPanel.add(sbTitle, BorderLayout.NORTH);

       
        JLabel BookingInfo = new JLabel(loadBookingHTML(), SwingConstants.CENTER);
        BookingInfo.setFont(new Font("Tahoma", Font.PLAIN, 18));

        
        JPanel centerBooking = new JPanel(new GridBagLayout());
        centerBooking.setBackground(Color.WHITE);
        centerBooking.add(BookingInfo);

        bookingPanel.add(centerBooking, BorderLayout.CENTER);

        
        //add cards
        centerBox.add(homePanel, "HOME");
        centerBox.add(bookingPanel, "BOOKING");
        centerBox.add(contactusPanel, "CONTACT");
        centerBox.add(profilePanel, "PROFILE");
        h.addActionListener(e -> cardLayout.show(centerBox, "HOME"));
        sb.addActionListener(e -> cardLayout.show(centerBox, "BOOKING"));
        c.addActionListener(e -> cardLayout.show(centerBox, "CONTACT"));
        p.addActionListener(e -> cardLayout.show(centerBox, "PROFILE"));
        
       
        
    }
    
    // Database connection method
    private void connectDB() 
     {
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BusManagement","root","Root@1234");
            System.out.println("Database Connected.");
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(this, "Database Connection Failed: " + e.getMessage());
        }
    }
    
    public class BusViewOpener implements ActionListener
    {

		@Override
		public void actionPerformed(ActionEvent e) {
			new ViewBusesFrame(RollNumber);
			
		}
    }
	private String loadProfileHTML() {

		    String html =
		        "<html><div style='text-align:center; width:700px;'>";

		    try {
		        String sql =
		            "SELECT Name, RollNumber, mobile " +
		            "FROM Student WHERE RollNumber = ?";

		        PreparedStatement pst = conn.prepareStatement(sql);
		        pst.setString(1, RollNumber);

		        ResultSet rs = pst.executeQuery();

		        if (rs.next()) {
		            html += "<b>Name:</b> " + rs.getString("Name") + "<br><br>";
		            html += "<b>Roll No:</b> " + rs.getString("RollNumber") + "<br><br>";
		            html += "<b>Email:</b> " + rs.getString("RollNumber") + "@lnmiit.ac.in"+"<br><br>";
		            html += "<b>Mobile Number:</b> " + rs.getString("mobile");
		        } else {
		            html += "Profile not found!";
		        }

		    } catch (SQLException e) {
		        html += "Error loading profile";
		    }

		    html += "</div></html>";
		    return html;
		}
	private String loadBookingHTML() {

	    String html =
	        "<html><div style='text-align:center; width:700px;'>";

	    try {
	        String sql =
	            "SELECT Booking_Id , RollNumber, SeatNumber, BusNumber " +
	            "FROM Bookings WHERE RollNumber = ?";

	        PreparedStatement pst = conn.prepareStatement(sql);
	        pst.setString(1, RollNumber);

	        ResultSet rs = pst.executeQuery();

	        if (rs.next()) {
	            html += "<b>Booking ID:</b> " + rs.getString("Booking_Id") + "<br><br>";
	            html += "<b>Roll No:</b> " + rs.getString("RollNumber") + "<br><br>";
	            html += "<b>SeatNumber:</b> " + rs.getString("SeatNumber") + "<br><br>";
	            html += "<b>Bus Number:</b> " + rs.getString("BusNumber");
	        } else {
	            html += "No bookings Available";
	        }

	    } catch (SQLException e) {
	        html += "Error loading profile";
	    }

	    html += "</div></html>";
	    return html;
	}

		
    	
    

    public static void main(String[] args) {
        new studentDashBoard("24ucs100");
    }
}



