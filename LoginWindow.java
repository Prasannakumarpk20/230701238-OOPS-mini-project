import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginWindow extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;

    public LoginWindow() {
        setTitle("Login - Airport Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        
        JLabel roleLabel = new JLabel("Role:");
        roleComboBox = new JComboBox<>(new String[] {"Admin", "User"});

        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Check for valid username/password
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String role = (String) roleComboBox.getSelectedItem();

                if (username.equals("admin") && password.equals("admin123") || username.equals("user") && password.equals("user123")) {
                    // Login successful
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    dispose(); // Close the login window
                    
                    // Pass user role to the main window and show it
                    new Main(role).setVisible(true);
                } else {
                    // Login failed
                    JOptionPane.showMessageDialog(null, "Invalid credentials.");
                }
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(roleLabel);
        panel.add(roleComboBox);
        panel.add(new JLabel()); // Placeholder
        panel.add(loginButton);

        add(panel);
    }

    public static void main(String[] args) {
        new LoginWindow().setVisible(true); // Show login window initially
    }
}
