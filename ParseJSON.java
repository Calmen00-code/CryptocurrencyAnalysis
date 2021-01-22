/*
Program: ParseJSON.java
Author: From Mr. Terence Tan Peng Lian, rewrite by Calmen Chia Kai Fong
Student ID: 20072144
Date Created: 25 September 2020
Last Modified: 25 September 2020
Purpose: Parsing json file to retrieve the data
Test Class: UnitTestAsset.java
*/

import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONArray;
import java.io.*;

public class ParseJSON
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
    /* ** ** **     END of Fields for assetFile.json    ** ** ** */
     
    /* ** ** **     Fields for tradeFile.json   ** ** ** */
    private static String tradeLabel;
    private static String priceChange;
    private static String priceChangePercent;
    private static String weightedAvgPrice;
    private static String prevClosePrice;
    private static String lastPrice;
    private static String lastQty;
    private static String bidPrice;
    private static String bidQty;
    private static String askPrice;
    private static String askQty;
    private static String openPrice;
    private static String highPrice;
    private static String lowPrice;
    private static String volume;
    private static String quoteVolume;
    private static long openTime;
    private static long closeTime;
    private static int firstId;
    private static int lastId;
    private static int count;
    /* ** ** **     END of Fields for tradeFile.json    ** ** */

    // SUBMODULE: readBaseAsset
    // IMPORT: fileName (String)
    // EXPORT: baseArr (Object Array)
    // ASSERTION: Return the baseArr storing all base string
    public static Object[] readBaseAsset( String fileName )
    {
        Object[] baseArr = null;
        BaseAsset base = null;
        initialiseAsset();

        try
        {
            JSONTokener jsonToken = new JSONTokener(new FileReader(fileName));
            JSONObject obj = new JSONObject(jsonToken);
            JSONArray symbols = obj.getJSONArray("symbols");
            JSONObject symbol = null;
            baseArr = new Object[symbols.length()];

            for( int i = 0; i < symbols.length(); i++ )
            {
                symbol = (JSONObject) symbols.get(i);

                /* All Data Extraction here */
                baseAsset = (String) symbol.get("baseAsset");
                baseAssetPrecision = (int) symbol.get("baseAssetPrecision");
                baseCommissionPrecision = (int) symbol.get("baseCommissionPrecision");
                base = new BaseAsset( baseAsset, baseAssetPrecision, 
                                      baseCommissionPrecision );
                baseArr[i] = base;
            }
        }
        catch(Exception e)    
        {
            e.printStackTrace();
        }
        return baseArr;
    }

    // SUBMODULE: readQuoteAsset
    // IMPORT: fileName (String)
    // EXPORT: quoteArr (Object Array)
    public static Object[] readQuoteAsset( String fileName )
    {
        Object[] quoteArr = null;
        QuoteAsset quote = null;

        initialiseAsset();
        
        try
        {
            JSONTokener jsonToken = new JSONTokener(new FileReader(fileName));
            JSONObject obj = new JSONObject(jsonToken);
            JSONArray symbols = obj.getJSONArray("symbols");
            JSONObject symbol = null;
            quoteArr = new Object[symbols.length()];


            for( int i = 0; i < symbols.length(); i++ )
            {
                symbol = (JSONObject) symbols.get(i);

                /* All Data Extraction here */
                quoteAsset = (String) symbol.get("quoteAsset");
                quoteAssetPrecision = (int) symbol.get("quoteAssetPrecision");
                quoteCommissionPrecision = (int) symbol.get("quoteCommissionPrecision");
                quote = new QuoteAsset( quoteAsset, quoteAssetPrecision,
                                        quoteCommissionPrecision );
                quoteArr[i] = quote;
            }
        }
        catch(Exception e)    
        {
            e.printStackTrace();
        }
        return quoteArr;
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
                label = (String) symbol.get("symbol");
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

    // SUBMODULE: readTrade
    // IMPORT: fileName (String)
    // EXPORT: tradeArr (Object Array)
    public static Object[] readTrade( String fileName )
    {
        Object[] edgeArr = null;
        Trade trade = null;

        try
        {
            JSONTokener jsonToken = new JSONTokener(new FileReader(fileName));
            JSONArray symbols = new JSONArray(jsonToken);
            JSONObject symbol = null;
            edgeArr = new Object[symbols.length()];

            for( int i = 0; i < edgeArr.length; i++ )
            {
                symbol = (JSONObject) symbols.get(i);

                /* All Data Extraction here */
                tradeLabel = (String) symbol.get("symbol");
                priceChange = (String) symbol.get("priceChange");
                priceChangePercent = (String) symbol.get("priceChangePercent");
                weightedAvgPrice = (String) symbol.get("weightedAvgPrice");
                prevClosePrice = (String) symbol.get("prevClosePrice");
                lastPrice = (String) symbol.get("lastPrice");
                lastQty = (String) symbol.get("lastQty");
                bidPrice = (String) symbol.get("bidPrice");
                bidQty = (String) symbol.get("bidQty");
                askPrice = (String) symbol.get("askPrice");
                askQty = (String) symbol.get("askQty");
                openPrice = (String) symbol.get("openPrice");
                highPrice = (String) symbol.get("highPrice");
                lowPrice = (String) symbol.get("lowPrice");
                volume = (String) symbol.get("volume");
                quoteVolume = (String) symbol.get("quoteVolume");
                openTime = (long) symbol.get("openTime");
                closeTime = (long) symbol.get("closeTime");
                firstId = (int) symbol.get("firstId");
                lastId = (int) symbol.get("lastId");
                count = (int) symbol.get("count");

                trade = new Trade( tradeLabel, priceChange, priceChangePercent, 
                                   weightedAvgPrice, prevClosePrice, lastPrice, lastQty,
                                   bidPrice, bidQty, askPrice, askQty, openPrice, 
                                   highPrice, lowPrice, volume, quoteVolume, openTime,
                                   closeTime, firstId, lastId, count );

                edgeArr[i] = trade;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return edgeArr;
    }

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

    // SUBMODULE: intialiseEdge
    // IMPORT: none
    // EXPORT: none
    private static void intialiseEdge()
    {
        tradeLabel = "";
        priceChange = "";
        priceChangePercent = "";
        weightedAvgPrice = "";
        prevClosePrice = "";
        lastPrice = "";
        lastQty = "";
        bidPrice = "";
        bidQty = "";
        askPrice = "";
        askQty = "";
        openPrice = "";
        highPrice = "";
        lowPrice = "";
        volume = "";
        quoteVolume = "";
        openTime = 0;
        closeTime = 0;
        firstId = 0;
        lastId = 0;
        count = 0;
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
