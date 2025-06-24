/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transportmanagementsystem.model;

/**
 *
 * @author AlainaImran
 */


public class Vehicle {
    private int vehicleId;
    private String busNo;
    private String driverName;
    private String route;
    private String departureTime;
    private int totalSeats;
    private int availableSeats;

    // Constructor
    public Vehicle(int vehicleId, String busNo, String driverName, String route, String departureTime, int totalSeats) {
        this.vehicleId = vehicleId;
        this.busNo = busNo;
        this.driverName = driverName;
        this.route = route;
        this.departureTime = departureTime;
        this.totalSeats = totalSeats;
        // Initialize availableSeats same as totalSeats by default
        this.availableSeats = totalSeats;  
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public String getBusNo() {
        return busNo;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getRoute() {
        return route;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}
