/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transportmanagementsystem.service;

/**
 *
 * @author AlainaImran
 */

public class DashboardService {

    public void viewProfile() {
        System.out.println("\n[Profile]");
        System.out.println("Username: admin");
        System.out.println("Role: Administrator");
    }

    public void manageTransport() {
        System.out.println("\n[Transport Management]");
        System.out.println("1. Add Bus");
        System.out.println("2. Remove Bus");
        System.out.println("3. Update Bus Info");
    }

    public void viewReports() {
        System.out.println("\n[Reports]");
        System.out.println("Trips Today: 14");
        System.out.println("Revenue: $2300");
    }
}
