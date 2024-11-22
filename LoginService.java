import java.sql.*;

public class LoginService {
    public String authenticateUser(String username, String password) throws SQLException {
        String sql = "SELECT Role FROM Users WHERE Username = ? AND Password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("Role");
            } else {
                return null; // Invalid credentials
            }
        }
    }
}
