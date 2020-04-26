package hw6;

import hw4.Edge;
import hw4.Graph;
import hw5.MarvelParser;
import hw5.PathAlgorithm;
import hw5.PathAlgorithmWithNumber;

import java.util.*;

public class MarvelPaths2  {

    PathAlgorithmWithNumber<String, Double> p = new PathAlgorithmWithNumber<>();

    /**
     * Read in data from given filename and parse it into algorithm optimized form
     *
     * @param filename filename CSV file that contains hero-book pairs
     * @effects g Graph will be filled with char - (1/number of book between char) - char
     */
    public void createNewGraph(String filename) {
        Map<String, Set<String>> charsInBooks = new HashMap<>();
        Set<String> chars = new HashSet<>();
        try {
            MarvelParser.readData(filename, charsInBooks, chars);
        } catch (Exception e) {
            System.out.println("Error when reading data from file " + filename);
            e.printStackTrace();
        } finally {
            Graph<String ,Double>g = new Graph<>();
            for (String c : chars)
                g.addNode(c);

            // Build Relationship
            Map<Map.Entry<String, String>, Integer> m = new HashMap<>();
            for (Map.Entry<String, Set<String>> entry : charsInBooks.entrySet()) {
                List<String> arr = new ArrayList<>(entry.getValue());
                for (int x = 0; x < arr.size(); x++) {
                    for (int y = x + 1; y < arr.size(); y++) {
                        Map.Entry<String, String> p = new AbstractMap.SimpleEntry<>(arr.get(x), arr.get(y));
                        Map.Entry<String, String> q = new AbstractMap.SimpleEntry<>(arr.get(y), arr.get(x));
                        // They should be the same number
                        // Either they will be null together or they should be the same
                        Integer i = m.get(p);
                        if (i == null) {
                            i = 1;
                        } else {
                            i += 1;
                        }
                        m.put(p, i);
                        m.put(q, i);
                    }
                }
            }

            // Connect Graph
            for (Map.Entry<Map.Entry<String, String>, Integer> entry : m.entrySet()) {
                String a = entry.getKey().getKey();
                String b = entry.getKey().getValue();
                Double d = 1.0 / entry.getValue();
                g.connect(a, b, d);
            }

            p.setGraph(g);
        }
    }

    /**
     * @param node1 String represent hero to begin search with
     * @param node2 String represent heros to end search with
     * @return String Indicate the path that start from node1 and end with node2
     * with least cost
     * if node 1 and/or node2 has no found in the graph, unknown character will be returned
     * If there is no path between these two character, no path will be returned
     */
    public String findPath(String node1, String node2) {
        StringBuilder result = new StringBuilder();
        int r = p.checkNode(node1, node2);
        PathAlgorithm.writeStats(result, r, node1, node2);

        if (r == 0) {
            Double totalCost = 0.0;
            Map<String, List<Edge<String, Double>>> minPaths = p.findDijkstra(node1, node2);
            // Format Result
            if (minPaths.get(node2) != null) {
                if (!node1.equals(node2)) {
                    List<Edge<String, Double>> ls = minPaths.get(node2);
                    totalCost = ls.get(ls.size() - 1).getName();
                    for (int i = 1; i < ls.size(); i++) {
                        Double value = i - 1 > 0 ? ls.get(i).getName() - ls.get(i - 1).getName() : ls.get(i).getName();
                        result.append(ls.get(i).getFrom()).append(" to ").append(ls.get(i).getTo())
                                .append(String.format(" with weight %.3f", value))
                                .append("\n");
                    }
                }
                result.append(String.format("total cost: %.3f\n", totalCost));
            } else {
                result.append("no path found\n");
            }
        }

        return result.toString();
    }
}