package transportmanagementsystem.presentation;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import transportmanagementsystem.repository.VehicleRepository;
import transportmanagementsystem.repository.RouteRepository;
import transportmanagementsystem.model.Vehicle;
import transportmanagementsystem.model.Route;

public class ViewAvailableRoutesUI extends JFrame {
    private JPanel resultsPanel;

    public ViewAvailableRoutesUI() {
        setTitle("Available Routes");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        add(new JScrollPane(resultsPanel), BorderLayout.CENTER);

        // Default to showing morning vehicles
        displayVehicles("Morning");

        setVisible(true);
    }

    
    public ViewAvailableRoutesUI(Vehicle vehicle) {
        this();  
        showVehicleRouteInWindow(vehicle);
    }

    private void showVehicleRouteInWindow(Vehicle vehicle) {
        setTitle("Route Details - " + vehicle.getBusNo());
        JTextArea area = new JTextArea(15, 30);
        area.setEditable(false);
        StringBuilder sb = new StringBuilder("Route for " + vehicle.getBusNo() + ":\n\n");

        List<Route> routeList = RouteRepository.fetchRouteDetails(vehicle.getVehicleId());
        for (Route r : routeList) {
            sb.append(r.getStopTime()).append(" - ").append(r.getStopName()).append("\n");
        }

        area.setText(sb.toString());
        getContentPane().removeAll();
        add(new JScrollPane(area), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void displayVehicles(String timeOfDay) {
        resultsPanel.removeAll();

        List<Vehicle> vehicles = VehicleRepository.fetchVehiclesByTime(timeOfDay);
        if (vehicles.isEmpty()) {
            resultsPanel.add(new JLabel("No vehicles found for " + timeOfDay));
        } else {
            for (Vehicle v : vehicles) {
                JPanel panel = new JPanel(new GridLayout(0, 1));
                panel.setBorder(BorderFactory.createTitledBorder("Bus: " + v.getBusNo()));
                panel.add(new JLabel("Driver: " + v.getDriverName()));

                JButton viewDetailsBtn = new JButton("View Route Details");
                viewDetailsBtn.addActionListener(e -> showRouteDetails(v.getVehicleId(), v.getBusNo()));
                panel.add(viewDetailsBtn);

                resultsPanel.add(panel);
            }
        }

        resultsPanel.revalidate();
        resultsPanel.repaint();
    }

    private void showRouteDetails(int vehicleId, String busNo) {
        List<Route> routeList = RouteRepository.fetchRouteDetails(vehicleId);

        JTextArea area = new JTextArea(15, 30);
        area.setEditable(false);
        StringBuilder sb = new StringBuilder("Route for " + busNo + ":\n\n");
        for (Route r : routeList) {
            sb.append(r.getStopTime()).append(" - ").append(r.getStopName()).append("\n");
        }
        area.setText(sb.toString());

        JOptionPane.showMessageDialog(this, new JScrollPane(area), "Route Details", JOptionPane.INFORMATION_MESSAGE);
    }
}
