import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<String> words = new ArrayList<>();
    private static final Random random = new Random();

    public static final String FILE_PATH = "words.txt";

    public static final String GAME_STATE_WIN = "YOU WIN";
    public static final String GAME_STATE_LOOSE = "YOU LOOSE";
    public static final String GAME_STATE_CONTINUE = "";

    public static final int MAX_NUMBER_OF_MISTAKES = 5;

    public static void main(String[] args) {
        showAndHandleMenu();
    }

    private static void showAndHandleMenu() {
        while (true) {
            System.out.println("Выберите из списка:");
            System.out.println("1) Начать новую игру");
            System.out.println("2) Выйти");
            System.out.print("Выбор: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    startGame();
                    break;
                case "2":
                    return;
                default:
                    System.out.println("Ошибка! Введено неверное значение");
            }
        }
    }

    private static void startGame() {
        String word = getRandomWord();
        Set<Character> guesses = new LinkedHashSet<>();

        showMask(word, guesses);

        int numberOfMistakes = 0;

        do {
            char letter = inputLetterAndValidate();

            if (isLetterWasAlreadyGuessed(guesses, letter)) {
                System.out.println("Вы уже вводили такую букву");
                continue;
            } else {
                guesses.add(letter);
            }

            if (!isLetterInWord(word, letter)) {
                System.out.println("Упс, такой буквы в слове нет\n");
                numberOfMistakes++;
            }

            String gameState = checkGameState(numberOfMistakes, word, guesses);


            if (gameState.equals(GAME_STATE_WIN)) {
                System.out.println(gameState);
                System.out.println("Загаданое слово: " + word);
                break;
            }

            if (gameState.equals(GAME_STATE_LOOSE)) {
                showGallows(numberOfMistakes);
                System.out.println(gameState);
                System.out.println("Загаданое слово: " + word);
                break;
            }

            if (numberOfMistakes > 0) {
                showGallows(numberOfMistakes);
            }

            showMask(word, guesses);
            System.out.println("Введённые буквы: " + guesses);

        } while (true);

    }

    private static String getRandomWord() {
        if (words.isEmpty()) {
            try {
                words = Files.readAllLines(Path.of(FILE_PATH));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return words.get(random.nextInt(words.size()));
    }

    private static void showMask(String word, Set<Character> guesses) {
        for (int i = 0; i < word.length(); i++) {
            if (isLetterWasAlreadyGuessed(guesses, word.charAt(i))) {
                System.out.print(word.charAt(i));
            } else {
                System.out.print("*");
            }
        }
        System.out.println();
    }

    private static char inputLetterAndValidate() {
        System.out.println("Введите букву русского алфавита");
        while (true) {
            String symbol = scanner.nextLine();
            if (isSymbolCorrect(symbol)) {
                return symbol.toLowerCase().charAt(0);
            } else {
                System.out.println("Введено неверное значение! Попробуйте ещё раз");
            }
        }
    }

    private static boolean isSymbolCorrect(String symbol) {
        return (isNotOneCharacter(symbol) && isLetter(symbol) && isCyrillic(symbol));
    }

    private static boolean isNotOneCharacter(String symbol) {
        return symbol.length() == 1;
    }

    private static boolean isLetter(String symbol) {
        return Character.isLetter(symbol.charAt(0));
    }

    private static boolean isCyrillic(String symbol) {
        return symbol.matches("([а-яА-я])");
    }


    private static boolean isLetterWasAlreadyGuessed(Set<Character> guesses, char letter) {
        return guesses.contains(letter);
    }

    private static boolean isLetterInWord(String word, char letter) {
        return word.indexOf(letter) != -1;
    }

    private static String checkGameState(int numberOfMistakes, String word, Set<Character> guesses) {
        if (numberOfMistakes == MAX_NUMBER_OF_MISTAKES) {
            return GAME_STATE_LOOSE;
        } else {
            int numberOfCorrectLetters = 0;
            for (int i = 0; i < word.length(); i++) {
                if (guesses.contains(word.charAt(i))) {
                    numberOfCorrectLetters++;
                }
            }
            if (numberOfCorrectLetters == word.length()) {
                return GAME_STATE_WIN;
            }
        }
        return GAME_STATE_CONTINUE;
    }

    private static void showGallows(int numberOfMistakes) {
        System.out.println(numberOfMistakes > 0 ? "|----------" : "|----------");
        System.out.println(numberOfMistakes > 1 ? "|       |  " : "|");
        System.out.println(numberOfMistakes > 2 ? "|       0  " : "|");
        System.out.println(numberOfMistakes > 3 ? "|      /|\\" : "|");
        System.out.println(numberOfMistakes > 4 ? "|      / \\" : "|");
        System.out.println();
    }
}
