/**
 *
 * @author curstinjr
 */
package za.ac.cput.stock.management.server.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
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

    @BeforeAll
    public static void setUpClass() throws Exception {
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
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

    /**
     * Test of add method, of class UserDAO.
     */
    @Test
    public void testAdd() throws SQLException {
        System.out.println("add");
        User user = new User("user_3","user",UserRole.USER,true);
        UserDAO instance = new UserDAO();
        boolean expResult = true;
        boolean result = instance.add(user);
        assertEquals(expResult, result);
    }

    /**
     * Test of delete method, of class UserDAO.
     */
    @Test
    public void testDelete() throws SQLException {
        System.out.println("delete");
        User user = null;
        UserDAO instance = new UserDAO();
        User expResult = null;
        User result = instance.delete(user);
        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class UserDAO.
     */
    @Test
    public void testUpdate() throws SQLException {
        System.out.println("update");
        User user = new User(4,"admin", "admin", UserRole.ADMIN,true);
        UserDAO instance = new UserDAO();
        boolean expResult = true;
        boolean result = instance.update(user);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAll method, of class UserDAO.
     */
    @Test
    public void testGetAll(){
        try {
            System.out.println("getAll");
            UserDAO instance = new UserDAO();
            List<User> expResult = null;
            List<User> result = instance.getAll();
            assertEquals(expResult, result);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
