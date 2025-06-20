/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transportmanagementsystem.repository;

/**
 *
 * @author WARDA YOUSAF
 */


import transportmanagementsystem.model.User;
import transportmanagementsystem.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public void saveUser(User user) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM users";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                users.add(new User(
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public int getUserCount() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT COUNT(*) AS total FROM users";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
