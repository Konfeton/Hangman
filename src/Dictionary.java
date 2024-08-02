package src;

import java.util.List;
import java.util.Random;

public class Dictionary {
    private List<String> words;
    private FileReader reader = new FileReader();

    public Dictionary(String fileName) {
        loadWords(fileName);
    }
    
    private void loadWords(String fileName) {
        List<String> wordsFromFile = reader.read(fileName);
        words = wordsFromFile;
    }

    public String getRandomWord(){
        return words.get(new Random().nextInt(words.size()));
    }
}
