package transportmanagementsystem.DASHBOARD;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Dashboard extends JFrame {

    private JPanel sideMenu;

    public Dashboard() {
        setTitle("Transport Management System");
        setSize(1200, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Modern Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(25, 30, 40));
        headerPanel.setPreferredSize(new Dimension(getWidth(), 80));
        headerPanel.setBorder(new EmptyBorder(15, 20, 15, 20));

        JButton menuButton = new JButton("☰");
        menuButton.setFont(new Font("Inter", Font.BOLD, 20));
        menuButton.setFocusPainted(false);
        menuButton.setBackground(new Color(45, 55, 72));
        menuButton.setForeground(Color.WHITE);
        menuButton.setBorderPainted(false);
        menuButton.setPreferredSize(new Dimension(55, 50));
        menuButton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel titleLabel = new JLabel("Transport Management System");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Inter", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        userPanel.setBackground(new Color(25, 30, 40));
        JLabel userLabel = new JLabel("Administrator");
        userLabel.setForeground(new Color(156, 163, 175));
        userLabel.setFont(new Font("Inter", Font.PLAIN, 14));
        userPanel.add(userLabel);

        headerPanel.add(menuButton, BorderLayout.WEST);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.add(userPanel, BorderLayout.EAST);

        // Clean Footer
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(new Color(25, 30, 40));
        footerPanel.setPreferredSize(new Dimension(getWidth(), 45));
        footerPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel copyrightLabel = new JLabel("© 2025 Transport Management Solutions");
        copyrightLabel.setForeground(new Color(107, 114, 128));
        copyrightLabel.setFont(new Font("Inter", Font.PLAIN, 12));

        JLabel statusLabel = new JLabel("System Online");
        statusLabel.setForeground(new Color(34, 197, 94));
        statusLabel.setFont(new Font("Inter", Font.PLAIN, 12));

        footerPanel.add(copyrightLabel, BorderLayout.WEST);
        footerPanel.add(statusLabel, BorderLayout.EAST);

        // Modern Side Menu
        sideMenu = new JPanel();
        sideMenu.setLayout(new GridLayout(6, 1, 8, 8));
        sideMenu.setBackground(new Color(31, 41, 55));
        sideMenu.setPreferredSize(new Dimension(260, getHeight()));
        sideMenu.setBorder(new EmptyBorder(25, 20, 25, 20));
        sideMenu.setVisible(false);

        JButton btnManageBooking = createMenuButton("Manage Bookings");
        JButton btnTrackVehicle = createMenuButton("Vehicle Tracking");
        JButton btnFinance = createMenuButton("Finance Management");
        JButton btnFeedback = createMenuButton("Customer Feedback");
        JButton btnLogout = createMenuButton("Logout");
        JButton btnBack = createMenuButton("Back to Login");

        sideMenu.add(btnManageBooking);
        sideMenu.add(btnTrackVehicle);
        sideMenu.add(btnFinance);
        sideMenu.add(btnFeedback);
        sideMenu.add(btnLogout);
        sideMenu.add(btnBack);

        // Main Content Area
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(249, 250, 251));
        centerPanel.setBorder(new EmptyBorder(40, 50, 40, 50));

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(229, 231, 235), 1),
            new EmptyBorder(50, 50, 50, 50)
        ));

        // Title Section
        JPanel titleSection = new JPanel(new BorderLayout());
        titleSection.setBackground(Color.WHITE);
        titleSection.setBorder(new EmptyBorder(0, 0, 40, 0));

        JLabel mainTitle = new JLabel("Transport Management System");
        mainTitle.setFont(new Font("Inter", Font.BOLD, 32));
        mainTitle.setForeground(new Color(17, 24, 39));
        mainTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel subtitle = new JLabel("Comprehensive Transportation Solutions");
        subtitle.setFont(new Font("Inter", Font.PLAIN, 16));
        subtitle.setForeground(new Color(107, 114, 128));
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);

        titleSection.add(mainTitle, BorderLayout.CENTER);
        titleSection.add(subtitle, BorderLayout.SOUTH);

        // Modern Features Grid
        JPanel featuresPanel = new JPanel(new GridLayout(2, 3, 20, 20));
        featuresPanel.setBackground(Color.WHITE);
        featuresPanel.setBorder(new EmptyBorder(20, 0, 20, 0));

        // Create feature cards
        JPanel[] featureCards = {
            createFeatureCard("🚚", "Vehicle Tracking", "Real-time GPS monitoring and route optimization"),
            createFeatureCard("📋", "Booking System", "Advanced reservation and scheduling management"),
            createFeatureCard("💰", "Finance Reports", "Comprehensive financial analytics and reporting"),
            createFeatureCard("⭐", "Customer Service", "Feedback management and satisfaction tracking"),
            createFeatureCard("🔔", "Smart Alerts", "Automated notifications and system updates"),
            createFeatureCard("📱", "Mobile Ready", "Responsive interface for all devices")
        };

        for (JPanel card : featureCards) {
            featuresPanel.add(card);
        }

        // Welcome message panel
        JPanel welcomePanel = new JPanel(new BorderLayout());
        welcomePanel.setBackground(new Color(245, 247, 250));
        welcomePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219), 1),
            new EmptyBorder(25, 30, 25, 30)
        ));

        JLabel welcomeLabel = new JLabel(
            "<html><div style='text-align: center; line-height: 1.6;'>"
                + "<h2 style='color: #1F2937; margin-bottom: 15px;'>Welcome to Transport Management</h2>"
                + "<p style='color: #6B7280; font-size: 16px;'>"
                + "Streamline your transportation operations with our comprehensive platform. "
                + "Built for efficiency, designed for growth and success."
                + "</p>"
                + "</div></html>"
        );
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);

        contentPanel.add(titleSection, BorderLayout.NORTH);
        contentPanel.add(welcomePanel, BorderLayout.CENTER);
        contentPanel.add(featuresPanel, BorderLayout.SOUTH);
        
        centerPanel.add(contentPanel, BorderLayout.CENTER);

        // Event Handlers
        menuButton.addActionListener(e -> {
            sideMenu.setVisible(!sideMenu.isVisible());
            menuButton.setText(sideMenu.isVisible() ? "✕" : "☰");
        });

        btnManageBooking.addActionListener(this::handleManageBooking);
        btnTrackVehicle.addActionListener(this::handleTrackVehicle);
        btnFinance.addActionListener(this::handleFinance);
        btnFeedback.addActionListener(this::handleFeedback);
        btnLogout.addActionListener(e -> handleLogout());
        btnBack.addActionListener(e -> handleBackToLogin());

        // Assemble UI
        add(headerPanel, BorderLayout.NORTH);
        add(footerPanel, BorderLayout.SOUTH);
        add(sideMenu, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Inter", Font.PLAIN, 14));
        button.setBackground(new Color(59, 130, 246));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(220, 42));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(37, 99, 235));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(59, 130, 246));
            }
        });
        
        return button;
    }

    private JPanel createFeatureCard(String icon, String title, String description) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(229, 231, 235), 1),
            new EmptyBorder(20, 15, 20, 15)
        ));

        // Icon and title panel
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        headerPanel.setBackground(Color.WHITE);
        
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Inter", Font.BOLD, 14));
        titleLabel.setForeground(new Color(17, 24, 39));
        
        headerPanel.add(iconLabel);
        headerPanel.add(titleLabel);

        // Description
        JLabel descLabel = new JLabel("<html><div style='text-align: center; color: #6B7280; font-size: 12px;'>" + description + "</div></html>");
        descLabel.setHorizontalAlignment(SwingConstants.CENTER);

        card.add(headerPanel, BorderLayout.NORTH);
        card.add(descLabel, BorderLayout.CENTER);

        // Hover effect
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                card.setBackground(new Color(249, 250, 251));
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(59, 130, 246), 2),
                    new EmptyBorder(19, 14, 19, 14)
                ));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                card.setBackground(Color.WHITE);
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(229, 231, 235), 1),
                    new EmptyBorder(20, 15, 20, 15)
                ));
            }
        });

        return card;
    }

    private void handleManageBooking(ActionEvent e) {
        JOptionPane.showMessageDialog(this, 
            "Booking Management System\nFeature in development", 
            "Manage Bookings", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleTrackVehicle(ActionEvent e) {
        JOptionPane.showMessageDialog(this, 
            "Vehicle Tracking System\nFeature in development", 
            "Vehicle Tracking", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleFinance(ActionEvent e) {
        JOptionPane.showMessageDialog(this, 
            "Financial Management Module\nFeature in development", 
            "Finance Management", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleFeedback(ActionEvent e) {
        JOptionPane.showMessageDialog(this, 
            "Customer Feedback System\nFeature in development", 
            "Customer Feedback", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleLogout() {
        int choice = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to logout?", 
            "Confirm Logout", 
            JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Logged out successfully");
            dispose();
            new transportmanagementsystem.presentation.LoginForm().setVisible(true);
        }
    }

    private void handleBackToLogin() {
        dispose();
        new transportmanagementsystem.presentation.LoginForm().setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new Dashboard().setVisible(true));
    }
}