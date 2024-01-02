package Components.Atoms;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageBackgroundPanel extends JPanel {
    private BufferedImage image;

    public ImageBackgroundPanel(BufferedImage image) {
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
