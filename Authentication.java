
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Authentication extends JFrame implements ActionListener {
    JTextField otpField;
    JButton confirm, resend;

    public Authentication() {
        setTitle("Authentication");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen (taskbar visible)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // -------- Background Image (Scaled) --------
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        ImageIcon bgIcon = new ImageIcon(
                RegistrationForm.class.getResource("centralplaza.jpg"));

        Image img = bgIcon.getImage();
        Image scaledImg = img.getScaledInstance(
                screenSize.width,
                screenSize.height,
                Image.SCALE_SMOOTH);

        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        JLabel background = new JLabel(scaledIcon);
        background.setBounds(0, 0, screenSize.width, screenSize.height);
        add(background);

        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBackground(Color.WHITE);
        panel1.setBounds(0, 0, screenSize.width, 75); // Position & size
        background.add(panel1);

        JLabel heading = new JLabel("LNMIIT BUS SERVICES");
        heading.setFont(new Font("Arial", Font.BOLD, 28));
        heading.setForeground(Color.BLACK);
        heading.setBounds(600, 15, 500, 40);
        panel1.add(heading);

        // -------- White Panel --------
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setBounds(550, 200, 400, 300); // Position & size
        background.add(panel);

        JLabel otp = new JLabel("Enter OTP");
        otp.setFont(new Font("Segoe UI Emoji", Font.BOLD, 24));
        otp.setBounds(145, 50, 200, 25);
        panel.add(otp);

        otpField = new JTextField();
        otpField.setBounds(100, 120, 200, 25);
        panel.add(otpField);

        confirm = new JButton("Confirm");
        confirm.setBounds(100, 170, 200, 25);
        panel.add(confirm);

        resend = new JButton("");
        resend.setBounds(100, 220, 200, 25);
        resend.setEnabled(false);
        panel.add(resend);

        Cursor c = new Cursor(Cursor.HAND_CURSOR);
        confirm.setCursor(c);

        ResendTimerThread timer = new ResendTimerThread();
        timer.start();

        confirm.setBackground(Color.WHITE);
        confirm.setForeground(Color.BLACK);

        resend.setBackground(Color.WHITE);
        resend.setForeground(Color.BLACK);

        confirm.setOpaque(true);
        confirm.setContentAreaFilled(true);

        resend.setOpaque(true);
        resend.setContentAreaFilled(true);

        confirm.addMouseListener(hoverEffect);
        resend.addMouseListener(hoverEffect);
        
        confirm.addActionListener(this);
        resend.addActionListener(this);
        setVisible(true);
    }

    MouseAdapter hoverEffect = new MouseAdapter() {

        @Override
        public void mouseEntered(MouseEvent e) {
            JButton btn = (JButton) e.getSource();
            if (btn.isEnabled()) {
                btn.setBackground(Color.BLUE);
                btn.setForeground(Color.WHITE);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JButton btn = (JButton) e.getSource();
            if (btn.isEnabled()) {
                btn.setBackground(Color.WHITE);
                btn.setForeground(Color.BLACK);
            }
        }
        
    };

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==resend) {
            ResendTimerThread timer = new ResendTimerThread();
            timer.start();
            
        }
    }

    public static void main(String[] args) {
        new Authentication();
    }

    class ResendTimerThread extends Thread {
        @Override
        public void run() {
            resend.setEnabled(false);
            resend.setBackground(Color.WHITE);
            resend.setForeground(Color.BLACK);
            
            for (int i = 60; i > 0; i--) {
                resend.setText("Resend in:" + i + "s");
                resend.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            resend.setText("Resend");
            resend.setEnabled(true);
            resend.setCursor(new Cursor(Cursor.HAND_CURSOR));

        }
    }

}
