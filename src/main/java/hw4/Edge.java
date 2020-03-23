package hw4;

import java.util.Comparator;

/** <b>Edge</b> represents an <b>immutable</b> edge in the graph
 * it set up a bridge between two node, and can be labeled with a name
 * There many kinds of edge, include reflexible edge, which the Node "from" and "to"
 *  reference to the same node
 * An edge can be compared by its name
 */
public class Edge implements Comparable<Edge> {
    private final String from;
    private final String  to;
    private final String name = "";

    // Abstraction Function:
    // an edge that start from node "from" and ends at Node "to"
    // named with String "name"

    // Representation invariant for every Edge:
    // from != NULL && to != NULL && name != NULL

    /** @param a is the String represent node that  the edge start from
     * @param b is the String represent node that the edge ends at
     * @param n is the String represent the label of the edge
          @effects Constructs a new Edge that connects Node a and Node b
         with name n
     */
    public Edge (String a, String b, String n) {
        throw new RuntimeException("Edge constructor Not Implemented");
    }

    /** @param a is the String represent node that  the edge start from
     * @param b is the String represent node that the edge ends at
     @effects Constructs a new Edge that connects Node a and Node b
     with name n
     */
    public Edge (String a, String b) {
        throw new RuntimeException("Edge constructor without label Not Implemented");
    }

    /**
     * Checks that the representation invariant holds (if any).
     **/
    // Throws a RuntimeException if the rep invariant is violated.
    private void checkRep() throws RuntimeException {
        if (from == null || to == null) {
            throw new RuntimeException("Node cannot be null");
        }

        if (name == null) {
            throw new RuntimeException("Label cannot be null");
        }
    }

    /** Returns the start from node
          @return source node of this edge
     */
    public String getFrom() {
        throw new RuntimeException("getFrom Not Implemented");
    }

    /**
     *  Returns the end node of the edge
     * @return Node Destination of this edge
     */
    public String getTo() {
        throw new RuntimeException("getTo Not Implemented");
    }

    /**
     * Returns the name of the edge
     * @return String the name of this edge
     */
    public String getName() {
        throw new RuntimeException("getName Not Implemented");
    }
    /** Compares two Edges
     @param edge The Edge to be compared.
     @requires rn != null
     @return positive if  this.name > edge.name
     0 if the name are same
     negative if this.name < edge.name
     */
    @Override
    public int compareTo(Edge edge) {
            throw new RuntimeException("CompareTo Not Implemented");
    }
}
