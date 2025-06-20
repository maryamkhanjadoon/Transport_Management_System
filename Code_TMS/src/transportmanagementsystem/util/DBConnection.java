/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transportmanagementsystem.util;

/**
 *
 * @author WARDA YOUSAF
 */

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/transport_db", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
