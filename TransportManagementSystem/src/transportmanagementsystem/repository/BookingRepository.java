/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transportmanagementsystem.repository;

/**
 *
 * @author AlainaImran
 */

import java.sql.*;
import transportmanagementsystem.model.Booking;
import transportmanagementsystem.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingRepository {
    public static boolean saveBooking(Booking booking) {
    try (Connection conn = DatabaseConnection.getConnection()) {
        String sql = "INSERT INTO bookings (customer_name, contact_info, vehicle_id, seats_booked, status, payment_status) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, booking.getCustomerName());
            stmt.setString(2, booking.getContactInfo());
            stmt.setInt(3, booking.getVehicleId());
            stmt.setInt(4, booking.getSeatsBooked());
            stmt.setString(5, booking.getStatus());
            stmt.setString(6, booking.getPaymentStatus());

            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    } catch (SQLException e) {
        System.out.println("Error saving booking: " + e.getMessage());
        return false;
    }
}

public static boolean cancelBooking(Booking booking) {
    Connection conn = DatabaseConnection.getConnection();
    if (conn == null) {
        System.out.println("Database connection failed.");
        return false;
    }

    String insertSQL = "INSERT INTO cancelled_bookings (customer_name, contact_info, vehicle_id, seats_booked, status, payment_status) VALUES (?, ?, ?, ?, ?, ?)";
    String deleteSQL = "DELETE FROM bookings WHERE booking_id = ?";

    try (
        PreparedStatement insertStmt = conn.prepareStatement(insertSQL);
        PreparedStatement deleteStmt = conn.prepareStatement(deleteSQL)
    ) {
        insertStmt.setString(1, booking.getCustomerName());
        insertStmt.setString(2, booking.getContactInfo());
        insertStmt.setInt(3, booking.getVehicleId());
        insertStmt.setInt(4, booking.getSeatsBooked());
        insertStmt.setString(5, booking.getStatus());
        insertStmt.setString(6, booking.getPaymentStatus());

        int inserted = insertStmt.executeUpdate();

        if (inserted > 0) {
            deleteStmt.setInt(1, booking.getBookingId());
            int deleted = deleteStmt.executeUpdate();

            if (deleted > 0) {
                System.out.println("Booking successfully cancelled and moved to cancelled_bookings.");
                return true;
            } else {
                System.out.println("Failed to delete booking after archiving.");
            }
        }
    } catch (SQLException e) {
        System.out.println("Error during cancellation: " + e.getMessage());
    }

    return false;
}

public static List<Booking> loadBookingsByCustomerName(String customerName) {
    List<Booking> bookings = new ArrayList<>();
    String sql = "SELECT * FROM bookings WHERE customer_name = ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, customerName);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Booking b = new Booking();
            b.setBookingId(rs.getInt("booking_id"));
            b.setCustomerName(rs.getString("customer_name"));
            b.setContactInfo(rs.getString("contact_info"));
            b.setVehicleId(rs.getInt("vehicle_id"));
            b.setSeatsBooked(rs.getInt("seats_booked"));
            b.setStatus(rs.getString("status"));
            b.setPaymentStatus(rs.getString("payment_status"));

            bookings.add(b);
        }

    } catch (SQLException e) {
        System.out.println("Error loading bookings: " + e.getMessage());
    }

    return bookings;
}

}