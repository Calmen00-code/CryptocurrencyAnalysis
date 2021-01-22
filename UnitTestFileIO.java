/*
Program: UnitTestFileIO.java
Author: Calmen Chia Kai Fong
Date: 27 October 2020
Last Modified: 27 October 2020
Purpose: Test the FileIO class
*/

public class UnitTestFileIO
{
    public static void main(String[] args)
    {
        String[] arr = FileIO.read("asset_info.csv");
        String[] formatted = null;

        for( int i = 0; i < arr.length; i++ )
        {
            arr[i] += ',';
            formatted = FileIO.format(arr[i], ',', 12);
            for( int j = 0; j < formatted.length; j++ ) 
                System.out.print(formatted[j]);

            System.out.println();
        } 
    }
}
