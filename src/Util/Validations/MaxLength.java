package Util.Validations;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class MaxLength {
    public static void setMaxLength(JTextField textField, int maxLength) {
        if (textField.getDocument() instanceof AbstractDocument) {
            AbstractDocument doc = (AbstractDocument) textField.getDocument();
            doc.setDocumentFilter(new DocumentFilter() {
                @Override
                public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                        throws BadLocationException {
                    int currentLength = fb.getDocument().getLength();
                    int leftover = maxLength - currentLength + length;
                    if (leftover > 0) {
                        int replaceLength = Math.min(leftover, text.length());
                        super.replace(fb, offset, length, text.substring(0, replaceLength), attrs);
                    }
                }
            });
        }
    }
}
