package transportmanagementsystem.presentation;

import transportmanagementsystem.model.Vehicle;
import transportmanagementsystem.model.UserLogin;
import transportmanagementsystem.controller.BookingController;
import transportmanagementsystem.DASHBOARD.Dashboard;

import javax.swing.*;
import java.awt.*;

public class BookVehicleUI extends JFrame {

    public BookVehicleUI(Vehicle v) {
        setTitle("Book Vehicle");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Top panel with back button
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("← Back");
        backButton.addActionListener(e -> {
            dispose();
            new Dashboard().setVisible(true);
        });
        topPanel.add(backButton);
        add(topPanel, BorderLayout.NORTH);

        // Center panel for booking form
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));

        String loggedInEmail = UserLogin.getCurrentUser().getEmail();

        // Fields
        JLabel lblUser = new JLabel("Logged-in User:");
        JTextField txtUser = new JTextField(loggedInEmail);
        txtUser.setEditable(false);

        JLabel lblBookingName = new JLabel("Booking Name:");
        JTextField txtBookingName = new JTextField();

        JLabel lblSeats = new JLabel("Number of Seats:");
        JTextField txtSeats = new JTextField();

        JLabel lblContact = new JLabel("Contact Info:");
        JTextField txtContact = new JTextField();

        JButton btnConfirm = new JButton("Confirm Booking");
        JButton btnCancel = new JButton("Cancel");

        // Booking action
        btnConfirm.addActionListener(e -> {
            String bookingName = txtBookingName.getText().trim();
            String contact = txtContact.getText().trim();
            String seatsStr = txtSeats.getText().trim();

            if (bookingName.isEmpty() || contact.isEmpty() || seatsStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            int seats;
            try {
                seats = Integer.parseInt(seatsStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid seat number.");
                return;
            }

            boolean success = BookingController.bookVehicle(loggedInEmail, contact, v, seats);

            if (success) {
                JOptionPane.showMessageDialog(this,
                        "✅ Vehicle Booked Successfully!\n\n" +
                        "Bus No: " + v.getBusNo() + "\n" +
                        "Route: " + v.getRoute() + "\n" +
                        "Departure: " + v.getDepartureTime() + "\n" +
                        "Reserved by: " + loggedInEmail + "\n" +
                        "Booked for: " + bookingName + "\n" +
                        "Seats: " + seats + "\n" +
                        "Contact: " + contact
                );
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Booking failed. Not enough seats or error occurred.");
            }
        });

        btnCancel.addActionListener(e -> dispose());

        // Add form components
        formPanel.add(lblUser);         formPanel.add(txtUser);
        formPanel.add(lblBookingName);  formPanel.add(txtBookingName);
        formPanel.add(lblSeats);        formPanel.add(txtSeats);
        formPanel.add(lblContact);      formPanel.add(txtContact);
        formPanel.add(btnConfirm);      formPanel.add(btnCancel);

        add(formPanel, BorderLayout.CENTER);
    }
}
