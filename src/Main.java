package src;

import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "words.txt";
    private static final Scanner scanner = new Scanner(System.in);

    private static final String COMMAND_GAME_START = "1";
    private static final String COMMAND_EXIT = "2";

    private static Dictionary dictionary = null;

    public static void main(String[] args) {

        do {
            System.out.println("Меню");
            System.out.printf("%s) Новая игра\n", COMMAND_GAME_START);
            System.out.printf("%s) Выход\n", COMMAND_EXIT);
            System.out.print("Выбор: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case COMMAND_GAME_START:{
                    if (dictionary == null){
                        dictionary = new Dictionary(FILE_NAME);
                    }
                    Game game = new Game(dictionary.getRandomWord());
                    game.startGameRound();
                    break;
                }

                case COMMAND_EXIT:{
                    return;
                }

                default:{
                    System.out.println("Неправильный ввод, попробуйте ещё раз!");
                }
            }
        } while (true);
    }
}
