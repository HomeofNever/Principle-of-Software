package hw5;

import hw4.Edge;
import hw4.Graph;

import java.util.*;

/**
 *  This class does not represent an ADT since it only utilize its underlay Graph to store
 *  relationships and guide any MarvelPath class with following interface to access graph
 *  It also provide some common pretty print situations.
 * @param <T> Node type
 * @param <Q> Edge Label Type
 */
public class PathAlgorithm<T extends Comparable<T>, Q extends Comparable<Q>> {

    protected Graph<T, Q> g = new Graph<>();

    public void setGraph(Graph<T, Q> g) {
        this.g = g;
    }

    public List<Edge<T, Q>> findBFS(T node1, T node2) {
        List<Edge<T, Q>> path = new ArrayList<>();
            // Prepare for the Queue, a map to store visited path and a placeholder for final result
            Queue<T> workList = new LinkedList<>();
            Map<T, List<Edge<T, Q>>> visited = new TreeMap<>();

            workList.offer(node1);
            visited.put(node1, new ArrayList<>());
            while (!workList.isEmpty()) {
                T current_node = workList.poll();

                // Break on result
                if (current_node.equals(node2)) {
                    path = visited.get(current_node);
                    break;
                }

                for (Edge<T, Q> e : g.connectedEdge(current_node)) {
                    if (!visited.containsKey(e.getTo())) {
                        List<Edge<T, Q>> p = new ArrayList<>(visited.get(e.getFrom()));
                        p.add(e);
                        visited.put(e.getTo(), p);
                        workList.offer(e.getTo());
                    }
                }
            }
        return path;
    }

    /**
     * Check if node exist in the graph
     * @param node1 String represent start node
     * @param node2 String represent end node
     * @return 0: normal,
     *                     1 first not exist,
     *                     2 second not exist,
     *                     3 both not exist,
     *                     4 both not exist and two nodes are the same
     */
    public int checkNode(T node1, T node2) {
        int r = 0;
        if (!g.getNodes().contains(node1)) {
            r += 1;
        }
        if ((!g.getNodes().contains(node2))) {
            r += 2;
        }

        if (r == 3) {
            if (node1.equals(node2))
                r += 1;
        }

        return r;
    }

    public static void writeStats(StringBuilder b, int result, String node1, String node2) {
        switch (result) {
            case 1:
            case 4:
                b.append("unknown character ").append(node1).append('\n');
                break;
            case 2:
                b.append("unknown character ").append(node2).append('\n');
                break;
            case 3:
                b.append("unknown character ").append(node1).append('\n');
                b.append("unknown character ").append(node2).append('\n');
                break;
            case 0:
                b.append("path from ").append(node1).append(" to ").append(node2).append(":\n");
        }
    }
}
