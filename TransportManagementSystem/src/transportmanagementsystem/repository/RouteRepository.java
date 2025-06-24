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
import transportmanagementsystem.model.Route;
import transportmanagementsystem.util.DatabaseConnection;

public class RouteRepository {

    public static List<Route> fetchRouteDetails(int vehicleId) {
        List<Route> routeList = new ArrayList<>();
        String query = "SELECT stop_name, stop_time FROM routes WHERE vehicle_id = ? ORDER BY stop_order ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, vehicleId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                routeList.add(new Route(rs.getString("stop_name"), rs.getTime("stop_time").toString()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return routeList;
    }
}
