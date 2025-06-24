package transportmanagementsystem.presentation;

import transportmanagementsystem.model.Booking;
import transportmanagementsystem.model.UserLogin;
import transportmanagementsystem.repository.BookingRepository;
import transportmanagementsystem.DASHBOARD.Dashboard;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CancelBookingsUI extends JFrame {

    private JTable bookingsTable;
    private DefaultTableModel tableModel;
    private String customerName;

    public CancelBookingsUI() {
        this.customerName = UserLogin.getLoggedInUsername();
        if (customerName == null) {
            JOptionPane.showMessageDialog(this, "No logged-in user found.");
            return;
        }

        setTitle("Cancel Bookings - " + customerName);
        setSize(900, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initUI();
        loadBookings();
        setVisible(true);
    }

    private void initUI() {
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Your Bookings (" + customerName + ")", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        add(label, BorderLayout.NORTH);

        bookingsTable = new JTable();
        tableModel = new DefaultTableModel(
                new Object[]{"ID", "Name", "Contact", "Vehicle ID", "Seats", "Status", "Payment", "Action"}, 0);
        bookingsTable.setModel(tableModel);
        JScrollPane scrollPane = new JScrollPane(bookingsTable);
        add(scrollPane, BorderLayout.CENTER);

        bookingsTable.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());
        bookingsTable.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor(new JCheckBox()));

        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(e -> {
            dispose();
            new Dashboard().setVisible(true);
        });
        JPanel southPanel = new JPanel();
        southPanel.add(backButton);
        add(southPanel, BorderLayout.SOUTH);
    }

    private void loadBookings() {
        tableModel.setRowCount(0);
        String currentUser = UserLogin.getCurrentUser().getEmail();
        List<Booking> bookings = BookingRepository.loadBookingsByCustomerName(currentUser);
        for (Booking b : bookings) {
            tableModel.addRow(new Object[]{
                    b.getBookingId(),
                    b.getCustomerName(),
                    b.getContactInfo(),
                    b.getVehicleId(),
                    b.getSeatsBooked(),
                    b.getStatus(),
                    b.getPaymentStatus(),
                    "Cancel"
            });
        }
    }

    private void handleCancel(Booking booking) {
        if ("paid".equalsIgnoreCase(booking.getPaymentStatus())) {
            JOptionPane.showMessageDialog(this, "Refund will be provided for this paid booking.");
        } else {
            JOptionPane.showMessageDialog(this, "Booking cancelled successfully.");
        }

        boolean success = BookingRepository.cancelBooking(booking);
        if (success) {
            
    SwingUtilities.invokeLater(() -> loadBookings());


        } else {
            JOptionPane.showMessageDialog(this, "Failed to cancel booking.");
        }
    }

    // Inner class for rendering buttons in JTable
    private class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            setText((value == null) ? "Cancel" : value.toString());
            return this;
        }
    }

    // Inner class for handling button actions in JTable
    private class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean clicked;
        private int row;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> fireEditingStopped());
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            this.row = row;
            label = (value == null) ? "Cancel" : value.toString();
            button.setText(label);
            clicked = true;
            return button;
        }

        public Object getCellEditorValue() {
            if (clicked) {
                int bookingId = (int) tableModel.getValueAt(row, 0);
                Booking booking = new Booking();
                booking.setBookingId(bookingId);
                booking.setCustomerName((String) tableModel.getValueAt(row, 1));
                booking.setContactInfo((String) tableModel.getValueAt(row, 2));
                booking.setVehicleId((int) tableModel.getValueAt(row, 3));
                booking.setSeatsBooked((int) tableModel.getValueAt(row, 4));
                booking.setStatus((String) tableModel.getValueAt(row, 5));
                booking.setPaymentStatus((String) tableModel.getValueAt(row, 6));
                handleCancel(booking);
            }
            clicked = false;
            return label;
        }
    }
}
