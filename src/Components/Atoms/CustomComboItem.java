package Components.Atoms;

public class CustomComboItem {
    private int value;
    private String text;

    public CustomComboItem(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }
}