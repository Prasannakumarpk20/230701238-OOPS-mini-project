import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class LoginScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, signUpButton;
    private LoginService loginService = new LoginService();

    public LoginScreen() {
        setTitle("Login - Airport Management System");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create panel with background image
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("airportmanagementsystem.jpeg"); // Assuming image.jpeg is in the project directory
                g.drawImage(backgroundImage.getImage(), 0, 0, null);
            }
        };
        panel.setLayout(new GridLayout(4, 2));

        // Username and password fields
        JLabel usernameLabel = new JLabel("Username: ");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password: ");
        passwordField = new JPasswordField();
        
        // Buttons for login and sign-up
        loginButton = new JButton("Login");
        signUpButton = new JButton("Sign Up");
        
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Empty space
        panel.add(loginButton);
        panel.add(new JLabel()); // Empty space
        panel.add(signUpButton);

        add(panel);
        
        // Action listeners for login and sign-up
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                try {
                    String role = loginService.authenticateUser(username, password);
                    if (role != null) {
                        JOptionPane.showMessageDialog(null, "Login successful! Role: " + role);
                        new Main(role).setVisible(true); // Pass role to main app
                        dispose(); // Close login window
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid credentials.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Database error during login.");
                }
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the sign-up form
                new SignUpScreen().setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        new LoginScreen().setVisible(true);
    }
}
