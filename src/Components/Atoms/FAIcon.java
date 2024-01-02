package Components.Atoms;

import javax.swing.*;
import static Assets.Fonts.Icons.loadFontAwesome;
import java.awt.Color;

public class FAIcon {
    public static JLabel FAicon(String iconCode, float size){
        JLabel newIcon = new JLabel(iconCode);
        newIcon.setFont(loadFontAwesome(size));
        return newIcon;
    }
    public static JLabel FAicon(String iconCode, float size, Color color){
        JLabel newIcon = new JLabel(iconCode);
        newIcon.setFont(loadFontAwesome(size));
        newIcon.setForeground(color);
        return newIcon;
    }
}
