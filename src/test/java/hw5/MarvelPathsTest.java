package hw5;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class MarvelPathsTest {

    MarvelPaths p = new MarvelPaths();

    @Before
    public void spinUp() {
        String myOwnCSV = "data/own.csv";
        p.createNewGraph(myOwnCSV);
    }

    private String readFileContent(String filename) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        StringBuilder result = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            result.append(line).append('\n');
        }

        return result.toString();
    }

    @Test
    public void findPath() {
        try {
            assertEquals(readFileContent("data/result/char1_char2.txt"), p.findPath("Char1", "Char2"));
            assertEquals(readFileContent("data/result/char2_char2.txt"), p.findPath("Char2", "Char2"));
            // Not Exist node/path
            assertEquals(readFileContent("data/result/char_not_exist.txt"), p.findPath("CharNotExist", "CharNotExist1"));
            assertEquals(readFileContent("data/result/char1_char_not_exist.txt"), p.findPath("Char1", "CharNotExist"));
            assertEquals(readFileContent("data/result/char4_no_path.txt"), p.findPath("Char4", "Char1"));
            // Shortest Path
            assertEquals(readFileContent("data/result/char1_char7.txt"), p.findPath("Char1", "Char7"));
            assertEquals(readFileContent("data/result/char7_char1.txt"), p.findPath("Char7", "Char1"));
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
