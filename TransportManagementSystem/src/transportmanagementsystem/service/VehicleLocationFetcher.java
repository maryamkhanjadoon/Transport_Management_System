/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transportmanagementsystem.service;

import java.sql.*;

public class VehicleLocationFetcher {

private static final String DB_URL = "jdbc:mysql://localhost:3306/vehicle_tracking?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public static double[] getLatestCoordinates(String vehicleId) {
        double[] coords = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String sql = "SELECT latitude, longitude FROM vehicle_location WHERE vehicle_id = ? ORDER BY timestamp DESC LIMIT 1";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, vehicleId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                double lat = rs.getDouble("latitude");
                double lng = rs.getDouble("longitude");
                coords = new double[]{lat, lng};
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Error fetching location: " + e.getMessage());
        }

        return coords;
    }
}
