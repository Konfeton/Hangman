package src.reader;

import src.Main;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileReader implements Reader {

    @Override
    public List<String> read(String path) {
        URL url = getClass().getResource(path);
        List<String> text;
        try {
            text = Files.readAllLines(Path.of(url.toURI()));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return text;
    }
}
