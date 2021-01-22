/*
Program: DSAGraph.java
Author: Calmen Chia Kai Fong
Date: 17 Sep 2020
Last Modified: 29 September 2020
Purpose: Implements DSAGraph
Test Class: UnitTestDSAGraph.java
*/

import java.util.*;
import java.io.*;

/* Parts of this class comprise externally-obtained code */
public class DSAGraph implements Serializable
{ 
    /*
        Obtained from Calmen Chia Kai Fong
        (Accessed on 17 September 2020)
        For the purpose of Workshop 6
        Starting from line 22 until line 147 
    */

    /* CLASSFIELDS */
    private DSALinkedList vertices;
    private DSALinkedList edges;
    private int count;

    /* CONSTRUCTOR */
    public DSAGraph()
    {
        vertices = new DSALinkedList();
        edges = new DSALinkedList();
    }

    /* MUTATORS */

    // SUBMODULE: addVertex
    // IMPORT: label (String), value (Object)
    // EXPORT: none
    // ASSERTION: Adding a new vertex to the graph
    public void addVertex( String label, Object value )
    {
        if( !hasVertex( label ) )
        {
            DSAGraphVertex vertex = new DSAGraphVertex( label, value ); // Create vertex
            vertices.insertLast( vertex );  // Insert the vertex
            count++;
        }
    }

    // SUBMODULE: addEdge
    // IMPORT: label1 (String), label2 (String), edgeLabel (String), str (String)
    // EXPORT: none
    // ASSERTION: Adding a new node into the edge
    public void addEdge( String label1, String label2, String edgeLabel, String str )
    {
        if( label1.equals(label2) )
            throw new IllegalArgumentException("Label cannot be the same");

        else if( !hasVertex(label1) && !hasVertex(label2) )
            throw new IllegalArgumentException("Label does not exist");

        else
        {
            DSAGraphVertex vertex1 = null, vertex2 = null;
            DSAGraphEdge edge = null;
            boolean found = false;

            vertex1 = getVertex(label1);
            vertex2 = getVertex(label2);

            vertex1.addEdge( vertex2 );
            edge = new DSAGraphEdge( vertex1, vertex2, edgeLabel, str );
            edge.setDirected( true );
            edges.insertLast( edge );
        }
    }

    // SUBMODULE: clearVisited
    // IMPORT: none
    // EXPORT: none
    public void clearVisited()
    {
        DSAGraphVertex v = null;

        for( Object o : vertices )
        {
            v = (DSAGraphVertex)o;
            v.setVisited(false);
        }
    }

    /* ACCESSORS */

    // SUBMODULE: hasVertex
    // IMPORT: inLabel (String)
    // EXPORT: boolean
    // ASSERTION: Check if the label exists or not
    public boolean hasVertex( String inLabel )
    {
        DSAGraphVertex vertex = null;
        boolean exist = false;

        Iterator iter = vertices.iterator();
        /* ASSERTION: Traverse through each list node and check 
                      for node with matching with label */
        while( iter.hasNext() && !exist )
        {   
            vertex = (DSAGraphVertex)iter.next(); 
            // Check if current vertex label is equivalent to inLabel
            if( vertex.getLabel().equals( inLabel ) )          
                exist = true;     // Stop the iterator 
        }
        return exist;
    }

    // SUBMODULE: hasEdge
    // IMPORT: inLabel (String)
    // EXPORT: boolean
    // ASSERTION: Check if the edge exists or not
    public boolean hasEdge( String inLabel )
    {
        DSAGraphEdge edge = null;
        boolean exist = false;

        Iterator iter = edges.iterator();
        /* ASSERTION: Traverse through each list node and check 
                      for node with matching with label */
        while( iter.hasNext() && !exist )
        {
            edge = (DSAGraphEdge)iter.next();
            // Check if current vertex label is equivalent to inLabel
            if( edge.getLabel().equals( inLabel ) )
                exist = true;
        }
        return exist;
    }       

    // SUBMODULE: getVertexCount
    // IMPORT: none
    // EXPORT: int
    public int getVertexCount()
    {
        int i = 0;
        for( Object o : vertices )
            i++;
        return i;
    }
    /* End of Code obtained from Calmen Chia Kai Fong, Workshop 6 */

    // SUBMODULE: getEdgeCount
    // IMPORT: none
    // EXPORT: int
    public int getEdgeCount()
    {
        DSAGraphVertex v;
        int sum = 0;
        
        Iterator iter = vertices.iterator();
            
        while( iter.hasNext() )
        {
            v = (DSAGraphVertex)iter.next();
            sum += v.getSize(); 
            // Every time addEdge is called, numEdge is increases by 1
        }
        return sum;
    }

    // SUBMODULE: getVertex
    // IMPORT: inLabel (String)
    // EXPORT: vertex (DSAGraphVertex)
    // References: Obtained from Calmen Chia Kai Fong, Workshop 6
    public DSAGraphVertex getVertex( String inLabel )
    {
        boolean found = false;
        DSAGraphVertex v = null;
        Iterator iter = vertices.iterator();
    
        if( !hasVertex(inLabel) )
            throw new IllegalArgumentException("Label does not exist");
        
        else
        {
            // ASSERTION: Traverse through vertices list until vertex.label = inLabel
            while( iter.hasNext() && !found )
            {
                v = (DSAGraphVertex)iter.next();        // Move to next vertex
                found = v.getLabel().equals(inLabel);   // Check if we found the Vertex or not
            }
        }
        return v;
    }
    /* End of Code obtained from Calmen Chia Kai Fong, Workshop 6 */
 
    // SUBMODULE: getAdjacent
    // IMPORT: label (String)
    // EXPORT: vertexList (Vertices)
    // References: Obtained from Calmen Chia Kai Fong, Workshop 6
    public DSALinkedList getAdjacent( String inLabel )
    {
        DSAGraphVertex v = null;

        if( !hasVertex(inLabel) )                       // If inLabel does not exists means vertex is not exists
            throw new IllegalArgumentException("Label does not exist");
    
        else
        {
            boolean found = false;
            
            Iterator iter = vertices.iterator();        // Iterate through vertices becase we want to get
                                                        // link list of vertex and link list is fields of vertex
            // ASSERTION: Iterate over vertices (to search for the desired vertex)
            while( iter.hasNext() && !found )
            {
                v = (DSAGraphVertex)iter.next();
                found = v.getLabel().equals(inLabel);
            }
        }
        return v.getAdjacent();  // Return vertex list
    }
    /* End of Code obtained from Calmen Chia Kai Fong, Workshop 6 */

    // SUBMODULE: getEdge
    // IMPORT: label (String)
    // EXPORT: edge (DSAGraphEdge)
    public DSAGraphEdge getEdge( String label )
    {
        DSAGraphEdge e = null;
        boolean found = false;
        Iterator iter = edges.iterator();

        if( hasEdge(label) )
        {
            while( iter.hasNext() && !found )
            {
                e = (DSAGraphEdge)iter.next();
                found = e.getLabel().equals(label);
            }
        }
        else
            throw new IllegalArgumentException("Edge does not exist");
        return e;
    }

    // SUBMODULE: getParentVertex
    // IMPORT: vertex (DSAGraphVertex)
    // EXPORT: str (String)
    // ASSERTION: Return the all vertex that link to imported vertex
    public String getParentVertex( DSAGraphVertex vertex )
    {
        DSAGraphEdge e = null;
        String str = "Can be converted from: ";
        
        for( Object o : edges )
        {
            e = (DSAGraphEdge) o;
            if( e.getTo().getLabel().equals(vertex.getLabel()) )
                str += e.getFrom().getLabel() + " ";
        }

        // This means nothing is converted from current asset
        if( str.equals("Can be converted from: ") )
            str += "none";
        return str;
    }

    // SUBMODULE: getChildVertex
    // IMPORT: vertex (DSAGraphVertex)
    // EXPORT: str (String)
    // ASSERTION: Return the all vertex that link to imported vertex
    public String getChildVertex( DSAGraphVertex vertex )
    {
        DSALinkedList list = getAdjacent( vertex.getLabel() );
        DSAGraphVertex v = null;
        String str = "Can be converted to: ";
        
        for( Object o : list )
        {
            v = (DSAGraphVertex) o;
            str += v.getLabel() + " ";
        }

        if( str.equals("Can be converted to: ") )
            str += "none";
        return str;
    }

    // SUBMODULE: getVertices
    // IMPORT: none
    // EXPORT: vertices (DSALinkedList)
    public DSALinkedList getVertices()
    {
        return vertices;
    }

    // SUBMODULE: getEdges
    // IMPORT: none
    // EXPORT: edges (DSALinkedList)
    public DSALinkedList getEdges()
    {
        return edges;
    }

    // SUBMODULE: getCount
    // IMPORT: none
    // EXPORT: count (Integer)
    public int getCount()
    {
        return count;
    }

    /* DOING METHODS */

    // SUBMODULE: isAdjacent
    // IMPORT: label1 (String), label2 (String)
    // EXPORT: boolean
    // References: Obtained from Calmen Chia Kai Fong, Workshop 6
    public boolean isAdjacent( String label1, String label2 )
    {
        boolean valid = false;
    
        if( !hasVertex(label1) && !hasVertex(label2) )      // If both node does not exists
            throw new IllegalArgumentException("Label does not exist");
        
        else if( label1.equals(label2) )                    // Label cannot be the same
            valid = false;

        else
        {
            DSAGraphVertex vertex = null;
            DSALinkedList ll = getAdjacent(label1);

            Iterator iter = ll.iterator();  // Iterator IS NOT vertices because we are looking
                                            // at the vertex's fields (link list)

            // ASSERTION: Traverse through list and check if label2 exists in label1
            while( iter.hasNext() && !valid )
            {
                vertex = (DSAGraphVertex)iter.next();

                // If iterated vertex value is EQUALS to vertex2 value means they are both connected
                valid = vertex.getLabel().equals(getVertex(label2).getLabel());
            }
        }
        return valid;
    }
    /* End of Code obtained from Calmen Chia Kai Fong, Workshop 6 */

    // SUBMODULE: displayAsList
    // IMPORT: none
    // EXPORT: none
    // References: Obtained from Calmen Chia Kai Fong, Workshop 6
    public void displayAsList()
    {
        DSALinkedList ll;
        DSAGraphVertex vertex;
        DSAGraphVertex adjVertex;
 
        for( Object i : vertices )
        { 
            vertex = (DSAGraphVertex) i;
            ll = getAdjacent( vertex.getLabel() );
            
            System.out.print(vertex.getLabel() + ": ");

            // Get the adjacent vertices of vertex
            for( Object j : ll )
            {
                adjVertex = (DSAGraphVertex) j;
                System.out.print(adjVertex.getLabel() + " ");
            }
            System.out.println();
        }
    }
    /* End of Code obtained from Calmen Chia Kai Fong, Workshop 6 */

/** ----------------------------- START Algorithm for DFS ----------------------------------- **/

    // SUBMODULE: dfs
    // IMPORT: start (String), src (String), dest (String)
    // EXPORT: T (String) 
    public void dfs( String start, String src, String dest )
    {
        String T = "", v = "", w = "", key = "", alternate = "", exclude = "";
        DSAStack S = new DSAStack();                 // The queue will store linked list
        DSAStack alternateStr = new DSAStack();      // Store the string for alternate path
        setNew();                                    // Set all nodes to new
        v = src;
        T += v;

        do
        { 
            if( !S.isEmpty() )
            {
                v = (String) S.pop();

                if( hasNew( getVertex(v), getAdjacent(v) ) )
                    w = getNextNew( getVertex(v), getAdjacent(v) );

                alternate = (String) alternateStr.pop();

                if( hasNew(getVertex(v), getAdjacent(v) ) )
                    T += alternate;
            }

            while( !v.equals(dest) && hasNew( getVertex(v), getAdjacent(v) ) )
            {
                w = getNextNew( getVertex(v), getAdjacent(v) );

                // If current v has more than one paths, store it to stack 
                // And lock the current path (Setting to old) so we wont repeat it
                if( getVertex(v).getSize() > 1 ) 
                {
                    getEdge(v+w).setVisited(true);   // Mark w as old if it is not dest      
                    S.push(v);
                    alternateStr.push(T);
                }
                T += " -> " + w;
                v = w;
            }
            if( v.equals(dest) )
                System.out.println(start + " -> " + T);
            T = "";
        } while( !S.isEmpty() );
    }

    // SUBMODULE: hasNew
    // IMPORT: from (DSAGraphVertex), fromList (DSALinkedList)
    // EXPORT: isNew (boolean)
    // References: Obtained from Calmen Chia Kai Fong, Workshop 6
    /* Helper method for dfs, check if new vertex exists in vertexList */
    private boolean hasNew( DSAGraphVertex from, DSALinkedList fromList )
    {
        boolean old = true;
        boolean isNew = false;
        DSAGraphVertex to = null;
        DSAGraphEdge edge = null;

        Iterator iter = fromList.iterator();
        while( iter.hasNext() && old )
        {
            to = (DSAGraphVertex) iter.next();
            edge = getEdge(from.getLabel() + to.getLabel());
            old = edge.getVisited();
        }
        isNew = !old; // old -> FALSE means isNew -> TRUE
        return isNew;
    }
    /* End of Code obtained from Calmen Chia Kai Fong, Workshop 6 */

    // SUBMODULE: getNextNew
    // IMPORT: from (DSAGraphVertex), fromList (DSALinkedList)
    // EXPORT: w (String)
    // References: Obtained from Calmen Chia Kai Fong, Workshop 6
    /* Helper method for dfs, get the label for the next new (unvisited) vertex */
    private String getNextNew( DSAGraphVertex from, DSALinkedList fromList )
    {
        boolean old = true;
        DSAGraphVertex to = null;
        DSAGraphEdge edge = null;
        String w = "";
 
        Iterator iter = fromList.iterator();

        // ASSERTION: Iterate through the list of vertex and stop when new vertex found
        while( iter.hasNext() && old )
        {
            to = (DSAGraphVertex) iter.next();
            edge = getEdge(from.getLabel() + to.getLabel());
            old = edge.getVisited();
        }
        w = to.getLabel();
        return w;
    }
    /* End of Code obtained from Calmen Chia Kai Fong, Workshop 6 */

/** ----------------------------- END Algorithm for DFS ------------------------------------ **/

    // SUBMODULE: setNew
    // IMPORT: none
    // EXPORT: none
    // References: Obtained from Calmen Chia Kai Fong, Workshop 6
    /* Helper method for dfs & bfs, mark all vertex to be new */
    private void setNew()
    {
        DSAGraphEdge edge = null;
        for(Object o : edges)
        {
            edge = (DSAGraphEdge) o;
            edge.setVisited(false);   // Mark all vertex to be new
        }
    }
    /* End of Code obtained from Calmen Chia Kai Fong, Workshop 6 */
}
