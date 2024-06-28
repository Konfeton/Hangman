import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<String> words = new ArrayList<>();
    private static final Random random = new Random();

    public static final String FILE_PATH = "words.txt";

    public static final String COMMAND_PLAY = "1";
    public static final String COMMAND_EXIT = "2";

    public static final String GAME_STATE_WIN = "YOU WIN";
    public static final String GAME_STATE_LOOSE = "YOU LOOSE";

    public static final int MAX_NUMBER_OF_MISTAKES = 6;
    public static final int NUMBER_OF_PICTURES = MAX_NUMBER_OF_MISTAKES + 1;

    public static void main(String[] args) {
        startGame();
    }

    private static void startGame() {
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

    private static void startGameRound() {
        String word = getRandomWord();
        Set<Character> guesses = new LinkedHashSet<>();
        int numberOfMistakes = 0;

        showGallows(numberOfMistakes);
        showMask(word, guesses);

        do {
            char letter = inputLetter();

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

            showGallows(numberOfMistakes);

            if (isWin(word, guesses)) {
                System.out.println(GAME_STATE_WIN);
                System.out.println("Загаданое слово: " + word);
                break;
            }

            if (isLoose(numberOfMistakes)) {
                System.out.println(GAME_STATE_LOOSE);
                System.out.println("Загаданое слово: " + word);
                break;
            }

            showMask(word, guesses);
            System.out.println("Введённые буквы: " + guesses);

        } while (true);

    }

    private static String getRandomWord() {
        if (words.isEmpty()) {
            try {
                URL url = Main.class.getResource(FILE_PATH);
                words = Files.readAllLines(Path.of(url.toURI()));
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
        int randomWordIndex = random.nextInt(words.size());
        return words.get(randomWordIndex);
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

    private static char inputLetter() {
        System.out.println("Введите букву русского алфавита");
        while (true) {
            String line = scanner.nextLine();
            if (isSymbolCorrect(line)) {
                char letter = line.toLowerCase().charAt(0);
                return letter;
            } else {
                System.out.println("Введено неверное значение! Попробуйте ещё раз");
            }
        }
    }

    private static boolean isSymbolCorrect(String text) {
        return (isOneCharacter(text) && isLetter(text) && isCyrillic(text));
    }

    private static boolean isOneCharacter(String text) {
        return text.length() == 1;
    }

    private static boolean isLetter(String text) {
        return Character.isLetter(text.charAt(0));
    }

    private static boolean isCyrillic(String text) {
        return text.matches("([а-яА-я])");
    }


    private static boolean isLetterWasAlreadyGuessed(Set<Character> guesses, char letter) {
        return guesses.contains(letter);
    }

    private static boolean isLetterInWord(String word, char letter) {
        return word.indexOf(letter) != -1;
    }

    private static void showGallows(int numberOfMistakes) {
        String[] pictures = new String[NUMBER_OF_PICTURES];
        pictures[0] = "|----------\n" +
                      "|          \n" +
                      "|          \n" +
                      "|          \n" +
                      "|            ";
        pictures[1] = "|----------\n" +
                      "|       |  \n" +
                      "|       0  \n" +
                      "|          \n" +
                      "|            ";
        pictures[2] = "|----------\n" +
                      "|       |  \n" +
                      "|       0  \n" +
                      "|      /   \n" +
                      "|            ";
        pictures[3] = "|----------\n" +
                      "|       |  \n" +
                      "|       0  \n" +
                      "|      /|  \n" +
                      "|            ";
        pictures[4] = "|----------\n" +
                      "|       |  \n" +
                      "|       0  \n" +
                      "|      /|\\\n" +
                      "|            ";
        pictures[5] = "|----------\n" +
                      "|       |  \n" +
                      "|       0  \n" +
                      "|      /|\\\n" +
                      "|      /     ";
        pictures[6] = "|----------\n" +
                      "|       |  \n" +
                      "|       0  \n" +
                      "|      /|\\\n" +
                      "|      / \\  ";

        System.out.println(pictures[numberOfMistakes]);
    }

    private static boolean isWin(String word, Set<Character> guesses) {
        int numberOfCorrectLetters = 0;
        for (int i = 0; i < word.length(); i++) {
            if (guesses.contains(word.charAt(i))) {
                numberOfCorrectLetters++;
            }
        }
        return numberOfCorrectLetters == word.length();
    }

    private static boolean isLoose(int numberOfMistakes) {
        return numberOfMistakes == MAX_NUMBER_OF_MISTAKES;
    }
}
