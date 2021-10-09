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


public class CategoryDAOTest
{
    
    public CategoryDAOTest()
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
     * Test of getAll method, of class CategoryDAO.
     */
    @Test
    public void testGetAll() throws SQLException
    {
        System.out.println("getAll");
        CategoryDAO dao = new CategoryDAO();
        System.out.println(dao.getAll());
    }
    
}
