package transportmanagementsystem.presentation;

import transportmanagementsystem.util.LoginDBConnection;
import transportmanagementsystem.DASHBOARD.Dashboard;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginForm extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtPassword;

    private int failedAttempts = 0;

    public LoginForm() {
        setTitle("Login Form");
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen

        // Title
        JLabel lblTitle = new JLabel("Transport Management System", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setBounds(30, 10, 340, 30);
        add(lblTitle);

        // Email
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(50, 60, 80, 25);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(140, 60, 200, 25);
        add(txtEmail);

        // Password
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 100, 80, 25);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(140, 100, 200, 25);
        add(txtPassword);

        // Login Button
        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(140, 150, 100, 30);
        add(btnLogin);

        // Action Listener
        btnLogin.addActionListener(e -> loginUser());
    }

    private void loginUser() {
        String email = txtEmail.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter email and password");
            return;
        }

        try (Connection con = LoginDBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(
                     "SELECT * FROM users WHERE email = ? AND password = ?")) {

            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "✅ Login Successful");
                failedAttempts = 0;

                // Open Dashboard and close login window
                dispose();
                new Dashboard().setVisible(true);

            } else {
                failedAttempts++;
                if (failedAttempts >= 2) {
                    JOptionPane.showMessageDialog(this, "⚠️ Multiple wrong attempts! Try again carefully.");
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Wrong email or password");
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginForm().setVisible(true));
    }
}
