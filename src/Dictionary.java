package src;

import src.reader.FileReader;
import src.reader.Reader;

import java.util.List;
import java.util.Random;

public class Dictionary {

    private List<String> words;
    private Reader reader;

    private String filePath = "../words.txt";

    public Dictionary() {
        reader = new FileReader();
        setWords();
    }

    public String getRandomWord(){
        Random random = new Random();
        int randomWordIndex = random.nextInt(words.size());
        return words.get(randomWordIndex);
    }

    private void setWords(){
        words = reader.read(filePath);
    }

}
