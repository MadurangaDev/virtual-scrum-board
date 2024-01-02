package Components.Atoms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static Components.Atoms.ThemedText.BoldText;
import static Components.Atoms.ThemedText.RegularText;

public class RoundBtn extends JButton {

    private int cornerRadius;

    public RoundBtn(JPanel content, Color backgroundColor, Color hoverBackgroundColor, int cornerRadius, int width, int height) {
        setPreferredSize(new Dimension(width, height));
        this.cornerRadius = cornerRadius;
        setContentAreaFilled(false); // Make the button transparent
        setLayout(new BorderLayout());
        setBackground(backgroundColor);

        add(content, BorderLayout.CENTER);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                setBackground(hoverBackgroundColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                setBackground(backgroundColor);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the rounded rectangle as the background
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        super.paintComponent(g);

        g2.dispose();
    }
}
