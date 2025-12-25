package campusComplaints;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Bus extends JFrame {

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    String RollNumber;
    JCheckBox[] seats = new JCheckBox[20];
    JButton book = new JButton("Confirm Booking");
    String busNo;

    public Bus(String busNo,String RollNumber) {

        this.busNo = busNo;
        this.RollNumber= RollNumber;
        connectDB();

        setTitle("Bus Seat Selection");
        setSize(500, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Bus No: " + busNo, SwingConstants.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        // ================= BUS PANEL =================
        JPanel busPanel = new JPanel(new GridBagLayout());
        busPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 25, 15, 25);

        int seatNo = 1;

        // DRIVER
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel driver = new JLabel("🧑‍✈️");
        driver.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 36));
        busPanel.add(driver, gbc);

        gbc.gridx = 1;
        busPanel.add(new JLabel(""), gbc);

        // SEATS (5 × 4 = 20)
        for (int row = 1; row <= 5; row++) {

            gbc.gridy = row;

            for (int col = 0; col <= 1; col++) {
                gbc.gridx = col;
                addSeat(busPanel, gbc, seatNo++);
            }

            gbc.gridx = 2;
            busPanel.add(new JLabel(""), gbc);

            for (int col = 3; col <= 4; col++) {
                gbc.gridx = col;
                addSeat(busPanel, gbc, seatNo++);
            }
        }

        // ================= BUTTON PANEL =================
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);

        book.setFont(new Font("Tahoma", Font.BOLD, 16));
        bottomPanel.add(book);

        add(busPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        loadBookedSeats();

        book.addActionListener(e -> bookSeats());

        setVisible(true);
    }

    // ================= ADD SEAT =================
    private void addSeat(JPanel panel, GridBagConstraints gbc, int seatNo) {

        if (seatNo > seats.length) return;

        JCheckBox seat = new JCheckBox("S" + seatNo);
        seat.setFont(new Font("Tahoma", Font.BOLD, 16));
        seat.setPreferredSize(new Dimension(90, 50));
        seat.setBackground(Color.WHITE);
        seat.setHorizontalAlignment(SwingConstants.CENTER);

        seats[seatNo - 1] = seat;
        panel.add(seat, gbc);
    }

    // ================= DB CONNECTION =================
    private void connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/BusManagement",
                    "root",
                    "Root@1234"
            );
            System.out.println("Database Connected");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    // ================= LOAD RESERVED SEATS =================
    private void loadBookedSeats() {
        try {
            String sql = "SELECT SeatNumber FROM Bookings WHERE BusNumber=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, busNo);
            rs = ps.executeQuery();

            while (rs.next()) {
                String bookedSeat = rs.getString("SeatNumber");

                for (JCheckBox seat : seats) {
                    if (seat != null && seat.getText().equals(bookedSeat)) {
                        seat.setEnabled(false);
                        seat.setForeground(Color.GRAY);
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    // ================= BOOK SEATS =================
    private void bookSeats() {
        try {
            

            String sql = "INSERT INTO Bookings (RollNumber, SeatNumber, BusNumber) VALUES (?,?,?)";
            ps = conn.prepareStatement(sql);

            boolean selected = false;

            for (JCheckBox seat : seats) {
                if (seat != null && seat.isSelected() && seat.isEnabled()) {

                    ps.setString(1, RollNumber);
                    ps.setString(2, seat.getText());
                    ps.setString(3, busNo);
                    ps.executeUpdate();

                    seat.setEnabled(false);
                    seat.setSelected(false);
                    seat.setForeground(Color.GRAY);

                    selected = true;
                }
            }

            if (selected) {
                JOptionPane.showMessageDialog(this, "Booking Successful");
            } else {
                JOptionPane.showMessageDialog(this, "Select at least one seat");
            }

        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(this, "Seat already booked!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    // ================= MAIN =================
    public static void main(String[] args) {
        new Bus("LN-101","24ucs100");
    }
}
