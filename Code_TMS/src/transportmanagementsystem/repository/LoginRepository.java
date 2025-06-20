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

public class LoginRepository {

    public boolean isValidUser(User user) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, user.getEmail());
            pst.setString(2, user.getPassword());

            ResultSet rs = pst.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

