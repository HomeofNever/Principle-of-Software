package hw5;

import hw4.Edge;
import hw4.Graph;

import java.util.*;

public class PathAlgorithmWithNumber<T extends Comparable<T>, Q extends Number & Comparable<Q>> extends PathAlgorithm<T, Q> {

    public Map<T, List<Edge<T, Double>>> findDijkstra(T node1, T node2) {
        // Prepare for the Queue, and init distance to inf
        Set<T> finished = new HashSet<>();
        Queue<Edge<T, Double>> active = new PriorityQueue<>(new EdgeComparator<>());
        Map<T, List<Edge<T, Double>>> minPaths = new HashMap<>();

        // init the start node as 0
        active.offer(new Edge<>(node1, node1, 0.0));
        while (!active.isEmpty()) {
            Edge<T, Double> minPath = active.poll();
            T minDest = minPath.getTo();

            if (finished.contains(minDest))
                continue;

            List<Edge<T, Double>> ls = new LinkedList<>();
            if (minPaths.get(minPath.getFrom()) != null)
                ls.addAll(minPaths.get(minPath.getFrom()));
            ls.add(minPath);
            minPaths.put(minDest, ls);

            if (minDest.equals(node2))
                break;

            for (Edge<T, Q> e : g.connectedEdge(minDest)) {
                if (!finished.contains(e.getTo())) {
                    Edge<T, Double> newPath = new Edge<>(e.getFrom(), e.getTo(),  e.getName().doubleValue() + minPath.getName());
                    active.offer(newPath);
                }
            }

            finished.add(minDest);
        }

        return minPaths;
    }
}

/**
 * Comparator class for Edge in Priority Queue
 * Will only compare edge weight
 */
class EdgeComparator<T extends Comparable<T>> implements Comparator<Edge<T, Double>> {
    public int compare(Edge<T, Double> v1, Edge<T, Double> v2)
    {
        return Double.compare(v1.getName(), v2.getName());
    }
}
