/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main;

/**
 *
 * @author Rising Star
 */
class Vehicle {
    String vehicleName;
    String vehicleColor;
    String licensePlate;
    String status; // available, booked, under Maintenance
    String currentLocation;
    String capacity;

    public void checkAvailability() {
        System.out.println("Checking vehicle availability...");
    }

    public void getDetails() {
        System.out.println("Vehicle details: " + vehicleName + ", " + status);
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    public void assignDriver() {
        System.out.println("Assigning driver...");
    }

    public void updateLocation(String newLocation) {
        this.currentLocation = newLocation;
    }
}


class Payment {
    String paymentID;
    String bookingID;
    String userID;
    String vehicleID;
    String paymentAmount;
    String paymentDate;
    String paymentMethod; // card, cash, other

    public void makePayment() {
        System.out.println("Processing payment of " + paymentAmount + " using " + paymentMethod);
    }

    public void getPaymentStatus() {
        System.out.println("Payment status: confirmed");
    }
}
class Booking {
    String bookingID;
    String userID;
    String vehicleID;
    String routeID;
    String bookingTime;
    String startLocation;
    String stopLocation;
    String status; // pending, confirmed, cancelled
    String estimatedCost;

    public void createBooking() {
        System.out.println("Creating booking...");
    }

    public void updateBooking() {
        System.out.println("Updating booking...");
    }

    public void cancelBooking() {
        this.status = "cancelled";
        System.out.println("Booking cancelled.");
    }

    public void confirmBooking(Vehicle vehicle, Payment payment) {
        if (vehicle.status.equalsIgnoreCase("available")) {
            this.status = "confirmed";
            vehicle.updateStatus("booked");
            vehicle.assignDriver();
            payment.makePayment();
            System.out.println("Booking confirmed!");
        } else {
            System.out.println("Vehicle not available for booking.");
        }
    }

    public void notifyDriver() {
        System.out.println("Notifying driver of the booking...");
    }
}

public class Main {
    public static void main(String[] args) {
        // Create a vehicle
        Vehicle vehicle = new Vehicle();
        vehicle.vehicleName = "Toyota Prius";
        vehicle.vehicleColor = "White";
        vehicle.licensePlate = "ABC-123";
        vehicle.status = "available";
        vehicle.currentLocation = "Station A";
        vehicle.capacity = "4";

        // Create a payment
        Payment payment = new Payment();
        payment.paymentID = "PAY001";
        payment.bookingID = "BOOK123";
        payment.userID = "USER001";
        payment.vehicleID = "VEH123";
        payment.paymentAmount = "500";
        payment.paymentDate = "2025-05-03";
        payment.paymentMethod = "card";

        // Create a booking
        Booking booking = new Booking();
        booking.bookingID = "BOOK123";
        booking.userID = "USER001";
        booking.vehicleID = "VEH123";
        booking.routeID = "ROUTE45";
        booking.bookingTime = "10:00 AM";
        booking.startLocation = "Station A";
        booking.stopLocation = "Station B";
        booking.status = "pending";
        booking.estimatedCost = "500";

        // Confirm the booking
        booking.confirmBooking(vehicle, payment);
    }
}
