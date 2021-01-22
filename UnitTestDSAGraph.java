/*
Program: UnitTestDSAGraphEdge.java
Author: Calmen Chia Kai Fong
Date: 16 October 2020
Purpose: Test Harness for DSAGraphEdge.java
*/

import org.junit.*;
import static org.junit.Assert.*;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONArray;
import java.io.*;

public class UnitTestDSAGraph
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
    public void testCreatingVertex()
    {
        graph.addVertex("A", 10);
        graph.addVertex("B", 20);
        graph.addVertex("C", 30);
        graph.addVertex("D", 40);
        graph.addVertex("E", 50);

        graph.displayAsList();        
        output = capOut.toString();
        assertEquals("A: \nB: \nC: \nD: \nE: \n", output);
    }

    @Test   
    public void testAddingEdge()
    {
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

        graph.displayAsList();
        output = capOut.toString();
        assertEquals("A: B D E \nB: C E \nC: D E \nD: \nE: \n", output);
    }

    @Test
    public void testAddingEdgeError()
    {
        try{ graph.addEdge("A", "A", "8", ""); }
        catch(IllegalArgumentException e){ assertEquals("Label cannot be the same", e.getMessage()); }

        try{ graph.addEdge("B", "B", "9", ""); }
        catch(IllegalArgumentException e){ assertEquals("Label cannot be the same", e.getMessage()); }

        try{ graph.addEdge("C", "C", "10", ""); }
        catch(IllegalArgumentException e){ assertEquals("Label cannot be the same", e.getMessage()); }

        try{ graph.addEdge("D", "D", "11", ""); }
        catch(IllegalArgumentException e){ assertEquals("Label cannot be the same", e.getMessage()); }

        try{ graph.addEdge("E", "E", "12", ""); }
        catch(IllegalArgumentException e){ assertEquals("Label cannot be the same", e.getMessage()); }

        try{ graph.addEdge("A", "Z", "13", ""); }
        catch(IllegalArgumentException e){ assertEquals("Label does not exist", e.getMessage()); }
        
        try{ graph.addEdge("Z", "A", "14", ""); }
        catch(IllegalArgumentException e){ assertEquals("Label does not exist", e.getMessage()); }

        try{ graph.addEdge("Z", "Z", "13", ""); }
        catch(IllegalArgumentException e){ assertEquals("Label cannot be the same", e.getMessage()); }
    }

    @Test
    public void testHasVertex()
    {
        testCreatingVertex();        
        
        assertEquals(false, graph.hasVertex("10"));
        assertEquals(false, graph.hasVertex("Z"));
        assertEquals(true, graph.hasVertex("A"));
        assertEquals(true, graph.hasVertex("B"));
        assertEquals(true, graph.hasVertex("C"));
        assertEquals(true, graph.hasVertex("D"));
        assertEquals(true, graph.hasVertex("E"));
    }

    @Test
    public void testHasEdge()
    {
        graph.addVertex("A", 10);
        graph.addVertex("B", 20);
        graph.addVertex("C", 30);
        graph.addVertex("D", 40);
        graph.addVertex("E", 50);

        graph.addEdge("A", "B", "AB", "");
        graph.addEdge("A", "D", "AD", "");
        graph.addEdge("A", "E", "AE", "");

        graph.addEdge("B", "C", "BC", "");
        graph.addEdge("B", "E", "BE", "");

        graph.addEdge("C", "D", "CD", "");
        graph.addEdge("C", "E", "CE", "");


        assertEquals(true, graph.hasEdge("AB"));
        assertEquals(true, graph.hasEdge("AD"));
        assertEquals(true, graph.hasEdge("AE"));
        assertEquals(true, graph.hasEdge("BC"));
        assertEquals(true, graph.hasEdge("BE"));
        assertEquals(true, graph.hasEdge("CD"));
        assertEquals(true, graph.hasEdge("CE"));


        assertEquals(false, graph.hasEdge("BA"));
        assertEquals(false, graph.hasEdge("DA"));
        assertEquals(false, graph.hasEdge("EA"));
        assertEquals(false, graph.hasEdge("CB"));
        assertEquals(false, graph.hasEdge("EB"));
        assertEquals(false, graph.hasEdge("DC"));
        assertEquals(false, graph.hasEdge("EC"));
    }
    
    @Test
    public void testGetVertexCount()
    {
        testCreatingVertex();
        assertEquals(5, graph.getVertexCount());
    }

    @Test   
    public void testGetEdgeCount()
    {
        graph.addVertex("A", 10);
        graph.addVertex("B", 20);
        graph.addVertex("C", 30);
        graph.addVertex("D", 40);
        graph.addVertex("E", 50);

        graph.addEdge("A", "B", "1", "AB");
        graph.addEdge("A", "D", "2", "AD");
        graph.addEdge("A", "E", "3", "AE");

        graph.addEdge("B", "C", "4", "BC");
        graph.addEdge("B", "E", "5", "BE");

        graph.addEdge("C", "D", "6", "CD");
        graph.addEdge("C", "E", "7", "CE");

        assertEquals(7, graph.getEdgeCount());
    } 

    @Test
    public void testGetVertex()
    {
        testCreatingVertex();
        
        assertEquals(10, graph.getVertex("A").getValue());
        assertEquals(20, graph.getVertex("B").getValue());
        assertEquals(30, graph.getVertex("C").getValue());
        assertEquals(40, graph.getVertex("D").getValue());
        assertEquals(50, graph.getVertex("E").getValue());
    }

    @Test
    public void testGetVertexError()
    {
        testCreatingVertex();

        try { assertEquals(60, graph.getVertex("F").getValue()); }
        catch(IllegalArgumentException e) { assertEquals("Label does not exist", e.getMessage()); }
    }

    @Test
    public void testVertexValue()
    {
        testCreatingVertex();
        
        assertEquals(10, graph.getVertex("A").getValue());
        assertEquals(20, graph.getVertex("B").getValue());
        assertEquals(30, graph.getVertex("C").getValue());
        assertEquals(40, graph.getVertex("D").getValue());
        assertEquals(50, graph.getVertex("E").getValue());
    }

    @Test
    public void testGetAdjacent()
    {
        DSALinkedList ll = null;

        graph.addVertex("A", 10);
        graph.addVertex("B", 20);
        graph.addVertex("C", 30);
        graph.addVertex("D", 40);
        graph.addVertex("E", 50);

        graph.addEdge("A", "B", "1", "AB");
        graph.addEdge("A", "D", "2", "AD");
        graph.addEdge("A", "E", "3", "AE");

        graph.addEdge("B", "C", "4", "BC");
        graph.addEdge("B", "E", "5", "BE");

        graph.addEdge("C", "D", "6", "CD");
        graph.addEdge("C", "E", "7", "CE");

        ll = graph.getAdjacent( "A" );
        assertEquals("20 40 50 ", displayLinkedList(ll));

        ll = graph.getAdjacent( "B" );
        assertEquals("30 50 ", displayLinkedList(ll));

        ll = graph.getAdjacent( "C" );
        assertEquals("40 50 ", displayLinkedList(ll));
    }

    @Test
    public void testGetEdge()
    {
        DSAGraphEdge edge = null;

        graph.addVertex("A", 10);
        graph.addVertex("B", 20);
        graph.addVertex("C", 30);
        graph.addVertex("D", 40);
        graph.addVertex("E", 50);

        graph.addEdge("A", "B", "AB", "10");
        graph.addEdge("A", "D", "AD", "20");
        graph.addEdge("A", "E", "AE", "30");

        graph.addEdge("B", "C", "BC", "40");
        graph.addEdge("B", "E", "BE", "50");

        graph.addEdge("C", "D", "CD", "60");
        graph.addEdge("C", "E", "CE", "70");

        edge = graph.getEdge("AB");
        assertEquals("10", edge.getSummary());
        edge = null;

        edge = graph.getEdge("AD");
        assertEquals("20", edge.getSummary());
        edge = null;

        edge = graph.getEdge("AE");
        assertEquals("30", edge.getSummary());
        edge = null;

        edge = graph.getEdge("BC");
        assertEquals("40", edge.getSummary());
        edge = null;

        edge = graph.getEdge("BE");
        assertEquals("50", edge.getSummary());
        edge = null;

        edge = graph.getEdge("CD");
        assertEquals("60", edge.getSummary());
        edge = null;

        edge = graph.getEdge("CE");
        assertEquals("70", edge.getSummary());
        edge = null;
    }

    public String displayLinkedList( DSALinkedList ll )
    {
        DSAGraphVertex vertex = null;
        String str = "";

        for( Object o : ll )
        {
            vertex = (DSAGraphVertex)o;
            str += vertex.getValue() + " ";
        }
        return str;
    }

    @Test
    public void testGraphEdgeValue()
    { 
        graph.addVertex("A", 10);
        graph.addVertex("B", 20);
        graph.addVertex("C", 30);
        graph.addVertex("D", 40);
        graph.addVertex("E", 50);

        graph.addEdge("A", "B", "1", " AB");
        graph.addEdge("A", "D", "2", " AD");
        graph.addEdge("A", "E", "3", " AE");

        graph.addEdge("B", "C", "4", " BC");
        graph.addEdge("B", "E", "5", " BE");

        graph.addEdge("C", "D", "6", " CD");
        graph.addEdge("C", "E", "7", " CE");

        assertEquals("\nA to B AB", graph.getEdge("1").toString());
        assertEquals("\nA to D AD", graph.getEdge("2").toString());  
        assertEquals("\nA to E AE", graph.getEdge("3").toString());
        assertEquals("\nB to C BC", graph.getEdge("4").toString());
        assertEquals("\nB to E BE", graph.getEdge("5").toString()); 
        assertEquals("\nC to D CD", graph.getEdge("6").toString());
        assertEquals("\nC to E CE", graph.getEdge("7").toString());
    }

    @Test
    public void testGetParentVertex()
    {
        DSAGraphVertex vertex = null;

        graph.addVertex("A", 10);
        graph.addVertex("B", 20);
        graph.addVertex("C", 30);
        graph.addVertex("D", 40);
        graph.addVertex("E", 50);

        graph.addEdge("A", "B", "1", " AB");
        graph.addEdge("A", "D", "2", " AD");
        graph.addEdge("A", "E", "3", " AE");

        graph.addEdge("B", "C", "4", " BC");
        graph.addEdge("B", "E", "5", " BE");

        graph.addEdge("C", "D", "6", " CD");
        graph.addEdge("C", "E", "7", " CE");

        vertex = graph.getVertex("A");
        assertEquals("Can be converted from: none", graph.getParentVertex(vertex));

        vertex = graph.getVertex("B");
        assertEquals("Can be converted from: A ", graph.getParentVertex(vertex));

        vertex = graph.getVertex("C");
        assertEquals("Can be converted from: B ", graph.getParentVertex(vertex));

        vertex = graph.getVertex("D");
        assertEquals("Can be converted from: A C ", graph.getParentVertex(vertex));

        vertex = graph.getVertex("E");
        assertEquals("Can be converted from: A B C ", graph.getParentVertex(vertex));
    }

    @Test
    public void testGetChildVertex()
    {
        DSAGraphVertex vertex = null;

        graph.addVertex("A", 10);
        graph.addVertex("B", 20);
        graph.addVertex("C", 30);
        graph.addVertex("D", 40);
        graph.addVertex("E", 50);

        graph.addEdge("A", "B", "1", " AB");
        graph.addEdge("A", "D", "2", " AD");
        graph.addEdge("A", "E", "3", " AE");

        graph.addEdge("B", "C", "4", " BC");
        graph.addEdge("B", "E", "5", " BE");

        graph.addEdge("C", "D", "6", " CD");
        graph.addEdge("C", "E", "7", " CE");

        vertex = graph.getVertex("A");
        assertEquals("Can be converted to: B D E ", graph.getChildVertex(vertex));

        vertex = graph.getVertex("B");
        assertEquals("Can be converted to: C E ", graph.getChildVertex(vertex));

        vertex = graph.getVertex("C");
        assertEquals("Can be converted to: D E ", graph.getChildVertex(vertex));
 
        vertex = graph.getVertex("D");
        assertEquals("Can be converted to: none", graph.getChildVertex(vertex));
 
        vertex = graph.getVertex("E");
        assertEquals("Can be converted to: none", graph.getChildVertex(vertex));
    } 

    @Test
    public void testVisited()
    {
        testCreatingVertex();
        DSALinkedList vertices = null;
        DSAGraphVertex vertex = null;

        graph.addEdge("A", "B", "1", "AB");
        graph.addEdge("A", "D", "2", "AD");
        graph.addEdge("A", "E", "3", "AE");

        graph.addEdge("B", "C", "4", "BC");
        graph.addEdge("B", "E", "5", "BE");

        graph.addEdge("C", "D", "6", "CD");
        graph.addEdge("C", "E", "7", "CE");

        vertices = graph.getVertices();
        
        for( Object o : vertices )
        {
            vertex = (DSAGraphVertex)o;
            assertEquals(false, vertex.getVisited());
        }

        for( Object o : vertices )
        {
            vertex = (DSAGraphVertex)o;
            vertex.setVisited(true);
        }

        for( Object o : vertices )
        {
            vertex = (DSAGraphVertex)o;
            assertEquals(true, vertex.getVisited());
        }        
    }

    @Test
    public void testGetCount()
    {
        graph.addVertex("A", 10);
        graph.addVertex("B", 20);
        graph.addVertex("C", 30);
        graph.addVertex("D", 40);
        graph.addVertex("E", 50);
        
        graph.addEdge("A", "B", "1", "AB");
        graph.addEdge("A", "D", "2", "AD");
        graph.addEdge("A", "E", "3", "AE");

        graph.addEdge("B", "C", "4", "BC");
        graph.addEdge("B", "E", "5", "BE");

        graph.addEdge("C", "D", "6", "CD");
        graph.addEdge("C", "E", "7", "CE");

        assertEquals(5, graph.getCount());
    }

    @Test
    public void testIsAdjacent()
    {
        graph.addVertex("A", 10);
        graph.addVertex("B", 20);
        graph.addVertex("C", 30);
        graph.addVertex("D", 40);
        graph.addVertex("E", 50);
        
        graph.addEdge("A", "B", "1", "AB");
        graph.addEdge("A", "D", "2", "AD");
        graph.addEdge("A", "E", "3", "AE");

        graph.addEdge("B", "C", "4", "BC");
        graph.addEdge("B", "E", "5", "BE");

        graph.addEdge("C", "D", "6", "CD");
        graph.addEdge("C", "E", "7", "CE");

        
        assertEquals( true, graph.isAdjacent("A", "B") );
        assertEquals( true, graph.isAdjacent("A", "D") );
        assertEquals( true, graph.isAdjacent("A", "E") );
        assertEquals( false, graph.isAdjacent("B", "A") );
        assertEquals( false, graph.isAdjacent("D", "A") );
        assertEquals( false, graph.isAdjacent("C", "A") );

        assertEquals( true, graph.isAdjacent("B", "C") );
        assertEquals( true, graph.isAdjacent("B", "E") );
        assertEquals( false, graph.isAdjacent("C", "B") );
        assertEquals( false, graph.isAdjacent("E", "B") );

        assertEquals( true, graph.isAdjacent("C", "D") );
        assertEquals( true, graph.isAdjacent("C", "E") );
        assertEquals( false, graph.isAdjacent("D", "C") );
        assertEquals( false, graph.isAdjacent("E", "C") );

// ------------------------------------------------------------------
        assertEquals( false, graph.isAdjacent("E", "A") );
        assertEquals( false, graph.isAdjacent("A", "C") );
        assertEquals( false, graph.isAdjacent("B", "D") );
        assertEquals( false, graph.isAdjacent("D", "E") );
        assertEquals( false, graph.isAdjacent("D", "B") );
    }  

    @Test
    public void testDisplayAsList()
    { 
        graph.addVertex("A", 10);
        graph.addVertex("B", 20);
        graph.addVertex("C", 30);
        graph.addVertex("D", 40);
        graph.addVertex("E", 50);

        graph.addEdge("A", "B", "1", "AB");
        graph.addEdge("A", "D", "2", "AD");
        graph.addEdge("A", "E", "3", "AE");

        graph.addEdge("B", "C", "4", "BC");
        graph.addEdge("B", "E", "5", "BE");

        graph.addEdge("C", "D", "6", "CD");
        graph.addEdge("C", "E", "7", "CE");

        graph.displayAsList();
        output = capOut.toString();
        assertEquals("A: B D E \nB: C E \nC: D E \nD: \nE: \n", output);
    }

    @Test
    public void testGraphEdgeLabel()
    {
        graph.addVertex("A", 10);
        graph.addVertex("B", 20);
        graph.addVertex("C", 30);
        graph.addVertex("D", 40);
        graph.addVertex("E", 50);

        graph.addEdge("A", "B", "1", "AB");
        graph.addEdge("A", "D", "2", "AD");
        graph.addEdge("A", "E", "3", "AE");

        graph.addEdge("B", "C", "4", "BC");
        graph.addEdge("B", "E", "5", "BE");

        graph.addEdge("C", "D", "6", "CD");
        graph.addEdge("C", "E", "7", "CE");

        assertEquals("1", graph.getEdge("1").getLabel());
        assertEquals("2", graph.getEdge("2").getLabel());  
        assertEquals("3", graph.getEdge("3").getLabel());
        assertEquals("4", graph.getEdge("4").getLabel());
        assertEquals("5", graph.getEdge("5").getLabel()); 
        assertEquals("6", graph.getEdge("6").getLabel());
        assertEquals("7", graph.getEdge("7").getLabel());
    }
       
    @Test
    public void testGraphEdgeToString()
    {
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

        assertEquals("\nA to B", graph.getEdge("1").toString());
        assertEquals("\nA to D", graph.getEdge("2").toString());
        assertEquals("\nA to E", graph.getEdge("3").toString());
        assertEquals("\nB to C", graph.getEdge("4").toString());
        assertEquals("\nB to E", graph.getEdge("5").toString());
        assertEquals("\nC to D", graph.getEdge("6").toString());
        assertEquals("\nC to E", graph.getEdge("7").toString());
    }

    @Test
    public void testCount()
    {
        graph.addVertex("A", 10);
        graph.addVertex("B", 20);
        graph.addVertex("C", 30);
        graph.addVertex("D", 40);
        graph.addVertex("E", 50);
 
        assertEquals(5, graph.getCount());
    }

    @Test
    public void testCountEmpty()
    {
        assertEquals(0, graph.getCount());
    }

    @Test
    public void testDFSStartEnd()
    {
        graph.addVertex("A", 25);
        graph.addVertex("B", 60);
        graph.addVertex("C", 45);
        graph.addVertex("D", 75);
        graph.addVertex("E", 95);
        graph.addVertex("F", 85);
        graph.addVertex("T", 115);
        graph.addVertex("G", 105);

        graph.addEdge("A", "B", "AB", "");
        graph.addEdge("A", "D", "AD", "");
        graph.addEdge("A", "C", "AC", "");
        graph.addEdge("A", "E", "AE", "");
        graph.addEdge("B", "E", "BE", "");
        graph.addEdge("E", "F", "EF", "");
        graph.addEdge("E", "G", "EG", "");
        graph.addEdge("D", "C", "DC", "");
        graph.addEdge("D", "F", "DF", "");
        graph.addEdge("F", "T", "FT", "");
        graph.addEdge("F", "G", "FG", "");
        graph.addEdge("T", "G", "TG", "");
        graph.addEdge("C", "F", "CF", ""); 

        String startLabel = "A";
        String endLabel = "G";

        DSALinkedList ll = graph.getAdjacent(startLabel);
        DSAGraphVertex v = null;
        String str = "";

        for( Object o : ll )
        {
            v = (DSAGraphVertex) o;

            graph.dfs(startLabel, v.getLabel(), endLabel);
        }

        output = capOut.toString();
        assertEquals("A -> B -> E -> F -> T -> G\nA -> B -> E -> F -> G\nA -> B -> E -> G\nA -> D -> C -> F -> T -> G\nA -> D -> C -> F -> G\nA -> C -> F -> T -> G\nA -> C -> F -> G\nA -> E -> F -> T -> G\nA -> E -> F -> G\nA -> E -> G\n", output);
    }

    // Start at begginning end at middle
    @Test
    public void testDFSStartMiddle()
    {
        graph.addVertex("A", 25);
        graph.addVertex("B", 60);
        graph.addVertex("C", 45);
        graph.addVertex("D", 75);
        graph.addVertex("E", 95);
        graph.addVertex("F", 85);
        graph.addVertex("T", 115);
        graph.addVertex("G", 105);

        graph.addEdge("A", "B", "AB", "");
        graph.addEdge("A", "D", "AD", "");
        graph.addEdge("A", "C", "AC", "");
        graph.addEdge("A", "E", "AE", "");
        graph.addEdge("B", "E", "BE", "");
        graph.addEdge("E", "F", "EF", "");
        graph.addEdge("E", "G", "EG", "");
        graph.addEdge("D", "C", "DC", "");
        graph.addEdge("D", "F", "DF", "");
        graph.addEdge("F", "T", "FT", "");
        graph.addEdge("F", "G", "FG", "");
        graph.addEdge("T", "G", "TG", "");
        graph.addEdge("C", "F", "CF", ""); 

        String startLabel = "A";
        String endLabel = "E";

        DSALinkedList ll = graph.getAdjacent(startLabel);
        DSAGraphVertex v = null;
        String str = "";

        for( Object o : ll )
        {
            v = (DSAGraphVertex) o;

            graph.dfs(startLabel, v.getLabel(), endLabel);
        }

        output = capOut.toString();
        assertEquals("A -> B -> E\nA -> E\n", output);
    }

    // Start at middle and end at middle
    @Test
    public void testDFSMiddleMiddle()
    {
        graph.addVertex("A", 25);
        graph.addVertex("B", 60);
        graph.addVertex("C", 45);
        graph.addVertex("D", 75);
        graph.addVertex("E", 95);
        graph.addVertex("F", 85);
        graph.addVertex("T", 115);
        graph.addVertex("G", 105);

        graph.addEdge("A", "B", "AB", "");
        graph.addEdge("A", "D", "AD", "");
        graph.addEdge("A", "C", "AC", "");
        graph.addEdge("A", "E", "AE", "");
        graph.addEdge("B", "E", "BE", "");
        graph.addEdge("E", "F", "EF", "");
        graph.addEdge("E", "G", "EG", "");
        graph.addEdge("D", "C", "DC", "");
        graph.addEdge("D", "F", "DF", "");
        graph.addEdge("F", "T", "FT", "");
        graph.addEdge("F", "G", "FG", "");
        graph.addEdge("T", "G", "TG", "");
        graph.addEdge("C", "F", "CF", ""); 

        String startLabel = "D";
        String endLabel = "T";

        DSALinkedList ll = graph.getAdjacent(startLabel);
        DSAGraphVertex v = null;
        String str = "";

        for( Object o : ll )
        {
            v = (DSAGraphVertex) o;

            graph.dfs(startLabel, v.getLabel(), endLabel);
        }

        output = capOut.toString();
        assertEquals("D -> C -> F -> T\nD -> F -> T\n", output);
    }
// 26 Tests
}
