/*
Program: Trade.java
Author: Calmen Chia Kai Fong
Student ID: 20072144
Date Created: 26 September 2020
Last Modified: 26 September 2020
Purpose: Class to handle the trade data
Test Class: UnitTestTrade.java
*/

import java.util.*;
import java.io.*;

public class Trade implements Serializable
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

    public Trade( String inTradeLabel, String inPriceChange, String inPriceChangePercent,
                  String inWeightedAvgPrice, String inPrevClosePrice, String inLastPrice,
                  String inLastQty, String inBidPrice, String inBidQty, String inAskPrice, 
                  String inAskQty, String inOpenPrice, String inHighPrice,
                  String inLowPrice, String inVolume, String inQuoteVolume, 
                  long inOpenTime, long inCloseTime, int inFirstId, int inLastId, 
                  int inCount )
    {
        tradeLabel = inTradeLabel;
        priceChange = inPriceChange;
        priceChangePercent = inPriceChangePercent;
        weightedAvgPrice = inWeightedAvgPrice;
        prevClosePrice = inPrevClosePrice;
        lastPrice = inLastPrice;
        lastQty = inLastQty;
        bidPrice = inBidPrice;
        bidQty = inBidQty;
        askPrice = inAskPrice;
        askQty = inAskQty;
        openPrice = inOpenPrice;
        highPrice = inHighPrice;
        lowPrice = inLowPrice;
        volume = inVolume;
        quoteVolume = inQuoteVolume;
        openTime = inOpenTime;  
        closeTime = inCloseTime;
        firstId = inFirstId;
        lastId = inLastId;
        count = inCount;
    } 

    public void output()
    {
        System.out.println("Trade: " + tradeLabel);
        System.out.println("Price Change: " + priceChange);
        System.out.println("Price Change Percent: " + priceChangePercent);
        System.out.println("Weighted Average Price: " + weightedAvgPrice);
        System.out.println("Previous Close Price: " + prevClosePrice);
        System.out.println("Last Price: " + lastPrice);
        System.out.println("Last Quantity: " + lastQty);
        System.out.println("Bid Price: " + bidPrice);
        System.out.println("Bid Quantity: " + bidQty);
        System.out.println("Ask Price: " + askPrice);
        System.out.println("Ask Quantity: " + askQty);
        System.out.println("Open Price: " + openPrice);
        System.out.println("High Price: " + highPrice);
        System.out.println("Low Price: " + lowPrice);
        System.out.println("Volume: " + volume);
        System.out.println("Quote Volume: " + quoteVolume);
        System.out.println("Open Time: " + openTime);
        System.out.println("Close Time: " + closeTime);
        System.out.println("First ID: " + firstId);
        System.out.println("Last ID: " + lastId);
        System.out.println("Count: " + count);
    }

	public String getTradeLabel() 
	{
		return tradeLabel;
	}
	
	public String getPriceChange() 
	{
		return priceChange;
	}
	
	public String getPriceChangePercent() 
	{
		return priceChangePercent;
	}
	
	public String getWeightedAvgPrice() 
	{
		return weightedAvgPrice;
	}
	
	public String getPrevClosePrice() 
	{
		return prevClosePrice;
	}
	
	public String getLastPrice() 
	{
		return lastPrice;
	}
	
	public String getLastQty() 
	{
		return lastQty;
	}
	
	public String getBidPrice() 
	{
		return bidPrice;
	}
	
	public String getBidQty() 
	{
		return bidQty;
	}
	
	public String getAskPrice() 
	{
		return askPrice;
	}
	
	public String getAskQty() 
	{
		return askQty;
	}
	
	public String getOpenPrice() 
	{
		return openPrice;
	}
	
	public String getHighPrice() 
	{
		return highPrice;
	}
	
	public String getLowPrice() 
	{
		return lowPrice;
	}
	
	public String getVolume() 
	{
		return volume;
	}
	
	public String getQuoteVolume() 
	{
		return quoteVolume;
	}
	
	public long getOpenTime() 
	{
		return openTime;
	}
	
	public long getCloseTime() 
	{
		return closeTime;
	}
	
	public long getFirstId() 
	{
		return firstId;
	}
	
	public long getLastId() 
	{
		return lastId;
	}
	
	public long getCount() 
	{
		return count;
	}    
}
