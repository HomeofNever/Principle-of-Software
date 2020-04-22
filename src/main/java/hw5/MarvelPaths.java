package hw5;

import hw4.Edge;
import hw4.Graph;

import java.util.*;

import static hw5.MarvelParser.readData;

/**
 * This class does not represent an ADT since it only utilize its underlay Graph to store
 * Hero-book relationship and implement an BFS search algorithm on the Graph.
 * The Class itself is actually **act  as** a GraphWrapper, a concrete usage of Graph
 */
public class MarvelPaths extends PathAlgorithm<String, String> {

    public MarvelPaths() {}

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
            g = new Graph<>();
            // Build Nodes
            for (String s:chars)
                g.addNode(s);

            for (Map.Entry<String, Set<String>> entry : charsInBooks.entrySet()) {
                // Heros need to be connected to each other if there are shared in one book
                for (String e1: entry.getValue()) {
                    for (String e2: entry.getValue()) {
                        if (!e1.equals(e2)) {
                            g.connect(e1, e2, entry.getKey());
                        }
                    }
                }
            }
        }
    }

    /**
     * @param node1 String represent hero to begin search with
     * @param node2 String represent heros to end search with
     * @return String Indicate the path that start from node1 and end with node2
     * if node 1 and/or node2 has no found in the graph, unknown character will be returned
     * If there is no path between these two character, no path will be returned
     */
    public String findPath(String node1, String node2) {
        StringBuilder result = new StringBuilder();
        if (checkNode(result, node1, node2)) {
            // Prepare for the Queue, a map to store visited path and a placeholder for final result
            Queue<String> workList = new LinkedList<>();
            Map<String, List<Edge<String, String>>> visited = new TreeMap<>();
            List<Edge<String, String>> path = new ArrayList<>();

            workList.offer(node1);
            visited.put(node1, new ArrayList<>());
            while (!workList.isEmpty()) {
                String current_node = workList.poll();

                // Break on result
                if (current_node.equals(node2)) {
                    path = visited.get(current_node);
                    break;
                }

                for (Edge<String, String> e: g.connectedEdge(current_node)) {
                    if (!visited.containsKey(e.getTo())) {
                        List<Edge<String, String>> p = new ArrayList<>(visited.get(e.getFrom()));
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
                for (Edge<String, String> e: path) {
                    result.append(e.getFrom()).append(" to ").append(e.getTo()).append(" via ").append(e.getName()).append('\n');
                }
            }
        }

        return result.toString();
    }
}