/*
Program: AssetInfo.java
Author: Calmen Chia Kai Fong
Date: 27 October 2020
Last Modified: 27 October 2020
Purpose: Handle the new data with asset_info.csv
Test Class: UnitTestAssetInfo.java
*/

import java.util.*;
import java.io.*;

public class AssetInfo implements Serializable
{
    private String rank;
    private String name;
    private String symbol;
    private String marketCap;
    private String markeyCap;
    private String price;
    private String circulatingSupply;      
    private String circulating;
    private String volume;
    private String oneHourPercent;
    private String twentyFourHourPercent;
    private String sevenDayPercent;

    public AssetInfo( String inRank, String inName, String inSymbol, 
                      String inMarketCap, String inMarkeyCap, String inPrice,
                      String inCirculatingSupply, String inCirculating, 
                      String inVolume, String inOneHourPercent, 
                      String inTwentyFourHourPercent, String inSevenDayPercent )
    {
        rank = inRank;
        name = inName;
        symbol = inSymbol;
        marketCap = inMarketCap;
        markeyCap = inMarkeyCap;
        price = inPrice;
        circulatingSupply = inCirculatingSupply;
        circulating = inCirculating;
        volume = inVolume;
        oneHourPercent = inOneHourPercent;
        twentyFourHourPercent = inTwentyFourHourPercent;
        sevenDayPercent = inSevenDayPercent;
    }

    public String getRank()
    {
        return rank;
    }

    public String getName()
    {
        return name;
    }

    public String getSymbol()
    {
        return symbol;
    }

    public String getMarketCap()
    {
        return marketCap;
    }

    public String getMarkeyCap()
    {
        return markeyCap;
    }

    public String getPrice()
    {
        return price;
    }

    public String getCirculatingSupply()
    {
        return circulatingSupply;
    }

    public String getCirculating()
    {
        return circulating;
    }

    public String getVolume()
    {
        return volume;
    }

    public String getOneHourPercent()
    {
        return oneHourPercent;
    }

    public String getTwentyFourHourPercent()
    {
        return twentyFourHourPercent;
    }

    public String getSevenDayPercent()
    {
        return sevenDayPercent;
    }

    public String toString()
    {
        String str = "";
        str = "\nName: " + getName() + "\nMarket Cap: " + getMarketCap() + 
              "\nMarkey Cap (cleaned): " + getMarkeyCap() + "\nPrice: " + getPrice() + 
              "\nCirculating Supply: " + getCirculatingSupply() + "\nCirculating: " + 
              getCirculating() + "\nVolume (24h): " + getVolume() + "\n% 1h: " + 
              getOneHourPercent() + "\n% 24 hour: " + getTwentyFourHourPercent() + 
              "\n% 7 days: " + getSevenDayPercent(); 
        return str;
    }

    // SUBMODULE: toStringTest
    // IMPORT: none
    // EXPORT: str (String)
    // ASSERTION: This is just for testing purposes only
    public String toStringTest()
    {
        String str = "";
        str = getName() + "\n" + getMarketCap() + "\n" + getMarkeyCap() + "\n" + getPrice() +
              "\n" + getCirculatingSupply() + "\n" + getCirculating() + "\n" + getVolume() +
              "\n" + getOneHourPercent() + "\n" + getTwentyFourHourPercent() + "\n" + 
              getSevenDayPercent();
        return str;
    }
}
