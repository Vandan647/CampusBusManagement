import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RegistrationForm extends JFrame implements ActionListener {

    JTextField nameField,emailField, mobileField;
    JPasswordField passField,confirmPassField;
    JButton otpBtn,clearBtn,eyeBtn1,eyeBtn2;
    ImageIcon eyeOpenIcon,eyeCloseIcon;
    char passEcho,confirmEcho;

    public RegistrationForm() {

        setTitle("Registration Form");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

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
        panel1.setBounds(0, 0, screenSize.width, 75);
        background.add(panel1);

        JLabel heading = new JLabel("LNMIIT BUS SERVICES");
        heading.setFont(new Font("Arial", Font.BOLD, 28));
        heading.setForeground(Color.BLACK);
        heading.setBounds(600, 15, 500, 40);
        panel1.add(heading);

    
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setBounds(500, 150, 500, 500);
        background.add(panel);


        JLabel title = new JLabel("Welcome, New User 👋");
        title.setFont(new Font("Segoe UI Emoji", Font.BOLD, 24));
        title.setBounds(120, 20, 300, 30);
        panel.add(title);

        
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(80, 80, 100, 25);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(200, 80, 200, 25);
        panel.add(nameField);

        
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(80, 130, 100, 25);
        panel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(200, 130, 200, 25);
        panel.add(emailField);

        
        JLabel mobileLabel = new JLabel("Mobile No:");
        mobileLabel.setBounds(80, 180, 100, 25);
        panel.add(mobileLabel);

        mobileField = new JTextField();
        mobileField.setBounds(200, 180, 200, 25);
        panel.add(mobileField);

        
        JLabel passLabel = new JLabel("Create Password:");
        passLabel.setBounds(80, 230, 100, 25);
        panel.add(passLabel);

        passField = new JPasswordField();
        passField.setBounds(200, 230, 200, 25);
        panel.add(passField);

        
        JLabel confirmPassLabel = new JLabel("Confirm Password:");
        confirmPassLabel.setBounds(80, 280, 150, 25);
        panel.add(confirmPassLabel);

        confirmPassField = new JPasswordField();
        confirmPassField.setBounds(200, 280, 200, 25);
        panel.add(confirmPassField);

        
        otpBtn = new JButton("Send OTP");
        otpBtn.setBounds(90, 340, 140, 35);
        panel.add(otpBtn);

        clearBtn = new JButton("Clear");
        clearBtn.setBounds(270, 340, 140, 35);
        panel.add(clearBtn);

        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        otpBtn.setCursor(cursor);
        clearBtn.setCursor(cursor);

        otpBtn.setBackground(Color.WHITE);
        otpBtn.setForeground(Color.BLACK);

        otpBtn.setOpaque(true);
        otpBtn.setContentAreaFilled(true);

        otpBtn.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                otpBtn.setBackground(Color.BLUE);
                otpBtn.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                otpBtn.setBackground(Color.WHITE);
                otpBtn.setForeground(Color.BLACK);
            }
        });

        clearBtn.setBackground(Color.LIGHT_GRAY);
        clearBtn.setForeground(Color.BLACK);
        clearBtn.addActionListener(this);

        eyeOpenIcon = new ImageIcon(getClass().getResource("eye_open.png"));
        eyeCloseIcon = new ImageIcon(getClass().getResource("eye_closed.png"));

        passEcho = passField.getEchoChar();
        confirmEcho = confirmPassField.getEchoChar();

        eyeBtn1 = new JButton(eyeOpenIcon);
        eyeBtn1.setBounds(400, 230, 35, 25);
        eyeBtn1.setBorderPainted(false);
        eyeBtn1.setFocusPainted(false);
        eyeBtn1.setContentAreaFilled(false);
        eyeBtn1.addActionListener(this);
        panel.add(eyeBtn1);

        eyeBtn2 = new JButton(eyeOpenIcon);
        eyeBtn2.setBounds(400, 280, 35, 25);
        eyeBtn2.setBorderPainted(false);
        eyeBtn2.setFocusPainted(false);
        eyeBtn2.setContentAreaFilled(false);
        eyeBtn2.addActionListener(this);
        panel.add(eyeBtn2);

        eyeBtn1.setCursor(cursor);
        eyeBtn2.setCursor(cursor);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == eyeBtn1) {
            if (passField.getEchoChar() == 0) {
            passField.setEchoChar(passEcho);
            eyeBtn1.setIcon(eyeOpenIcon);
        } else {
            passField.setEchoChar((char) 0);
            eyeBtn1.setIcon(eyeCloseIcon);
        }
        }

        else if (e.getSource() == eyeBtn2) {
        if (confirmPassField.getEchoChar() == 0) {
            confirmPassField.setEchoChar(confirmEcho);
            eyeBtn2.setIcon(eyeOpenIcon);
        } else {
            confirmPassField.setEchoChar((char) 0);
            eyeBtn2.setIcon(eyeCloseIcon);
        }
        }

        else if (e.getSource()==otpBtn) {

        }

        else if (e.getSource()==clearBtn) {
            clearFields();
        }

    }

    private void clearFields() {
        nameField.setText("");
        emailField.setText("");
        mobileField.setText("");
        passField.setText("");
        confirmPassField.setText("");
    }

    public static void main(String[] args) {
        new RegistrationForm();
    }

}
