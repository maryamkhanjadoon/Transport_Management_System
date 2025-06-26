package transportmanagementsystem.controller;

import transportmanagementsystem.presentation.PaymentUI;
import transportmanagementsystem.repository.BookingRepository;
import transportmanagementsystem.presentation.PaymentProcess;
import transportmanagementsystem.model.Booking;

import javax.swing.*;
import java.util.List;

public class PaymentController {
    private PaymentUI view;
    private String customerName;  // ✅ Track logged-in user

    // ✅ Constructor now accepts customerName
    public PaymentController(PaymentUI view, String customerName) {
        this.view = view;
        this.customerName = customerName;
    }

    // ✅ Only load unpaid bookings for this customer
    public void loadUnpaidBookings() {
        System.out.println("🔍 Loading unpaid bookings for: " + customerName);
        List<Booking> all = BookingRepository.loadBookingsByCustomerName(customerName);
        List<Booking> unpaid = all.stream()
                .filter(b -> "unpaid".equalsIgnoreCase(b.getPaymentStatus()))
                .toList();
        view.setBookings(unpaid);
    }

    // ✅ Optional: load all bookings (for future admin use)
    public void loadAllBookings() {
        List<Booking> all = BookingRepository.loadBookingsByCustomerName(customerName);
        view.setBookings(all);
    }

    public void handlePayment(int selectedRow) {
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(view, "Please select a booking.");
            return;
        }

        int bookingId = (int) view.getBookingTable().getValueAt(selectedRow, 0); // booking_id
        String customerName = (String) view.getBookingTable().getValueAt(selectedRow, 1); // customer_name

        PaymentProcess paymentForm = new PaymentProcess(customerName, bookingId, this);
        paymentForm.setVisible(true);
    }
}
