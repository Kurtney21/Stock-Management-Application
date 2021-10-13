/**
 *
 * @author curstinjr
 */
package za.ac.cput.stock.management.client.entry;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import za.ac.cput.stock.management.common.User;


public class ClientTest
{
    
    public ClientTest()
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
     * Test of startConnection method, of class Client.
     */
    @Test
    public void testStartConnection() throws Exception
    {
        System.out.println("startConnection");
        Client instance = new Client();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of requestLogin method, of class Client.
     */
    @Test
    public void testRequestLogin()
    {
        System.out.println("requestLogin");
        User user = null;
        Client instance = new Client();
        User expResult = null;
        User result = instance.requestLogin(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of requestCategories method, of class Client.
     */
    @Test
    public void testRequestCategories()
    {
        System.out.println("getCategories");
        Client client = new Client();
        
        System.out.println(client.requestCategories());
    }
}
