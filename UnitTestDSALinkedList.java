 /***************************************************************************
 *  FILE: TestLinkedList.java
 *  AUTHOR: Connor Beardsmore - 15504319
 *  UNIT: DSA120 Prac 8
 *  PURPOSE: Basic Test Harness For Linked List
 *  LAST MOD: 12/10/15
 *  REQUIRES: NONE
 ***************************************************************************/

/*                                                 *
    This class comprise externally-obtained code 
*   Obtained from Calmen Chia Kai Fong             *
    (Accessed on 25 August 2020)
    Which is done for the purposes of Workshop 4     
*                                                  */

import java.io.*;
import java.util.*;

public class UnitTestDSALinkedList
{
	public static void main(String args[])
	{
        // VARIABLE DECLARATIONS
        int iNumPassed = 0;
        int iNumTests = 0;
        DSALinkedList ll = null;
        String sTestString;
        Object nodeValue;

//---------------------------------------------------------------------------

        System.out.println("\n\nTesting Normal Conditions - Constructor");
        System.out.println("=======================================");

        // TEST 1 : CONSTRUCTOR
        try {
            iNumTests++;
            ll = new DSALinkedList();
            System.out.print("Testing creation of DSALinkedList (isEmpty()): ");
            if (ll.isEmpty() == false)
                throw new IllegalArgumentException("Head must be null.");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

//---------------------------------------------------------------------------

        System.out.println("\nTest inserting first and removing first (stack behaviour)");
        System.out.println("==========================================================");

        // TEST 2 : INSERT FIRST
        try {
            iNumTests++;
            System.out.print("Testing insertFirst(): ");
            ll.insertFirst("abc");
            ll.insertFirst("jkl");
            ll.insertFirst("xyz");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // TEST 3 : PEEK LAST
        try {
            iNumTests++;
            System.out.print("Testing peekLast(): ");
            sTestString = (String)ll.peekLast();
            if (sTestString != "abc")
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed   ");
        } catch(Exception e) { System.out.println("FAILED"); }

        // TEST 4 : REMOVE FIRST
        try {
            iNumTests++;
            System.out.print("Testing removeFirst(): ");
            sTestString = (String)ll.removeFirst();
            if (sTestString != "xyz")
                throw new IllegalArgumentException("FAILED.");
            sTestString = (String)ll.removeFirst();
            if (sTestString != "jkl")
                throw new IllegalArgumentException("FAILED.");
            sTestString = (String)ll.removeFirst();
            if (sTestString != "abc")
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // TEST 5 : IS EMPTY
        try {
            iNumTests++;
            System.out.print("Testing isEmpty(): ");
            sTestString = (String)ll.removeFirst();
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

//---------------------------------------------------------------------------

        System.out.println("\nTest inserting last and removing first (queue behaviour)");
        System.out.println("========================================================");

        // TEST 6 : INSERT LAST()
        try {
            iNumTests++;
            System.out.print("Testing insertLast(): ");
            ll.insertLast("abc");
            ll.insertLast("jkl");
            ll.insertLast("xyz");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // TEST 8 : PEEK FIRST
        try {
            iNumTests++;
            System.out.print("Testing peekFirst(): ");
            sTestString = (String)ll.peekFirst();
            if (sTestString != "abc")
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // TEST 9 : REMOVE FIRST
        try {
            iNumTests++;
            System.out.print("Testing removeFirst(): ");
            sTestString = (String)ll.removeFirst();
            if (sTestString != "abc")
                throw new IllegalArgumentException("FAILED.");
            sTestString = (String)ll.removeFirst();
            if (sTestString != "jkl")
                throw new IllegalArgumentException("FAILED.");
            sTestString = (String)ll.removeFirst();
            if (sTestString != "xyz")
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // TEST 7 : IS EMPTY 2
        try {
            iNumTests++;
            System.out.print("Testing isEmpty(): ");
            sTestString = (String)ll.removeFirst();
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

//---------------------------------------------------------------------------

        System.out.println("\nTest inserting last and removing last (queue behaviour)");
        System.out.println("========================================================");

        // TEST 10 : INSERT LAST()
        try {
            iNumTests++;
            System.out.print("Testing insertLast(): ");
            ll.insertLast("abc");
            ll.insertLast("jkl");
            ll.insertLast("xyz");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // TEST 11: REMOVE LAST
        try {
            iNumTests++;
            System.out.print("Testing.removeLast(): ");
            sTestString = (String)ll.removeLast();
            if (!sTestString.equals("xyz"))
                throw new IllegalArgumentException("FAILED.");
            sTestString = (String)ll.removeLast();
            if (!sTestString.equals("jkl"))
                throw new IllegalArgumentException("FAILED.");
            sTestString = (String)ll.removeLast();
            if (!sTestString.equals("abc"))
                throw new IllegalArgumentException("FAILED.");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }
            
        // TEST 12 : IS EMPTY 2
        try {
            iNumTests++;
            System.out.print("Testing isEmpty(): ");
            sTestString = (String)ll.removeFirst();
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

//----------------------------------------------------------------------------------------------

        // TEST 13 : INSERT FIRST
        try {
            iNumTests++;
            System.out.print("Testing insertFirst(): ");
            ll.insertFirst("abc");
            ll.insertFirst("jkl");
            ll.insertFirst("xyz");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // TEST 14: ITERATOR
        try {
            iNumTests++;
            String str = "";
            System.out.print("Testing iterator(): ");
            for( Object o : ll ) {
                str += o + "\n";
            }
            if( str.equals("xyz\njkl\nabc\n") )
                System.out.println("passed");
            else
                System.out.println("FAILED");

            iNumPassed++;
        } catch(UnsupportedOperationException e) { System.out.println(e.getMessage()); }        

        // TEST 15: ITERATOR next
        System.out.print("Testing iterator() next: ");
        iNumTests++;
        Iterator iter = ll.iterator();
        nodeValue = (String)iter.next() + "\n";
        nodeValue += (String)iter.next();
        if( nodeValue.equals("xyz\njkl") ) {
            iNumPassed++;
            System.out.println("passed");
        } else 
            System.out.println("FAILED");

        // TEST 16: ITERATOR hasNext (TRUE)
        System.out.print("Testing iterator() hasNext (expeced TRUE): ");
        iNumTests++;
        ll.removeFirst(); ll.removeFirst(); // Now is left with "abc"
        if( iter.hasNext() ) { // Should return TRUE
            System.out.println("passed");
            iNumPassed++;
        } else {
            System.out.println("FAILED");
        }

        // TEST 17: ITERATOR hasNext (FALSE)
        iNumTests++;
        System.out.print("Testing iterator() hasNext (expeced FALSE): ");
        String data = (String) iter.next(); // This will return "abc" and now pointing to null
        if( data.equals("abc") )
        {
            if( iter.hasNext() ) { // Should return FALSE
                System.out.println("FAILED");
            } else {
                System.out.println("passed");
                iNumPassed++;
            }
        }
        else
            System.out.println("FAILED");

//---------------------------------------------------------------------------

        // PRINT TEST SUMMARY
        System.out.print("\nNumber PASSED: " + iNumPassed + "/" + iNumTests);
        System.out.print(" -> " + (int)(double)iNumPassed/iNumTests*100 + "%\n");  
    }
//---------------------------------------------------------------------------  
}
