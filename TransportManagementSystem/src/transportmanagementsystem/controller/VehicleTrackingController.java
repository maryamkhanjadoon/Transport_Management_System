/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transportmanagementsystem.controller;

import transportmanagementsystem.service.VehicleTrackingService;

public class VehicleTrackingController {

    private VehicleTrackingService service;

    public VehicleTrackingController() {
        this.service = new VehicleTrackingService();
    }

    public void processLocation(String vehicleId, double latitude, double longitude) {
        service.setLocation(vehicleId, latitude, longitude);
    }
}

