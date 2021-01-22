/*
Program: Serialisation.java
Author: Calmen Chia Kai Fong
Date Created: 01 October 2020
Last Modified: 01 October 2020
Purpose: Serialisation for the graph, so that 
         we can save and load the binance data
Test Class: UnitTestSerialisation.java
*/

import java.util.*;
import java.io.*;

public class Serialisation
{
    // SUBMODULE: save
    // IMPORT: graphToSave (DSAGraph), fileName (String)
    // EXPORT: none
    // ASSERTION: Save the graph to the file so that we can retrieve it
    public static void save( DSAGraph graphToSave, String fileName )
    {
        FileOutputStream fos = null;
        ObjectOutputStream oos;

        if( graphToSave != null )
        {
            try
            {
                fos = new FileOutputStream( fileName );
                oos = new ObjectOutputStream( fos );
                oos.writeObject( graphToSave );

                oos.close();
            }
            catch(Exception e)
            {
                throw new IllegalArgumentException("Unable to save object to file: " + e.getMessage());
            }
        }
        else
            throw new IllegalArgumentException("Nothing to save. Please load the data first");
    }

    // SUBMODULE: load
    // IMPORT: fileName (String)
    // EXPORT: graph (DSAGraph)
    // ASSERTION: Read the graph and return it
    public static DSAGraph load( String fileName )
    {
        FileInputStream fis = null;
        ObjectInputStream ois;
        DSAGraph graph = null;

        try
        {
            fis = new FileInputStream( fileName );
            ois = new ObjectInputStream( fis );

            graph = (DSAGraph) ois.readObject();
            ois.close();
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Class DSAGraph not found" + e.getMessage());
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException("Unable to load object from file: " + e.getMessage());
        }
        return graph;
    }
}
