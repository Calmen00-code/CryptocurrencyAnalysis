/*
Program: QuoteAsset.java
Author: Calmen Chia Kai Fong
Date: 26 October 2020
Last Modified: 26 October 2020
Purpose: Handling data for quote asset
Test Class: UnitTestAsset.java 
*/

import java.util.*;
import java.io.*;

public class QuoteAsset implements Serializable 
{
    private String quoteAsset;
    private int quoteAssetPrecision;
    private int quoteCommissionPrecision;

    public QuoteAsset( String inQuoteAsset, int inQuoteAssetPrecision, 
                       int inQuoteCommissionPrecision )
    {
        quoteAsset = inQuoteAsset;
        quoteAssetPrecision = inQuoteAssetPrecision;
        quoteCommissionPrecision = inQuoteCommissionPrecision;
    }

    public String getQuoteAsset()
    {
        return quoteAsset;
    }

    public int getQuoteAssetPrecision()
    {
        return quoteAssetPrecision;
    }

    public int getQuoteCommissionPrecision()
    {
        return quoteCommissionPrecision;
    }

    public String toStringQuote( DSABinarySearchTree assetInfo )
    {
        String str = "";
        AssetInfo info = null;

        info = findAssetInfo(assetInfo);

        // If info is NULL, then it means the asset does not exist
        if( info != null )
        {
            str = "\nAsset: " + quoteAsset + info.toString() + 
                  "\nAsset Precision: " + quoteAssetPrecision + 
                  "\nAsset Commission Precision: " + quoteCommissionPrecision + "\n";
        }
        return str;
    }

    // SUBMODULE: findAssetInfo
    // IMPORT: assetInfo (DSABinarySearchTree)
    // EXPORT: info (AssetInfo)
    // ASSERTION: info is NULL if baseAsset is not found in the hash table
    public AssetInfo findAssetInfo( DSABinarySearchTree assetInfo )
    {
        AssetInfo info = null;
        // If the baseAsset is existed, we need to get it and return it
        info = (AssetInfo) assetInfo.find( quoteAsset );

        return info;
    }

    // SUBMODULE: testToString
    // IMPORT: assetInfo (DSABinarySearchTree)
    // EXPORT: str (String)
    // ASSERTION: This toString is just for unit test purposes only
    public String testToString( DSABinarySearchTree assetInfo )
    {
        String str = "";
        AssetInfo info = null;

        info = findAssetInfo(assetInfo);
        if( info != null )
           str = info.toStringTest();
        return str;
    }
}
