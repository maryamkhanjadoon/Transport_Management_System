package transportmanagementsystem.presentation;

import transportmanagementsystem.DASHBOARD.Dashboard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;

public class GetLocationApp {

    private JLabel mapLabel;
    private ImageIcon mapIcon;
    private JFrame frame;
    private Timer trackingTimer;
    private static final int MAP_INSET = 15;

    private double markerX = 0;
    private double markerY = 0;

    private final double[][] path = new double[][] {
        {24.8600, 67.0005},
        {24.8602, 67.0010},
        {24.8604, 67.0015},
        {24.8606, 67.0020},
        {24.8608, 67.0025},
        {24.8610, 67.0030},
        {24.8608, 67.0025},
        {24.8606, 67.0020},
        {24.8604, 67.0015},
        {24.8602, 67.0010},
        {24.8600, 67.0005}
    };

    private final double maxLat = 24.8610;
    private final double minLat = 24.8590;
    private final double minLng = 67.0000;
    private final double maxLng = 67.0030;

    private int currentSegmentIndex = 0;
    private final int stepsPerSegment = 30;
    private int currentStep = 0;

    private Image carImage;

    public GetLocationApp() {
        createAndShowGUI();
    }

    public void createAndShowGUI() {
        frame = new JFrame("Vehicle Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 550);
        frame.setLayout(new BorderLayout(10, 10));

        // Load images
        mapIcon = new ImageIcon("resources/map_image.png");
        if (mapIcon.getIconWidth() <= 0 || mapIcon.getIconHeight() <= 0) {
            JOptionPane.showMessageDialog(frame, "Map image not loaded! Check 'resources/map_image.png'");
            return;
        }
        carImage = new ImageIcon("resources/car_icon.png").getImage();

        // Button Panel
        JPanel buttonPanel = new JPanel(new BorderLayout()) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(235, 215, 255));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        buttonPanel.setBorder(new EmptyBorder(12, 12, 12, 12));

        // ➕ Add Back (X) button on top-left
        JButton backButton = new JButton("✕");
        backButton.setPreferredSize(new Dimension(35, 35));
        backButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        backButton.setBackground(new Color(220, 38, 38)); // red
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setMargin(new Insets(0, 0, 0, 0));
        backButton.addActionListener(e -> {
            frame.dispose();
            new Dashboard().setVisible(true);
        });

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        leftPanel.setOpaque(false);
        leftPanel.add(backButton);
        buttonPanel.add(leftPanel, BorderLayout.WEST);

        // ➕ Add Track Button in center
        JButton trackButton = new GradientButton("Track Vehicle Location");
        trackButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        trackButton.setForeground(Color.WHITE);
        trackButton.setFocusPainted(false);
        trackButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        trackButton.addActionListener(this::startTracking);

        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.add(trackButton);
        buttonPanel.add(centerPanel, BorderLayout.CENTER);

        // Map Label with Vehicle Marker
        mapLabel = new JLabel(mapIcon) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (carImage != null) {
                    g.drawImage(carImage, (int) markerX + MAP_INSET, (int) markerY + MAP_INSET, 30, 30, this);
                } else {
                    g.setColor(Color.RED);
                    g.fillOval((int) markerX + MAP_INSET, (int) markerY + MAP_INSET, 15, 15);
                }
            }
        };
        mapLabel.setBorder(new EmptyBorder(MAP_INSET, MAP_INSET, MAP_INSET, MAP_INSET));

        JPanel bottomPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(235, 215, 255));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        bottomPanel.setPreferredSize(new Dimension(700, 50));

        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(mapLabel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void startTracking(ActionEvent e) {
        if (trackingTimer != null) trackingTimer.cancel();

        trackingTimer = new Timer(true);
        trackingTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                moveMarkerAlongPath();
                SwingUtilities.invokeLater(() -> mapLabel.repaint());
            }
        }, 0, 600);
    }

    private void moveMarkerAlongPath() {
        double[] start = path[currentSegmentIndex];
        double[] end = path[(currentSegmentIndex + 1) % path.length];

        double t = (double) currentStep / stepsPerSegment;
        double lat = start[0] + t * (end[0] - start[0]);
        double lng = start[1] + t * (end[1] - start[1]);

        int imgWidth = mapIcon.getIconWidth();
        int imgHeight = mapIcon.getIconHeight();

        markerX = mapValue(lng, minLng, maxLng, 0, imgWidth - 30);
        markerY = mapValue(maxLat - lat, 0, maxLat - minLat, 0, imgHeight - 30);

        currentStep++;
        if (currentStep > stepsPerSegment) {
            currentStep = 0;
            currentSegmentIndex = (currentSegmentIndex + 1) % path.length;
        }
    }

    private int mapValue(double value, double minSrc, double maxSrc, int minDst, int maxDst) {
        double ratio = (value - minSrc) / (maxSrc - minSrc);
        ratio = Math.max(0, Math.min(ratio, 1));
        return (int) (minDst + ratio * (maxDst - minDst));
    }

    // Gradient Button Class
    static class GradientButton extends JButton {
        private boolean hovered = false;

        public GradientButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setOpaque(false);
            setFocusPainted(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setMargin(new Insets(10, 25, 10, 25));
            addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent e) { hovered = true; repaint(); }
                public void mouseExited(java.awt.event.MouseEvent e) { hovered = false; repaint(); }
            });
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Color colorStart = hovered ? new Color(138, 43, 226) : new Color(128, 0, 128);
            Color colorEnd = hovered ? new Color(199, 21, 133) : new Color(139, 0, 139);
            GradientPaint gp = new GradientPaint(0, 0, colorStart, 0, getHeight(), colorEnd);
            g2d.setPaint(gp);
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(getText());
            int textHeight = fm.getAscent();
            int x = (getWidth() - textWidth) / 2;
            int y = (getHeight() + textHeight) / 2 - 3;

            g2d.setColor(Color.WHITE);
            g2d.drawString(getText(), x, y);
            g2d.dispose();
        }
    }

    // Entry for standalone testing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GetLocationApp());
    }
}
