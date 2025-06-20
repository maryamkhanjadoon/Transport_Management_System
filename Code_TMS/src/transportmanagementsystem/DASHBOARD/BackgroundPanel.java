/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transportmanagementsystem.DASHBOARD;

/**
 *
 * @author WARDA YOUSAF
 */


import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel() {
        // Use getResource to load the image safely
        ImageIcon icon = new ImageIcon(getClass().getResource("/transportmanagementsystem/DASHBOARD/bg.jpg"));
        backgroundImage = icon.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
