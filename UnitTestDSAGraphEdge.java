/*
Program: UnitTestGraphEdge.java
Author: Calmen Chia Kai Fong
Date Created: 12 October 2020
Last Modified: 12 October 2020
Purpose: Test Class for DSAGraphEdge.java
*/

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class UnitTestDSAGraphEdge
{
    DSAGraph graph = null;

    @Before
    public void setUp()
    {
        Object[] baseArr = ParseJSON.readBaseAsset("assetFile.json");
        Object[] quoteArr = ParseJSON.readQuoteAsset("assetFile.json");
        Object[] edgeArr = ParseJSON.readEdge("assetFile.json");

        graph = storeGraph( baseArr, quoteArr, edgeArr );
    }

    @After
    public void tearDown()
    {
        graph = null;
    }

    @Test
    public void testLabel()
    {
        // Get edge object array
        Object[] edgeArr = ParseJSON.readEdge("assetFile.json");
        Edge edge = null;

        // Compare each edge object array to check if they are stored in graph
        for( int i = 0; i < edgeArr.length; i++ )
        {
            edge = (Edge) edgeArr[i];
            assertEquals( true, graph.hasEdge( edge.getLabel() ) );
        }
    }

    @Test
    public void testFromTo()
    { 
        Object[] baseArr = ParseJSON.readBaseAsset("assetFile.json");
        Object[] quoteArr = ParseJSON.readQuoteAsset("assetFile.json");
        Object[] edgeArr = ParseJSON.readEdge("assetFile.json");
        BaseAsset base = null;
        QuoteAsset quote = null;
        DSAGraphEdge graphEdge = null;
        String baseLabel = "", quoteLabel = "";
        DSAGraphVertex from = null, to = null;

        for( int i = 0; i < baseArr.length; i++ )
        {
            base = (BaseAsset) baseArr[i];
            quote = (QuoteAsset) quoteArr[i];

            baseLabel = base.getBaseAsset(); 
            quoteLabel = quote.getQuoteAsset();
            graphEdge = graph.getEdge(baseLabel + quoteLabel);

            from = graphEdge.getFrom();
            to = graphEdge.getTo();

            assertEquals( from, graph.getVertex( baseLabel ) );
            assertEquals( to, graph.getVertex( quoteLabel ) );
        }
    }

    @Test
    public void testDirectedFalse()
    {
        DSAGraph newGraph = new DSAGraph();

        newGraph.addVertex("A", 1);
        newGraph.addVertex("B", 2);
        newGraph.addVertex("C", 3);
        newGraph.addVertex("D", 4);
        newGraph.addVertex("E", 5);

        newGraph.addEdge("A", "B", "1", "");
        newGraph.addEdge("A", "D", "2", "");
        newGraph.addEdge("A", "E", "3", "");

        newGraph.addEdge("B", "C", "4", "");
        newGraph.addEdge("B", "E", "5", "");

        newGraph.addEdge("C", "D", "6", "");
        newGraph.addEdge("C", "E", "7", "");

        newGraph.getEdge("1").setDirected(false);
        newGraph.getEdge("2").setDirected(false);
        newGraph.getEdge("3").setDirected(false);
        newGraph.getEdge("4").setDirected(false);
        newGraph.getEdge("5").setDirected(false);

        assertEquals(false, newGraph.getEdge("1").getDirected());
        assertEquals(false, newGraph.getEdge("2").getDirected());
        assertEquals(false, newGraph.getEdge("3").getDirected());
        assertEquals(false, newGraph.getEdge("4").getDirected());
        assertEquals(false, newGraph.getEdge("5").getDirected());
    }

    @Test
    public void testDirectedTrue()
    {
        DSAGraph newGraph = new DSAGraph();

        newGraph.addVertex("A", 1);
        newGraph.addVertex("B", 2);
        newGraph.addVertex("C", 3);
        newGraph.addVertex("D", 4);
        newGraph.addVertex("E", 5);

        newGraph.addEdge("A", "B", "1", "");
        newGraph.addEdge("A", "D", "2", "");
        newGraph.addEdge("A", "E", "3", "");

        newGraph.addEdge("B", "C", "4", "");
        newGraph.addEdge("B", "E", "5", "");

        newGraph.addEdge("C", "D", "6", "");
        newGraph.addEdge("C", "E", "7", "");

        newGraph.getEdge("1").setDirected(true);
        newGraph.getEdge("2").setDirected(true);
        newGraph.getEdge("3").setDirected(true);
        newGraph.getEdge("4").setDirected(true);
        newGraph.getEdge("5").setDirected(true);

        assertEquals(true, newGraph.getEdge("1").getDirected());
        assertEquals(true, newGraph.getEdge("2").getDirected());
        assertEquals(true, newGraph.getEdge("3").getDirected());
        assertEquals(true, newGraph.getEdge("4").getDirected());
        assertEquals(true, newGraph.getEdge("5").getDirected());
    }

    @Test
    public void testVisitedFalse()
    {
        DSAGraph newGraph = new DSAGraph();

        newGraph.addVertex("A", 1);
        newGraph.addVertex("B", 2);
        newGraph.addVertex("C", 3);
        newGraph.addVertex("D", 4);
        newGraph.addVertex("E", 5);

        newGraph.addEdge("A", "B", "1", "");
        newGraph.addEdge("A", "D", "2", "");
        newGraph.addEdge("A", "E", "3", "");

        newGraph.addEdge("B", "C", "4", "");
        newGraph.addEdge("B", "E", "5", "");

        newGraph.addEdge("C", "D", "6", "");
        newGraph.addEdge("C", "E", "7", "");

        newGraph.getEdge("1").setVisited(false);
        newGraph.getEdge("2").setVisited(false);
        newGraph.getEdge("3").setVisited(false);
        newGraph.getEdge("4").setVisited(false);
        newGraph.getEdge("5").setVisited(false);

        assertEquals(false, newGraph.getEdge("1").getVisited());
        assertEquals(false, newGraph.getEdge("2").getVisited());
        assertEquals(false, newGraph.getEdge("3").getVisited());
        assertEquals(false, newGraph.getEdge("4").getVisited());
        assertEquals(false, newGraph.getEdge("5").getVisited());
    }

    @Test
    public void testVisitedTrue()
    {
        DSAGraph newGraph = new DSAGraph();

        newGraph.addVertex("A", 1);
        newGraph.addVertex("B", 2);
        newGraph.addVertex("C", 3);
        newGraph.addVertex("D", 4);
        newGraph.addVertex("E", 5);

        newGraph.addEdge("A", "B", "1", "");
        newGraph.addEdge("A", "D", "2", "");
        newGraph.addEdge("A", "E", "3", "");

        newGraph.addEdge("B", "C", "4", "");
        newGraph.addEdge("B", "E", "5", "");

        newGraph.addEdge("C", "D", "6", "");
        newGraph.addEdge("C", "E", "7", "");

        newGraph.getEdge("1").setVisited(true);
        newGraph.getEdge("2").setVisited(true);
        newGraph.getEdge("3").setVisited(true);
        newGraph.getEdge("4").setVisited(true);
        newGraph.getEdge("5").setVisited(true);

        assertEquals(true, newGraph.getEdge("1").getVisited());
        assertEquals(true, newGraph.getEdge("2").getVisited());
        assertEquals(true, newGraph.getEdge("3").getVisited());
        assertEquals(true, newGraph.getEdge("4").getVisited());
        assertEquals(true, newGraph.getEdge("5").getVisited());
    }

    // SUBMODULE: storeGraph
    // IMPORT: baseArr (Object Array), quoteArr (Object Array), 
    //         edgeArr (Object Array)
    // EXPORT: graph (DSAGraph)
    // ASSERTION: Add all symbol found in dataArr to the graph (Adding Vertex)
    public static DSAGraph storeGraph( Object[] baseArr, Object[] quoteArr, 
                                       Object[] edgeArr )
    {
        DSAGraph graph = new DSAGraph();
        DSABinarySearchTree assetInfo = cryptoGraph.readAssetInfo();
        BaseAsset base = null;
        QuoteAsset quote = null;
        Edge edge = null;
        String baseAsset = "";
        String quoteAsset = "";

        // baseArr.length OR quoteArr.length is fine since they are same
        for( int i = 0; i < baseArr.length; i++ )
        {
            base = (BaseAsset) baseArr[i];
            quote = (QuoteAsset) quoteArr[i];
            edge = (Edge) edgeArr[i];

            /* * Extract base asset and quote asset * */
            baseAsset = base.getBaseAsset();
            quoteAsset = quote.getQuoteAsset();

            /* * Creating all asset in graph * */
            graph.addVertex(baseAsset, base.toStringBase(assetInfo));
            graph.addVertex(quoteAsset, quote.toStringQuote(assetInfo));

            /* * Linking assets in graph * */
            graph.addEdge(baseAsset, quoteAsset, edge.getLabel(), edge.toString());
        }
        return graph;
    }
// 6 Tests
}
