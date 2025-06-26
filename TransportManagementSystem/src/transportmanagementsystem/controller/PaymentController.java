package transportmanagementsystem.controller;

import transportmanagementsystem.presentation.PaymentUI;
import transportmanagementsystem.repository.BookingRepository;
import transportmanagementsystem.presentation.PaymentProcess;

import javax.swing.*;

public class PaymentController {
    private PaymentUI view;

    public PaymentController(PaymentUI view) {
        this.view = view;
    }

    public void loadUnpaidBookings() {
        System.out.println("Loading unpaid bookings...");
        view.setBookings(BookingRepository.getUnpaidBookings());
    }

    public void loadAllBookings() {
        view.setBookings(BookingRepository.getAllBookings());
    }

   public void handlePayment(int selectedRow) {
    if (selectedRow < 0) {
        JOptionPane.showMessageDialog(view, "Please select a booking.");
        return;
    }

    int bookingId = (int) view.getBookingTable().getValueAt(selectedRow, 0); // booking_id
    String customerName = (String) view.getBookingTable().getValueAt(selectedRow, 1); // customer_name

    // Create and show payment form, passing required data
    PaymentProcess paymentForm = new PaymentProcess(customerName, bookingId, this);
    paymentForm.setVisible(true); // ✅ This line shows the form
}

}
