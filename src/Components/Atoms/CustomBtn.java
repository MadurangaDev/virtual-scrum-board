package Components.Atoms;

import Assets.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static Components.Atoms.ThemedText.BoldText;

public class CustomBtn {
    private static JLabel label;
    public static JPanel createFlatButton(String text, Color backgroundColor, Color textColor, int fontSize) {
        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setBackground(backgroundColor);

        label = BoldText(text, fontSize);
        label.setForeground(textColor);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        buttonPanel.add(label, BorderLayout.CENTER);

        buttonPanel.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
                // Handle button click event
//                buttonPanel.setBackground(Colors.primaryBlack);
//                label.setForeground(Colors.neutralBlue);
//                buttonPanel.repaint();
//                JOptionPane.showMessageDialog(null, "Button Clicked!");
//            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Handle mouse enter event
                buttonPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                buttonPanel.setBackground(Colors.secondaryBlue);
                label.setForeground(textColor);
                buttonPanel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Handle mouse exit event
                buttonPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                buttonPanel.setBackground(backgroundColor);
                label.setForeground(textColor);
                buttonPanel.repaint();
            }
        });

        return buttonPanel;
    }
    public static void setText(String text) {
        if (label != null) {
            label.setText(text);
        }
    }
}
