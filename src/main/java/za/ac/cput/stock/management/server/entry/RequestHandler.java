/**
 * @author Curstin Jade Rose (220275408)
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year ADP 262s
 */
package za.ac.cput.stock.management.server.entry;

import java.sql.SQLException;
import java.util.List;
import za.ac.cput.stock.management.common.Customer;
import za.ac.cput.stock.management.common.User;
import za.ac.cput.stock.management.server.dao.CustomerDAO;
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
    private CustomerDAO customerDAO;
    
    public RequestHandler()
    {
        try
        {
            userDAO = new UserDAO();
            customerDAO = new CustomerDAO();
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
    
    public List<Customer> requestListOfCustomers(){
        List<Customer> custList = this.customerDAO.getAll();
        return custList;
    }
    
    public List<User> requestListOfUsers(){
        List<User> userList = this.userDAO.getAll();
        return userList;
    }
    
    public Customer addCustomer(Customer customer){
        customer = this.customerDAO.add(customer);
        System.out.println("Added!!!");
        return customer;
    }
}
