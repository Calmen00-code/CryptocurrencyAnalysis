/*
Program: UnitTestBaseAsset.java
Author: Calmen Chia Kai Fong
Date Created: 30 September 2020
Last Modified: 30 September 2020
Purpose: Test Harness for BaseAsset.java, QuoteAsset and ParseJSON.java
Requirement: Make sure to run test for BinarySearchTree, AssetInfo and CryptoGraph first
*/

import org.junit.Test;
import static org.junit.Assert.*;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONArray;
import java.io.*;

public class UnitTestAsset
{
    public static final int ASSET_DATA_TYPES = 12;

    @Test
    public void testReadAsset()
    {
        DSABinarySearchTree assetInfo = null;
        Object[] baseAssetArr = ParseJSON.readBaseAsset("assetFile.json");
        Object[] quoteAssetArr = ParseJSON.readQuoteAsset("assetFile.json");
        BaseAsset base = null;
        QuoteAsset quote = null;
        AssetInfo baseInfo = null;
        AssetInfo quoteInfo = null;
        String baseAsset = "";
        String quoteAsset = "";
        int baseAssetPrecision;
        int quoteAssetPrecision;
        int baseCommissionPrecision;
        int quoteCommissionPrecision;

        assetInfo = readFunc(); // Received the assetInfo

        try
        {
            JSONTokener jsonToken = new JSONTokener(new FileReader("assetFile.json"));
            JSONObject obj = new JSONObject(jsonToken);
            JSONArray symbols = obj.getJSONArray("symbols");
            JSONObject symbol = null;
            assetInfo = readFunc();     // Using binary tree to store all the csv data
    
            for( int i = 0; i < symbols.length(); i++ )
            {
                symbol = (JSONObject) symbols.get(i);

                /* Extract the data */
                baseAsset = (String) symbol.get("baseAsset");
                quoteAsset = (String) symbol.get("quoteAsset");
                baseAssetPrecision = (int) symbol.get("baseAssetPrecision");
                quoteAssetPrecision = (int) symbol.get("quoteAssetPrecision");
                baseCommissionPrecision = (int) symbol.get("baseCommissionPrecision");
                quoteCommissionPrecision = (int) symbol.get("quoteCommissionPrecision");

                base = (BaseAsset) baseAssetArr[i]; // Typecast it first 
                quote = (QuoteAsset) quoteAssetArr[i]; // Typecast it first

                /* Comparison */
                assertEquals(base.getBaseAsset(), baseAsset);
                assertEquals(quote.getQuoteAsset(), quoteAsset);
                assertEquals(base.getBaseAssetPrecision(), baseAssetPrecision);
                assertEquals(quote.getQuoteAssetPrecision(), quoteAssetPrecision);
                assertEquals(base.getBaseCommissionPrecision(), baseCommissionPrecision);
                assertEquals(quote.getQuoteCommissionPrecision(), quoteCommissionPrecision);

                /* Check if what we are calling from asset is the same as what we 
                   calling directly through binary search tree AssetInfo */
                baseInfo = (AssetInfo) assetInfo.find(baseAsset);
                quoteInfo = (AssetInfo) assetInfo.find(quoteAsset);
                
                if( baseInfo != null )  // NULL means the base is not existing asset
                    assertEquals(base.testToString(assetInfo), baseInfo.toStringTest());
                if( quoteInfo != null ) // NULL means the quote is not existing asset
                    assertEquals(quote.testToString(assetInfo), quoteInfo.toStringTest());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
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
