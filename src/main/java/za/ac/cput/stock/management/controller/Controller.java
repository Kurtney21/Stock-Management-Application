/**
 *
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year
 */

package za.ac.cput.stock.management.controller;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import za.ac.cput.stock.management.client.entry.Client;
import za.ac.cput.stock.management.client.gui.MainFrame;
import za.ac.cput.stock.management.common.Customer;
import za.ac.cput.stock.management.common.Product;
import za.ac.cput.stock.management.common.Transaction;
import za.ac.cput.stock.management.common.User;


public class Controller 
{
    static final Client client = new Client();
    private MainFrame mainFrame;
    private static User validUser;              // valid user object
    
    public User checkAuthentication(
            String userName, 
            String password, 
            JFrame frame)
    {
        // create user object to check if user in the database.
        var user = new User(userName, password);
        
        try
        {
            // returns user from the database
            validUser = client.requestLogin(user);
        }
        catch(NullPointerException ex)
        {
            JOptionPane.showMessageDialog(frame, "Server offline.");
            return null;
        }
        
        if (validUser != null)
        {
            mainFrame = new MainFrame();
            // admin login
            if (validUser.getUserRole().getRoleCode() == 1 
                    && validUser.isStatus())
            {
                JOptionPane.showMessageDialog(frame, 
                        "Welcome " + userName + "!");
                mainFrame.setVisible(true);
                mainFrame.getLoginIcnLbl().setText(userName);
                mainFrame.getWelcomeLbl().setText("Welcome " + userName + "!!");
                frame.dispose();
            }
            // employee login
            else if (validUser.getUserRole().getRoleCode() == 2 
                    && validUser.isStatus())
            {
                JOptionPane.showMessageDialog(frame, 
                        "Welcome " + userName + "!");
                mainFrame.setVisible(true);
                mainFrame.getAdminMenu().setVisible(false);
                mainFrame.getLoginIcnLbl().setText(userName);
                frame.dispose();
            }
            // account disabled
            else 
            {
                JOptionPane.showMessageDialog(frame, 
                    "Account disabled.");
            }
        }
        else
        {
            // the user doesnt have an account
            JOptionPane.showMessageDialog(frame, 
                    "Incorrect username or password.\nTry again.");
        }
        return validUser;
    }
    
    public void onStart(){
        populateTables();
    }
    
    public void populateTables()
    {
    }
    
    public List<Product> getProducts()
    {
        return client.requestProducts();
    }
    
    //populates Customer Table
    
    
    public Object [] getCategories()
    {
        return client.requestCategories().toArray();
    }
    
    public Object [] getVendors()
    {
        return client.requestVendors().toArray();
    }
    
    public List<Customer> getCustomers()
    {
        return client.requestCustomers();
    }
    
    public List<User> getUsers()
    {
        return client.requestUsers();
    }

    public User getValidUser()
    {   
        return validUser;
    }
    
    public List<Transaction> getTransactions()
    {
        return client.requestTransactions();
    }
    
    public Object [] getProductNames()
    {
        var productLen = getProducts().size();
        
        Object [] productNames = new Object [productLen];
        
        for (int i = 0; i < productLen; i++)
        {
            String productName = getProducts().get(i).getProuductName();
            productNames[i] = productName;
        }
            
        return productNames;
    }
    
    public Object [] getCustomerNames()
    {
        var customers = client.requestCustomers();
        
        var customersLen = customers.size();
        
        Object [] customerNames = new Object [customersLen];
        
        for (int i = 0; i < customersLen; i++)
        {
            String customerName = customers.get(i).getName();
            customerNames[i] = customerName;
        }
        
        return customerNames;
    }
    
    public void addProduct(
            String productName, 
            int stockQuantity,
            double productPrice,
            String vendor, 
            String category)
    {
        Product product = new Product(
                productName, 
                category, 
                vendor, 
                stockQuantity,
                productPrice);
        
        boolean isAddProduct = client.requestAddProduct(product);
        
        if(isAddProduct)
        {
            JOptionPane.showMessageDialog(mainFrame, "Inserted a new record.");
}
        else
        {
            JOptionPane.showMessageDialog(mainFrame, "Error.");
        }
    }
    
    public int addTransaction(
            String productName,
            int totalQuantity,
            double totalPrice,
            String customerName,
            String userName)
    {
        Product product = null;
        Customer customer = null;
        User user = null;
        
        for (int i = 0; i < getProducts().size(); i++)
        {
            if (productName.equals(getProducts().get(i).getProuductName()))
            {
                product = getProducts().get(i);
            }
        }
        
        for (int i = 0; i < getCustomers().size(); i++)
        {
            if (customerName.equals(getCustomers().get(i).getName()))
            {
                customer = getCustomers().get(i);
            }
        }
        
        for (int i = 0; i < getUsers().size(); i++)
        {
            if (userName.equals(getUsers().get(i).getUsername()))
            {
                user = getUsers().get(i);
            }
        }
        return client.requestAddTransaction(
                product, customer, user, totalQuantity, totalPrice);
    }
    
    public void updateProduct(
            int productId,
            String name,
            String category,
            String vendor,
            int stockQuantity,
            double productPrice)
    {
        Product product = new Product(
                productId,
                name, 
                category, 
                vendor, 
                stockQuantity, 
                productPrice);
        
        
        boolean isUpdateProduct = client.requestUpdateProduct(product);
        
        if (isUpdateProduct)
        {
            JOptionPane.showMessageDialog(mainFrame, "Updated record.");
        }
        else
        {
            JOptionPane.showMessageDialog(mainFrame, "Error.");
        }
    }
    
    public void updateStockQuantity(int productId, int transactionId)
    {
        
        Transaction transaction = new Transaction(transactionId, productId);
        boolean isUpdateProduct = client.requestUpdateStockQuantity(transaction);
        
        if (isUpdateProduct)
        {
            JOptionPane.showMessageDialog(mainFrame, "Updated stock.");
        }
        else
        {
            JOptionPane.showMessageDialog(mainFrame, "Error.");
        }
    }
    
    
    public List<Product> getProductsByCategory(String category)
    {
        return client.requestProductsByCategory(category);
    }
    
    //Add Customer
    public void addCustomer(
            String custName,
            String custLastname,
            String custEmail)
    {
        Customer customer = new Customer(custName, custLastname, custEmail);
        client.requestAddCustomer(customer);
    }
}
