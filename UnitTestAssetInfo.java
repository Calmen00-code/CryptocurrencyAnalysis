/*
Program: UnitTestAssetInfo.java
Author: Calmen Chia Kai Fong
Date: 16 October 2020
Last Modified: 27 October 2020
Purpose: Test Harness for AssetInfo.java
Requirement: Make sure FileIO Test is run as we will use 
             formatted method from it here
*/

import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;

public class UnitTestAssetInfo
{
    public static final int ASSET_DATA_TYPES = 12;

    @Test
    public void TestLength()
    {
        String[] arr = FileIO.read("asset_info.csv");
        assertEquals(arr.length, 2600); // Ensure that every line is read
    }
    
    @Test
    public void TestDataRead()
    {
        String[] arr = FileIO.read("asset_info.csv");
        String[] formatted = null;
        AssetInfo info = null;

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

            assertEquals(info.getRank(), formatted[0]);
            assertEquals(info.getName(), formatted[1]);
            assertEquals(info.getSymbol(), formatted[2]);
            assertEquals(info.getMarketCap(), formatted[3]);
            assertEquals(info.getMarkeyCap(), formatted[4]);
            assertEquals(info.getPrice(), formatted[5]);
            assertEquals(info.getCirculatingSupply(), formatted[6]);
            assertEquals(info.getCirculating(), formatted[7]);
            assertEquals(info.getVolume(), formatted[8]);
            assertEquals(info.getOneHourPercent(), formatted[9]);
            assertEquals(info.getTwentyFourHourPercent(), formatted[10]);
            assertEquals(info.getSevenDayPercent(), formatted[11]);
        }
    }
}
