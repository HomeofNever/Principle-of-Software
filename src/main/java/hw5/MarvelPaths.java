package hw5;

import hw4.Edge;
import hw4.Graph;

import java.util.*;

import static hw5.MarvelParser.readData;

/**
 * <b>MarvelPath</b> represents an <b>mutable</b>  Paths represent
 * Marvel Heros relation connected by books.
 * It contains a Graph that store the relations and provide methods to
 * find the path between them..
 */
class MarvelPaths {
    Graph g = new Graph();
    // Abstraction Function:
    // Make use of class Graph, we have a structure Graph to represent
    // a set of nodes as heros and edges with book name that connecting heros.

    // Representation invariant for every MarvelPath
    // g != null
    // An empty Path is allowed

    public MarvelPaths() {}

    private void checkRep() throws RuntimeException {
        if (g == null)
            throw new RuntimeException("Graph g does not init properly!");
    }

    /**
     * @param filename CSV file that contains hero-book pairs
     * @effects g Graph will be filled with given file data's hero-book relationship
     */
    public void createNewGraph(String filename) {
        Map<String, Set<String>> charsInBooks = new HashMap<>();
        Set<String> chars = new HashSet<>();
        try {
            readData(filename,charsInBooks,chars);
        } catch (Exception e) {
            System.out.println("Error when reading data from file " + filename);
            e.printStackTrace();
        } finally {
            g = new Graph();
            MarvelParser.buildGraph(g, charsInBooks, chars);
        }

        // checkRep();
    }

    /**
     * @param node1 String represent hero to begin search with
     * @param node2 String represent heros to end search with
     * @return String Indicate the path that start from node1 and end with node2
     * if node 1 and/or node2 has no found in the graph, unknown character will be returned
     * If there is no path between these two character, no path will be returned
     */
    public String findPath(String node1, String node2) {
        // checkRep();
        StringBuilder result = new StringBuilder();
        if (!g.getNodes().contains(node1)) {
            result.append("unknown character ").append(node1).append('\n');
        }
        if ((!g.getNodes().contains(node2)) && (!node1.equals(node2))) {
            result.append("unknown character ").append(node2).append('\n');
        }

        if (result.length() == 0) {
            result.append("path from ").append(node1).append(" to ").append(node2).append(":\n");
            // Prepare for the Queue, a map to store visited path and a placeholder for final result
            Queue<String> workList = new LinkedList<>();
            Map<String, List<Edge>> visited = new TreeMap<>();
            List<Edge> path = new ArrayList<>();

            workList.offer(node1);
            visited.put(node1, new ArrayList<>());
            while (!workList.isEmpty()) {
                String current_node = workList.poll();

                // Break on result
                if (current_node.equals(node2)) {
                    path = visited.get(current_node);
                    break;
                }

                for (Edge e: g.connectedEdge(current_node)) {
                    if (!visited.containsKey(e.getTo())) {
                        List<Edge> p = new ArrayList<>(visited.get(e.getFrom()));
                        p.add(e);
                        visited.put(e.getTo(), p);
                        workList.offer(e.getTo());
                    }
                }
            }

            // Output path format
            if (path.size() == 0 && !node1.equals(node2)) {
                result.append("no path found\n");
            } else {
                for (Edge e: path) {
                    result.append(e.getFrom()).append(" to ").append(e.getTo()).append(" via ").append(e.getName()).append('\n');
                }
            }
        }

        return result.toString();
    }
}