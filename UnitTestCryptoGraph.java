/*
Program: UnitTest.java
Author: Calmen Chia Kai Fong
Date Created: 08 October 2020
Last Modified: 08 October 2020
Purpose: Test Class for cryptoGraph.java
*/

import org.junit.*;
import static org.hamcrest.CoreMatchers.*;  // Allow assertThat
import static org.junit.Assert.*;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONArray;
import java.io.*;
import java.util.*;

public class UnitTestCryptoGraph
{
    public static final int ASSET_DATA_TYPES = 12;
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
    public void testReadAssetInfo()
    {
        DSABinarySearchTree assetInfo = null;
        AssetInfo info = null;
        String[] assetData = FileIO.read("asset_info.csv");
        String[] splitData = null;
        String symbol = "";
        assetInfo = readFunc();

        for( int i = 0; i < assetData.length; i++ )
        {
            splitData = assetData[i].split(",");
            symbol = splitData[2];

            /* Checking if the asset symbol is found at the binary search tree */
            info = (AssetInfo) assetInfo.find(symbol);
            assertEquals(symbol, info.getSymbol()); 
        }
    }

    @Test
    public void testStoreGraph()
    {
        Object[] baseArr = ParseJSON.readBaseAsset("assetFile.json");
        Object[] quoteArr = ParseJSON.readQuoteAsset("assetFile.json");
        DSABinarySearchTree assetInfo = cryptoGraph.readAssetInfo();
        BaseAsset base = null;
        QuoteAsset quote = null;

        Object[] edgeArr = ParseJSON.readEdge("assetFile.json");
        Edge edge = null;

        DSAGraph graph = cryptoGraph.storeGraph( baseArr, quoteArr, edgeArr, assetInfo );

        for( int i = 0; i < baseArr.length; i++ )
        {
            base = (BaseAsset) baseArr[i];
            quote = (QuoteAsset) quoteArr[i];
            
            assertEquals( true, graph.hasVertex(base.getBaseAsset()));
            assertEquals( true, graph.hasVertex(quote.getQuoteAsset()));
        }

        for( int i = 0; i < edgeArr.length; i++ )
        {
            edge = (Edge) edgeArr[i];
            assertEquals( true, graph.hasEdge(edge.getLabel()));
        }
    }   

    @Test
    public void testToUpper()
    {
        String str = "";
        str = cryptoGraph.toUpper("abc");
        assertEquals(str, "ABC");

        str = cryptoGraph.toUpper("123");
        assertEquals(str, "123");
    }
   
    @Test
    public void testFindTrade()
    {
        Object[] tradeArr = ParseJSON.readTrade("Trade.json");
        assertEquals(-1, cryptoGraph.findTrade("cccAAA", tradeArr));
        assertEquals(-1, cryptoGraph.findTrade("CCCaaa", tradeArr));
        assertThat(-1, not(cryptoGraph.findTrade("ETHBTC", tradeArr)));
    }

    public static DSABinarySearchTree readFunc()
    {
        String[] arr = FileIO.read("asset_info.csv");
        String[] formatted = null;
        AssetInfo info = null;
        DSABinarySearchTree assetInfo = new DSABinarySearchTree();

        // Formatting the string as there are "xx,xx,xx,xx"
        for( int i = 0; i < arr.length; i++ )
        {
            arr[i] += ',';  // Add ',' to the end of the string for parsing

            // The formatting occurs here
            formatted = FileIO.format(arr[i], ',', ASSET_DATA_TYPES); 

            // Using the formatted array we could create the AssetInfo Object
            info = new AssetInfo( formatted[0], formatted[1], formatted[2], formatted[3], 
                                  formatted[4], formatted[5], formatted[6], formatted[7], 
                                  formatted[8], formatted[9], formatted[10], formatted[11] );

            assetInfo.insert(info.getSymbol(), info);
        }
        return assetInfo;
    }
}
