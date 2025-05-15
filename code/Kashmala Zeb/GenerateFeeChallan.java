/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package challan;


import java.util.Scanner;

public class GenerateFeeChallan {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

       
        System.out.println("Enter Student Name:");
        String studentName = input.nextLine();

        System.out.println("Enter Student ID:");
        String studentId = input.nextLine();

        System.out.println("Enter Route (main road(main campus), Havelian, or Mansehra):");
        String route = input.nextLine();

        System.out.println("Enter Month:");
        String month = input.nextLine();

       
        int fee = 0;
        if (route.equalsIgnoreCase("main road(main campus)")) {
            fee = 4000;
        } else if (route.equalsIgnoreCase("Havelian")) {
            fee = 6500;
        } else if (route.equalsIgnoreCase("Mansehra")) {
            fee = 5000;
        } else {
            System.out.println("Invalid route selected.");
            return;
        }


        System.out.println("\n========== Transport Fee Challan ==========");
        System.out.println("Student Name : " + studentName);
        System.out.println("Student ID   : " + studentId);
        System.out.println("Route        : " + route);
        System.out.println("Month        : " + month);
        System.out.println("Fee Amount   : Rs. " + fee);
        System.out.println("Due Date     : 10-" + month + "-2025");
        System.out.println("===========================================");
    }
}
