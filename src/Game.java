package src;

import src.printer.ConsolePrinter;
import src.printer.Printer;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Game {
    private final Scanner scanner;
    private Dictionary dictionary;
    private Printer printer;
    private final InputHandler inputHandler;

    private static final String COMMAND_PLAY = "1";
    private static final String COMMAND_EXIT = "2";

    public static final int MAX_NUMBER_OF_MISTAKES = 6;

    public Game() {
        scanner = new Scanner(System.in);
        dictionary = new Dictionary();
        printer = new ConsolePrinter();
        inputHandler = new InputHandler();
    }

    public void startGame() {
        while (true) {
            System.out.println("Выберите из списка:");
            System.out.println(COMMAND_PLAY + ") Начать новую игру");
            System.out.println(COMMAND_EXIT + ") Выйти");
            System.out.print("Выбор: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case COMMAND_PLAY:
                    startGameRound();
                    break;
                case COMMAND_EXIT:
                    return;
                default:
                    System.out.println("Ошибка! Введено неверное значение");
            }
        }
    }

    private void startGameRound() {
        String word = dictionary.getRandomWord();
        Set<Character> wordCharacters = new HashSet<>();
        for (char c : word.toCharArray()) {
            wordCharacters.add(c);
        }

        int mistakesCounter = 0;

        printer.printHangman(mistakesCounter);

        Set<Character> guesses = new LinkedHashSet<>();
        printer.printWordMask(word, guesses);
        GameState gameState = null;

        do {
            char letter = inputHandler.inputLetter();

            if (isLetterWasUsed(guesses, letter)) {
                System.out.println("Вы уже вводили эту букву");
                continue;
            }
            if (!isWordContainLetter(word, letter)){
                mistakesCounter++;
            }

            printer.printHangman(mistakesCounter);
            printer.printWordMask(word, guesses);
            printer.printUsedLetters(guesses);

            gameState = getGameState(mistakesCounter, wordCharacters, guesses);

        } while (gameState == GameState.CONTINUE);

        System.out.println(gameState.getMessage());
        System.out.println("Загаданное слово: " + word);
    }

    private static boolean isLetterWasUsed(Set<Character> guesses, char letter) {
        return !guesses.add(letter);
    }

    private static boolean isWordContainLetter(String word, char letter) {
        return word.contains(String.valueOf(letter));
    }

    private GameState getGameState(int numberOfMistakes, Set<Character> wordCharacters, Set<Character> guesses) {
        if (numberOfMistakes == MAX_NUMBER_OF_MISTAKES) {
            return GameState.LOST;
        }else if (guesses.containsAll(wordCharacters)) {
            return GameState.WIN;
        }else {
            return GameState.CONTINUE;
        }
    }
}
