package hw6;

import hw4.Edge;
import hw4.Graph;
import hw5.MarvelParser;
import hw5.PathAlgorithm;
import javafx.util.Pair;

import java.util.*;

public class MarvelPaths2 extends  PathAlgorithm<String, Double> {

    /**
     *  Read in data from given filename and parse it into algorithm optimized form
     * @param filename filename CSV file that contains hero-book pairs
     * @effects g Graph will be filled with char - (1/number of book between char) - char
     */
    @Override
    public void createNewGraph(String filename) {
        Map<String, Set<String>> charsInBooks = new HashMap<>();
        Set<String> chars = new HashSet<>();
        try {
            MarvelParser.readData(filename, charsInBooks, chars);
        } catch (Exception e) {
            System.out.println("Error when reading data from file " + filename);
            e.printStackTrace();
        } finally {
            g = new Graph<>();
            for (String c : chars)
                g.addNode(c);

            // Build Relationship
            Map<Pair<String, String>, Integer> m = new HashMap<>();
            for (Map.Entry<String, Set<String>> entry : charsInBooks.entrySet()) {
                List<String> arr = new ArrayList<>(entry.getValue());
                for (int x = 0; x < arr.size(); x++) {
                    for (int y = x + 1; y < arr.size(); y++) {
                        Pair<String, String> p = new Pair<>(arr.get(x), arr.get(y));
                        Pair<String, String> q = new Pair<>(arr.get(y), arr.get(x));
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
            for (Map.Entry<Pair<String, String>, Integer> entry : m.entrySet()) {
                String a = entry.getKey().getKey();
                String b = entry.getKey().getValue();
                Double d = 1.0 / entry.getValue();
                g.connect(a, b, d);
            }
        }
    }

    /**
     * @param node1
     * @param node2
     * @return
     */
    @Override
    public String findPath(String node1, String node2) {
        StringBuilder result = new StringBuilder();
        boolean found = false;
        Double totalCost = 0.0;
        List<Pair<String, Double>> path = new LinkedList<>();

        if (checkNode(result, node1, node2)) {
            if (!node1.equals(node2)) {
                // Prepare for the Queue, and init distance to inf
                Queue<Pair<String, Double>> active = new PriorityQueue<>(new PairComparator());
                Map<String,Double> distances = new HashMap<>();
                Map<String,String> paths = new HashMap<>();

                for (String s: g.getNodes()) {
                    distances.put(s, Double.POSITIVE_INFINITY);
                    paths.put(s, null);
                    active.offer(new Pair<>(s, Double.POSITIVE_INFINITY));
                }

                // init the start node as 0
                distances.put(node1, 0.0);
                active.offer(new Pair<>(node1, 0.0));
                while (!active.isEmpty()) {
                    Pair<String, Double> minPath = active.poll();
                    String minDest = minPath.getKey();

                    for (Edge<String, Double> e : g.connectedEdge(minDest)) {
                        Double temp_distance = distances.get(minDest) + e.getName();
                        if (temp_distance < distances.get(e.getTo())) {
                            distances.put(e.getTo(), temp_distance);
                            paths.put(e.getTo(), minDest);
                        }
                    }
                }

                // The final map store end node as result
                if (paths.get(node2) != null) {
                    found = true;
                    String current= node2;
                    while (!current.equals(node1)) {
                        path.add(0, new Pair<>(current, distances.get(current)));
                        current = paths.get(current);
                    }
                    path.add(0, new Pair<>(current, distances.get(current)));
                }
            }

            // Format Result
            if (found || node1.equals(node2)) {
                if (path.size() > 0) {
                    totalCost = path.get(path.size() - 1).getValue();
                    for (int i = 0; i < path.size() - 1; i++) {
                        Pair<String, Double> current = path.get(i);
                        Pair<String, Double> next = path.get(i + 1);
                        Double value = next.getValue() - current.getValue();
                        result.append(current.getKey()).append(" to ").append(next.getKey())
                                .append(String.format(" with weight %.3f", value))
                                .append("\n");
                    }
                }
                result.append(String.format("total cost: %.3f",totalCost));
            } else {
                result.append("no path found\n");
            }
        }

        return result.toString();
    }
}

/**
 * Comparator class for Pair in Priority Queue
 * Will only compare edge weight
 */
class PairComparator implements Comparator<Pair<String, Double>>{
    public int compare(Pair<String, Double> v1, Pair<String, Double> v2)
    {
        return Double.compare(v1.getValue(), v2.getValue());
    }
}