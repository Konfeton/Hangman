package src;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Game {

    private final MaskedWord maskedWord;
    private final Scanner scanner = new Scanner(System.in);

    private static final int MAX_NUMBER_OF_MISTAKES = 6;
    private Set<Character> guesses;
    private Set<Character> mistakes;
    private GameState gameState;

    public Game(String word){
        maskedWord = new MaskedWord(word);
        guesses = new LinkedHashSet<>();
        mistakes = new HashSet<>();
    }

    public void startGameRound() {

        do {
            System.out.println(Hangman.getPicture(mistakes.size()));
            System.out.println("Введённые буквы: " + guesses);
            System.out.println(maskedWord.getMask());
            char letter = getLetterFromUser();
            if (isLetterWasEntered(letter)) {
                System.out.println("Вы уже вводили эту букву!");
                continue;
            }
            if (!isLetterInWord(letter)){
                mistakes.add(letter);
            }
            gameState = getGameState();
        } while (gameState == GameState.CONTINUE);

        System.out.println(gameState);
    }

    private char getLetterFromUser() {
        String userInput;
        do {
            System.out.println("Введите одну букву русского алфавита");
            userInput = scanner.nextLine().toLowerCase();
            if (Validator.isValid(userInput)){
                break;
            }
            System.out.println("Неправильный ввод!");
        } while (true);
        return userInput.charAt(0);
    }

    private boolean isLetterWasEntered(char letter) {
        return !guesses.add(letter);
    }

    private boolean isLetterInWord(char letter) {
        return maskedWord.openLetter(letter);
    }

    private GameState getGameState(){
        if (isVictory()){
            return GameState.VICTORY;
        } else if (isDefeat()) {
            return GameState.DEFEAT;
        }else {
            return GameState.CONTINUE;
        }
    }

    private boolean isDefeat() {
        return mistakes.size() == MAX_NUMBER_OF_MISTAKES;
    }

    private boolean isVictory() {
        return maskedWord.isAllLettersGuessed();
    }

}
