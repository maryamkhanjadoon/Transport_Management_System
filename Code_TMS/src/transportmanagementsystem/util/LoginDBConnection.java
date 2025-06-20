package transportmanagementsystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoginDBConnection { // ✅ Class name updated to match Java naming convention

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/transport_db"; // ✅ Change DB name if needed
            String username = "root";
            String password = "root"; // ✅ Use your real password here

            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Connection failed: " + e.getMessage());
        }
    }
}
