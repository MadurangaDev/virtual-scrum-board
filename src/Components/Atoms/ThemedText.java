package Components.Atoms;

import Assets.Colors;

import static Assets.Fonts.ThemedFonts.*;
import javax.swing.JLabel;
import java.awt.*;

public class ThemedText {
    public static JLabel BoldText(String text, float size){
        JLabel newText = new JLabel(text);
        newText.setFont(loadBoldFont(size));
        newText.setForeground(Colors.neutralBlue);
        return newText;
    }
    public static JLabel RegularText(String text, float size){
        JLabel newText = new JLabel(text);
        newText.setFont(loadRegularFont(size));
        newText.setForeground(Colors.neutralBlue);
        return newText;
    }
    public static JLabel BoldText(String text, float size, Color color){
        JLabel newText = new JLabel(text);
        newText.setFont(loadBoldFont(size));
        newText.setForeground(color);
        return newText;
    }
    public static JLabel RegularText(String text, float size, Color color){
        JLabel newText = new JLabel(text);
        newText.setFont(loadRegularFont(size));
        newText.setForeground(color);
        return newText;
    }
}
