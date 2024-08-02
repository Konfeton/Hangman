package src;

public class Hangman {
    public static final String[] hangmanPictures = {
                    """
                        |-------
                        |
                        |
                        |
                        |
                        |
                    """,
                    """
                        |-------
                        |    |
                        |    0
                        |
                        |
                        |
                    """,
                    """
                        |-------
                        |    |
                        |    0
                        |   /
                        |
                    """,
                    """
                        |-------
                        |    |
                        |    0
                        |   /|
                        |
                        |
                    """,
                    """
                        |-------
                        |    |
                        |    0
                        |   /|\\
                        |
                        |
                    """,
                    """
                        |-------
                        |    |
                        |    0
                        |   /|\\
                        |   /
                        |
                    """,
                    """
                        |-------
                        |    |
                        |    0
                        |   /|\\
                        |   / \\
                        |
                    """,

    };

    public static String getPicture(int numberOfMistakes) {
        return hangmanPictures[numberOfMistakes];
    }
}
