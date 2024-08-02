package src;

import java.util.Arrays;

public class MaskedWord {
    private final String word;
    private final char[] maskedWord;
    private int lettersOpened;

    public MaskedWord(String word) {
        this.word = word;
        maskedWord = new char[word.length()];
        Arrays.fill(maskedWord, '*');
    }

    public boolean openLetter(char letter){
        boolean isOpen = false;
        for (int i = 0; i < maskedWord.length; i++) {
            if (word.charAt(i) == letter) {
                isOpen = true;
                maskedWord[i] = letter;
                lettersOpened++;
            }
        }
        return isOpen;
    }

    public boolean isAllLettersGuessed(){
        return lettersOpened == maskedWord.length;
    }

    public String  getMask() {
        return String.valueOf(maskedWord);
    }

    public String getWord() {
        return word;
    }
}
