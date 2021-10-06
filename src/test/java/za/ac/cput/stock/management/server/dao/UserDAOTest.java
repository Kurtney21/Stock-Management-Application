/**
 *
 * @author curstinjr
 */
package za.ac.cput.stock.management.server.dao;

import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.stock.management.common.User;
import za.ac.cput.stock.management.common.UserRole;


public class UserDAOTest
{
    private User user;
    
    public UserDAOTest()
    {
    }
    
    @BeforeEach
    public void setUp()
    {
        this.user = new User("admin_1", "admin");
    }
    
    @AfterEach
    public void tearDown()
    {
    }

    /**
     * Test of getUserLogins method, of class UserDAO.
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetUserLogins() throws SQLException
    {
        System.out.println("checkAuthentication");
        UserDAO dao = new UserDAO();
        
        User expResult = new User(0, "admin_1", "admin", UserRole.ADMIN, true);
        var result = dao.getUserLogins(user);
        
        assertEquals(expResult, result);
    }
}
