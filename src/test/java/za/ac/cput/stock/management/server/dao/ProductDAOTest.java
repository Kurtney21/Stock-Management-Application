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
import za.ac.cput.stock.management.common.Product;


public class ProductDAOTest
{
    
    public ProductDAOTest()
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
     * Test of add method, of class ProductDAO.
     */
    @Test
    public void testAdd() throws SQLException
    {
        System.out.println("add");
        Product product = new Product("File", "Filing", "ABC Stationery", 10, 6.99);
        ProductDAO dao = new ProductDAO();
        System.out.println(dao.add(product));
    }

    /**
     * Test of delete method, of class ProductDAO.
     */
    @Test
    public void testDelete() throws SQLException
    {
        System.out.println("delete");
        Product t = null;
        ProductDAO instance = new ProductDAO();
        Product expResult = null;
        Product result = instance.delete(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class ProductDAO.
     */
    @Test
    public void testUpdate() throws SQLException
    {
        System.out.println("update");
        ProductDAO dao = new ProductDAO();
        Product product = new Product(
                19, 
                "Flip File", 
                "Filing", 
                "ABC Stationery", 
                55, 
                3.99);
        System.out.println(dao.update(product));
    }

    /**
     * Test of getAll method, of class ProductDAO.
     */
    @Test
    public void testGetAll() throws SQLException
    {
        System.out.println("getAll");
        ProductDAO dao = new ProductDAO();
        System.out.println(dao.getAll());
    }

    /**
     * Test of getProductsByCategory method, of class ProductDAO.
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetProductsByCategory() throws SQLException
    {
        System.out.println("getProductsByCategory");
        ProductDAO dao = new ProductDAO();
        System.out.println(dao.getProductsByCategory("Filing"));
    }
    
}
