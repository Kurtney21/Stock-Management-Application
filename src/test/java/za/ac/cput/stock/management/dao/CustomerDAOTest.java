/**
 *
 * @author curstinjr
 */
package za.ac.cput.stock.management.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.stock.management.model.Customer;


public class CustomerDAOTest
{
    private ArrayList<Customer> customers; 
    private CustomerDAO cusDAO;
    private Customer customer;
    
    public CustomerDAOTest()
    {
    }
    
    @BeforeEach
    public void setUp() throws SQLException
    {
        this.cusDAO = new CustomerDAO();
        this.customer = new Customer(1, "Curstin", "Rose", "curstinr@email.com");
        this.customers = new ArrayList<>();
        this.customers.add(customer);
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
        var expResult = customer;
        var result = cusDAO.add(customer);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of read method, of class CustomerDAO.
     */
    @Test
    public void testGetAll()
    {
        System.out.println("read");
        var expResult = customers.get(0);
        var result = cusDAO.getAll().get(0);
        
        assertEquals(expResult, result);
    }
}
