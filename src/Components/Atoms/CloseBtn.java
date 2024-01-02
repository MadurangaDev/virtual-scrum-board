package Components.Atoms;

import Assets.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CloseBtn extends JButton {

    private static final long serialVersionUID = 1L;
    private Color backgroundColor;
    private Color foregroundColor;
    private boolean entireClose;

    public CloseBtn(boolean entireClose) {
        super("");
        this.entireClose = entireClose;
        this.backgroundColor = Colors.neutralBlue; // Set your desired background color
        this.foregroundColor = Colors.darkBlack; // Set your desired foreground color
        this.setLayout(null);
        JLabel closeIcon = FAIcon.FAicon("\uf00d", 11);
        closeIcon.setBounds(6, 5, 11, 11);
        this.add(closeIcon);
        initialize();
    }

    private void initialize() {
        setOpaque(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(foregroundColor);
        setPreferredSize(new Dimension(20, 20));

        // Add mouse listeners
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setBackgroundColor(Colors.secondaryBlack); // Set your desired pressed color
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackgroundColor(Colors.neutralBlue); // Set your desired background color
            }
        });
        // Add action listener to close the application
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(entireClose) {
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Are you sure you want to exit?",
                            "Confirm Exit",
                            JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        // User clicked "Yes," close the application
                        System.exit(0);
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(backgroundColor);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        super.paintComponent(g2d);
        g2d.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Do not paint the border
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        repaint();
    }

    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
        setForeground(foregroundColor);
    }
}
