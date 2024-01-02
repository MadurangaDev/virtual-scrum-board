package Assets.Fonts;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JLabel;

public class Icons {
    public static Font loadFontAwesome(float size) {
        try (InputStream is = Icons.class.getResourceAsStream("/Assets/Fonts/FontAwesome6FreeSolid.otf")) {
            if (is != null) {
                return Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.PLAIN, size);
            } else {
                throw new IOException("Font file not found");
            }
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
}
