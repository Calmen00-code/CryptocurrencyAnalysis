/*
Program: Filter.java
Author: Calmen Chia Kai Fong
Student ID: 20072144
Date Created: 26 September 2020
Last Modified: 26 September 2020
Purpose: Handling data for filter in each symbol
Requires: This will be called by Edge.java (Association)
Test Class: UnitTestEdge.java
*/

import org.json.JSONObject;
import java.io.*;
import java.util.*;

public class Filter implements Serializable
{
    private String filterType;
    private String minPrice;
    private String maxPrice;
    private String tickSize;
    private String multiplierUp;
    private String multiplierDown;
    private int avgPriceMins;
    private String minQty;
    private String maxQty;
    private String stepSize;
    private String minNotional;
    private boolean applyToMarket;
    private int limit;
    private int maxNumOrders;
    private int maxNumAlgoOrders;
    private String str;     // This is for toString purpose

    public Filter( String filterType, JSONObject filterObj )    
    {
        switch( filterType )
        {
            case "PRICE_FILTER":
                minPrice = (String) filterObj.get("minPrice");
                maxPrice = (String) filterObj.get("maxPrice");
                tickSize = (String) filterObj.get("tickSize");
                str = "\nFilter Type: " + filterType + "\nMin. Price: " + minPrice + 
                " Max. Price: " + maxPrice + " Tick Size: " + tickSize + "\n";
            break;
            case "PERCENT_PRICE":
                multiplierUp = (String) filterObj.get("multiplierUp");
                multiplierDown = (String) filterObj.get("multiplierDown");
                avgPriceMins = (int) filterObj.get("avgPriceMins");
                str = "\nFilter Type: " + filterType + "\nMultiplier Up: " + multiplierUp +
                "\t\tMultiplier Down: " + multiplierDown + "\t\tAverage Price Min.: " + 
                avgPriceMins + "\n";
            break;
            case "LOT_SIZE":
                minQty = (String) filterObj.get("minQty");
                maxQty = (String) filterObj.get("maxQty");
                stepSize = (String) filterObj.get("stepSize");
                str = "\nFilter Type: " + filterType + "\nMin. Quantity: " + minQty + 
                "\tMax. Quantity: " + maxQty + "\tStep Size: " + stepSize + "\n";
            break;
            case "MIN_NOTIONAL":
                minNotional = (String) filterObj.get("minNotional");
                applyToMarket = (boolean) filterObj.get("applyToMarket");
                avgPriceMins = (int) filterObj.get("avgPriceMins");
                str = "\nFilter Type: " + filterType + "\nMin. Notional: " + minNotional + 
                "\tApply To Market: " + applyToMarket + "\tAverage Price Min. : " +
                avgPriceMins + "\n";
            break;
            case "ICEBERG_PARTS":
                limit = (int) filterObj.get("limit");
                str = "\nFilter Type: " + filterType + "\nLimit: " + limit + "\n";
            break;
            case "MARKET_LOT_SIZE":
                minQty = (String) filterObj.get("minQty");
                maxQty = (String) filterObj.get("maxQty");
                stepSize = (String) filterObj.get("stepSize");
                str = "\nFilter Type: " + filterType + "\nMin. Quantity: " + minQty + 
                "\tMax. Quantity: " + maxQty + "\tStep Size: " + stepSize + "\n";
            break;
            case "MAX_NUM_ORDERS":
                maxNumOrders = (int) filterObj.get("maxNumOrders");
                str = "\nFilter Type: " + filterType + "\nMax. Number Order: " + 
                maxNumOrders + "\n";
            break;
            case "MAX_NUM_ALGO_ORDERS":
                maxNumAlgoOrders = (int) filterObj.get("maxNumAlgoOrders");
                str = "\nFilter Type: " + filterType + "\nMax. Number Algo Order: " + 
                maxNumAlgoOrders + "\n";
            break;
            // We do not need default as reading filterType from the file is always valid
        }
    }    

    public String toString()
    {
        return str;
    }

}
