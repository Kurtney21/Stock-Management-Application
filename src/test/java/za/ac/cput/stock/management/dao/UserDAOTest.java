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
import za.ac.cput.stock.management.model.User;
import za.ac.cput.stock.management.model.UserRole;


public class UserDAOTest
{
    private ArrayList<User> users;
    private UserDAO userDAO;
    private User user;
    
    public UserDAOTest()
    {
    }
    
    @BeforeEach
    public void setUp() throws SQLException
    {
        this.userDAO = new UserDAO();
        this.user = new User(1, "admin_1", "admin", UserRole.ADMIN);
        
        this.users = new ArrayList<>();
        this.users.add(user);
    }
    
    @AfterEach
    public void tearDown()
    {
    }

    /**
     * Test of add method, of class UserDAO.
     */
    @Test
    public void testAdd()
    {
        System.out.println("add");
        var expResult = user;
        var result = userDAO.add(user);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getAll method, of class UserDAO.
     */
    @Test
    public void testGetAll()
    {
        System.out.println("getAll");
        var expResult = users.get(0);
        var result = userDAO.getAll().get(0);
        
        assertEquals(expResult, result);
    }
}
