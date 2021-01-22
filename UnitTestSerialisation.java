/*
Program: UnitTestSerialisation.java
Author: Calmen Chia Kai Fong
Date: 08 October 2020
Purpose: Serialisation Unit Testing
*/

import org.junit.*;
import static org.junit.Assert.*;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONArray;
import java.io.*;

public class UnitTestSerialisation
{
    /* ** ** **     Fields for assetFile.json   ** ** ** */
    private static String label; // Not using symbol as it was used to represent symbols 
    private static String status;
    private static String baseAsset;
    private static int baseAssetPrecision;
    private static String quoteAsset;
    private static int quotePrecision;
    private static int quoteAssetPrecision;
    private static int baseCommissionPrecision;
    private static int quoteCommissionPrecision;
    private static JSONArray orderTypes;
    private static boolean icebergAllowed;
    private static boolean ocoAllowed;
    private static boolean quoteOrderQtyMarketAllowed;
    private static boolean isSpotTradingAllowed;
    private static boolean isMarginTradingAllowed;
    private static JSONArray filters;
    private static JSONArray permissions;

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
    public void testSave()
    {
        DSABinarySearchTree assetInfo = null;
        Object[] baseAssets = ParseJSON.readBaseAsset("assetFile.json");
        Object[] quoteAssets = ParseJSON.readQuoteAsset("assetFile.json");
        Object[] edges = ParseJSON.readEdge("assetFile.json");
        BaseAsset base = null;
        QuoteAsset quote = null;
        Edge edge = null;
        String baseAsset = "";
        String quoteAsset = "";

        assetInfo = cryptoGraph.readAssetInfo();

        // baseAssets.length OR quotAssets.length are same
        for( int i = 0; i < baseAssets.length; i++ )
        {
            base = (BaseAsset) baseAssets[i];
            quote = (QuoteAsset) quoteAssets[i];
            edge = (Edge) edges[i];

            /* * Extract base asset and quote asset * */
            baseAsset = base.getBaseAsset();
            quoteAsset = quote.getQuoteAsset();

            /* * Creating all asset in graph * */
            graph.addVertex(baseAsset, base.toStringBase(assetInfo));
            graph.addVertex(quoteAsset, quote.toStringQuote(assetInfo));

            /* * Linking assets in graph * */
            graph.addEdge(baseAsset, quoteAsset, edge.getLabel(), edge.toString());
        }

        Serialisation.save( graph, "graph.dat" );
    }     

    @Test
    public void testLoad()
    {
        graph = Serialisation.load( "graph.dat" );
        Object[] baseAssets = ParseJSON.readBaseAsset("assetFile.json");
        Object[] quoteAssets = ParseJSON.readQuoteAsset("assetFile.json");
        Object[] edges = ParseJSON.readEdge("assetFile.json");
        BaseAsset base = null;
        QuoteAsset quote = null;
        Edge edge = null;
        String baseAsset = "";
        String quoteAsset = "";
 
        // baseAssets.length OR quoteAssets.length are same
        for( int i = 0; i < baseAssets.length; i++ )
        {
            
            base = (BaseAsset) baseAssets[i];
            quote = (QuoteAsset) quoteAssets[i];
            edge = (Edge) edges[i];

            /* * Extract base asset and quote asset * */
            baseAsset = base.getBaseAsset();
            quoteAsset = quote.getQuoteAsset();

            // If the baseAsset and quoteAsset exist in the graph, then is PASSED
            assertEquals( true, graph.hasVertex(baseAsset) );
            assertEquals( true, graph.hasVertex(quoteAsset) );
        }
    }   
    
    // SUBMODULE: readAsset
    // IMPORT: fileName (String)
    // EXPORT: assetArr (Object Array)
    // ASSERTION: Return the baseArr storing all base string
/*    public static Object[] readAsset( String fileName )
    {
        Object[] dataArr = null;
        Asset asset = null;

        initialiseAsset();

        try
        {
            JSONTokener jsonToken = new JSONTokener(new FileReader(fileName));
            JSONObject obj = new JSONObject(jsonToken);
            JSONArray symbols = obj.getJSONArray("symbols");
            JSONObject symbol = null;
            String base = "";
            dataArr = new Object[symbols.length()];

            for( int i = 0; i < symbols.length(); i++ )
            {
                symbol = (JSONObject) symbols.get(i);

                /* All Data Extraction here */
/*                baseAsset = (String) symbol.get("baseAsset");
                quoteAsset = (String) symbol.get("quoteAsset");
                baseAssetPrecision = (int) symbol.get("baseAssetPrecision");
                quoteAssetPrecision = (int) symbol.get("quoteAssetPrecision");
                baseCommissionPrecision = (int) symbol.get("baseCommissionPrecision");
                quoteCommissionPrecision = (int) symbol.get("quoteCommissionPrecision");
                asset = new Asset( baseAsset, quoteAsset, baseAssetPrecision, 
                                   quoteAssetPrecision, baseCommissionPrecision,
                                   quoteCommissionPrecision );
                dataArr[i] = asset;
            }
        }
        catch(Exception e)    
        {
            e.printStackTrace();
        }
        return dataArr;
    }

    // SUBMODULE: readEdge
    // IMPORT: fileName (String)
    // EXPORT: edgeArr (Object Array)
    public static Object[] readEdge( String fileName )
    {
        Object[] edgeArr = null;
        String[] convertedOrderType = null;
        Object[] convertedFilters = null;
        String[] convertedPermissions = null;
        Edge edge = null;
 
        initialiseAsset();

        try
        {            
            JSONTokener jsonToken = new JSONTokener(new FileReader(fileName));
            JSONObject obj = new JSONObject(jsonToken);
            JSONArray symbols = obj.getJSONArray("symbols");
            JSONObject symbol = null;
            String base = "";
            edgeArr = new Object[symbols.length()];

            for( int i = 0; i < symbols.length(); i++ )
            {
                symbol = (JSONObject) symbols.get(i);

                /* Extraction of data here */
  /*              label = (String) symbol.get("symbol");
                status = (String) symbol.get("status");
                quotePrecision = (int) symbol.get("quotePrecision");
                orderTypes = symbol.getJSONArray("orderTypes");
                icebergAllowed = (boolean) symbol.get("icebergAllowed");
                ocoAllowed = (boolean) symbol.get("ocoAllowed");
                quoteOrderQtyMarketAllowed = (boolean) symbol.get("quoteOrderQtyMarketAllowed");
                isSpotTradingAllowed = (boolean) symbol.get("isSpotTradingAllowed");
                isMarginTradingAllowed = (boolean) symbol.get("isMarginTradingAllowed");
        
                filters = symbol.getJSONArray("filters");
                permissions = symbol.getJSONArray("permissions");
            
                convertedOrderType = toStrArray(orderTypes);
                convertedFilters = toObjArray(filters);
                convertedPermissions = toStrArray(permissions);

                edge = new Edge( label, status, quotePrecision,
                                 convertedOrderType, icebergAllowed, ocoAllowed, 
                                 quoteOrderQtyMarketAllowed, isSpotTradingAllowed, 
                                 isMarginTradingAllowed, convertedFilters, 
                                 convertedPermissions );
                edgeArr[i] = edge;
            }
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
        return edgeArr;
    }
*/
    // SUBMODULE: initialiseAsset
    // IMPORT: none
    // EXPORT: none
    private static void initialiseAsset()
    {
        label = ""; 
        status = "";
        baseAsset = "";
        baseAssetPrecision = 0;
        quoteAsset = "";
        quotePrecision = 0;
        quoteAssetPrecision = 0;
        baseCommissionPrecision = 0;
        quoteCommissionPrecision = 0;
        orderTypes = null;
        icebergAllowed = false;
        ocoAllowed = false;
        quoteOrderQtyMarketAllowed = false;
        isSpotTradingAllowed = false;
        isMarginTradingAllowed = false;
        JSONArray filters = null;
        permissions = null;
    }

    // SUBMODULE: toStrArray
    // IMPORT: inArr (JSONArray)
    // EXPORT: convertedArr (String Array)
    public static String[] toStrArray( JSONArray inArr )
    {
        String[] convertedArr = new String[inArr.length()];

        for( int i = 0; i < inArr.length(); i++ )
            convertedArr[i] = (String) inArr.get(i);
        return convertedArr;
    }

    // SUBMODULE: toObjArray
    // IMPORT: inArr (JSONArray)
    // EXPORT: convertedArr (Object Array)
    public static Object[] toObjArray( JSONArray inArr )
    {
        Object[] convertedArr = new Object[inArr.length()];
        Filter filter = null;
        JSONObject filterObj = null;
        String filterType = "";
        
        for( int i = 0; i < inArr.length(); i++ )
        {
            filterObj = (JSONObject) inArr.get(i);
            filterType = (String) filterObj.get("filterType");
            filter = new Filter( filterType, filterObj );
            convertedArr[i] = filter;
        }
        return convertedArr;
    }
}
