/*
Program: DSAGraphVertex.java
Author: Calmen Chia Kai Fong
Date: 17 September 2020
Purpose: Helper class for DSAGraph.java
Test Class: UnitTestDSAGraphVertex.java
*/


/*                                               *
    This class comprise externally-obtained code 
*   Obtained from Calmen Chia Kai Fong           *
    (Accessed on 17 September 2020)
    Which is done for the purposes of Workshop 6 
*                                                */

import java.io.*;
import java.util.*;
public class DSAGraphVertex implements Serializable
{
    // CLASSFIELDS
    private String label;
    private Object value;
    private DSALinkedList link;
    private boolean visited;
    private int numEdge;

    // CONSTRUCTOR
    public DSAGraphVertex( String inLabel, Object inValue )
    {
        label = inLabel;
        value = inValue;
        link = new DSALinkedList();     // Make a new link list to points to current vertex
        visited = false;
    }

    // MUTATOR
    public void addEdge( DSAGraphVertex vertex )
    {
        link.insertLast(vertex);        // Link the current vertex to imported vertex
        numEdge++;                      // Everytime we make a connection, there is an edge
    }

    public void setVisited( boolean value )
    {
        visited = value;
    }

    // SUBMODULE: toString
    // IMPORT: none
    // EXPORT: str (String)
    public String toString()
    {
        String str = "";
        DSAGraphVertex vertex = null;

        for( Object o : link )
        {
            vertex = (DSAGraphVertex)o;
            str += vertex.value + " ";
        }
        return str;
    }

    // ACCESSOR
    public Object getValue()
    {
        return value;
    }

    public String getLabel()
    {
        return label;
    }

    public DSALinkedList getAdjacent()
    {
        return link;
    }

    public int getSize()
    {
        return numEdge;
    }

    public boolean getVisited()
    {
        return visited;
    }
}
