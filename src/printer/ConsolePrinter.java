package src.printer;

import src.HangmanState;

import java.util.Set;

public class ConsolePrinter implements Printer {
    @Override
    public void printHangman(int numberOfMistakes) {
        String hangmanPicture = HangmanState.values()[numberOfMistakes].getHangmanPicture();
        System.out.println(hangmanPicture);
    }

    @Override
    public void printWordMask(String word, Set<Character> guesses) {
        for (int position = 0; position < word.length(); position++) {
            if (guesses.contains(word.charAt(position))) {
                System.out.print(word.charAt(position));
            }else{
                System.out.print("*");
            }
        }
        System.out.println();
    }

    @Override
    public void printUsedLetters(Set<Character> guesses) {
        System.out.println("Введённые буквы: " + guesses);
    }
}
