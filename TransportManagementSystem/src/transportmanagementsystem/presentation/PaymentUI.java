/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transportmanagementsystem.presentation;

/**
 *
 * @author Rising Star
 */



import transportmanagementsystem.controller.PaymentController;
import transportmanagementsystem.model.Booking;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PaymentUI extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private PaymentController controller;

    public PaymentUI() {
        setTitle("Payment Interface");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        model = new DefaultTableModel(new Object[]{"Booking ID", "Customer Name", "Vehicle ID", "Seats Booked", "Status", "Payment Status"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton payButton = new JButton("Pay Now");
        payButton.addActionListener(e -> controller.handlePayment(table.getSelectedRow()));

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(payButton, BorderLayout.SOUTH);

        add(panel);

        controller = new PaymentController(this);
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
