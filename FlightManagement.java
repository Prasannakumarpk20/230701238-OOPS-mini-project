import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class FlightManagement extends JFrame {
    public FlightManagement() {
        setTitle("Flight Management");
        setSize(500, 300);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(5, 2));
        JLabel flightLabel = new JLabel("Flight Number:");
        JTextField flightField = new JTextField();
        JLabel departureCityLabel = new JLabel("Departure City:");
        JTextField departureCityField = new JTextField();
        JLabel arrivalCityLabel = new JLabel("Arrival City:");
        JTextField arrivalCityField = new JTextField();
        JLabel capacityLabel = new JLabel("Capacity:");
        JTextField capacityField = new JTextField();
        
        JButton addFlightButton = new JButton("Add Flight");
        
        panel.add(flightLabel); panel.add(flightField);
        panel.add(departureCityLabel); panel.add(departureCityField);
        panel.add(arrivalCityLabel); panel.add(arrivalCityField);
        panel.add(capacityLabel); panel.add(capacityField);
        panel.add(addFlightButton);
        
        add(panel);
        
        addFlightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Check if the input fields are not empty
                if (flightField.getText().isEmpty() || departureCityField.getText().isEmpty() ||
                    arrivalCityField.getText().isEmpty() || capacityField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields must be filled.");
                    return;
                }

                try {
                    // Database connection
                    Connection conn = DatabaseConnection.getConnection();
                    String sql = "INSERT INTO Flights (FlightNumber, DepartureCity, ArrivalCity, Capacity) VALUES (?, ?, ?, ?)";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, flightField.getText());
                    stmt.setString(2, departureCityField.getText());
                    stmt.setString(3, arrivalCityField.getText());
                    
                    // Check if Capacity is a valid integer before inserting
                    try {
                        stmt.setInt(4, Integer.parseInt(capacityField.getText()));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Capacity must be a valid integer.");
                        return;
                    }
                    
                    // Execute the query
                    int rowsAffected = stmt.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Flight added successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to add flight.");
                    }
                    conn.close(); // Close the connection
                } catch (SQLException ex) {
                    ex.printStackTrace(); // Print detailed error to the console
                    JOptionPane.showMessageDialog(null, "Error adding flight: " + ex.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) {
        // Initialize and show the JFrame
        FlightManagement frame = new FlightManagement();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
