/**
 *
 * @author curstinjr
 */
package za.ac.cput.stock.management.server.dao;

import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import za.ac.cput.stock.management.common.Customer;
import za.ac.cput.stock.management.common.Product;
import za.ac.cput.stock.management.common.User;
import za.ac.cput.stock.management.common.UserRole;


public class TransactionDAOTest
{
    
    public TransactionDAOTest()
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
     * Test of addTransaction method, of class TransactionDAO.
     */
    @Test
    public void testAddTransaction() throws SQLException
    {
        System.out.println("addTransaction");
        Product product = new Product(19, "Flip File", "Filing", "ABC Stationery", 5, 3.99);
        Customer customer = new Customer(3, "Brianna", "Hildebrande", "bri@email.com");
        User user = new User(3, "user_1", "user", UserRole.USER);
        int quantity = 2;
        double total = 52.85;
        
        TransactionDAO dao = new TransactionDAO();
        dao.addTransaction(product, customer, user, quantity,total);
    }

    /**
     * Test of add method, of class TransactionDAO.
     */
    @Test
    public void testAdd() throws SQLException
    {
        System.out.println("add");
        Object t = null;
        TransactionDAO instance = new TransactionDAO();
        boolean expResult = false;
        boolean result = instance.add(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class TransactionDAO.
     */
    @Test
    public void testDelete() throws SQLException
    {
        System.out.println("delete");
        Object t = null;
        TransactionDAO instance = new TransactionDAO();
        Object expResult = null;
        Object result = instance.delete(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class TransactionDAO.
     */
    @Test
    public void testUpdate() throws SQLException
    {
        System.out.println("update");
        Object t = null;
        TransactionDAO instance = new TransactionDAO();
        boolean expResult = false;
        boolean result = instance.update(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAll method, of class TransactionDAO.
     */
    @Test
    public void testGetAll() throws SQLException
    {
        System.out.println("getAll");
        TransactionDAO dao = new TransactionDAO();
        System.out.println(dao.getAll());
    }

    /**
     * Test of updateStockQuantity method, of class TransactionDAO.
     */
    @Test
    public void testUpdateStockQuantity() throws SQLException
    {
        System.out.println("updateStockQuantity");
        TransactionDAO dao = new TransactionDAO();
        //System.out.println(dao.updateStockQuantity(19, 6));
    }
}
