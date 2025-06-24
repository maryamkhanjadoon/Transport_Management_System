/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transportmanagementsystem.validator;

/**
 *
 * @author AlainaImran
 */

import transportmanagementsystem.model.Booking;

public class BookingValidator {

    public static boolean isValid(Booking booking) {
        if (booking == null) return false;

        // Validate customer name
        if (booking.getCustomerName() == null || booking.getCustomerName().trim().isEmpty()) {
            System.out.println("Validation failed: Customer name is empty.");
            return false;
        }

        // Validate contact info
        if (booking.getContactInfo() == null || booking.getContactInfo().trim().length() < 5) {
            System.out.println("Validation failed: Contact info is invalid.");
            return false;
        }

        // Validate vehicle ID
        if (booking.getVehicleId() <= 0) {
            System.out.println("Validation failed: Invalid vehicle ID.");
            return false;
        }

        // Validate number of seats
        if (booking.getSeatsBooked() <= 0 || booking.getSeatsBooked() > 10) {
            System.out.println("Validation failed: Invalid number of seats.");
            return false;
        }

        // Validate status
        if (booking.getStatus() == null || 
            !(booking.getStatus().equalsIgnoreCase("pending") || booking.getStatus().equalsIgnoreCase("confirmed"))) {
            System.out.println("Validation failed: Status must be 'pending' or 'confirmed'.");
            return false;
        }

        return true; // Passed all checks
    }
}

