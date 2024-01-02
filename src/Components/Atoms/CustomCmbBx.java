package Components.Atoms;

import Assets.Colors;
import Enums.InputStatus;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Components.Atoms.CustomComboItem;

import static Components.Atoms.FAIcon.FAicon;

public class CustomCmbBx {
    private static JLabel userIDIcon;
    private static JLabel userArrowIcon;
    private static InputStatus status;
    private static JPanel userIDInputHolder;
    private static JComboBox<CustomComboItem> userIDInput;

    public static JPanel customCmbBx(String inputID, String icon, int width, int height, InputStatus status1, String[] options, int[] optionValues) {
        status = status1;
        final int borderWidth = 1;
        final int iconHeight = Math.ceilDiv((height - (2 * borderWidth)), 2);
        final int iconMargin = Math.floorDiv((height - iconHeight), 2);

        userIDInputHolder = new JPanel();
        userIDInputHolder.setLayout(null);
        userIDInputHolder.setName(inputID + "Border");

        userIDIcon = FAicon(icon, iconHeight, getColorBasedOnStatus(status));
        userIDIcon.setBounds(iconMargin, iconMargin, iconHeight + 2, iconHeight);
        userIDIcon.setName(inputID + "Icon");

        userArrowIcon = FAicon("\uf0d7", iconHeight, getColorBasedOnStatus(status));
        userArrowIcon.setBounds((width - iconMargin - iconHeight + 2), iconMargin + Math.floorDiv(iconMargin, 2), iconHeight + 2, Math.ceilDiv(iconHeight, 2));
        userArrowIcon.setName(inputID + "Arrow");

        userIDInputHolder.add(userIDIcon);
        userIDInputHolder.add(userArrowIcon);

        userIDInput = new JComboBox<>();
        for (int i = 0; i < options.length; i++) {
            CustomComboItem item = new CustomComboItem(optionValues[i], options[i]);
            userIDInput.addItem(item);
        }

        userIDInput.setName(inputID);
        userIDInput.setBounds(borderWidth, borderWidth, (width - (2 * borderWidth)), (height - (2 * borderWidth)));
        userIDInput.setBorder(new EmptyBorder(0, (iconHeight + (2 * iconMargin) - borderWidth), 0, (iconHeight + 2 + (2 * iconMargin))));
        configureComboBox(userIDInput);

        userIDInput.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateComponentOrder();
            }
        });

        userIDInput.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                updateComponentOrder();
            }
        });

        userIDInputHolder.add(userIDInput);
        userIDInputHolder.setBackground(getColorBasedOnStatus(status));
        userIDInputHolder.setPreferredSize(new Dimension(width, height));

        return userIDInputHolder;
    }

    private static void configureComboBox(JComboBox<CustomComboItem> comboBox) {
        comboBox.setRenderer(new ComboBoxListCellRenderer());
        comboBox.setUI(new BasicComboBoxUI() {
            @Override
            public void paint(final Graphics g, final JComponent c) {
                final Rectangle r = this.rectangleForCurrentValue();
                this.paintCurrentValueBackground(g, r, true);
                this.paintCurrentValue(g, r, true);
            }

            @Override
            public void paintCurrentValueBackground(final Graphics g, final Rectangle bounds, final boolean hasFocus) {
                final Color t = g.getColor();
                g.setColor(comboBox.isEnabled() ? g.getColor() : Color.GRAY);
                g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
                g.setColor(t);
            }
        });
        comboBox.setBackground(Colors.secondaryBlack);
        comboBox.setForeground(getColorBasedOnStatus(status));
    }

    private static void updateComponentOrder() {
        userIDInputHolder.setBackground(getColorBasedOnStatus(status));
        userIDInputHolder.repaint();
        userIDInputHolder.setComponentZOrder(userIDIcon, 0);
        userIDInputHolder.setComponentZOrder(userArrowIcon, 1);
        userIDInputHolder.setComponentZOrder(userIDInput, 2);
    }

    private static Color getColorBasedOnStatus(InputStatus status) {
        if (status == InputStatus.WARNING) {
            return Colors.warning;
        } else if (status == InputStatus.SUCCESS) {
            return Colors.success;
        } else {
            return Colors.neutralBlue;
        }
    }

    public static class ComboBoxListCellRenderer extends DefaultListCellRenderer {
        private static final long serialVersionUID = 1L;

        @Override
        public Component getListCellRendererComponent(final JList list, final Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
            if (value instanceof CustomComboItem) {
                CustomComboItem customItem = (CustomComboItem) value;
                this.setToolTipText(customItem.getText());
                this.setBackground(isSelected ? Colors.primaryBlue : Colors.primaryBlack);
                this.setForeground(Colors.neutralBlue);
                this.setText(customItem.getText());
                this.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 8));
            }
            return this;
        }
    }

    public JComboBox<CustomComboItem> getComponent() {
        return userIDInput;
    }
    public static CustomComboItem getSelectedValue(JComboBox<CustomComboItem> comboBox) {
        return (CustomComboItem) comboBox.getSelectedItem();
    }
}
