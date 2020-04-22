package hw5;

import hw4.Graph;

/**
 *  This class does not represent an ADT since it only utilize its underlay Graph to store
 *  relationships and guide any MarvelPath class with following interface to access graph
 *  It also provide some common pretty print situations.
 * @param <T> Node type
 * @param <Q> Edge Label Type
 */
abstract public class PathAlgorithm<T extends Comparable<T>, Q extends Comparable<Q>> {
    protected Graph<T, Q> g = new Graph<>();

    /**
     *  Read in data from given filename and parse it into algorithm optimized
     *  form. It depends on what algorithm is used in findPath method
     * @param filename filename CSV file that contains hero-book pairs
     * @effects g Graph will be filled with given file data 's relationship
     */
    public abstract void createNewGraph(String filename);

    /**
     * FindPath based on given algorithm implemented in this function
     * @param node1 Start node
     * @param node2 End node
     * @return String Indicate the path that start from node1 and end with node2
     *  if node 1 and/or node2 has no found in the graph, unknown character will be returned
     *  if there is no path between these two character, no path will be returned
     */
    public abstract  String findPath(String node1, String node2);

    /**
     * Check if node exist in the graph and write output accordingly
     * @param b String builder for result output
     * @param node1 String represent start node
     * @param node2 String represent end node
     * @return if either node does not exist in the graph
     */
    protected boolean checkNode(StringBuilder b, T node1, T node2) {
        boolean flag =true;
        if (!g.getNodes().contains(node1)) {
            b.append("unknown character ").append(node1).append('\n');
            flag = false;
        }
        if ((!g.getNodes().contains(node2)) && (!node1.equals(node2))) {
            b.append("unknown character ").append(node2).append('\n');
            flag = false;
        }
        if (flag) {
            b.append("path from ").append(node1).append(" to ").append(node2).append(":\n");
        }
        return flag;
    }
}
