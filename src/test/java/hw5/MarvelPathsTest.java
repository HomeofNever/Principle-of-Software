package hw5;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class MarvelPathsTest {

    MarvelPaths p = new MarvelPaths();

    @Before
    public void spinUp() {
        String myOwnCSV = "data/own.csv";
        p.createNewGraph(myOwnCSV);
    }

    private String readFileContent(String filename) {

        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (Exception e) {
            System.out.println("An error occurred when reading file " + filename);
            e.printStackTrace();
        }

        return contentBuilder.toString();
    }

    @Test
    public void findPath() {
        assertEquals(readFileContent("data/result/char1_char2.txt"), p.findPath("Char1", "Char2"));
        assertEquals(readFileContent("data/result/char2_char2.txt"), p.findPath("Char2", "Char2"));
        // Not Exist node/path
        assertEquals(readFileContent("data/result/char_not_exist.txt"), p.findPath("CharNotExist", "CharNotExist1"));
        assertEquals(readFileContent("data/result/char1_char_not_exist.txt"), p.findPath("Char1", "CharNotExist"));
        assertEquals(readFileContent("data/result/char4_no_path.txt"), p.findPath("Char4", "Char1"));
        // Shortest Path
        assertEquals(readFileContent("data/result/char1_char7.txt"), p.findPath("Char1", "Char7"));
        assertEquals(readFileContent("data/result/char7_char1.txt"), p.findPath("Char7", "Char1"));
    }
}
