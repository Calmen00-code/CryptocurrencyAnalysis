/*
Program: UnitTestGraphVertex.java
Author: Calmen Chia Kai Fong
Date Created: 12 October 2020
Last Modified: 12 October 2020
Purpose: Test Class for DSAGraphVertex.java
         !! Ensures that UnitTestDSAGraph.java is PASSED before testing this !!
*/

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;
import java.io.*;

@RunWith(JUnit4.class)
public class UnitTestDSAGraphVertex
{
    ByteArrayOutputStream capOut = null;    
    String output = "";
    DSAGraph graph;

    @Before
    public void setUp()
    {
        capOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capOut));
        graph = new DSAGraph();
    }

    @After
    public void tearDown()
    {
        output = "";
        capOut = null;
        graph = null;
    }

    @Test
    public void testVisitedFalse()
    {
        DSAGraphVertex vertex = null;

        vertex = new DSAGraphVertex("A", 10);
        vertex.setVisited(false); 
        assertEquals(false, vertex.getVisited());
    }

    @Test
    public void testVisitedTrue()
    {
        DSAGraphVertex vertex = null;

        vertex = new DSAGraphVertex("A", 10);
        vertex.setVisited(true); 
        assertEquals(true, vertex.getVisited());
    }

    @Test
    public void testGetLabel()
    {
        DSAGraphVertex vertex = null;

        vertex = new DSAGraphVertex("A", 10);
        assertEquals("A", vertex.getLabel());

        vertex = new DSAGraphVertex("B", 10.1);
        assertEquals("B", vertex.getLabel());

        vertex = new DSAGraphVertex("C", -11);
        assertEquals("C", vertex.getLabel());

        vertex = new DSAGraphVertex("D", "ABC");
        assertEquals("D", vertex.getLabel());

        vertex = new DSAGraphVertex("E", '-');
        assertEquals("E", vertex.getLabel());
    }

    @Test
    public void testGetValue()
    {
        DSAGraphVertex vertex = null;

        vertex = new DSAGraphVertex("A", 10);
        assertEquals(10, vertex.getValue());

        vertex = new DSAGraphVertex("B", 10.1);
        double testNum = (double) vertex.getValue();
        assertEquals(10.1, testNum, 0.001);

        vertex = new DSAGraphVertex("C", -11);
        assertEquals(-11, vertex.getValue());

        vertex = new DSAGraphVertex("D", "ABC");
        assertEquals("ABC", vertex.getValue());

        vertex = new DSAGraphVertex("E", '-');
        assertEquals('-', vertex.getValue());
    }

    @Test
    public void testToString()
    {
        DSAGraphVertex vertex = null;

        graph.addVertex("A", 10);
        graph.addVertex("B", 20);
        graph.addVertex("C", 30);
        graph.addVertex("D", 40);
        graph.addVertex("E", 50);

        graph.addEdge("A", "B", "1", "");
        graph.addEdge("A", "D", "2", "");
        graph.addEdge("A", "E", "3", "");

        graph.addEdge("B", "C", "4", "");
        graph.addEdge("B", "E", "5", "");

        graph.addEdge("C", "D", "6", "");
        graph.addEdge("C", "E", "7", "");

        vertex = (DSAGraphVertex) graph.getVertex("A");
        assertEquals("20 40 50 ", vertex.toString());

        vertex = (DSAGraphVertex) graph.getVertex("B");
        assertEquals("30 50 ", vertex.toString());

        vertex = (DSAGraphVertex) graph.getVertex("C");
        assertEquals("40 50 ", vertex.toString());

        vertex = (DSAGraphVertex) graph.getVertex("D");
        assertEquals("", vertex.toString());

        vertex = (DSAGraphVertex) graph.getVertex("E");
        assertEquals("", vertex.toString()); 
    }

    @Test
    public void testGetAdjacent()
    {
        DSAGraphVertex vertex = null;
        DSALinkedList list = null;
        String str = "";

        graph.addVertex("A", 10);
        graph.addVertex("B", 20);
        graph.addVertex("C", 30);
        graph.addVertex("D", 40);
        graph.addVertex("E", 50);

        graph.addEdge("A", "B", "1", "");
        graph.addEdge("A", "D", "2", "");
        graph.addEdge("A", "E", "3", "");

        graph.addEdge("B", "C", "4", "");
        graph.addEdge("B", "E", "5", "");

        graph.addEdge("C", "D", "6", "");
        graph.addEdge("C", "E", "7", "");

        vertex = (DSAGraphVertex) graph.getVertex("A");
        list = vertex.getAdjacent();
 
        for( Object o : list )
        {
            vertex = (DSAGraphVertex) o;
            str += vertex.getLabel() + " ";
        }
        assertEquals("B D E ", str);

        str = "";
        vertex = (DSAGraphVertex) graph.getVertex("B");
        list = vertex.getAdjacent();
 
        for( Object o : list )
        {
            vertex = (DSAGraphVertex) o;
            str += vertex.getLabel() + " ";
        }
        assertEquals("C E ", str);

        str = "";
        vertex = (DSAGraphVertex) graph.getVertex("C");
        list = vertex.getAdjacent();
 
        for( Object o : list )
        {
            vertex = (DSAGraphVertex) o;
            str += vertex.getLabel() + " ";
        }
        assertEquals("D E ", str);

        str = "";
        vertex = (DSAGraphVertex) graph.getVertex("D");
        list = vertex.getAdjacent();
 
        for( Object o : list )
        {
            vertex = (DSAGraphVertex) o;
            str += vertex.getLabel() + " ";
        }
        assertEquals("", str);

        str = "";
        vertex = (DSAGraphVertex) graph.getVertex("E");
        list = vertex.getAdjacent();
 
        for( Object o : list )
        {
            vertex = (DSAGraphVertex) o;
            str += vertex.getLabel() + " ";
        }
        assertEquals("", str);
    }

    @Test
    public void testGetSize()
    { 
        DSAGraphVertex vertex = null;

        graph.addVertex("A", 10);
        graph.addVertex("B", 20);
        graph.addVertex("C", 30);
        graph.addVertex("D", 40);
        graph.addVertex("E", 50);

        graph.addEdge("A", "B", "1", "");
        graph.addEdge("A", "D", "2", "");
        graph.addEdge("A", "E", "3", "");

        graph.addEdge("B", "C", "4", "");
        graph.addEdge("B", "E", "5", "");

        graph.addEdge("C", "D", "6", "");
        graph.addEdge("C", "E", "7", "");

        vertex = graph.getVertex("A");
        assertEquals(3, vertex.getSize());

        vertex = graph.getVertex("B");
        assertEquals(2, vertex.getSize());

        vertex = graph.getVertex("C");
        assertEquals(2, vertex.getSize());

        vertex = graph.getVertex("D");
        assertEquals(0, vertex.getSize());

        vertex = graph.getVertex("E");
        assertEquals(0, vertex.getSize());
    }
}
