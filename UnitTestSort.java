/*
Program: UnitTestDSAGraphEdge.java
Author: Calmen Chia Kai Fong
Date: 16 October 2020
Purpose: Test Harness for DSAGraphEdge.java
*/

import org.junit.*;
import static org.junit.Assert.*;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONArray;
import java.io.*;

public class UnitTestSort
{
    ByteArrayOutputStream capOut = null;    
    String output = "";
    DSAGraph graph;

    @Before
    public void setUp()
    {
        capOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capOut));
        graph = new DSAGraph();
    }

    @After
    public void tearDown()
    {
        output = "";
        capOut = null;
        graph = null;
    }

    @Test
    public void testSortDouble()
    {
        double arr[] = { 12.321, 55.3214, 33.3, 42.5, 100.2, 2000.2213 };

        Sort.insertionSortDouble( arr );
        assertEquals( 2000.2213, arr[0], 0.001 );
        assertEquals( 100.2, arr[1], 0.001 );
        assertEquals( 55.3214, arr[2], 0.001 );
        assertEquals( 42.5, arr[3], 0.001 );
        assertEquals( 33.3, arr[4], 0.001 );
        assertEquals( 12.321, arr[5], 0.001 );
    }

    @Test
    public void testSortLong()
    {
        long arr[] = { 12, 55, 33, 42, 100, 2000 };

        Sort.insertionSortLong( arr );
        assertEquals( 2000, arr[0] );
        assertEquals( 100, arr[1] );
        assertEquals( 55, arr[2] );
        assertEquals( 42, arr[3] );
        assertEquals( 33, arr[4] );
        assertEquals( 12, arr[5] );
    }
}
