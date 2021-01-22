/*
Program: DSAGraphEdge.java
Author: Calmen Chia Kai Fong
Date: 24 September 2020
Purpose: Graph Edge for java
Test Class: UnitTestDSAGraphEdge.java
*/

/* Code below was adopted from Practical 06 */

import java.util.*;
import java.io.*;

public class DSAGraphEdge implements Serializable
{
    private DSAGraphVertex from;
    private DSAGraphVertex to;
    private String summary;
    private String label;
    private boolean directed;
    private boolean visited;

    public DSAGraphEdge( DSAGraphVertex fromVertex, DSAGraphVertex toVertex, 
                         String inLabel, String inSummary )
    {
        from = fromVertex;
        to = toVertex;
        label = inLabel;
        summary = inSummary;
        directed = true;
        visited = false;    // False by default
    }

    public void setDirected( boolean value )
    {
        directed = value;
    }

    public void setVisited( boolean value )
    {
        visited = value;
    }

    public String getLabel()
    {
        return label;
    }

    public DSAGraphVertex getFrom()
    {
        return from;
    }

    public DSAGraphVertex getTo()
    {
        return to;
    }

    public boolean getDirected()
    {
        return directed;
    }

    public boolean getVisited()
    {
        return visited;
    }

    public String getSummary()
    {
        return summary;
    }

    public String toString()
    {
        String str = "";
        str += "\n" + from.getLabel() + " to " + to.getLabel() + summary; 
        return str;
    }
}
