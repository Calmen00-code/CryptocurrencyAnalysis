/*  Program: DSALinkedList.java
    Author: Calmen Chia Kai Fong
    Date: 25 Aug 2020
    Purpose: Implement Single Link List using java
    Test Class: UnitTestDSALinkedList.java
*/

/*                                                 *
    This class comprise externally-obtained code 
*   Obtained from Calmen Chia Kai Fong             *
    (Accessed on 25 August 2020)
    Which is done for the purposes of Workshop 4     
*                                                  */

import java.util.*;
import java.io.*;

public class DSALinkedList implements Iterable, Serializable
{
    /* ** Private inner class for DSALinkedList ** */
    private class DSAListNode implements Serializable
    {
        /* CLASSFIELDS */
        public Object data;
        public DSAListNode next;
        public DSAListNode prev;

        /* CONSTRUCTOR */
        public DSAListNode(Object inData)
        {
            data = inData;
            next = null;
            prev = null;
        }
    }

    /* Interface for DSALinkedListIterator */
    public Iterator iterator()
    {
        return new DSALinkedListIterator(this);
    }

    /* ** Private ineer class for DSALinkedListIterator ** */
    private class DSALinkedListIterator implements Iterator
    {
        /* CLASSFIELDS */
        private DSAListNode cursor; // Lect slides named it iterNext (i prefer cursor, it means current pointer)

        /* CONSTRUCTOR */
        public DSALinkedListIterator(DSALinkedList thisList)
        {
            cursor = thisList.head; // First make cursor points to our head of the DATA STRUCTURE
        }

        /* DOING METHODS */
    
        // SUBMODULE: next
        // IMPORT: none
        // EXPORT: value (Object)
        // ASSERTION: Move the cursor to the next one and return current cursor value  
        public Object next()
        {
            Object value = null;
            if( cursor == null )
                value = null; // Since cursor has points to nothing, therefore it stores no value
            else { // if cursor is not NULL, we need to move to the next cursor
                value = cursor.data; 
                cursor = cursor.next; // Move cursor to the next one 
                                      // Again, we can use next even tho next is in DSAListNode private class
            }
            return value;
        }
        
        // SUBMODULE: hasNext
        // IMPORT: none
        // EXPORT: TRUE if cursor is not NULL
        public boolean hasNext()
        {
            return (cursor != null);
        }

        // We are not going to use this, but since interface forces us, then we still need to write it
        public void remove() { throw new UnsupportedOperationException("Not Supported"); }
    }

    /* CLASSFIELDS */
    private DSAListNode head;
    private DSAListNode tail;

    /* CONSTRUCTOR */
    public DSALinkedList()
    {
        head = null;
        tail = null;
    }

    /* MUTATOR */

    // SUBMODULE: insertFirst
    // IMPORT: inValue (Object)
    // EXPORT: none
    // ASSERTION: Insert element before head
    public void insertFirst( Object inValue )
    {
        DSAListNode newNd = new DSAListNode(inValue); // Creating the new node
        
        if( head == null ) { // If head is NULL, assign the newNd as head
            head = newNd;
            tail = newNd; // Since this is first node, then it is head and tail at the same time 
        } else { // Otherwise, make the newNd points towards head.next and replace head with it
            DSAListNode currN = head;
            newNd.next = currN; // Make newNd pointing to the same as head 
            head = newNd; // Update head to newNd
            currN.prev = newNd;
        }
        newNd.prev = null; // newly created node previous will be NULL as it is the head
    }

    // SUBMODULE: insertLast
    // IMPORT: inValue (Object)
    // EXPORT: none
    // ASSERTION: Insert element at tail
    public void insertLast( Object inValue )
    {
        DSAListNode newNd = new DSAListNode(inValue); // Creating the new node
        if( head == null ) { // If head is NULL, assign the newNd as head
            head = newNd; 
            newNd.prev = null; // newly created node previous will be NULL as it is the head
            tail = newNd; // Since this is first node, then it is head and tail at the same time
        } else { // Otherwise, find the last list and assign newNd to it
            DSAListNode currNd = head; /* Create a node to control the iteration AND
                                          Iteration ALWAYS starts from head traversing to last */
            // ASSERTION: Loop through the lists and assign newNd to the last list
            while( currNd.next != null ) {
                currNd = currNd.next; // Always make currNd points to its next node
            }
            currNd.next = newNd; // Make next node of currNd pointing towards newNd (currNd.next NO LONGER NULL)
            newNd.prev = currNd; // newNd.prev will be currNd since currNd.next is newNd
            newNd.next = null;
            tail = newNd; // newNd.next is now tail as it is the last lists (NOTE: newNd.next is NULL)
        }
    }

    /* ACCESSOR */

    // SUBMODULE: peekLast
    // IMPORT: none
    // EXPORT: nodeVal (Object)
    // ASSERTION: Return the last value of the list
    public Object peekLast()
    {
        Object nodeVal = null;
        if( isEmpty() ) { // If list is empty, there is nothing to be return
            throw new IllegalArgumentException("List is empty!");
        } else {
            nodeVal = tail.data; 
        }
        return nodeVal;
    }

    // SUBMODULE: peekFirst
    // IMPORT: none
    // EXPORT: nodeVal (Object)
    // ASSERTION: Return the first value of the list
    public Object peekFirst()
    {
        Object nodeVal = null;
        if( isEmpty() ) { // If list is empty, there is nothing to be return
            throw new IllegalArgumentException("List is empty!");
        } else {
            nodeVal = head.data;
        }
        return nodeVal;
    }

    // SUBMODULE: removeFirst
    // IMPORT: none
    // EXPORT: removeVal (Object)
    // ASSERTION: Remove the first node from the list
    public Object removeFirst()
    {
        Object removeVal = null;
        if( isEmpty() ) { // If list is empty, there is nothing to be return
            throw new IllegalArgumentException("List is empty!");
        } else {
            removeVal = head.data; // The head will be removed, so retrieves its data here
            head = head.next; // Shift head forwards. Current next node of head node will become head now
        }
        return removeVal;
    }

    // SUBMODULE: removeLast
    // IMPORT: none
    // EXPORT: removeVal (Object)
    // ASSERTION: Remove the first node from the list
    public Object removeLast()
    {
        Object removeVal = null;
        if( isEmpty() ) { // If list is empty, there is nothing to be return
            throw new IllegalArgumentException("List is empty!");
        } else {
            removeVal = tail.data; // The head will be removed, so retrieves its data here
            if( head.equals(tail) )
            {
                head = null;
                tail = null;
            }
            else
            {
                tail = tail.prev; // Shift head forwards. Current next node of head node will become head now
            }
        }
        return removeVal;
    }

    /* DOING METHODS */
    public boolean isEmpty()
    {
        return head == null;
    }
}
