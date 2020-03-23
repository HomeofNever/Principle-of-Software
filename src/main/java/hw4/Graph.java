package hw4;

import java.util.*;

/**
 * <b>Graph</b> represents an <b>mutable</b> graph consists of nodes and edges
 * It represents a multi-bidirectional graph
 */
public class Graph {
    private HashMap<String, HashSet<Edge>> graph;

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
        throw new RuntimeException("Graph constructor not implemented");
    }

    /**
     * Checks that the representation invariant holds (if any).
     **/
    // Throws a RuntimeException if the rep invariant is violated.
    private void checkRep() throws RuntimeException {
        if (graph == null) {
            throw new RuntimeException("Graph cannot be null");
        }

        for (Set<Edge> s : graph.values()) {
            for (Edge n : s) {
                if (!graph.containsKey(n.getTo())) {
                    throw new RuntimeException("Edge connected to non-listed node");
                }
            }
        }
    }

    /**
     * add new nodes and a edge to the graph
     * @param a String represent Node which the edge starts at
     * @param b String represent Node which the edge ends at
     * @param edgeName String the name of the edge
     * @return boolean true iff the edge successfully added to the graph
     */
    public boolean connect(String a, String b, String edgeName) {
        throw new RuntimeException("Connect not implemented");
    }

    /**
     * add new nodes and a edge to the graph
     * @param a String represent Node which the edge starts at
     * @param b String represent Node which the edge ends at
     * @return boolean true iff the edge successfully added to the graph
     */
    public boolean connect(String a, String b) {
       return connect(a, b , "");
    }

    /**
     * Return a Set of edge that start from given Node a
     * @param a String represent Node where the edge start from
     * @return A set of edge that start from given Node a.
     *  An empty  set will return if node "a" cannot be found in the graph
     */
    public Set<Edge> connectedEdge(String a) {
        throw new RuntimeException("ConnectedEdge not implemented");
    }
}
