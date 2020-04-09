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

    @Test
    public void findPath() {
        assertEquals("path from Char1 to Char2:\n" +
                "Char1 to Char2 via Book1\n", p.findPath("Char1", "Char2"));
        assertEquals("path from Char2 to Char2:\n", p.findPath("Char2", "Char2"));
        // Not Exist node/path
        assertEquals("unknown character CharNotExist\n" +
                "unknown character CharNotExist1\n", p.findPath("CharNotExist", "CharNotExist1"));
        assertEquals("unknown character CharNotExist\n", p.findPath("Char1", "CharNotExist"));
        assertEquals("path from Char4 to Char1:\n" +
                "no path found\n", p.findPath("Char4", "Char1"));
        assertEquals("unknown character CharNotExist\n", p.findPath("CharNotExist", "CharNotExist"));
        // Shortest Path
        assertEquals("path from Char1 to Char7:\n" +
                "Char1 to Char6 via Book1\n" +
                "Char6 to Char7 via Book5\n", p.findPath("Char1", "Char7"));
        assertEquals("path from Char7 to Char1:\n" +
                "Char7 to Char6 via Book5\n" +
                "Char6 to Char1 via Book1\n", p.findPath("Char7", "Char1"));
    }
}
