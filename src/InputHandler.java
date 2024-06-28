package src;

import java.util.Scanner;

public class InputHandler {

    private static final Scanner scanner = new Scanner(System.in);

    public char inputLetter(){

        do {
            System.out.println("Введите одну букву на русском языке");
            String text = scanner.nextLine();

            if (isInputValid(text)){
                return text.toLowerCase().charAt(0);
            }
        }while(true);
    }

    private boolean isInputValid(String text){
        return (isOneSymbol(text) && isLetter(text) && isCyrillic(text));
    }

    private boolean isOneSymbol(String text) {
        return text.length() == 1;
    }

    private boolean isLetter(String text) {
        return Character.isLetter(text.charAt(0));
    }

    private boolean isCyrillic(String text) {
        return text.matches("([а-яА-я])");
    }
}
