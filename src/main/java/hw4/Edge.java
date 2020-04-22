package hw4;

import java.util.Objects;

/** <b>Edge</b> represents an <b>immutable</b> edge in the graph
 * it set up a bridge between two node, and can be labeled with a name
 * There many kinds of edge, include reflective edge, which the Node "from" and "to"
 *  reference to the same node
 * An edge can be compared by its name
 */
public class Edge<T extends Comparable<T>, Q extends Comparable<Q>>  implements Comparable<Edge<T, Q>> {
    private final T from;
    private final T to;
    private final Q name;

    // Abstraction Function:
    // an edge that start from node "from" and ends at Node "to"
    // named with String "name"

    // Representation invariant for every Edge:
    // from != NULL && to != NULL && name != NULL

    /** @param a is the type T represent node that  the edge start from
     * @param b is the type T represent node that the edge ends at
     * @param n is type T represent the label of the edge
          @effects Constructs a new Edge that connects Node a and Node b
         with name n
     */
    public Edge (T a, T b, Q n){
        from = a;
        to = b;
        name = n;
        // checkRep();
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
    public T getFrom() {
        return from;
    }

    /**
     *  Returns the end node of the edge
     * @return Node Destination of this edge
     */
    public T getTo() {
        return to;
    }

    /**
     * Returns the name of the edge
     * @return String the name of this edge
     */
    public Q getName() {
        return name;
    }

    /** Compares two Edges
     @param edge The Edge to be compared.
     @requires edge != null
     @return positive if  this.name > edge.name
     0 if the name are same
     negative if this.name < edge.name
     */
    @Override
    public int compareTo(Edge<T, Q> edge) {
            if (getTo().equals(edge.getTo())) {
                return getName().compareTo(edge.getName());
            }

            return getTo().compareTo(edge.getTo());
    }

    /**
    @param o The Edge to be compared.
     @return true iff this has the same attribute as o
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?, ?> edge = (Edge<?, ?>) o;
        return getFrom().equals(edge.getFrom()) &&
                getTo().equals(edge.getTo()) &&
                getName().equals(edge.getName());
    }

    /**
     @return int the hashcode of edge
     */
    @Override
    public int hashCode() {
        return Objects.hash(getFrom(), getTo(), getName());
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", name=" + name +
                '}';
    }
}
