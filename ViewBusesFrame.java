package campusComplaints;

import javax.swing.*;
import java.awt.*;

public class ViewBusesFrame extends JFrame {
	String RollNumber;
    ViewBusesFrame(String RollNumber) {
        this.RollNumber = RollNumber;
        this.setLayout(null);
        this.setVisible(true);
        this.setBounds(800, 300, 500, 800);

        // ================= HEADER =================
        JPanel header = new JPanel(null);
        header.setBounds(0, 0, 500, 53);
        header.setBackground(new Color(173, 216, 230));
        this.add(header);

        Font hf = new Font("Tahoma", Font.BOLD, 13);

        JLabel hBus = new JLabel("BUS NO");
        hBus.setBounds(20, 15, 80, 25);
        hBus.setFont(hf);

        JLabel hFrom = new JLabel("FROM");
        hFrom.setBounds(110, 15, 100, 25);
        hFrom.setFont(hf);

        JLabel hTo = new JLabel("TO");
        hTo.setBounds(230, 15, 100, 25);
        hTo.setFont(hf);

        JLabel hTime = new JLabel("TIME");
        hTime.setBounds(320, 15, 60, 25);
        hTime.setFont(hf);

        header.add(hBus);
        header.add(hFrom);
        header.add(hTo);
        header.add(hTime);

        // ================= PANELS =================
        int y = 53;

        Color[] colors = {
                new Color(255,204,204), new Color(204,255,204),
                new Color(255,255,204), new Color(255,229,204),
                new Color(204,255,255), new Color(255,204,229),
                new Color(230,230,250), new Color(224,255,255),
                new Color(240,248,255), new Color(255,239,213),
                new Color(232,232,232), new Color(245,245,220),
                new Color(255,228,225), new Color(224,255,224)
        };

        String[][] data = {
                {"LN-101", "LNMIIT", "AjmeriGate", "07:00 AM"},
                {"LN-102", "AjmeriGate", "LNMIIT", "08:00 AM"},
                {"LN-103", "LNMIIT", "RajaPark", "10:00 AM"},
                {"LN-104", "RajaPark", "LNMIIT", "12:00noon"},
                {"LN-105", "LNMIIT", "RajaPark", "1:00 PM"},
                {"LN-106", "RajaPark ", "LNMIIT", "03:00 PM"},
                {"LN-107", "LNMIIT", "RajaPark", "04:00 PM"},
                {"LN-108", "LNMIIT", "AjmeriGate", "04:30 PM"},
                {"LN-109", "RajaPark", "LNMIIT", "05:15 PM"},
                {"LN-110", "LNMIIT", "RajaPark", "05:00 PM"},
                {"LN-111", "LNMIIT", "AjmeriGate", "06:00 PM"},
                {"LN-112", "AjmeriGate", "LNMIIT", "08:15 PM"},
                {"LN-113", "RajaPark", "LNMIIT", "09:00 PM"},
                {"LN-114", "AjmeriGate", "LNMIIT", "09:00 PM"}
        };

        for (int i = 0; i < 14; i++) {

            JPanel p = new JPanel(null);
            p.setBounds(0, y, 500, 53);
            p.setBackground(colors[i]);

            JLabel bus = new JLabel(data[i][0]);
            bus.setBounds(20, 15, 80, 25);

            JLabel from = new JLabel(data[i][1]);
            from.setBounds(110, 15, 100, 25);

            JLabel to = new JLabel(data[i][2]);
            to.setBounds(230, 15, 100, 25);

            JLabel time = new JLabel(data[i][3]);
            time.setBounds(320, 15, 60, 25);

            String busNo = data[i][0];  // LN-101, LN-102, etc.

            JButton book = new JButton("Book Seat");

            book.addActionListener(e -> {
                new Bus(busNo,RollNumber);   
            });
            book.setBounds(380, 10, 100, 30);

            p.add(bus);
            p.add(from);
            p.add(to);
            p.add(time);
            p.add(book);

            this.add(p);
            y += 53;
        }
    }

    public static void main(String[] args) {
        new ViewBusesFrame("24ucs100");
    }
}

