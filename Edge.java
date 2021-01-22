/*
Program: Edge.java
Author: Calmen Chia Kai Fong
Student ID: 20072144
Date Created: 29 September 2020
Last Modified: 29 September 2020
Purpose: Handling all edge in a vertex
Requires: DSAGraphEdge.java (Association)
          cryptoGraph -> storeGraph method (Association)
Test Class: UnitTestEdge.java
*/

import java.util.*;
import java.io.*;

public class Edge implements Serializable
{
    private String label; 
    private String status;
    private int quotePrecision;
    private String[] orderTypes;    // JSONArray in ParseJSON.java
    private boolean icebergAllowed;
    private boolean ocoAllowed;
    private boolean quoteOrderQtyMarketAllowed;
    private boolean isSpotTradingAllowed;
    private boolean isMarginTradingAllowed;
    private Object[] filters;
    private String[] permissions;

    public Edge( String inLabel, String inStatus, int inQuotePrecision, 
                 String[] inOrderTypes, boolean inIcebergAllowed, boolean inOcoAllowed, 
                 boolean inQuoteOrderQtyMarketAllowed, boolean inIsSpotTradingAllowed, 
                 boolean inIsMarginTradingAllowed, Object[] inFilters, 
                 String[] inPermissions )
    {
        label = inLabel; 
        status = inStatus;
        quotePrecision = inQuotePrecision;
        orderTypes = inOrderTypes;
        icebergAllowed = inIcebergAllowed;
        ocoAllowed = inOcoAllowed;
        quoteOrderQtyMarketAllowed = inQuoteOrderQtyMarketAllowed;
        isSpotTradingAllowed = inIsSpotTradingAllowed; 
        isMarginTradingAllowed = inIsMarginTradingAllowed;
        filters = inFilters;
        permissions = inPermissions;
    }
    
    public String getLabel()
    {
        return label;
    }

    public String getStatus()
    {
        return status;
    }

    public int getQuotePrecision()
    {
        return quotePrecision;
    }

    public String[] getOrderTypes()
    {
        return orderTypes;
    }

    public String getOrderTypesStr()
    {
        String str = "";

        for( int i = 0; i < orderTypes.length; i++ )
        {
            if( i < orderTypes.length - 1 )
                str += orderTypes[i] + ", ";
            else
                str += orderTypes[i];
        }
        return str;
    }        

    public boolean getIcebergAllowed()
    {
        return icebergAllowed;
    }

    public boolean getOcoAllowed()
    {
        return ocoAllowed;
    }

    public boolean getQuoteOrderQtyMarketAllowed()
    {
        return quoteOrderQtyMarketAllowed;
    }

    public boolean getIsSpotTradingAllowed()
    {
        return isSpotTradingAllowed;
    }

    public boolean getIsMarginTradingAllowed()
    {
        return isMarginTradingAllowed;
    }

    public Object[] getFilters()
    {
        return filters;
    }

    public String getFiltersStr()
    {
        String str = "\n";

        for( int i = 0; i < filters.length; i++ )
            str += filters[i];
        return str;
    }

    public String[] getPermissions()
    {
        return permissions;
    }

    public String getPermissionsStr()
    {
        String str = "";

        for( int i = 0; i < permissions.length; i++ )
        {
            if( i < permissions.length - 1 )
                str += permissions[i] + ", ";
            else
                str += permissions[i];
        }
        return str;
    }

    public String toString()
    {
        String str = "\nLabel: " + label + "\nStatus: " + status + "\nQuotePrecision: " +
                     quotePrecision + "\nOrder Types: " + getOrderTypesStr() + 
                     "\nIce Berg Allowed: " + icebergAllowed + "\nOCO Allowed: " + 
                     ocoAllowed + "\nQuote Order Quantity Market Allowed: " + 
                     quoteOrderQtyMarketAllowed + "\nSpot Trading Allowed: " + 
                     isSpotTradingAllowed + "\nMargin Trading Allowed: " + 
                     isMarginTradingAllowed + "\nFilters: " + getFiltersStr() + 
                     "\nPermissions: " + getPermissionsStr();
        return str;
    }

}
