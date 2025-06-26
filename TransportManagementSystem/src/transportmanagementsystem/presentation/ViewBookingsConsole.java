/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transportmanagementsystem.presentation;

/**
 *
 * @author Rising Star
 */

   
import transportmanagementsystem.repository.BookingRepository;

public class ViewBookingsConsole {
    public static void main(String[] args) {
        BookingRepository.printAllBookings();  // Prints all bookings to console
    }
}

