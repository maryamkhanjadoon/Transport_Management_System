package transportmanagementsystem.presentation;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class RegistrationForm extends JFrame {

    private JTextField txtName, txtEmail;
    private JPasswordField txtPassword;

    private Connection con;
    private PreparedStatement pst;

    public RegistrationForm() {
        setTitle("User Registration Form");
        setSize(400, 350); // Suitable form size
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        setLayout(null);

        // Title
        JLabel lblTitle = new JLabel("Transport Management System", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setBounds(30, 10, 340, 30);
        add(lblTitle);

        // Name
        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(50, 60, 80, 25);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(140, 60, 200, 25);
        add(txtName);

        // Email
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(50, 100, 80, 25);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(140, 100, 200, 25);
        add(txtEmail);

        // Password
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 140, 80, 25);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(140, 140, 200, 25);
        add(txtPassword);

        // Register Button
        JButton btnRegister = new JButton("Register");
        btnRegister.setBounds(140, 180, 100, 30);
        add(btnRegister);

        // Already have an account
        JLabel lblHaveAccount = new JLabel("Already have an account?");
        lblHaveAccount.setBounds(110, 220, 180, 25);
        add(lblHaveAccount);

        JButton btnLoginNow = new JButton("Login Now");
        btnLoginNow.setBounds(140, 250, 120, 25);
        add(btnLoginNow);

        // Database connection
        connectToDatabase();

        btnRegister.addActionListener(e -> registerUser());

        btnLoginNow.addActionListener(e -> {
            dispose();
            new LoginForm().setVisible(true);
        });
    }

    private void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/transport_db", "root", "root");
            System.out.println("✅ Database connected successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Database Connection Failed: " + e.getMessage());
            con = null;
        }
    }

    private void registerUser() {
        String name = txtName.getText();
        String email = txtEmail.getText();
        String password = new String(txtPassword.getPassword());

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!");
            return;
        }

        try {
            pst = con.prepareStatement("INSERT INTO users (name, email, password) VALUES (?, ?, ?)");
            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, password);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "User Registered Successfully!");
            txtName.setText("");
            txtEmail.setText("");
            txtPassword.setText("");

        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(this, "Email already registered!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrationForm().setVisible(true));
    }
}
