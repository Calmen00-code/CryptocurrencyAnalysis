/*
Program: DSABinarySearchTree.java
Author: Calmen Chia Kai Fong
Date: 11 Sept 2020
Purpose: Binary Search Tree in java
Test Class: UnitTestDSABinarySearchTree.java
*/

/*                                                 *
    This class comprise externally-obtained code 
*   Obtained from Calmen Chia Kai Fong             *
    (Accessed on 11 September 2020)
    Which is done for the purposes of Workshop 5     
*                                                  */

import java.io.*;

public class DSABinarySearchTree implements Serializable
{
    /* Private inner class: DSATreeNode */
    private class DSATreeNode implements Serializable
    {
        DSATreeNode left;
        DSATreeNode right;
        String key;
        Object data;

        /* CONSTRUCTOR */
        public DSATreeNode( String inKey, Object inData )
        {
            if( inKey == null )
                throw new IllegalArgumentException("Key cannot be null");
            left = null;
            right = null;
            data = inData;
            key = inKey;
        }
    }

    /* CLASSFIELDS */
    private DSATreeNode root;

    /* ** Wrapper methods for MUTATORS ** */

    // SUBMODULE: insert
    // IMPORT: key (String), data (Object)
    // EXPORT: none
    public void insert( String key, Object data )
    {
        // Now root has the address of all the trees 
        try { root = insertRec( root, key, data ); } 
        catch(IllegalArgumentException e) { /* No output as we just want to continue */ }
    }     
    /* ** END Wrapper methods for MUTATORS ** */


    /* ** Wrapper methods for ACCESSORS ** */

    // SUBMODULE: find
    // IMPORT: key (String)
    // EXPORT: data (Object)
    public Object find( String key )
    {
        Object data = null;
        try { data = findRec( root, key ); }
        catch(IllegalArgumentException e) { /* No output as we just want to continue */ }
        return data;
    }

    // SUBMODULE: delete
    // IMPORT: key (String)
    // EXPORT: none
    public void delete( String key )
    {
        Object data = null;
        try { data = deleteRec( root, key ); }
        catch(IllegalArgumentException e) { System.out.println(e.getMessage()); }
    }
            
    // SUBMODULE: inOrderTraverse
    // IMPORT: none
    // EXPORT: (String)
    public String inOrderTraverse()
    {
        return inOrderRec( root, "" );
    }

    // SUBMODULE: preOrderTraverse
    // IMPORT: none
    // EXPORT: (String)
    public String preOrderTraverse()
    {
        return preOrderRec( root, "" );
    }

    // SUBMODULE: postOrderTraverse
    // IMPORT: none
    // EXPORT: (String)
    public String postOrderTraverse()
    {
        return postOrderRec( root, "" );
    }

    // SUBMODULE: minKey
    // IMPORT: none
    // EXPORT: minimum (String)
    public String minKey()
    {
        return minRec( root );
    }

    // SUBMODULE: maxKey
    // IMPORT: none
    // EXPORT: maximum (String)
    public String maxKey()
    {
        return maxRec( root );
    }

    // SUBMODULE: height
    // IMPORT: currNd (DSATreeNode)
    // EXPORT: value (Integer)
    public int height()
    {
        return heightRec( root );
    }

    // SUBMODULE: balance
    // IMPORT: none
    // EXPORT: balance (Integer)
    public int balance()
    {
        int unbalanced = balanceRec( root, 0 );
        System.out.println("Unbalanced: " + unbalanced);
        int balance = (10 - unbalanced) * 10;
        return balance;
    }
    /* ** END Wrapper methods for ACCESSORS ** */

    /* MUTATORS */

    // SUBMODULE: insertRec
    // IMPORT: currNd (DSATreeNode), key (String), data (Object)
    // EXPORT: updateNd (DSATreeNode)
    // ASSERTION: Create the new tree node 
    private DSATreeNode insertRec( DSATreeNode currNd, String key, Object data )
    {
        DSATreeNode updateNd;
        updateNd = currNd;
        if( currNd == null )
        {
            currNd = new DSATreeNode( key, data );
            updateNd = currNd;
        }
        else if( key.equals(currNd.key) )
            throw new IllegalArgumentException( "Existing key provided" );      // Key must be unique

        else if( key.compareTo(currNd.key) < 0 )
            currNd.left = insertRec( currNd.left, key, data );                  // Recursive to left

        else
            currNd.right = insertRec( currNd.right, key, data );                // Recursive to right

        return updateNd;
    }

    // SUBMODULE: deleteRec
    // IMPORT: currNd (DSATreeNode), key (String) 
    // EXPORT: updateNd (DSATreeNode)
    // ASSERTION: Delete the branch (key)
    private DSATreeNode deleteRec( DSATreeNode currNd, String key )
    {
        DSATreeNode updateNd = currNd;
        if( currNd == null )                                            // Base case: No such node exists
            throw new IllegalArgumentException("No such tree exists");

        else if( key.equals(currNd.key) )                               // Base case: Found
            updateNd = deleteNode( currNd );

        else if( key.compareTo(currNd.key) < 0 )                        // Recurse left
            currNd.left = deleteRec( currNd.left, key );

        else                                                            // Recurse right
            currNd.right = deleteRec( currNd.right, key );

        return updateNd;
    }

    // SUBMODULE: deleteNode
    // IMPORT: key (String), delNd (DSATreeNode)
    // EXPORT: updateNd (DSATreeNode)
    // ASSERTION: Delete the node
    /* Helper method for deleteRec */
    private DSATreeNode deleteNode( DSATreeNode delNd )
    {
        DSATreeNode updateNd = null;
        if( delNd.left == null && delNd.right == null )
            updateNd = null;                                            // No branches/children (leaf)

        else if( delNd.left == null && delNd.right != null )            // Only have right children
            updateNd = delNd.right;

        else if( delNd.left != null && delNd.right == null )            // Only have left children
            updateNd = delNd.left;

        else                                                
        {
            updateNd = promoteSuccessor( delNd.right );     // Get successor (left node of most right node)
            if( updateNd != delNd.right )
                updateNd.right = delNd.right;               // Update right

            updateNd.left = delNd.left;                     // Update left
        }
        return updateNd;
    }

    // SUBMODULE: promoteSuccessor
    // IMPORT: delNd (DSATreeNode)
    // EXPORT: successor (DSATreeNode)
    // ASSERTION: Find the next successor of delNd
    /* Helper method for deleteNode */
    private DSATreeNode promoteSuccessor( DSATreeNode delNd )
    {
        DSATreeNode successor = delNd;
        if( delNd.left == null )                    // Left is nothing automatically become successor
            successor = delNd;
        else
        {
            successor = promoteSuccessor(delNd.left);   // The left of delNd will be successor
                                                        // Because it is smaller
            if( successor == delNd.left )
                delNd.left = successor.right;       // Adopt to right child
        }
        return successor;
    }

    /* ACCESSORS */

    // SUBMODULE: findRec
    // IMPORT: currNd (DSATreeNode), key (String)
    // EXPORT: data (Object)
    // ASSERTION: Find the node with the key and get its data
    private Object findRec( DSATreeNode currNd, String key )
    {
        Object data = null;

        if( currNd == null )                                                    // Base case: Not found
            throw new IllegalArgumentException("Key does not exists");

        else if( key.equals(currNd.key) )                                       // Base case: Found
            data = currNd.data;

        else if( key.compareTo(currNd.key) < 0 )                                // Recursive to left
            data = findRec( currNd.left, key );

        else
            data =findRec( currNd.right, key );                                 // Recursive to right
        
        return data;
    }

    // SUBMODULE: inOrderRec
    // IMPORT: currNd (DSATreeNode), str (String)
    // EXPORT: str (String)
    private String inOrderRec( DSATreeNode currNd, String str )
    {
        if( currNd != null )
        {
            str = inOrderRec( currNd.left, str );
            str += currNd.data + " ";
            str = inOrderRec( currNd.right, str );
        }
        return str;
    }

    // SUBMODULE: preOrderRec
    // IMPORT: currNd (DSATreeNode), str (String)
    // EXPORT: str (String)
    private String preOrderRec( DSATreeNode currNd, String str )
    {
        if( currNd != null )
        {
            str += currNd.data + " ";
            str = preOrderRec( currNd.left, str );
            str = preOrderRec( currNd.right, str );
        }
        return str;
    }

    // SUBMODULE: postOrderRec
    // IMPORT: currNd (DSATreeNode), str (String)
    // EXPORT: str (String)
    private String postOrderRec( DSATreeNode currNd, String str )
    {
        if( currNd != null )
        {
            str = postOrderRec( currNd.right, str );
            str += currNd.data + " ";
            str = postOrderRec( currNd.left, str );
        }
        return str;
    }

    // SUBMODULE: minRec
    // IMPORT: currNd (DSATreeNode)
    // EXPORT: key (String)
    // ASSERTION: Find the smallest key
    private String minRec( DSATreeNode currNd )
    {
        String key = "";
        if( currNd.left != null )
            key = minRec( currNd.left );
        else 
            key = currNd.key; 
        return key;
    } 

    // SUBMODULE: maxRec
    // IMPORT: currNd (DSATreeNode)
    // EXPORT: key (String)
    // ASSERTION: Find the largest key
    private String maxRec( DSATreeNode currNd )
    {
        String key = "";
        if( currNd.right != null )
            key = maxRec( currNd.right );
        else
            key = currNd.key;
        return key;
    }

    // SUBMODULE: heightRec
    // IMPORT: currNd (DSATreeNode)
    // EXPORT: currHeight (Integer)
    // ASSERTION: Get the height of the tree
    private int heightRec( DSATreeNode currNd )
    {
        int currHeight = 0, iLeftHeight = 0, iRightHeight = 0;

        if( currNd == null )
            currHeight = - 1;
        else
        { 
            iLeftHeight = heightRec( currNd.left );
            iRightHeight = heightRec( currNd.right );
            currHeight = max(iLeftHeight, iRightHeight) + 1;
        }
        return currHeight;
    }

    // SUBMODULE: max
    // IMPORT: x (Integer), y (Integer)
    // EXPORT: maximum (Integer)
    // ASSERTION: Find the larger value between x and y
    private int max( int x, int y )
    {
        int maximum = 0;
        if( x > y )
            maximum = x;
        else
            maximum = y;
        return maximum;
    }

    // SUBMODULE: balanceRec
    // IMPORT: currNd (DSATreeNode), unbalanced (Integer)
    // EXPORT: unbalanced (Integer)
    private int balanceRec( DSATreeNode currNd , int unbalanced )
    {
        if( currNd != null )
        {
            if( (currNd.left != null && currNd.right == null) || (currNd.left == null && currNd.right != null) )
                unbalanced++;

            unbalanced = balanceRec( currNd.left, unbalanced );
            unbalanced = balanceRec( currNd.right, unbalanced );
        }
        return unbalanced;
    }
    // C,B,D,A,F
}
