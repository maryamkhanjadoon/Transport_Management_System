package transportmanagementsystem.presentation;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import transportmanagementsystem.repository.VehicleRepository;
import transportmanagementsystem.model.Vehicle;
import transportmanagementsystem.DASHBOARD.Dashboard;

public class ViewVehiclesUI extends JFrame {

    private JPanel resultsPanel;

    public ViewVehiclesUI() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Available Vehicles");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top panel with back button and filters
        JPanel topPanel = new JPanel(new BorderLayout());

        // Back Button on the left
        JButton backBtn = new JButton("← Back");
        backBtn.addActionListener(e -> {
            dispose();
            new Dashboard().setVisible(true);
        });
        JPanel leftBackPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftBackPanel.add(backBtn);
        topPanel.add(leftBackPanel, BorderLayout.WEST);

        // Filter buttons in the center
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        JButton morningBtn = new JButton("Morning");
        JButton eveningBtn = new JButton("Evening");
        filterPanel.add(morningBtn);
        filterPanel.add(eveningBtn);
        topPanel.add(filterPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        // Results area
        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(resultsPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Event listeners
        morningBtn.addActionListener(e -> displayVehicles("morning"));
        eveningBtn.addActionListener(e -> displayVehicles("evening"));

        setVisible(true);
    }

    private void displayVehicles(String timeOfDay) {
        resultsPanel.removeAll(); // Clear previous results

        List<Vehicle> vehicles = VehicleRepository.fetchVehiclesByTime(timeOfDay);
        if (vehicles.isEmpty()) {
            resultsPanel.add(new JLabel("No vehicles found for " + timeOfDay));
        } else {
            for (Vehicle v : vehicles) {
                JPanel panel = new JPanel(new BorderLayout());
                panel.setBorder(BorderFactory.createTitledBorder("Bus No: " + v.getBusNo()));
                panel.setBackground(Color.WHITE);
                panel.setMaximumSize(new Dimension(650, 150));

                // Bus info
                JPanel infoPanel = new JPanel(new GridLayout(4, 1));
                infoPanel.add(new JLabel("Driver: " + v.getDriverName()));
                infoPanel.add(new JLabel("Route: " + v.getRoute()));
                infoPanel.add(new JLabel("Departure: " + v.getDepartureTime()));
                infoPanel.add(new JLabel("Seats: " + v.getTotalSeats()));
                panel.add(infoPanel, BorderLayout.CENTER);

                // Buttons
                JPanel actionPanel = new JPanel(new GridLayout(2, 1, 5, 5));
                JButton viewRouteBtn = new JButton("View Route/Details");
                JButton bookBtn = new JButton("Book Vehicle");

                // View Route action
                viewRouteBtn.addActionListener(e -> new ViewAvailableRoutesUI(v));

                // Book Vehicle action
                bookBtn.addActionListener(e -> new BookVehicleUI(v).setVisible(true));

                actionPanel.add(viewRouteBtn);
                actionPanel.add(bookBtn);
                panel.add(actionPanel, BorderLayout.EAST);

                resultsPanel.add(panel);
            }
        }

        resultsPanel.revalidate();
        resultsPanel.repaint();
    }
}
