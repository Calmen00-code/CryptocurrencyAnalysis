/*
Program: BaseAsset.java
Author: Calmen Chia Kai Fong
Student ID: 20072144
Date Created: 25 September 2020
Last Modified: 29 September 2020
Purpose: Handling data for base asset
Test Class: UnitTestAsset.java
*/

import java.util.*;
import java.io.*;

public class BaseAsset implements Serializable 
{
    private String baseAsset;
    private int baseAssetPrecision;
    private int baseCommissionPrecision;

    public BaseAsset( String inBaseAsset, int inBaseAssetPrecision, 
                      int inBaseCommissionPrecision )
    {
        baseAsset = inBaseAsset;
        baseAssetPrecision = inBaseAssetPrecision;
        baseCommissionPrecision = inBaseCommissionPrecision;
    }

    public String getBaseAsset()
    {
        return baseAsset;
    }
 
    public int getBaseAssetPrecision()
    {
        return baseAssetPrecision;
    }

    public int getBaseCommissionPrecision()
    {
        return baseCommissionPrecision;
    }

    public String toStringBase( DSABinarySearchTree assetInfo )
    {
        String str = "";
        AssetInfo info = null;

        // See if the current baseAsset label is in the hash 
        info = findAssetInfo(assetInfo);

        // If info is NULL, then it means the asset does not exist
        if( info != null )
        {
            str = "\nAsset: " + baseAsset + info.toString() + 
                  "\nAsset Precision: " + baseAssetPrecision + 
                  "\nAsset Commission Precision: " + baseCommissionPrecision + "\n"; 
        }
        return str;
    }

    // SUBMODULE: findAssetInfo
    // IMPORT: assetInfo (DSAHashTable)
    // EXPORT: info (AssetInfo)
    // ASSERTION: info is NULL if baseAsset is not found in the hash table
    public AssetInfo findAssetInfo( DSABinarySearchTree assetInfo )
    {
        AssetInfo info = null;
        // If the baseAsset is existed, we need to get it and return it
        info = (AssetInfo) assetInfo.find( baseAsset );

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
