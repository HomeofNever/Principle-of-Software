package hw4;

import java.util.*;

/**
 * <b>Graph</b> represents an <b>mutable</b> graph consists of nodes and edges
 * It represents a multi-bidirectional graph
 */
public class Graph<T extends Comparable<T>, Q extends  Comparable<Q>> {
    private TreeMap<T, TreeSet<Edge<T, Q>>> graph;

    // Abstraction Function:
    // Make use of adjacent list, we have a map that represent the start nodes corresponded with
    // a set of edges connected with it

    // Representation invariant for every Graph g:
    // forall list of graph.values() :: forall edge in list :: graph.keys().contains(edge.getTo())
    // An empty graph is allowed

    /**
     * @effects Construct an empty graph
     */
    public Graph() {
        graph = new TreeMap<>();
    }

    /**
     * Checks that the representation invariant holds (if any).
     **/
    // Throws a RuntimeException if the rep invariant is violated.
    private void checkRep() throws RuntimeException {
        if (graph == null) {
            throw new RuntimeException("Graph cannot be null");
        }

        for (Set<Edge<T, Q>> s : graph.values()) {
            if (s == null) {
                throw new RuntimeException("Node with uninitialized Set");
            }
            for (Edge<T, Q> n : s) {
                if (!graph.containsKey(n.getTo())) {
                    throw new RuntimeException("Edge connected to non-listed node");
                }
            }
        }
    }

    /**
     * add new node to the graph
     * @param a String represent Node to be added
     * @effects  add given node as key to map graph
     * @return boolean true iff the edge successfully added to the graph
     */
    public boolean addNode(T a) {
        if (!graph.containsKey(a)) {
            graph.put(a, new TreeSet<>());
            return true;
        }

        return false;
    }

    /**
     * get all nodes in the graph
     * @return Set of Strings that represent nodes
     * An empty set will return if the graph is empty
     */
    public Set<T> getNodes() {
        return Collections.unmodifiableSet(graph.keySet());
    }

    /**
     * add new nodes and a edge to the graph
     * @param a String represent Node which the edge starts at
     * @param b String represent Node which the edge ends at
     * @param edgeName String the name of the edge
     * @return boolean true iff the edge successfully added to the graph
     */
    public boolean connect(T a, T b, Q edgeName) {
        Edge<T, Q> toBeAdded = new Edge<T, Q>(a, b, edgeName);
        if (graph.containsKey(a) && graph.containsKey(b)) {
            boolean result = graph.get(a).add(toBeAdded);
            // checkRep();
            return result;
        }

        return false;
    }

    /**
     * Return a Set of edge that start from given Node a
     * @param a String represent Node where the edge start from
     * @return A set of edge that start from given Node a.
     *  An empty set will return if node "a" does not exist
     */
    public Set<Edge<T, Q>> connectedEdge(T a) {
        if (graph.get(a) == null)
            return new TreeSet<>();
       return Collections.unmodifiableSet(graph.get(a));
    }

    /**
     * Return a Set of edge that start from given Node a
     * @param a String represent Node where the edge start from
     * @return A list of node that start from given Node a, alphabetically ordered.
     * e.g. Node(Label)
     *  An empty list will return if node "a" does not exist
     */
    public List<String> connectedNodes(T a) {
        ArrayList<String> r = new ArrayList<>();
        for (Edge<T, Q> i : connectedEdge(a)) {
            r.add(i.getTo() + "(" + i.getName() + ")");
        }

        return r;
    }
}
