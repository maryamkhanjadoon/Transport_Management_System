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
import java.util.*;
import transportmanagementsystem.model.Vehicle;
import transportmanagementsystem.util.DatabaseConnection;

public class VehicleRepository {

    public static List<Vehicle> fetchVehiclesByTime(String timeOfDay) {
    List<Vehicle> vehicles = new ArrayList<>();
    String query = "SELECT * FROM vehicles WHERE route_type = ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setString(1, timeOfDay.substring(0, 1).toUpperCase() + timeOfDay.substring(1).toLowerCase()); 
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Vehicle v = new Vehicle(
                    rs.getInt("vehicle_id"),         
                    rs.getString("bus_number"),
                    rs.getString("driver_name"),
                    rs.getString("route"),
                    rs.getString("departure_time"),
                    rs.getInt("total_seats")
            );
            vehicles.add(v);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return vehicles;
}
public static Vehicle fetchVehicleByBusNo(String busNo) {
    String query = "SELECT * FROM vehicles WHERE bus_number = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, busNo);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Vehicle(
                rs.getInt("vehicle_id"),               
                rs.getString("bus_number"),
                rs.getString("driver_name"),
                rs.getString("route"),
                rs.getString("departure_time"),
                rs.getInt("total_seats")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

    public static void updateSeats(String busNo, int updatedSeats) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
