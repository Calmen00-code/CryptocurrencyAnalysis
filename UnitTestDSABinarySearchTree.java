import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;
import java.io.*;

@RunWith(JUnit4.class)
public class UnitTestDSABinarySearchTree 
{
    ByteArrayOutputStream capOut = null;    
    String output = "";
    DSABinarySearchTree tree;

    @Before
    public void setUp()
    {
        capOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capOut));
        tree = new DSABinarySearchTree();
    }

    @After
    public void tearDown()
    {
        output = "";
        capOut = null;
        tree = null;
    }

    @Test
    public void testInsert()
    {   
        tree.insert("F", "Calmen");
        tree.insert("B", "James");
        tree.insert("D", "Dorcas");
        tree.insert("A", "Alex");
        tree.insert("C", 180);
        tree.insert("E", 102.23);
        tree.insert("G", 200321);
        tree.insert("I", 43.231);
        tree.insert("H", 55);
    }

    @Test
    public void testInsertInvalid()
    {
        tree.insert(null, "Calmen");
        output = capOut.toString(); 
        assertEquals( "", output );
    }

    @Test
    public void testInsertExistingKey()
    {
        testInsert(); // Use back the data insert

        tree.insert("G", "Diff Data but same Key"); 
        output = capOut.toString();        
        assertEquals( "", output );     
    }

    @Test
    public void testFind()
    {
        Object data = null;
        testInsert(); // Use back the data insert

        data = tree.find("F");
        assertEquals("Calmen", (String)data);

        data = tree.find("B");
        assertEquals("James", (String)data);

        data = tree.find("C");
        assertEquals(180, (int)data);

        data = tree.find("E");
        assertEquals(102.23, (double)data, 0.001);
    }

    @Test
    public void testNotFound()
    {
        Object data = null;
        testInsert(); // Use back the data insert

        data = tree.find("Invalid_Key");
        output = capOut.toString(); 
        assertEquals( "", output );
    }

    @Test
    public void testTraverseInOrder()
    {
        String str = "";
        testInsert(); // Use back the data insert
        
        str = tree.inOrderTraverse();
        assertEquals("Alex James 180 Dorcas 102.23 Calmen 200321 55 43.231 ", str);
    }

    @Test
    public void testTraversePreOrder()
    {
        String str = "";
        testInsert(); // Use back the data insert

        str = tree.preOrderTraverse();
        assertEquals("Calmen James Alex Dorcas 180 102.23 200321 43.231 55 ", str);
    }

    @Test
    public void testTraversePostOrder()
    {
        String str = "";
        testInsert(); // Use back the data insert

        str = tree.postOrderTraverse();
        assertEquals("43.231 55 200321 Calmen 102.23 Dorcas 180 James Alex ", str);
    }

    @Test
    public void testMin()
    {
        String min = "";
        testInsert(); // Use back the data insert

        min = tree.minKey();
        assertEquals("A", min);
    }

    @Test
    public void testMax()
    {
        String max = "";
        testInsert(); // Use back the data insert

        max = tree.maxKey();
        assertEquals("I", max);
    }

    @Test
    public void testHeight()
    {
        testInsert(); // Use back the data insert

        int height = tree.height();
        assertEquals(3, height);
    }

    @Test
    public void testBalance()
    {
        testInsert(); // Use back the data insert

        int balance = tree.balance();
        assertEquals(80, balance);
    }

    @Test
    public void testDeleteOneChild()
    {
        testInsert(); // Use back the data insert
    
        tree.delete("G");
        String str = tree.inOrderTraverse();
        assertEquals("Alex James 180 Dorcas 102.23 Calmen 55 43.231 ", str);
    }

    @Test
    public void testDeleteTwoChild()
    {
        testInsert(); // Use back the data insert

        tree.delete("D");
        String str = tree.inOrderTraverse();
        assertEquals("Alex James 180 102.23 Calmen 200321 55 43.231 ", str);
    }

    @Test
    public void testDeleteNoChild()
    {
        testInsert(); // Use back the data insert

        tree.delete("H");
        String str = tree.inOrderTraverse();
        assertEquals("Alex James 180 Dorcas 102.23 Calmen 200321 43.231 ", str);
    }
// 15 Tests
}
