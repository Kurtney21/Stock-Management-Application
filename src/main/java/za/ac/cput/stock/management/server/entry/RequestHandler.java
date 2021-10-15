/**
 * @author Curstin Jade Rose (220275408)
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year ADP 262s
 */
package za.ac.cput.stock.management.server.entry;

import java.sql.SQLException;
import java.util.List;
import za.ac.cput.stock.management.common.Customer;
import za.ac.cput.stock.management.common.Invoice;
import za.ac.cput.stock.management.common.Product;
import za.ac.cput.stock.management.common.Sale;
import za.ac.cput.stock.management.common.Transaction;
import za.ac.cput.stock.management.common.User;
import za.ac.cput.stock.management.server.dao.CategoryDAO;
import za.ac.cput.stock.management.server.dao.CustomerDAO;
import za.ac.cput.stock.management.server.dao.ProductDAO;
import za.ac.cput.stock.management.server.dao.TransactionDAO;
import za.ac.cput.stock.management.common.User;
import za.ac.cput.stock.management.server.dao.CustomerDAO;
import za.ac.cput.stock.management.server.dao.SalesReportDAO;
import za.ac.cput.stock.management.server.dao.UserDAO;
import za.ac.cput.stock.management.server.dao.VendorDAO;

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
    private ProductDAO productDAO;
    private VendorDAO vendorDAO;
    private CustomerDAO customerDAO;
    private TransactionDAO transactionDAO;
    private SalesReportDAO salesReportDAO;
    
    public RequestHandler()
    {
        try
        {
            userDAO = new UserDAO();
            categoryDAO = new CategoryDAO();
            productDAO = new ProductDAO();
            vendorDAO = new VendorDAO();
            customerDAO = new CustomerDAO();
            transactionDAO = new TransactionDAO();
            salesReportDAO  = new SalesReportDAO();
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
    
    public List getCategories()
    {
        return this.categoryDAO.getAll();
    }
    
    public List getVendors()
    {
        return this.vendorDAO.getAll();
    }
    
    public List getProducts()
    {
        return this.productDAO.getAll();
    }
    
    public List getCustomers()
    {
        return this.customerDAO.getAll();
    }
    
    public List getUsers()
    {
        return this.userDAO.getAll();
    }
    
    public List getTransactions()
    {
        return this.transactionDAO.getAll();
    }
    
    public List<Product> getProductsByCategory(String category)
    {
        return this.productDAO.getProductsByCategory(category);
    }
    
    public boolean addProduct(Product product)
    {
        return this.productDAO.add(product);
    }
    
    public boolean addCustomer(Customer customer)
    {
        return this.customerDAO.add(customer);
    }
    
    public boolean addUser(User user)
    {
        return this.userDAO.add(user);
    }
    
    public int addTransaction(
            Product product, 
            Customer customer, 
            User user,
            int totalQuantity,
            double totalPrice)
    {
        return this.transactionDAO.addTransaction(
                product, 
                customer, 
                user,
                totalQuantity,
                totalPrice);
    }
    
    public boolean updateProduct(Product product)
    {
        return this.productDAO.update(product);
    }
    
    public boolean updateStockQuantity(Transaction transaction)
    {
        return this.transactionDAO.updateStockQuantity(transaction);
    }
    
    public boolean updateCustomer(Customer customer)
    {
        return this.customerDAO.update(customer);
    }
    
    public boolean updateUser(User user)
    {
        return this.userDAO.update(user);
    }

    public List getSales(){
        return this.salesReportDAO.getAll();
    }
    
    public List<Invoice> getCustomerInvoices(String customerName){
        return this.salesReportDAO.getCustomerInvoice(customerName);
    }
}
