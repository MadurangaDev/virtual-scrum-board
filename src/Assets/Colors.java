package Assets;
import java.awt.Color;

public class Colors {
    public static Color darkBlack = hexResolver("#081530ff");
    public static Color primaryBlack = hexResolver("#192428ff");
    public static Color secondaryBlack = hexResolver("#2D383Cff");
    public static Color neutralBlack = hexResolver("#414C50ff");
    public static Color primaryBlue = hexResolver("#0784B5ff");
    public static Color secondaryBlue = hexResolver("#39ACE7ff");
    public static Color neutralBlue = hexResolver("#D9F3F4ff");
    public static Color warning = hexResolver("#FF4444ff");
    public static Color success = hexResolver("#00C851ff");
    
    public static Color transparent = new Color(0, 0, 0, 0);
    public static Color primaryBlackImageOverlay = hexResolver("#19242866");
    public static Color neutralBlue_0_5 = hexResolver("#D9F3F480");
    public static Color neutralBlue_0_65 = hexResolver("#D9F3F4A6");
    public static Color neutralBlue_0_2 = hexResolver("#D9F3F433");
    public static Color warning_0_6 = hexResolver("#FF444499");
    public static Color primaryBlack_0_6 = hexResolver("#19242899");
    public static Color primaryBlack_0_68 = hexResolver("#192428AB");
    public static Color neutralBlue_0_75 = hexResolver("#D9F3F4BF");
    public static Color primaryBlue_0_5 = hexResolver("#0784B580");

    private static Color hexResolver(String hexCode) {
        Color generatedColor;
        try{
            hexCode = hexCode.replace("#", "");

            int r = Integer.parseInt(hexCode.substring(0, 2), 16);
            int g = Integer.parseInt(hexCode.substring(2, 4), 16);
            int b = Integer.parseInt(hexCode.substring(4, 6), 16);
            int a = Integer.parseInt(hexCode.substring(6, 8), 16);
            generatedColor = new Color(r, g, b, a);
        }
        catch(Exception e){
            generatedColor = new Color(0, 0, 0, 255);
        }
        return generatedColor;
    }
}
