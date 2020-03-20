package hw4;


import java.util.Iterator;

class GraphWrapper {
    private Graph g;

    public GraphWrapper() {
        throw new RuntimeException("Graph wrapper instructor Not Implemented");
    }

    /*
     Adds a node represented by the string nodeData to your graph. If an identical node already exists in the graph, the output of addNode is not defined, that is, it is left at your discretion.
     */
    public void addNode(String nodeData) {
        throw new RuntimeException("add Node Not Implemented");
    }

    /*
        Creates an edge from parentNode to childNode with label edgeLabel in your graph.
        If either of the nodes does not exist in the graph, the output of this command is not defined.
        If an identical edge (same parent, child, and label) already exists, the output of this command is not defined either, as it is left at your discretion whether to allow identical edges in your implementation.
     */
    public void addEdge(String parentNode, String childNode, String edgeLabel) {
        throw new RuntimeException("getEdge Not Implemented");
    }

    /*
        This operation has no effect on your graph.
        It returns an iterator which represents the nodes in lexicographical (alphabetical) order.
     */
    public Iterator<String> listNodes() {
        throw new RuntimeException("List Nodes Not Implemented");
    }

    /*
       This operation has no effect on your graph.
       It returns an iterator which represents the list of childNode(edgeLabel) in lexicographical (alphabetical) order by node name and secondarily by edge label. childNode(edgeLabel) means there is an edge with label edgeLabel from parentNode to childNode.
       If there are multiple edges from parentNode to some childNode, there should be a separate entry for each edge.
       If there is a reflexive edge, parentNode(edgeLabel) should be in the list.
     */
    public Iterator<String> listChildren(String parentNode) {
        throw new RuntimeException("listChildren Not Implemented");
    }
}