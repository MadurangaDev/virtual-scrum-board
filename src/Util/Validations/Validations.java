package Util.Validations;

public class Validations {
    public static boolean emptyCheck(String text){
        String newText = text.trim();
        return newText.isEmpty();
    }
    public static boolean equalCheck(String text1, String text2){
        String newText1 = text1.trim();
        String newText2 = text2.trim();
        return newText1.equals(newText2);
    }
}
