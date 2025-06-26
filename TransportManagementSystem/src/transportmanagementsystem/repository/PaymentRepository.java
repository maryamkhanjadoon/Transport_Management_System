/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transportmanagementsystem.repository;

/**
 *
 * @author Rising Star
 */

   
import transportmanagementsystem.model.Payment;
import transportmanagementsystem.util.DatabaseConnection;

import java.sql.*;

public class PaymentRepository {

    public static int savePayment(Payment payment) {
        String sql = "INSERT INTO payments (method, amount, booking_id) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, payment.getMethod());
            stmt.setDouble(2, payment.getAmount());
            stmt.setInt(3, payment.getBookingId());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                ResultSet keys = stmt.getGeneratedKeys();
                if (keys.next()) {
                    return keys.getInt(1); // return generated payment_id
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Error saving payment: " + e.getMessage());
        }
        return -1; // failed
    }
}


