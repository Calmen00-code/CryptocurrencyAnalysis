/*
Program: FileIO.java
Author: Calmen Chia Kai Fong
Date: 26 October 2020
Last Modified: 26 October 2020
Purpose: Reading the latest asset_info.csv
Test Class: UnitTestFileIO.java
*/

import java.io.*;

public class FileIO
{
    // SUBMODULE: read
    // IMPORT: fileName (String)
    // EXPORT: strArr (String Array)
    // ASSERTION: Reading the file line by line
    public static String[] read(String fileName)
    {
        FileInputStream fis = null;
        InputStreamReader isr;
        BufferedReader bufRdr;

        String line = "";
        String[] strArr = null;
        int i = 0, fileSize = 0;

        fileSize = getSize(fileName);
        strArr = new String[fileSize];
        try 
        {
            fis = new FileInputStream( fileName );
            isr = new InputStreamReader( fis );
            bufRdr = new BufferedReader(isr);

            line = bufRdr.readLine();
            // ASSERTION: Iterate until the end of file
            while( line != null )
            {
                strArr[i] = line;
                i++;
                line = bufRdr.readLine();
            }

            fis.close();
        }
        catch(IOException e)
        {
            if( fis != null )
            {
                try { fis.close(); }
                catch(IOException ex2) { }
            }
            System.out.println("Error in processing the file: " + e.getMessage());
        }
        return strArr;
    }

    // SUBMODULE: format
    // IMPORT: line (String), delim (Character), delimSize (Integer)
    // EXPORT: row (String Array)
    // ASSERTION: Return the data in the row
    public static String[] format( String line, char delim, int delimSize )
    {
        String[] row = new String[delimSize];
        int jj = 0;
        char ch = 0;
        String tmp = "";

        for( int i = 0; i < line.length(); i++ )
        {
            if( line.charAt(i) == '"' )
            {
                i++;                    // Increment as we want to skip '"'
                ch = line.charAt(i);    // Store the next char

                while( ch != '"' )      // Loop until the next closing '"' 
                {
                    tmp += ch;
                    i++;
                    ch = line.charAt(i);
                }
            }       
            // Copy all data character by character until we see delim
            else if( line.charAt(i) != delim )
                tmp += line.charAt(i);
            else
            {
                row[jj] = tmp;  // If delim was found, store it into string array
                tmp = "";       // Reset tmp 
                jj++;           // Move to next index
            }
        }
        return row;
    }

    // SUBMODULE: getSize
    // IMPORT: fileName (String)
    // EXPORT: size (Integer)
    // ASSERTION: Return the number of rows of the file 
    public static int getSize( String fileName )
    {
        FileInputStream fis = null;
        InputStreamReader ois;
        BufferedReader bufRdr;
        int size = 0;

        try
        {
            fis = new FileInputStream( fileName );
            ois = new InputStreamReader( fis );
            bufRdr = new BufferedReader( ois );

            while( bufRdr.readLine() != null )
                size++;
        }
        catch(IOException e)
        {
            System.out.println("Error in file processing: " + e.getMessage());
        }
        return size;       
    }
}
