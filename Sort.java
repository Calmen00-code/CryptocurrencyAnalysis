/*
Program: Sort.java
Author: Calmen Chia Kai Fong
Date: 25 October 2020
Last Modified: 25 October 2020
Purpose: Handling the sorting algorithm
Test Class: UnitTestSort.java
*/

public class Sort
{
    // SUBMODULE: insertionSortDouble
    // IMPORT: arr (Double Array)
    // EXPORT: none
    // ASSERTION: Perform insertion sort
    public static void insertionSortDouble( double[] arr )
    {
        int jj = 0;
        for( int ii = 1; ii < arr.length; ii++ )
        {
            jj = ii; // this assignment here sort from index before ii

            // the loop will check from index ii until first index
            while( (jj > 0) && ( arr[jj] > arr[jj-1] ) ) 
            {                   
                // value before ii are all sorted
                arr = swapDouble( arr, jj, (jj - 1) );
                jj--;
            }
        }
    }

    // SUBMODULE: insertionSortLong
    // IMPORT: arr (Long Array)
    // EXPORT: none
    // ASSERTION: Perform insertion sort
    public static void insertionSortLong( long[] arr )
    {
        int jj = 0;
        for( int ii = 1; ii < arr.length; ii++ )
        {
            jj = ii; // this assignment here sort from index before ii

            // the loop will check from index ii until first index
            while( (jj > 0) && (arr[jj] > arr[jj-1]) ) 
            {                   
                // value before ii are all sorted
                arr = swapLong( arr, jj, (jj - 1) );
                jj--;
            }
        }
    }

    // SUBMODULE: swapDouble
    // IMPORT: array (Double Array), idx (Integer), idxNext (Integer)
    // EXPORT: array (Double Array)
    private static double[] swapDouble( double[] array, int idx, int idxNext )
    {
        double temp = 0.0;
        temp = array[idx];
        array[idx] = array[idxNext];
        array[idxNext] = temp;
        return array;
    }

    // SUBMODULE: swapLong
    // IMPORT: array (Long Array), idx (Integer), idxNext (Integer)
    // EXPORT: array (Long Array)
    private static long[] swapLong( long[] array, int idx, int idxNext )
    {
        long temp = 0;
        temp = array[idx];
        array[idx] = array[idxNext];
        array[idxNext] = temp;
        return array;
    }
}
