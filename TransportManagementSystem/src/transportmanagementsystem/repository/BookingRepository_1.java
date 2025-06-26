package transportmanagementsystem.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import transportmanagementsystem.model.Booking;
import transportmanagementsystem.util.DatabaseConnection;

public class BookingRepository {

    // ✅ Save a new booking to the database
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
            System.out.println("❌ Error saving booking: " + e.getMessage());
            return false;
        }
    }

    // ✅ Fetch bookings with 'unpaid' payment status
    public static List<Booking> getUnpaidBookings() {
        List<Booking> unpaidBookings = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM bookings WHERE payment_status = 'unpaid'";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Booking booking = mapResultSetToBooking(rs);
                    unpaidBookings.add(booking);
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching unpaid bookings: " + e.getMessage());
        }
        return unpaidBookings;
    }

    // ✅ Fetch all bookings
    public static List<Booking> getAllBookings() {
        List<Booking> allBookings = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM bookings";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Booking booking = mapResultSetToBooking(rs);
                    allBookings.add(booking);
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching all bookings: " + e.getMessage());
        }
        return allBookings;
    }

    // ✅ Mark a booking as paid & confirmed
    public static boolean markBookingAsPaid(int bookingId) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE bookings SET payment_status = 'paid', status = 'confirmed' WHERE booking_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, bookingId);
                int rowsUpdated = stmt.executeUpdate();
                return rowsUpdated > 0;
            }
        } catch (SQLException e) {
            System.out.println("❌ Error updating payment status: " + e.getMessage());
            return false;
        }
    }

    // ✅ Console method to print all bookings
    public static void printAllBookings() {
        List<Booking> bookings = getAllBookings();
        if (bookings.isEmpty()) {
            System.out.println("ℹ No bookings found.");
        } else {
            System.out.println("📋 All Bookings:");
            for (Booking b : bookings) {
                System.out.println(b); // toString() of Booking class handles formatting
            }
        }
    }

    // ✅ Helper method to reduce repetition
    private static Booking mapResultSetToBooking(ResultSet rs) throws SQLException {
        Booking booking = new Booking();
        booking.setBookingId(rs.getInt("booking_id"));
        booking.setCustomerName(rs.getString("customer_name"));
        booking.setContactInfo(rs.getString("contact_info"));
        booking.setVehicleId(rs.getInt("vehicle_id"));
        booking.setSeatsBooked(rs.getInt("seats_booked"));
        booking.setStatus(rs.getString("status"));
        booking.setPaymentStatus(rs.getString("payment_status"));
        return booking;
    }
}
