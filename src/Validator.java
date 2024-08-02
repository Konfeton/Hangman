package src;

public class Validator {
    public static boolean isValid(String userInput) {
        return isOneCharacter(userInput) && isCyrillic(userInput);
    }

    private static boolean isCyrillic(String userInput) {
        return userInput.matches("[а-яА-Я]");
    }

    private static boolean isOneCharacter(String userInput) {
        return userInput.length() == 1;
    }
}
