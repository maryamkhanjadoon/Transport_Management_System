package transportmanagementsystem.controller;

import transportmanagementsystem.model.Vehicle;
import transportmanagementsystem.model.Booking;
import transportmanagementsystem.repository.BookingRepository;
import transportmanagementsystem.validator.BookingValidator;

public class BookingController {

    /**
 * Processes a booking request.
     * @param customerName
     * @param contactInfo
     * @param vehicle
     * @param seats
     * @return 
 */
public static boolean bookVehicle(String customerName, String contactInfo, Vehicle vehicle, int seats) {
    if (vehicle.getAvailableSeats() < seats) {
        System.out.println("Not enough seats available.");
        return false;
    }

    Booking booking = new Booking();
    booking.setCustomerName(customerName);
    booking.setContactInfo(contactInfo);
    booking.setVehicleId(vehicle.getVehicleId());
    booking.setSeatsBooked(seats);
    booking.setStatus("Pending");
    booking.setPaymentStatus("unpaid");

    if (!BookingValidator.isValid(booking)) {
        System.out.println("Booking validation failed.");
        return false;
    }

    boolean saved = BookingRepository.saveBooking(booking);
    return saved;
}

/**
 * Cancels a booking if allowed. If the booking is unpaid, it is cancelled directly.
 * If it was paid, a refund message is shown (actual refund logic not implemented).
 * @param booking The booking to cancel
 * @return true if successfully cancelled

     * @param booking
     * @return */
public static boolean cancelBooking(Booking booking) {
    if (booking.getPaymentStatus().equalsIgnoreCase("paid")) {
        System.out.println("Booking was paid. Refund will be processed.");
    } else {
        System.out.println("Unpaid booking. Cancelling directly...");
    }

    return BookingRepository.cancelBooking(booking);
}

}