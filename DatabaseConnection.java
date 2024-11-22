import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Method to create a connection to the database
public class DatabaseConnection {
    
    // Ensure this method is public so it can be accessed from other classes
    public static Connection getConnection() throws SQLException {
        try {
            // Return a connection to the database using the JDBC URL, username, and password
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/airportmanagement", "root", "Prasannakumar20");
        } catch (SQLException e) {
            // Throw the exception if a connection cannot be established
            throw new SQLException("Failed to connect to the database.", e);
        }
    }
}

/*public class DatabaseConnection {
    // Update the URL, username, and password according to your MySQL Workbench configuration
    private static final String URL = "jdbc:mysql://localhost:3306/airportmanagement"; // Replace 'your_database_name' with the actual database name
    private static final String USER = "root"; // Replace 'root' with your MySQL username if different
    private static final String PASSWORD = ""; // Replace 'your_password' with your MySQL password

    public static Connection getConnection() throws SQLException {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new SQLException("Failed to connect to the database.");
        }
    }
} */
