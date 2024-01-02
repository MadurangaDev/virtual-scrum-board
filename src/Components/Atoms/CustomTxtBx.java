package Components.Atoms;

import Assets.Colors;
import Enums.InputStatus;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import static Components.Atoms.FAIcon.FAicon;
import java.awt.event.*;
import static Util.Validations.MaxLength.*;

public class CustomTxtBx extends JPanel {
    private JPanel userIDInputHolder;
    private String icon;
    private int width;
    private int height;
    private InputStatus status;
    private boolean isPassword;
    private int maxLength;
    private boolean disabled;
    private Color accentColor;
    public JPasswordField passInput;
    public JTextField textInput;
    private JLabel userIDIcon;

    private JPanel inputDisabler = new JPanel();

    public CustomTxtBx(String icon, int width, int height, InputStatus status, boolean isPassword, int maxLength, boolean disabled) {
        this.icon = icon;
        this.width = width;
        this.height = height;
        this.status = status;
        this.isPassword = isPassword;
        this.maxLength = maxLength;
        this.disabled = disabled;
        this.accentColor = status==InputStatus.WARNING?Colors.warning:(status==InputStatus.SUCCESS?Colors.success:Colors.neutralBlue);
        renderElement();
    }
    private void renderElement(){
        inputDisabler.setPreferredSize(new Dimension(width, height));
        inputDisabler.setBackground(Colors.primaryBlack_0_6);
        inputDisabler.setBounds(0, 0, width, height);
        final int borderWidth = 1;
        final int iconHeight = Math.ceilDiv((height - (2*borderWidth)), 2);
        final int iconMargin = Math.floorDiv((height - iconHeight), 2);


        userIDInputHolder = new JPanel();
        userIDInputHolder.setLayout(null);
        userIDInputHolder.setBackground(accentColor);
        userIDInputHolder.setPreferredSize(new Dimension(width, height));

        if(disabled) {
            userIDInputHolder.add(inputDisabler);
        }
        else{
            userIDInputHolder.remove(inputDisabler);
        }

        userIDIcon = FAicon(icon, iconHeight, (accentColor));
        userIDIcon.setBounds(iconMargin, iconMargin, iconHeight+2, iconHeight);

        userIDInputHolder.add(userIDIcon);


        if(!isPassword){
            textInput = new JTextField(){
                @Override
                protected void paintComponent(Graphics g) {
                    if (!isEnabled()) {
                        // Customize the background color for the disabled state
                        g.setColor(Colors.secondaryBlack); // Replace with your desired color
                        g.fillRect(2*borderWidth, 2*borderWidth, (width-(6*borderWidth)), (height-(6*borderWidth)));
                    } else {
                        // Use the default painting for the enabled state
                        super.paintComponent(g);
                    }
                }};
            textInput.setEnabled(!disabled);
            textInput.setBounds(borderWidth, borderWidth, (width-(2*borderWidth)), (height-(2*borderWidth)));
            textInput.setBackground(Colors.secondaryBlack);
            textInput.setBorder(new EmptyBorder(0, (iconHeight+(2*iconMargin)-borderWidth), 0, iconMargin));
            textInput.setForeground(accentColor);
            setMaxLength(textInput, maxLength);
            userIDInputHolder.add(textInput);
        }
        else{
            passInput = new JPasswordField(){
                @Override
                protected void paintComponent(Graphics g) {
                    if (!isEnabled()) {
                        // Customize the background color for the disabled state
                        g.setColor(getBackground()); // Replace with your desired color
                        g.fillRect(2*borderWidth, 2*borderWidth, (width-(6*borderWidth)), (height-(6*borderWidth)));
                    } else {
                        // Use the default painting for the enabled state
                        super.paintComponent(g);
                    }
                }};
            passInput.setEnabled(!disabled);
            passInput.setBounds(borderWidth, borderWidth, (width-(2*borderWidth)), (height-(2*borderWidth)));
            passInput.setBackground(Colors.secondaryBlack);
            passInput.setBorder(new EmptyBorder(0, (iconHeight+(2*iconMargin)-borderWidth), 0, iconMargin));
            passInput.setForeground(accentColor);
            setMaxLength(passInput, maxLength);
            userIDInputHolder.add(passInput);
        }

    }
    public JPanel getElement(){
        return userIDInputHolder;
    }
    public String getText(){
        if(!isPassword){
            return textInput.getText().trim();
        }
        else{
            return new String(passInput.getPassword()).trim();
        }
    }
    public void setStatus(InputStatus status){
        this.status = status;
        this.accentColor = status==InputStatus.WARNING?Colors.warning:(status==InputStatus.SUCCESS?Colors.success:Colors.neutralBlue);
        userIDInputHolder.setBackground(accentColor);
        userIDIcon.setForeground(accentColor);
        if(!isPassword){
            textInput.setForeground(accentColor);
        }
        else{
            passInput.setForeground(accentColor);
        }
    }
    public void setDisabled(boolean disabled){
        this.disabled = disabled;
        if(disabled) {
            userIDInputHolder.add(inputDisabler);
        }
        else{
            userIDInputHolder.remove(inputDisabler);
        }
    }
    public void setText(String text){
        if(!isPassword){
            textInput.setText(text);
        }
        else{
            passInput.setText(text);
        }
    }
}
