package hw4;

import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class GraphTest {
    Graph g = new Graph();

    Edge ab = new Edge("a", "b");
    Edge bc = new Edge("b", "c");
    Edge cd = new Edge("c", "d");
    Edge ad = new Edge("a", "d", "edge1");
    Edge aa = new Edge("a", "a");

    Edge ab_1 = new Edge("a", "b", "edge1");
    Edge ad_1 = new Edge("a", "d", "edge2");

    Edge ada = new Edge("ad", "a");
    Edge ada_1 = new Edge("ad", "a", "some edge");

    @org.junit.Before
    public void spinUp() {
        // Constructing graph
        assertTrue(g.addNode("a"));
        assertTrue(g.addNode("b"));
        assertTrue(g.addNode("c"));
        assertTrue(g.addNode("d"));
        assertTrue(g.addNode("ad"));

        assertTrue(g.connect("a", "b"));
        assertTrue(g.connect("b", "c"));
        assertTrue(g.connect("c", "d"));
        assertTrue(g.connect("a", "d", "edge1"));
        assertTrue(g.connect("a", "a"));
        assertTrue(g.connect("ad", "a"));
    }

    @Test
    public void addNode() {
        // Add new node
        assertTrue(g.addNode("e"));
        assertFalse(g.addNode("a"));

        TreeSet<String> nodes = new TreeSet<>();
        nodes.add("a");
        nodes.add("b");
        nodes.add("c");
        nodes.add("d");
        nodes.add("e");
        nodes.add("ad");

        // Get Nodes
        assertEquals(nodes, g.getNodes());
    }

    @org.junit.Test
    public void connect() {
        assertFalse(g.connect("a", "b"));
        assertFalse(g.connect("a", "a"));
        assertFalse(g.connect("ad", "a"));
        assertTrue(g.connect("a", "b", "edge1"));
        assertTrue(g.connect("a", "d", "edge2"));
        assertTrue(g.connect("ad", "a", "some edge"));

        // The graph should have be like
        TreeSet<Edge> empty = new TreeSet<>();
        TreeSet<Edge> a = new TreeSet<>();
        TreeSet<Edge> b = new TreeSet<>();
        TreeSet<Edge> c = new TreeSet<>();
        TreeSet<Edge> s_ad = new TreeSet<>();

        a.add(ab);
        a.add(ad);
        a.add(aa);
        a.add(ad_1);
        a.add(ab_1);
        b.add(bc);
        c.add(cd);
        s_ad.add(ada);
        s_ad.add(ada_1);

        assertEquals(a, g.connectedEdge("a"));
        assertEquals(b, g.connectedEdge("b"));
        assertEquals(c,g.connectedEdge("c"));
        assertEquals(s_ad, g.connectedEdge("ad"));
        assertEquals(empty, g.connectedEdge("d"));
        assertEquals(empty, g.connectedEdge("f"));

        // connectedNodes will be tested in GraphWrapper
    }

}