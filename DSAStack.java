/*  Program: DSAStack.java
    Author: Calmen Chia Kai Fong
    Date: 18 Aug 2020
    Purpose: ADT that performs Stack 
    Test Class: UnitTestStack.java
*/

/*                                                 *
    This class comprise externally-obtained code 
*   Obtained from Calmen Chia Kai Fong             *
    (Accessed on 18 August 2020)
    Which is done for the purposes of Workshop 3     
*                                                  */

import java.util.*;

public class DSAStack implements Iterable
{
    private DSALinkedList stack;

    /* CONSTRUCTOR */
    public DSAStack()
    {
        stack = new DSALinkedList();
    }
    
    /* DOING METHODS */

    // SUBMODULE: push
    // IMPORT: value (String)
    // EXPORT: none
    // ASSERTION: Insert value into the last element 
    public void push( Object value )
    {
        stack.insertFirst(value);
    }

    // SUBMODULE: pop
    // IMPORT: none
    // EXPORT: topVal (Object)
    // ASSERTION: Pop out or remove the last element 
    public Object pop()
    {
        Object topVal = stack.peekFirst();
        stack.removeFirst();
        return topVal;
    }

    // SUBMODULE: top
    // IMPORT: none
    // EXPORT: topVal (Object)
    // ASSERTION: Return the top value of stack 
    public Object top()
    {
        return stack.peekFirst();
    }

    public boolean isEmpty()
    {
        return stack.isEmpty();
    }

    public Iterator iterator()
    {
        return stack.iterator();
    }
}
