/**
 *
 * @author curstinjr
 */
package za.ac.cput.stock.management.server.dao;

import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import za.ac.cput.stock.management.common.Customer;


public class CustomerDAOTest
{
    
    public CustomerDAOTest()
    {
    }

    @BeforeAll
    public static void setUpClass() throws Exception {
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
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
        Customer cus = new Customer("Jake","Blake","jakeblake@pin.com");
        CustomerDAO instance = new CustomerDAO();
        boolean expResult = true;
        boolean result = instance.add(cus);
        assertEquals(expResult, result);
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
        Customer t = new Customer(1,"Joe", "Joe", "joejoe@mkf.com");
        CustomerDAO instance = new CustomerDAO();
        boolean expResult = true;
        boolean result = instance.update(t);
        assertEquals(expResult, result);
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
