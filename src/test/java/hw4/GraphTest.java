package hw4;

import org.junit.Test;

import java.util.Set;

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

        // Get Nodes
        assertEquals(Set.of("a", "b", "c", "d", "e", "ad"), g.getNodes());
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
        assertEquals(Set.of(ab, ad, aa, ad_1, ab_1), g.connectedEdge("a"));
        assertEquals(Set.of(bc), g.connectedEdge("b"));
        assertEquals(Set.of(cd),g.connectedEdge("c"));
        assertEquals(Set.of(ada, ada_1), g.connectedEdge("ad"));
        assertEquals(Set.of(), g.connectedEdge("d"));
        assertEquals(Set.of(), g.connectedEdge("f"));

        // connectedNodes will be tested in GraphWrapper
    }

}