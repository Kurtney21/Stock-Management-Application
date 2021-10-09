/**
 * @author Curstin Jade Rose (220275408)
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year ADP 262s
 */
package za.ac.cput.stock.management.server.entry;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import za.ac.cput.stock.management.common.User;
import za.ac.cput.stock.management.server.dao.CategoryDAO;
import za.ac.cput.stock.management.server.dao.UserDAO;

/**
 * This class handles the requests received 
 * by the client and calls methods from DAO.
 * 
 * @author curstinjr
 */
public class RequestHandler
{
    private UserDAO userDAO;
    private CategoryDAO categoryDAO;
    
    public RequestHandler()
    {
        try
        {
            userDAO = new UserDAO();
        } 
        catch (SQLException ex)
        {
           ex.printStackTrace();
        }
    }
    /**
     * Calls the UserDAO getUserLogins() that 
     * returns a user object from the database.
     * @param user
     * @return 
     */
    public User requestToLogin(User user)
    {
        User userObj = this.userDAO.getUserLogins(user);
        return userObj;
    }
    
    public List getAllCategories()
    {
        var categories = this.categoryDAO.getAll();
        return categories;
    }
}
