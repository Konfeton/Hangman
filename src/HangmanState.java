package src;

public enum HangmanState {
    ZERO_MISTAKE_HANGMAN(
            "|----------\n" +
                          "|          \n" +
                          "|          \n" +
                          "|          \n" +
                          "|            "
    ),
    ONE_MISTAKE_HANGMAN(
            "|----------\n" +
                          "|       |  \n" +
                          "|       0  \n" +
                          "|          \n" +
                          "|            "
    ),
    TWO_MISTAKE_HANGMAN(
            "|----------\n" +
                          "|       |  \n" +
                          "|       0  \n" +
                          "|      /   \n" +
                          "|            "
    ),
    THREE_MISTAKE_HANGMAN(
            "|----------\n" +
                          "|       |  \n" +
                          "|       0  \n" +
                          "|      /|  \n" +
                          "|            "
    ),
    FOUR_MISTAKE_HANGMAN(
            "|----------\n" +
                          "|       |  \n" +
                          "|       0  \n" +
                          "|      /|\\\n" +
                          "|            "
    ),
    FIVE_MISTAKE_HANGMAN(
            "|----------\n" +
                          "|       |  \n" +
                          "|       0  \n" +
                          "|      /|\\\n" +
                          "|      /     "
    ),
    SIX_MISTAKE_HANGMAN(
            "|----------\n" +
                          "|       |  \n" +
                          "|       0  \n" +
                          "|      /|\\\n" +
                          "|      / \\   "
    );

    private final String hangmanPicture;

    HangmanState(String hangmanPicture) {
        this.hangmanPicture = hangmanPicture;
    }

    public String getHangmanPicture() {
        return hangmanPicture;
    }


}
