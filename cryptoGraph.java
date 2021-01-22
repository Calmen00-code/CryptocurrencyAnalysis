/*
Program: cryptoGraph.java
Author: Calmen Chia Kai Fong
Student ID: 20072144
Created Date: 25 September 2020
Last Modified: 25 September 2020
Purpose: cryptoGraph for DSA Assignment 
Test Class: UnitTestCryptoGraph.java
*/

import java.io.*;
import java.util.*;

public class cryptoGraph
{
    public static final int EXIT = 9;
    public static final int TEN = 10;
    public static final int TOTAL_ASSET = 305;
    public static final int ASSET_DATA_TYPES = 12;

    public static void main(String[] args)
    {
        if( args[0].equals("-i") )
            interactiveMode();
        else if( args[0].equals("-r") )
        {   
            if( args.length > 2 )
                reportMode( args[1], args[2] );
            else
                System.out.println("Please refer to README.txt and enter proper mode");
        }
        else
            System.out.println("Please refer to README.txt and enter proper mode");
    }

    // SUBMODULE: interactiveMode
    // IMPORT: none
    // EXPORT: none
    // ASSERTION: Showing menu for interactive mode
    public static void interactiveMode()
    {
        Scanner sc = new Scanner(System.in);
        DSAGraph graph = null;
        Object[] baseArr = null;
        Object[] quoteArr = null;
        Object[] edgeArr = null;
        Object[] tradeArr = null;
        DSABinarySearchTree assetInfo = null;
        int loadMode;
        int opt;

        do
        {
            System.out.println("1. Load data");
            System.out.println("2. Find and display asset");
            System.out.println("3. Find and display trade details");
            System.out.println("4. Find and display potential trade paths");
            System.out.println("5. Set asset filter");
            System.out.println("6. Asset overview");
            System.out.println("7. Trade overview");
            System.out.println("8. Save data (serialised)");
            System.out.println("9. Exit");
            opt = 0;
            opt = UserInterface.userInput("Option: ", 1, 9);
            switch( opt )
            {
                case 1: // Case 1: Load Data
                    loadMode = 0;
                    System.out.println();
                    System.out.println("1. Asset data");
                    System.out.println("2. Trade data");
                    System.out.println("3. Serialised data");
                    loadMode = UserInterface.userInput("Option: ", 1, 3);
                    if( loadMode == 1 )
                    {
                        if( baseArr == null && quoteArr == null && 
                            edgeArr == null && graph == null )
                        {
                            // all Base Assets data obtained
                            baseArr = ParseJSON.readBaseAsset("assetFile.json"); 
                            // all Quote Assets data obtained
                            quoteArr = ParseJSON.readQuoteAsset("assetFile.json");
                            // all data for asset info is stored
                            assetInfo = readAssetInfo(); 

                            // edgeAsset is obtained
                            edgeArr = ParseJSON.readEdge("assetFile.json");    
                            graph = storeGraph( baseArr, quoteArr, edgeArr, assetInfo );
                            System.out.println("Asset data is loaded");
                        }
                        else
                            System.out.println("Asset data is ALREADY loaded");
                    }
                    else if( loadMode == 2 )
                    {
                        if( tradeArr == null )
                        {
                            tradeArr = ParseJSON.readTrade("Trade.json");
                            System.out.println("Trade data is loaded");
                        }
                        else
                            System.out.println("Trade data is ALREADY loaded");
                    }
                    else
                    {
                        try{ graph = Serialisation.load( "graph.dat" ); }
                        catch(IllegalArgumentException e) { System.out.println(e.getMessage()); }
                    }
                break;

                case 2: // Case 2: Find and display asset
                    try {
                        String assetLabel = UserInterface.userInput("Asset: ");
                        assetLabel = toUpper(assetLabel); // Since all are capital
                        DSAGraphVertex assetVertex = graph.getVertex(assetLabel);
                        System.out.println(assetVertex.getValue());
                    } catch(NullPointerException e) {
                        System.out.print("Please ensure the data is complete! ");
                        System.out.println("(Asset & Trade data are loaded)");
                    } catch(IllegalArgumentException e) {
                        System.out.println("Asset does not exist!");
                    }
                break;

                case 3: // Case 3: Find and Display trade
                    Trade trade = null;
                    int idx = 0;
                    if( tradeArr == null )
                        System.out.println("Please ensure you have loaded the trade data");
                    else
                    {
                        String tradeLabel = UserInterface.userInput("Trade: ");
                        tradeLabel = toUpper(tradeLabel); // Since all are capital
                        idx = findTrade(tradeLabel, tradeArr);
                        if( idx != -1 )
                        {
                            trade = (Trade) tradeArr[idx];
                            trade.output();
                        }
                        else
                            System.out.println("Trade does not exist!");
                    }
                break;

                case 4: // Case 4: Find and Display potential paths
                    try {
                        String src = UserInterface.userInput("Base Asset: ");
                        String dest = UserInterface.userInput("Quote Asset: ");
                        src = toUpper( src );
                        dest = toUpper( dest );
                        displayPotentialPath( src, dest, graph );
                    } catch(IllegalArgumentException e) {
                            System.out.println("Asset does not exist!");
                    }
                break;

                case 5: // Set asset filter 
                    // Graph is only complete when assetArr and edgeArr is obtained
                    if( baseArr != null && quoteArr == null && edgeArr == null )
                        System.out.println("Load Trade data to complete the data");
                    else if( baseArr != null && quoteArr == null && edgeArr == null )
                        System.out.println("Load Asset data to complete the data");
                    else if( baseArr != null && quoteArr == null && edgeArr == null )
                        System.out.println("No data loaded yet");
                    else
                    {
                        System.out.println("1. Filter by including");
                        System.out.println("2. Filter by excluding");
                        int filterOpt = UserInterface.userInput("Option: ", 1, 2);
                        if( filterOpt == 1 )
                            includeFilter( graph, baseArr );
                        else
                            excludeFilter( graph, baseArr );
                    }
                break;

                case 6: // Case 6: Asset Overview
                    // Graph is only complete when assetArr and edgeArr is obtained
                    if( graph == null )
                        System.out.println("Data is empty! Please load it");
                    else
                    {
                        DSALinkedList theList = graph.getVertices();
                        DSAGraphVertex vertex = null;
                        String parentVertex = "";
                        String childVertex = "";

                        for( Object o : theList )
                        {
                            vertex = (DSAGraphVertex) o;
                            System.out.println(vertex.getLabel());
                            parentVertex = graph.getParentVertex( vertex );
                            System.out.println(parentVertex);
                            childVertex = graph.getChildVertex( vertex );
                            System.out.println(childVertex + "\n");
                        }
                    }
                break;

                case 7: // Case 7: Trade Overview
                    double[] priceArr = null;
                    double[] volumeArr = null;
                    long[] countArr = null;

                    if( tradeArr == null )
                        System.out.println("Please load trade data first");
                    else
                    {

                        priceArr = getPriceArr( tradeArr );
                        System.out.println("\nTop 10 Price");
                        for( int i = 0; i < TEN; i++ )
                            System.out.println(priceArr[i]);                

                        System.out.println();
                        volumeArr = getVolumeArr( tradeArr );
                        System.out.println("Top 10 Volume");
                        for( int i = 0; i < TEN; i++ )
                            System.out.println(volumeArr[i]);                

                        System.out.println();
                        countArr = getCountArr( tradeArr );
                        System.out.println("Top 10 Count");
                    
                        for( int i = 0; i < TEN; i++ )
                            System.out.println(countArr[i]);                
                    }
                break;

                case 8: // Case 8: Save data (Serialised)
                    try {
                        Serialisation.save( graph, "graph.dat" );
                        System.out.println("Data Saved");
                    } catch(IllegalArgumentException e) {
                        System.out.println();
                        System.out.println(e.getMessage());
                    }
                break;
            }
            System.out.println();
        } while( opt != EXIT ); 
    }

    // SUBMODULE: reportMode
    // IMPORT: assetFile (String), tradeFile (String)
    // EXPORT: none
    // ASSERTION: Showing display for report mode
    public static void reportMode( String assetFile, String tradeFile )
    {
        Object[] baseArr = ParseJSON.readBaseAsset(assetFile);
        Object[] quoteArr = ParseJSON.readQuoteAsset(assetFile);
        Object[] edgeArr = ParseJSON.readEdge(assetFile);
        Object[] tradeArr = ParseJSON.readTrade(tradeFile);
        double[] priceArr = getPriceArr(tradeArr);
        DSABinarySearchTree assetInfo = readAssetInfo(); 

        DSAGraph graph = null;

        graph = storeGraph( baseArr, quoteArr, edgeArr, assetInfo ); 

        System.out.println("Number of assets: " + graph.getCount());
        System.out.println("Number of connections between assets: " + graph.getEdgeCount());
        System.out.println("Highest Price: " + priceArr[0]);
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Below is the representation of each asset, and the asset they can be converted to\n\n");
        graph.displayAsList();
        System.out.println("-------------------------------------------------------------------------");
    }

    // SUBMODULE: readAssetInfo
    // IMPORT: none
    // EXPORT: assetInfo (DSABinarySearchTree)
    // ASSERTION: The array stores the object for each asset
    public static DSABinarySearchTree readAssetInfo()
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

            // The object is then stored in the array 
            assetInfo.insert(info.getSymbol(), info);
        }
        return assetInfo;
    }

    // SUBMODULE: storeGraph
    // IMPORT: baseArr (Object Array), quoteArr (Object Array), 
    //         edgeArr (Object Array), assetInfo (DSABinarySearchTree)
    // EXPORT: graph (DSAGraph)
    // ASSERTION: Add all symbol found in dataArr to the graph (Adding Vertex)
    public static DSAGraph storeGraph( Object[] baseArr, Object[] quoteArr, 
                                       Object[] edgeArr, DSABinarySearchTree assetInfo )
    {
        DSAGraph graph = new DSAGraph();
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

    // SUBMODULE: toUpper
    // IMPORT: str (String)
    // EXPORT: capitals (String)
    // ASSERTION: Converts str to Upper Case
    public static String toUpper( String str )
    {
        String capitals = "";
        for( int i = 0; i < str.length(); i++ )
        {
            // Convert each char to upper case
            if( str.charAt(i) >= 'a' && str.charAt(i) <= 'z' )
                capitals += (char)(str.charAt(i) - 32);   
            else
                capitals += (char)(str.charAt(i));
        }
        return capitals;
    }

    // SUBMODULE: findTrade
    // IMPORT: tradeLabel (String), tradeArr (Object Array)
    // EXPORT: idx (Integer)
    // ASSERTION: Find and return the index of trade object,
    //            If does not exist, then we will return -1
    public static int findTrade( String tradeLabel, Object[] tradeArr )
    {
        Trade trade = null;
        boolean found = false;
        int i = 0, idx = -1;

        while( (i < tradeArr.length) && !found )
        {
            trade = (Trade) tradeArr[i];

            // Check if the trade is found
            found = trade.getTradeLabel().equals(tradeLabel);
            // If we have found the location of where trade located
            if( found )
                idx = i;
            i++;
        }
        return idx;
    }
    
    // SUBMODULE: includeFilter
    // IMPORT: graph (DSAGraph), assetArr (Object Array)
    // EXPORT: none
    // ASSERTION: Menu for include filter option
    public static void includeFilter( DSAGraph graph, Object[] assetArr )
    {
        int num = UserInterface.userInput("Enter the numbers of asset to include: ", 1, TOTAL_ASSET);
        String[] filterLabels = new String[num];
        String asset = "";
        boolean stop = false;
        int i = 0;
        DSALinkedList theList = null;
        DSAGraphVertex vertex = null;

        do
        {
            do
            {
                asset = UserInterface.userInput("Asset: ");
                asset = toUpper( asset );
                if( graph.hasVertex(asset) )
                {
                    filterLabels[i] = asset;
                    stop = true;
                }
                else
                {
                    System.out.println("Asset does not exist\n");
                    stop = false;
                }
            } while( !stop );
            i++;
        } while( i < num );
        // ASSERTION: Input all asset to filterLabels

        theList = graph.getVertices();

        for( Object o : theList )
        {
            vertex = (DSAGraphVertex) o;

            // Ignore all except for the labels equals
            if( hasFilter( vertex.getLabel(), filterLabels ) )
                System.out.println( vertex.getValue() );
        }
    }

    // SUBMODULE: excludeFilter
    // IMPORT: graph (DSAGraph), assetArr (Object Array)
    // EXPORT: none
    // ASSERTION: Menu for include filter option
    public static void excludeFilter( DSAGraph graph, Object[] assetArr )
    {
        int num = UserInterface.userInput("Enter the numbers of asset to include: ", 1, TOTAL_ASSET);
        String[] filterLabels = new String[num];
        String asset = "";
        boolean stop = false;
        int i = 0;
        DSALinkedList theList = null;
        DSAGraphVertex vertex = null;

        do
        {
            do
            {
                asset = UserInterface.userInput("Asset: ");
                asset = toUpper( asset );
                if( graph.hasVertex(asset) )
                {
                    filterLabels[i] = asset;
                    stop = true;
                }
                else
                {
                    System.out.println("Asset does not exist\n");
                    stop = false;
                }
            } while( !stop );
            i++;
        } while( i < num );
        // ASSERTION: Input all asset to filterLabels

        theList = graph.getVertices();

        for( Object o : theList )
        {
            vertex = (DSAGraphVertex) o;

            // Ignore any label equals
            if( !hasFilter( vertex.getLabel(), filterLabels ) )
                System.out.println( vertex.getValue() );
        }
    }

    // SUBMODULE: hasFilter
    // IMPORT: label (String), filterLabels (String Array)
    // EXPORT: boolean
    // ASSERTION: Check if current filter is in the filter label or not
    private static boolean hasFilter( String label, String[] filterLabels )
    {
        int i = 0;
        boolean isFilter = false;

        while( i < filterLabels.length && !isFilter )
        {
            if( label.equals(filterLabels[i]) )
                isFilter = true;    // Stop the loop when we found the filter
            i++;
        }
        return isFilter;
    }

    // SUBMODULE: displayPotentialPath
    // IMPORT: src (String), dest (String), graph (DSAGraph)
    // EXPORT: none
    public static void displayPotentialPath( String src, String dest, DSAGraph graph )
    {
        DSALinkedList ll = null;
        DSAGraphVertex v = null;
        String str = "";

        ll = graph.getAdjacent( src );

        for( Object o : ll )
        {
            v = (DSAGraphVertex) o;
            graph.dfs( src, v.getLabel(), dest );
        }
    }

    // SUBMODULE: getPriceArr
    // IMPORT: tradeArr (Object Array)
    // EXPORT: priceArr (Double Array)
    // ASSERTION: Get all the price into the priceArr 
    public static double[] getPriceArr( Object[] tradeArr )
    {
        double[] priceArr = new double[ tradeArr.length ];

        for( int i = 0; i < tradeArr.length; i++ )
        {
            Trade t = (Trade) tradeArr[i];
            priceArr[i] = Double.parseDouble(t.getOpenPrice());
        }
        
        Sort.insertionSortDouble( priceArr );
        return priceArr;
    }

    // SUBMODULE: getVolumeArr
    // IMPORT: tradeArr (Object Array)
    // EXPORT: volumeArr (Double Array)
    // ASSERTION: Get all the price into the priceArr 
    public static double[] getVolumeArr( Object[] tradeArr )
    {
        double[] volumeArr = new double[ tradeArr.length ];

        for( int i = 0; i < tradeArr.length; i++ )
        {
            Trade t = (Trade) tradeArr[i];
            volumeArr[i] = Double.parseDouble(t.getVolume());
        }
        
        Sort.insertionSortDouble( volumeArr );
        return volumeArr;
    }

    // SUBMODULE: getCountArr
    // IMPORT: tradeArr (Object Array)
    // EXPORT: countArr (Integer Array)
    // ASSERTION: Get all the price into the priceArr 
    public static long[] getCountArr( Object[] tradeArr )
    {
        long[] countArr = new long[ tradeArr.length ];

        for( int i = 0; i < tradeArr.length; i++ )
        {
            Trade t = (Trade) tradeArr[i];
            countArr[i] = t.getCount();
        }
        
        Sort.insertionSortLong( countArr );
        return countArr;
    }
}
