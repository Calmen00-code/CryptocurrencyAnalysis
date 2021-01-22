/*
Program: UnitTestStack.java
Author: Calmen Chia Kai Fong
Date: 16 October 2020
Purpose: Unit Test for Stack Data Structure
*/

/*                                                 *
    This class comprise externally-obtained code 
*   Obtained from Calmen Chia Kai Fong             *
    (Accessed on 25 August 2020)
    Which is done for the purposes of Workshop 4     
*                                                  */

import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;

public class UnitTestStack
{
    ByteArrayOutputStream capOut = null;    
    String output = "";
    DSAStack st;

    @Before
    public void setUp()
    {
        capOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capOut));
        st = new DSAStack();
    }

    @After
    public void tearDown()
    {
        output = "";
        capOut = null;
        st = null;
    }

    @Test // Testing for Stack Push List
    public void testStackPush()
    {
        st.push("abc"); st.push(10); st.push("xyz"); st.push(2020);
        assertEquals("2020 xyz 10 abc ", IterateOverStack());
    }   

    @Test // Testing for Stack Pop List
    public void testStackPop()
    {
        /* Push first or else you have nothing to pop! */
        st.push("abc"); st.push(10); st.push("xyz"); st.push(2020);
        st.pop(); st.pop();
        /* EXPECTED: 10 abc because the lists is 2020 -> xyz -> 10 -> abc intially 
                     then is being removeFirst() two times so 10 abc */    
        assertEquals("10 abc ", IterateOverStack());

        /* Pop second time, EXPECTED: abc */
        st.pop(); // Now is left with the last list
        assertEquals("abc ", IterateOverStack());
    }

    @Test // Testing for Stack Pop when list is NULL
    public void testStackPopEmpty()
    {
        /* Pop when there is no list */
        try { st.pop(); }
        catch(IllegalArgumentException e) { System.out.print(e.getMessage()); }

        output = capOut.toString();
        assertEquals("List is empty!", output);
    }

    @Test // Testing for Stack Pop when list is finished popping
    public void testStackOverPop()
    {
        /* Pop when you have lists initially but pop until empty */
        st.push("abc"); st.push(10); st.push("xyz"); st.push(2020);
        try { 
            // This should throw an exception at pop number 5 because it is empty lists 
            st.pop(); st.pop(); st.pop(); st.pop(); st.pop();
        } catch(IllegalArgumentException e) { System.out.print(e.getMessage()); }

        output = capOut.toString();
        assertEquals("List is empty!", output);
    }

    @Test // Testing for Top (without any pop)
    public void testStackTop()
    {
        /* Peek without any pop */
        st.push("abc"); st.push(10); st.push("xyz"); st.push(2020);
        Object value = st.top();
        assertEquals(2020, value);
    }

    @Test // Testing for peek (with pop)
    public void testStackTopPop()
    {
        Object value = null;

        /* Top with pops */
        st.push("abc"); st.push(10); st.push("xyz"); st.push(2020);
        st.pop(); // First pop
        /* EXPECTED: xyz */
        value = st.top();
        assertEquals("xyz", value);

        st.pop(); // Second pop
        /* EXPECTED: 10 abc */
        value = st.top();
        assertEquals(10, value);
    }

    @Test // Testing for peek (with peek after pops finished)
    public void testStackTopPopsEmpty()
    {
        Object value = null;

        /* Top when pops reaches the limit */
        st.push("abc"); st.push(10); st.push("xyz"); st.push(2020);
        st.pop(); st.pop(); st.pop(); st.pop(); // Now the list is empty
        try { value = st.top(); }
        catch(IllegalArgumentException e) { System.out.print(e.getMessage()); }
        output = capOut.toString();
        /* EXPECTED: List is empty! <- this is because all lists are popped */
        assertEquals("List is empty!", output);
    }

    @Test // Testing for peek (when nothing is in the list
    public void testStackTopEmpty()
    {
        Object value = null;

        /* Top when nothing is inserted into list */
        try { st.pop(); }
        catch(IllegalArgumentException e) { System.out.print(e.getMessage()); }
        output = capOut.toString();
        /* EXPECTED: List is empty! <- this is because nothing is inserted into the list */
        assertEquals(output, "List is empty!");
    }

    public String IterateOverStack()
    {
        String str = "";
        for( Object o : st )
            str += o + " ";

        return str;
    }
}
