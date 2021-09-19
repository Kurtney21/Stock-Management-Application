/**
 *
 * @author curstinjr
 */
package za.ac.cput.stock.management.dbconnection;

import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class DBConnectionTest
{
    
    public DBConnectionTest()
    {
    }
    
    @BeforeAll
    public static void setUpClass()
    {
    }
    
    @AfterAll
    public static void tearDownClass()
    {
    }
    
    @BeforeEach
    public void setUp()
    {
    }
    
    @AfterEach
    public void tearDown()
    {
    }

    /**
     * Test of getDerbyConnection method, of class DBConnection.
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetDerbyConnection() throws SQLException
    {
        System.out.println("getDerbyConnection");
        
        DBConnection instance = new DBConnection();
        
        String expResult = "ROOT";
        String result = instance.getDerbyConnection().getSchema();
        
        assertEquals(expResult, result);
    }
    
}
