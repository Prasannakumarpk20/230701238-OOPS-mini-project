import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SignUpScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton signUpButton;
    private JTextField roleField; // Admin or User

    public SignUpScreen() {
        setTitle("Sign Up - Airport Management System");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panel = new JPanel(new GridLayout(4, 2));
        JLabel usernameLabel = new JLabel("Username: ");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password: ");
        passwordField = new JPasswordField();
        JLabel roleLabel = new JLabel("Role (Admin/User): ");
        roleField = new JTextField();
        
        signUpButton = new JButton("Sign Up");
        
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(roleLabel);
        panel.add(roleField);
        panel.add(new JLabel()); // Empty space
        panel.add(signUpButton);

        add(panel);
        
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String role = roleField.getText();

                try {
                    if (role.equals("Admin") || role.equals("User")) {
                        Connection conn = DatabaseConnection.getConnection();
                        String sql = "INSERT INTO Users (Username, Password, Role) VALUES (?, ?, ?)";
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setString(1, username);
                        stmt.setString(2, password);
                        stmt.setString(3, role);
                        stmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Sign up successful.");
                        dispose(); // Close sign-up window
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid role. Please choose Admin or User.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error during sign-up.");
                }
            }
        });
    }

    public static void main(String[] args) {
        new SignUpScreen().setVisible(true);
    }
}
