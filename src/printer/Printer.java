package src.printer;

import java.util.Set;

public interface Printer {
    void printHangman(int numberOfMistakes);
    void printWordMask(String word, Set<Character> guesses);
    void printUsedLetters(Set<Character> guesses);
}
