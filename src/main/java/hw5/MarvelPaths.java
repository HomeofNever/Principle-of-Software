package hw5;

import hw4.Graph;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static hw5.MarvelParser.readData;

class MarvelPaths {
    Graph g = new Graph();

    public MarvelPaths(String filename) {
        createNewGraph(filename);
    }

    public void createNewGraph(String filename) {
        Map<String, Set<String>> charsInBooks = new HashMap<>();
        Set<String> chars = new HashSet<>();
        try {
            readData(filename,charsInBooks,chars);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            MarvelParser.buildGraph(g, charsInBooks, chars);
        }
    }

    public String findPath(String node1, String node2) {
        return "";
    }
}