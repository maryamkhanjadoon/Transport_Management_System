package transportmanagementsystem.presentation;

import transportmanagementsystem.controller.PaymentController;
import transportmanagementsystem.model.Booking;
import transportmanagementsystem.DASHBOARD.Dashboard;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PaymentUI extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private PaymentController controller;
    private String loggedInCustomerName;

    public PaymentUI(String customerName) {
        this.loggedInCustomerName = customerName;

        setTitle("Payment Interface");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // ✅ Create top panel for Back Button
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        topPanel.setBackground(new Color(245, 245, 245));

        JButton backButton = new JButton("←");
        backButton.setPreferredSize(new Dimension(45, 28));
        backButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        backButton.setBackground(new Color(220, 53, 69)); // Red
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setToolTipText("Back to Dashboard");
        backButton.addActionListener(e -> {
            dispose();
            new Dashboard().setVisible(true);
        });
        topPanel.add(backButton);

        // ✅ Table setup
        model = new DefaultTableModel(
            new Object[]{"Booking ID", "Customer Name", "Vehicle ID", "Seats Booked", "Status", "Payment Status"}, 0
        );
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // ✅ Payment button
        JButton payButton = new JButton("Pay Now");
        payButton.addActionListener(e -> controller.handlePayment(table.getSelectedRow()));

        // ✅ Panel assembly
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(topPanel, BorderLayout.NORTH);              // Top panel with back button
        panel.add(scrollPane, BorderLayout.CENTER);           // Table
        panel.add(payButton, BorderLayout.SOUTH);             // Bottom pay button

        add(panel);

        // ✅ Controller and data
        controller = new PaymentController(this, loggedInCustomerName);
        controller.loadUnpaidBookings();
    }

    public void setBookings(List<Booking> bookings) {
        model.setRowCount(0);
        for (Booking b : bookings) {
            model.addRow(new Object[]{
                b.getBookingId(), b.getCustomerName(), b.getVehicleId(),
                b.getSeatsBooked(), b.getStatus(), b.getPaymentStatus()
            });
        }
    }

    public JTable getBookingTable() {
        return table;
    }
}
