package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Stream;

public class FileReader {

    public List<String> read(String fileName) {
        List<String> listOfLines;
        try (InputStream inputStream = Dictionary.class.getResourceAsStream(fileName)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            Stream<String> lines = bufferedReader.lines();
            listOfLines = lines.toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listOfLines;
    }
}
