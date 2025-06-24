/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transportmanagementsystem.model;

/**
 *
 * @author AlainaImran
 */

public class Route {
    private String stopName;
    private String stopTime;

    public Route(String stopName, String stopTime) {
        this.stopName = stopName;
        this.stopTime = stopTime;
    }

    public String getStopName() {
        return stopName;
    }

    public String getStopTime() {
        return stopTime;
    }
}
