
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Bus extends JFrame {

    // ---------- SEATS ----------
    JRadioButton[] seats = new JRadioButton[50];
    ButtonGroup seatGroup = new ButtonGroup();

    // ---------- ICONS ----------
    ImageIcon availableIcon = new ImageIcon(
            getClass().getResource("/seats/seat_available.png"));

    ImageIcon selectedIcon = new ImageIcon(
            getClass().getResource("/seats/seat_selected.png"));

    ImageIcon bookedIcon = new ImageIcon(
            getClass().getResource("/seats/seat_booked.png"));

    // ---------- DB ----------
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    String url = "jdbc:mysql://localhost:3306/BusManagement";
    String user = "user1";
    String pass = "User1@00";

    // ---------- PASSED VALUES ----------
    String busNumber;
    String rollNumber;

    // ---------- CONSTRUCTOR ----------
    public Bus(String busNumber, String rollNumber) {
        this.busNumber = busNumber;
        this.rollNumber = rollNumber;

        connectDB();

        setTitle("Seat Booking");
        setSize(380, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel legendTitle = new JLabel("Seat Legend");
        legendTitle.setBounds(130, 10, 120, 20);
        legendTitle.setFont(new Font("Arial", Font.BOLD, 14));
        add(legendTitle);

        addLegendIcon(availableIcon, "Available", 40);
        addLegendIcon(selectedIcon, "Selected", 70);
        addLegendIcon(bookedIcon, "Booked", 100);

        JPanel busPanel = new JPanel(null);
        busPanel.setBounds(50, 130, 280, 500);
        busPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        add(busPanel);

        JLabel steering = new JLabel("🧑‍✈️");
        steering.setBounds(240, 10, 30, 30);
        busPanel.add(steering);

        int[] leftX = {30, 70};
        int[] rightX = {150, 190, 230};

        int y = 50;
        int seatNo = 1;

        for (int row = 0; row < 10; row++) {
            for (int x : leftX) {
                createSeat(busPanel, seatNo++, x, y);
            }

            for (int x : rightX) {
                createSeat(busPanel, seatNo++, x, y);
            }

            y += 40;
        }

        loadBookedSeats();

        JButton submit = new JButton("Confirm Booking");
        submit.setBounds(120, 650, 150, 30);
        add(submit);

        submit.addActionListener(e -> confirmBooking());

        setVisible(true);
    }

    // ---------- DB CONNECTION ----------
    void connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    // ---------- LOAD BOOKED SEATS ----------
    void loadBookedSeats() {
        try {
            String sql = "SELECT * FROM Bus WHERE BusNumber=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, busNumber);
            rs = ps.executeQuery();

            if (rs.next()) {
                for (int i = 1; i <= 50; i++) {
                    String status = rs.getString("S" + i + "status");
                    if ("booked".equals(status)) {
                        seats[i - 1].setIcon(bookedIcon);
                        seats[i - 1].setDisabledIcon(bookedIcon);
                        seats[i - 1].setEnabled(false);

                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    // ---------- CONFIRM BOOKING ----------
    void confirmBooking() {
        int selectedSeat = -1;

        for (int i = 0; i < seats.length; i++) {
            if (seats[i].isSelected()) {
                selectedSeat = i + 1;
                break;
            }
        }

        if (selectedSeat == -1) {
            JOptionPane.showMessageDialog(this, "Please select a seat");
            return;
        }

        bookSeatInDB(selectedSeat);
    }

    // ---------- TRANSACTION ----------
    void bookSeatInDB(int seatNo) {
        String seatColumn = "S" + seatNo + "status";

        try {
            con.setAutoCommit(false);

            String checkSQL
                    = "SELECT " + seatColumn + " FROM Bus WHERE BusNumber=?";
            ps = con.prepareStatement(checkSQL);
            ps.setString(1, busNumber);
            rs = ps.executeQuery();

            if (rs.next() && "booked".equals(rs.getString(1))) {
                JOptionPane.showMessageDialog(this, "Seat already booked!");
                return;
            }

            String insertBooking
                    = "INSERT INTO Bookings (RollNumber, SeatNumber, BusNumber) VALUES (?, ?, ?)";
            ps = con.prepareStatement(insertBooking);
            ps.setString(1, rollNumber);
            ps.setString(2, "S" + seatNo);
            ps.setString(3, busNumber);
            ps.executeUpdate();

            String updateBus
                    = "UPDATE Bus SET " + seatColumn + "='booked' WHERE BusNumber=?";
            ps = con.prepareStatement(updateBus);
            ps.setString(1, busNumber);
            ps.executeUpdate();

            con.commit();

            JOptionPane.showMessageDialog(this, "Seat S" + seatNo + " booked successfully!");

            seats[seatNo - 1].setIcon(bookedIcon);
            seats[seatNo - 1].setDisabledIcon(bookedIcon);
            seats[seatNo - 1].setEnabled(false);

        } catch (Exception e) {
            try {
                con.rollback();
            } catch (Exception ex) {
            }
            JOptionPane.showMessageDialog(this, "Booking failed!");
        }
    }

    // ---------- CREATE SEAT ----------
    void createSeat(JPanel panel, int index, int x, int y) {
        if (index > seats.length) {
            return;
        }

        JRadioButton seat = new JRadioButton();
        seat.setBounds(x, y, 30, 30);

        seat.setIcon(availableIcon);
        seat.setSelectedIcon(selectedIcon);

        seat.setOpaque(false);
        seat.setContentAreaFilled(false);
        seat.setBorderPainted(false);
        seat.setFocusPainted(false);
        

        seat.addActionListener(e -> {
            for (JRadioButton s : seats) {
                if (s != null && s.isEnabled() && !s.isSelected()) {
                    s.setIcon(availableIcon);
                }
            }
            seat.setIcon(selectedIcon);
        });

        seats[index - 1] = seat;
        seatGroup.add(seat);
        seat.setDisabledSelectedIcon(bookedIcon);

        panel.add(seat);
    }

    // ---------- LEGEND ----------
    void addLegendIcon(ImageIcon icon, String text, int y) {
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setBounds(80, y, 24, 24);
        add(iconLabel);

        JLabel label = new JLabel("= " + text);
        label.setBounds(110, y, 100, 20);
        add(label);
    }

    // ---------- MAIN ----------
    public static void main(String[] args) {
        new Bus("LN-101", "24UCS000");
    }
}
