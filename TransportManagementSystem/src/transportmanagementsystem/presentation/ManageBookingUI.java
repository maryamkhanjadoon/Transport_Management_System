/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transportmanagementsystem.presentation;

/**
 *
 * @author AlainaImran
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ManageBookingUI extends JFrame {

    public ManageBookingUI() {
        // Set title
        setTitle("Manage Bookings");

        // Set size
        setSize(600, 500);

        // Center the window
        setLocationRelativeTo(null);

        // Set close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use BorderLayout for main layout
        setLayout(new BorderLayout());

        // ----- NORTH: Title and Description -----
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(70, 130, 180));
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Manage Bookings", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);

        JLabel descLabel = new JLabel("Easily manage your transport bookings from one place.", SwingConstants.CENTER);
        descLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        descLabel.setForeground(Color.WHITE);

        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.add(descLabel, BorderLayout.SOUTH);

        // ----- CENTER: Buttons -----
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 2, 15, 15));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        buttonPanel.setBackground(Color.WHITE);

        String[] buttonNames = {
            "Book Journey",
            "Payment & Confirmation",
            "My Bookings",
            "Cancel Booking",};

        for (String name : buttonNames) {
            JButton btn = new JButton(name);
            btn.setFocusPainted(false);
            btn.setFont(new Font("SansSerif", Font.PLAIN, 14));
            btn.setBackground(new Color(100, 149, 237));
            btn.setForeground(Color.WHITE);
            btn.setBorder(BorderFactory.createLineBorder(new Color(65, 105, 225)));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

            if (name.equals("Book Journey")) {
                btn.addActionListener(e -> {
                    new ViewVehiclesUI();
                });
            }
            if (name.equals("Payment & Confirmation")) {
                btn.addActionListener(e -> {

                });
            }
            if (name.equals("My Bookings")) {
                btn.addActionListener(e -> {
                    // new MyBookingsUI();
                });
            }
            if (name.equals("Cancel Bookings")) {
                btn.addActionListener(e -> {
                     new CancelBookingsUI();
                });
            }

            buttonPanel.add(btn);
        }

        // Add components to frame
        add(topPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        // Set visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Run GUI in Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new ManageBookingUI());
    }
}
