/**
 *
 * @author curstinjr
 */
package za.ac.cput.stock.management.dbconnection;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class DBConnectionTest
{
    
    public DBConnectionTest()
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
        
        String expResult = "StockManagementDB".toUpperCase();
        String result = instance.getDerbyConnection().getSchema();
        
        assertEquals(expResult, result);
    }
}
