import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Flight Management class (stub)


// Ticket Booking class with form fields
class TicketBooking extends JFrame {
    public TicketBooking() {
        setTitle("Ticket Booking");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the form panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10)); // 5 rows, 2 columns layout

        // Create form fields
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel flightLabel = new JLabel("Flight Number:");
        JTextField flightField = new JTextField();

        JLabel dateLabel = new JLabel("Date (DD/MM/YYYY):");
        JTextField dateField = new JTextField();

        JLabel seatLabel = new JLabel("Seat Preference:");
        JComboBox<String> seatComboBox = new JComboBox<>(new String[]{"Window", "Aisle", "Middle"});

        JButton submitButton = new JButton("Submit");

        // Add components to the panel
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(flightLabel);
        panel.add(flightField);
        panel.add(dateLabel);
        panel.add(dateField);
        panel.add(seatLabel);
        panel.add(seatComboBox);
        panel.add(new JLabel()); // Empty cell for alignment
        panel.add(submitButton);

        // Action listener for the submit button
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Fetch input values
                String name = nameField.getText();
                String flightNumber = flightField.getText();
                String date = dateField.getText();
                String seatPreference = (String) seatComboBox.getSelectedItem();

                // Display confirmation dialog
                JOptionPane.showMessageDialog(null, 
                    "Ticket booked successfully!\n" +
                    "Name: " + name + "\n" +
                    "Flight Number: " + flightNumber + "\n" +
                    "Date: " + date + "\n" +
                    "Seat Preference: " + seatPreference, 
                    "Confirmation", 
                    JOptionPane.INFORMATION_MESSAGE);

                // Close the booking window
                dispose();
            }
        });

        // Add panel to the frame
        add(panel);
    }
}

// Check-In System class with form fields
class CheckInSystem extends JFrame {
    public CheckInSystem() {
        setTitle("Check-In System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the form panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10)); // 4 rows, 2 columns layout

        // Create form fields
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel flightLabel = new JLabel("Flight Number:");
        JTextField flightField = new JTextField();

        JButton checkInButton = new JButton("Check-In");

        // Add components to the panel
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(flightLabel);
        panel.add(flightField);
        panel.add(new JLabel()); // Empty cell for alignment
        panel.add(checkInButton);

        // Action listener for the check-in button
        checkInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Fetch input values
                String name = nameField.getText();
                String flightNumber = flightField.getText();

                // Display confirmation dialog
                JOptionPane.showMessageDialog(null, 
                    "Check-In successful!\n" +
                    "Name: " + name + "\n" +
                    "Flight Number: " + flightNumber, 
                    "Confirmation", 
                    JOptionPane.INFORMATION_MESSAGE);

                // Close the check-in window
                dispose();
            }
        });

        // Add panel to the frame
        add(panel);
    }
}

// Main class for the application
public class Main extends JFrame {
    private String userRole;

    // Constructor
    public Main(String userRole) {
        this.userRole = userRole;
        setTitle("Main - Airport Management System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1)); // Layout for buttons
        JButton flightButton = new JButton("Manage Flights");
        JButton ticketButton = new JButton("Book Ticket");
        JButton checkInButton = new JButton("Check-In");

        // Action listener for Manage Flights button
        flightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Only allow Admin to access flight management
                if ("Admin".equals(userRole)) {
                    new FlightManagement().setVisible(true); // Open Flight Management for Admin
                } else {
                    JOptionPane.showMessageDialog(null, "Access denied: Admin only.");
                }
            }
        });

        // Action listener for Book Ticket button
        ticketButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TicketBooking ticketBooking = new TicketBooking(); // Create instance of TicketBooking
                ticketBooking.setVisible(true); // Make TicketBooking window visible
            }
        });

        // Action listener for Check-In button
        checkInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CheckInSystem checkInSystem = new CheckInSystem(); // Create instance of CheckInSystem
                checkInSystem.setVisible(true); // Make CheckInSystem window visible
            }
        });

        panel.add(flightButton); // Add buttons to panel
        panel.add(ticketButton);
        panel.add(checkInButton);

        add(panel); // Add panel to frame
    }

    // Main method
    public static void main(String[] args) {
        String role = "Admin"; // Can be changed to "User" or any other role for testing
        new Main(role).setVisible(true); // Pass the role to the constructor
    }
}
    