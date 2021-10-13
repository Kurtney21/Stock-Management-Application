/**
 *
 * @author curstinjr
 */
package za.ac.cput.stock.management.server.dao;

import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import za.ac.cput.stock.management.common.Customer;


public class CustomerDAOTest
{
    
    public CustomerDAOTest()
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
     * Test of add method, of class CustomerDAO.
     */
    @Test
    public void testAdd() throws SQLException
    {
        System.out.println("add");
        Customer cus = null;
        CustomerDAO instance = new CustomerDAO();
        boolean expResult = false;
        boolean result = instance.add(cus);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class CustomerDAO.
     */
    @Test
    public void testDelete() throws SQLException
    {
        System.out.println("delete");
        Customer t = null;
        CustomerDAO instance = new CustomerDAO();
        Customer expResult = null;
        Customer result = instance.delete(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class CustomerDAO.
     */
    @Test
    public void testUpdate() throws SQLException
    {
        System.out.println("update");
        Customer t = null;
        CustomerDAO instance = new CustomerDAO();
        boolean expResult = false;
        boolean result = instance.update(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAll method, of class CustomerDAO.
     */
    @Test
    public void testGetAll() throws SQLException
    {
        System.out.println("getAll");
        CustomerDAO dao = new CustomerDAO();
        System.out.println(dao.getAll());
    }
    
}
