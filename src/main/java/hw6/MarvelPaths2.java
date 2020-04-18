package hw6;

import hw4.Edge;
import hw4.Graph;
import hw5.MarvelParser;
import hw5.PathAlgorithm;

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
    @Override
    public String findPath(String node1, String node2) {
        StringBuilder result = new StringBuilder();
        Double totalCost = 0.0;
        Map<String, List<Edge<String, Double>>> minPaths = new HashMap<>();
        Set<String> finished = new HashSet<>();

        if (checkNode(result, node1, node2)) {
            // Prepare for the Queue, and init distance to inf
            Queue<Edge<String, Double>> active = new PriorityQueue<>(new EdgeComparator());

            // init the start node as 0
            active.offer(new Edge<>(node1, node1, 0.0));
            while (!active.isEmpty()) {
                Edge<String, Double> minPath = active.poll();
                String minDest = minPath.getTo();

                if (finished.contains(minDest))
                    continue;

                List<Edge<String, Double>> ls = new LinkedList<>();
                if (minPaths.get(minPath.getFrom()) != null)
                    ls.addAll(minPaths.get(minPath.getFrom()));
                ls.add(minPath);
                minPaths.put(minDest, ls);

                if (minDest.equals(node2))
                    break;

                for (Edge<String, Double> e : g.connectedEdge(minDest)) {
                    if (!finished.contains(e.getTo())) {
                        Edge<String, Double> newPath = new Edge<>(e.getFrom(), e.getTo(), e.getName() + minPath.getName());
                        active.offer(newPath);
                    }
                }

                finished.add(minDest);
            }

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
                result.append(String.format("total cost: %.3f\n",totalCost));
            } else {
                result.append("no path found\n");
            }
        }

        return result.toString();
    }
}

/**
 * Comparator class for Edge in Priority Queue
 * Will only compare edge weight
 */
class EdgeComparator implements Comparator<Edge<String, Double>> {
    public int compare(Edge<String, Double> v1, Edge<String, Double> v2)
    {
        return Double.compare(v1.getName(), v2.getName());
    }
}