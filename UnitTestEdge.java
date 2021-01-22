/*
Program: UnitTestEdge.java
Author: Calmen Chia Kai Fong
Date Created: 30 September 2020
Last Modified: 01 October 2020
Purpose: Test Harness for Edge.java, Filter.java and ParseJSON.java
*/

import org.junit.*;
import static org.junit.Assert.*;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONArray;
import java.io.*;

public class UnitTestEdge
{
    @Test
    public void testReadEdge()
    {
        UnitTestEdge test = new UnitTestEdge();

        Object[] edgeArr = ParseJSON.readEdge("assetFile.json");
        Edge edge = null;
        String label; 
        String status;
        int quotePrecision;
        JSONArray orderTypes;    // JSONArray in ParseJSON.java
        String orderTypesStr;
        boolean icebergAllowed;
        boolean ocoAllowed;
        boolean quoteOrderQtyMarketAllowed;
        boolean isSpotTradingAllowed;
        boolean isMarginTradingAllowed;
        JSONArray filters;
        String filtersStr;
        JSONArray permissions;
        String permissionsStr;

        try
        {
            JSONTokener jsonToken = new JSONTokener(new FileReader("assetFile.json"));
            JSONObject obj = new JSONObject(jsonToken);
            JSONArray symbols = obj.getJSONArray("symbols");
            JSONObject symbol = null;
    
            for( int i = 0; i < symbols.length(); i++ )
            {
                symbol = (JSONObject) symbols.get(i);

                /* Extract the data */
                label = (String) symbol.get("symbol");
                status = (String) symbol.get("status");
                quotePrecision = (int) symbol.get("quotePrecision");
                orderTypes = symbol.getJSONArray("orderTypes");
                orderTypesStr = test.getStr(orderTypes);
                
                icebergAllowed = (boolean) symbol.get("icebergAllowed");
                ocoAllowed = (boolean) symbol.get("ocoAllowed");
                quoteOrderQtyMarketAllowed = (boolean) symbol.get("quoteOrderQtyMarketAllowed");
                isSpotTradingAllowed = (boolean) symbol.get("isSpotTradingAllowed");
                isMarginTradingAllowed = (boolean) symbol.get("isMarginTradingAllowed");
                filters = symbol.getJSONArray("filters");
                filtersStr = test.getStrFilters(label, filters);

                permissions = symbol.getJSONArray("permissions");
                permissionsStr = getStr(permissions);

                edge = (Edge) edgeArr[i]; // Typecast it first 

                /* Comparison */
                assertEquals(edge.getLabel(), label);
                assertEquals(edge.getStatus(), status);
                assertEquals(edge.getQuotePrecision(), quotePrecision);
                assertEquals(edge.getOrderTypesStr(), orderTypesStr);
                assertEquals(edge.getIcebergAllowed(), icebergAllowed);
                assertEquals(edge.getOcoAllowed(), ocoAllowed);
                assertEquals(edge.getQuoteOrderQtyMarketAllowed(), quoteOrderQtyMarketAllowed);
                assertEquals(edge.getIsSpotTradingAllowed(), isSpotTradingAllowed);
                assertEquals(edge.getIsMarginTradingAllowed(), isMarginTradingAllowed);
                assertEquals(edge.getFiltersStr(), filtersStr);
                assertEquals(edge.getPermissionsStr(), permissionsStr);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadFilters()
    {
        UnitTestEdge test = new UnitTestEdge();

        Object[] edgeArr = ParseJSON.readEdge("assetFile.json");
        Edge edge = null;
        String label; 
        JSONArray filters;
        String filtersStr;

        try
        {
            JSONTokener jsonToken = new JSONTokener(new FileReader("assetFile.json"));
            JSONObject obj = new JSONObject(jsonToken);
            JSONArray symbols = obj.getJSONArray("symbols");
            JSONObject symbol = null;
            for( int i = 0; i < symbols.length(); i++ )
            {
                symbol = (JSONObject) symbols.get(i);


                /* Extract the data */
                label = (String) symbol.get("symbol");
                filters = symbol.getJSONArray("filters");
                filtersStr = test.getStrFilters(label, filters);

                edge = (Edge) edgeArr[i]; // Typecast it first 

                assertEquals(edge.getFiltersStr(), filtersStr);
            } 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    // SUBMODULE: getStrFilters
    // IMPORT: label (String), arr (JSONArray)
    // EXPORT: str (String)
    // ASSERTION: Get the representation of the filters
    public String getStrFilters(String label, JSONArray arr)
    {
        Filter filter = null;
        String str = "\n";
        Object[] objArr = ParseJSON.toObjArray(arr);

        for( int i = 0; i < objArr.length; i++ )
        {
            filter = (Filter) objArr[i];
            str += filter.toString();
        }
        return str;
    }
 
    // SUBMODULE: getStr
    // IMPORT: arr (JSONArray)
    // EXPORT: str (String)
    // ASSERTION: Return the representation of arr
    public String getStr(JSONArray arr)
    {
        String str = "";

        for( int i = 0; i < arr.length(); i++ )
        {
            if( i < arr.length() -1 )
                str += (String) arr.get(i) + ", ";
            else
                str += arr.get(i);
        }
        return str;
    }
}
