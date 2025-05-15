/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.trackvehiclelocation;

class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public void requestTracking(TrackingAppUI app) {
        app.trackVehicle();
    }
}
class TrackingAppUI {
    private LocationHandler locationHandler;

    public TrackingAppUI(LocationHandler handler) {
        this.locationHandler = handler;
    }

    public void trackVehicle() {
        locationHandler.getVehicleLocation();
    }
}

class LocationHandler {
    private TrackingServer server;
    private Vehicle[] vehicles;

    public LocationHandler(TrackingServer server, Vehicle[] vehicles) {
        this.server = server;
        this.vehicles = vehicles;
    }

    public void getVehicleLocation() {
        for (Vehicle v : vehicles) {
            TrackingDevice device = v.getTrackingDevice();
            String location = device.getGPSModule().getCoordinates();
            server.sendLocation(v.getId(), location);
        }
    }
}
class TrackingServer {
    private LocationDatabase database;

    public TrackingServer(LocationDatabase db) {
        this.database = db;
    }

    public void sendLocation(String vehicleId, String location) {
        database.storeLocation(vehicleId, location);
    }
}
class LocationDatabase {
    public void storeLocation(String vehicleId, String location) {
        System.out.println("Storing location for vehicle " + vehicleId + ": " + location);
    }
}
class Vehicle {
    private String id;
    private TrackingDevice trackingDevice;

    public Vehicle(String id, TrackingDevice device) {
        this.id = id;
        this.trackingDevice = device;
    }

    public String getId() {
        return id;
    }

    public TrackingDevice getTrackingDevice() {
        return trackingDevice;
    }
}
class TrackingDevice {
    private GPSModule gpsModule;
    private SIMModule simModule;

    public TrackingDevice(GPSModule gps, SIMModule sim) {
        this.gpsModule = gps;
        this.simModule = sim;
    }

    public GPSModule getGPSModule() {
        return gpsModule;
    }

    public SIMModule getSIMModule() {
        return simModule;
    }
}
class GPSModule {
    private Satellite satellite;

    public GPSModule(Satellite satellite) {
        this.satellite = satellite;
    }

    public String getCoordinates() {
        return satellite.provideCoordinates();
    }
}
class SIMModule {
    public void transmitData(String data) {
        System.out.println("Transmitting data: " + data);
    }
}
class Satellite {
    public String provideCoordinates() {
        return "Lat: 34.1, Long: 73.2";
    }
}

public class TrackVehicleLocation {

    public static void main(String[] args) {
         Satellite sat = new Satellite();
        GPSModule gps = new GPSModule(sat);
        SIMModule sim = new SIMModule();
        TrackingDevice device = new TrackingDevice(gps, sim);

        Vehicle v1 = new Vehicle("V001", device);
        Vehicle v2 = new Vehicle("V002", device);
        Vehicle[] vehicles = {v1, v2};

        LocationDatabase db = new LocationDatabase();
        TrackingServer server = new TrackingServer(db);
        LocationHandler handler = new LocationHandler(server, vehicles);
        TrackingAppUI app = new TrackingAppUI(handler);
        User user = new User("Alice");

        user.requestTracking(app);
    }
}
