/*
Program: UnitTestTrade.java
Author: Calmen Chia Kai Fong
Date Created: 09 October 2020
Last Modidified: 09 October 2020
Purpose: Testing Trade Class
*/

import org.junit.Test;
import static org.junit.Assert.*;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONArray;
import java.io.*;

public class UnitTestTrade
{
    private String tradeLabel;
    private String priceChange;
    private String priceChangePercent;
    private String weightedAvgPrice;
    private String prevClosePrice;
    private String lastPrice;
    private String lastQty;
    private String bidPrice;
    private String bidQty;
    private String askPrice;
    private String askQty;
    private String openPrice;
    private String highPrice;
    private String lowPrice;
    private String volume;
    private String quoteVolume;
    private long openTime;
    private long closeTime;
    private int firstId;
    private int lastId;
    private int count;

    @Test
    public void testReadTrade()
    {       
        Object[] edgeArr = null;
        Trade trade = null;
        Trade tradeTest = null;

        try
        {
            JSONTokener jsonToken = new JSONTokener(new FileReader("Trade.json"));
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
                tradeTest = (Trade) edgeArr[i];

                assertEquals( tradeTest.getTradeLabel(), tradeLabel );
                assertEquals( tradeTest.getPriceChange(), priceChange );
                assertEquals( tradeTest.getPriceChangePercent(), priceChangePercent );
                assertEquals( tradeTest.getWeightedAvgPrice(), weightedAvgPrice );
                assertEquals( tradeTest.getPrevClosePrice(), prevClosePrice );
                assertEquals( tradeTest.getLastPrice(), lastPrice );
                assertEquals( tradeTest.getLastQty(), lastQty );
                assertEquals( tradeTest.getBidPrice(), bidPrice );
                assertEquals( tradeTest.getBidQty(), bidQty );
                assertEquals( tradeTest.getAskPrice(), askPrice );
                assertEquals( tradeTest.getAskQty(), askQty );
                assertEquals( tradeTest.getOpenPrice(), openPrice );
                assertEquals( tradeTest.getHighPrice(), highPrice );
                assertEquals( tradeTest.getLowPrice(), lowPrice );
                assertEquals( tradeTest.getVolume(), volume );
                assertEquals( tradeTest.getQuoteVolume(), quoteVolume );
                assertEquals( tradeTest.getOpenTime(), openTime );
                assertEquals( tradeTest.getCloseTime(), closeTime );
                assertEquals( tradeTest.getFirstId(), firstId );
                assertEquals( tradeTest.getLastId(), lastId );
                assertEquals( tradeTest.getCount(), count );
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
