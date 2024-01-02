package Assets.Fonts;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class ThemedFonts {
    public static Font loadRegularFont(float size) {
        try (InputStream is = Icons.class.getResourceAsStream("/Assets/Fonts/IstokWeb-Regular.ttf")) {
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
    public static Font loadBoldFont(float size) {
        try (InputStream is = Icons.class.getResourceAsStream("/Assets/Fonts/IstokWeb-Bold.ttf")) {
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
