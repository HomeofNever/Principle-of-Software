package hw6;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MarvelPaths2Test {

    MarvelPaths2 p = new MarvelPaths2();

    @Before
    public void spinUp() {
        String myOwnCSV = "data/paths.csv";
        p.createNewGraph(myOwnCSV);
    }

    @Test
    public void findPath() {
        // One edge
        assertEquals("path from Char1 to Char3:\n" +
                "Char1 to Char3 with weight 0.333\n" +
                "total cost: 0.333", p.findPath("Char1", "Char3"));
        assertEquals("path from Char7 to Char6:\n" +
                "Char7 to Char6 with weight 1.000\n" +
                "total cost: 1.000", p.findPath("Char7", "Char6"));
        // Two edges
        assertEquals("path from Char1 to Char5:\n" +
                "Char1 to Char2 with weight 0.500\n" +
                "Char2 to Char5 with weight 0.250\n" +
                "total cost: 0.750", p.findPath("Char1", "Char5"));
        // Special Cases
        assertEquals("path from Char1 to Char1:\n" +
                "total cost: 0.000", p.findPath("Char1", "Char1"));
        assertEquals("path from Char1 to Char6:\n" +
                "no path found\n", p.findPath("Char1", "Char6"));
        assertEquals("unknown character CharNotExist\n",
                p.findPath("CharNotExist", "CharNotExist"));
        assertEquals("unknown character CharNotExist1\n" +
                "unknown character CharNotExist2\n", p.findPath("CharNotExist1", "CharNotExist2"));
    }
}
