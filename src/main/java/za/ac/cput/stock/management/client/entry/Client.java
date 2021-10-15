/**
 * @author Curstin Jade Rose (220275408)
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year ADP 262s
 */
package za.ac.cput.stock.management.client.entry;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import za.ac.cput.stock.management.common.Customer;
import za.ac.cput.stock.management.common.Invoice;
import za.ac.cput.stock.management.common.Product;
import za.ac.cput.stock.management.common.Sale;
import za.ac.cput.stock.management.common.Transaction;
import za.ac.cput.stock.management.common.User;

/**
 * The is the Client endpoint used to communicate
 * to the server. It request for 
 * 
 * @author curstinjr
 */
public class Client
{
    private final String IP = "127.0.0.1";  // The localhost addresss
    private final int PORT = 4444;          // Port number to communicate on
    
    private Socket clientSocket;            // Client socket
    private ObjectInputStream in;
    private ObjectOutputStream out;
    
    public Client()
    {
        try
        {
            startConnection();
        } 
        catch (IOException ex)
        {
            
        }
    }
    
    /**
     * Starts a connection and attempts to
     * connect to the server on the specified
     * IP address and port number.
     * 
     * @throws IOException 
     */
    private void startConnection() throws IOException
    {
        clientSocket = new Socket(this.IP, this.PORT);
        clientSocket.setKeepAlive(true);
        createStreams();
    }
    
    /**
     * Create object communication streams to send 
     * and receive object data.
     */
    private void createStreams()
    {
        try
        {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
        } 
        catch (IOException ex)
        {
           ex.printStackTrace();
        }
    }
    
    /**
     * Makes a request to server to login 
     * and waits it to send a user object.
     * Returns a user object or null.
     * 
     * @param user
     * @return 
     */
    public User requestLogin(User user)
    {
        User validUser = null;
        
        try
        {
            out.writeObject("requestToLogin");
            out.flush();
            out.writeObject(user);
            out.flush();
            
            validUser = (User)in.readObject();
        }
        catch (IOException | ClassNotFoundException ex )
        {
            System.out.println(ex.getMessage());
        }
        return validUser;
    }
    
    public List<String> requestCategories()
    {
        List<String> categories = null;
        try
        {
            out.writeObject("requestCategories");
            out.flush();
            
            categories = (List<String>) in.readObject();
            
        } catch (IOException | ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        return categories;
    }
    
    public List<Product> requestProducts()
    {
        List products = null;
        try
        {
            out.writeObject("requestProducts");
            out.flush();
            
            products = (List<Product>) in.readObject();
        } 
        catch (IOException | ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        return products;
    }
    
    public List<String> requestVendors()
    {
        List<String> vendors = null;
        try
        {
            out.writeObject("requestVendors");
            out.flush();
            
            vendors = (List<String>) in.readObject();
        } 
        catch (IOException | ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        return vendors;
    }
    
    public List<Customer> requestCustomers()
    {
        List<Customer> customers = null;
        
        try
        {
            out.writeObject("requestCustomers");
            out.flush();
            
            customers = (List<Customer>) in.readObject();
        } 
        catch (IOException | ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        return customers;
    }
    
    public List<User> requestUsers()
    {
        List<User> users = null;
        
        try
        {
            out.writeObject("requestUsers");
            out.flush();
            
            users = (List<User>) in.readObject();
        } 
        catch (IOException | ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        return users;
    }
    
    public List<Transaction> requestTransactions()
    {
        List<Transaction> transactions = null;
        
        try
        {
            out.writeObject("requestTransactions");
            out.flush();
            
            transactions = (List<Transaction>) in.readObject();
        }
        catch (IOException | ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        return transactions;
    }
    
    public boolean requestAddProduct(Product product)
    {
        boolean isAddProduct = false;
        
        try
        {
            out.writeObject("requestAddProduct");
            out.flush();
            out.writeObject(product);
            out.flush();
            
            isAddProduct = in.readBoolean();
        } 
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return isAddProduct;
    }
    
    public int requestAddTransaction(
            Product product, 
            Customer customer,
            User user,
            int totalQuantity,
            double totalPrice)
    {
        int transactionId = 0;
        
        try
        {
            out.writeObject("requestAddTransaction");
            out.flush();
            out.writeObject(product);
            out.flush();
            out.writeObject(customer);
            out.flush();
            out.writeObject(user);
            out.flush();
            
            out.writeInt(totalQuantity);
            out.flush();
            out.writeDouble(totalPrice);
            out.flush();
            
            transactionId = in.readInt();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        
        return transactionId;
    }
    
    public boolean requestUpdateProduct(Product product)
    {
        boolean isUpdateProduct = false;
        
        try
        {
            out.writeObject("requestUpdateProduct");
            out.flush();
            out.writeObject(product);
            out.flush();
            
            isUpdateProduct = in.readBoolean();
        } 
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return isUpdateProduct;
    }
    
    public boolean requestUpdateStockQuantity(Transaction transaction)
    {
        boolean isUpdateStockQuantity = false;
        
        try
        {
            out.writeObject("requestUpdateStockQuantity");
            out.flush();
            out.writeObject(transaction);
            out.flush();
            
            isUpdateStockQuantity = in.readBoolean();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        return isUpdateStockQuantity;
    }
    
    public List<Product> requestProductsByCategory(String category)
    {
        List<Product> productsByCategory = null;
        
        try
        {
            out.writeObject("requestProductsByCategory");
            out.flush();
            out.writeObject(category);
            out.flush();
            
            productsByCategory = (List<Product>) in.readObject();
        } 
        catch (IOException | ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        
        return productsByCategory;
    }
    
    public boolean requestAddCustomer(Customer customer)
    {
        boolean isAddCustomer = false;
        
        try
        {
            out.writeObject("requestAddCustomer");
            out.flush();
            out.writeObject(customer);
            out.flush();
            
            isAddCustomer = in.readBoolean();
        } 
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return isAddCustomer;
    }
    
    public boolean requestUpdateCustomer(Customer customer)
    {
        boolean isUpdateCustomer = false;
        
        try
        {
            out.writeObject("requestUpdateCustomer");
            out.flush();
            out.writeObject(customer);
            out.flush();
            
            isUpdateCustomer = in.readBoolean();
        } 
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return isUpdateCustomer;
    }
    
    public boolean requestAddUser(User user)
    {
        boolean isAddUser = false;
        
        try
        {
            out.writeObject("requestAddUser");
            out.flush();
            out.writeObject(user);
            out.flush();
            
            isAddUser = in.readBoolean();
        } 
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return isAddUser;
    }
    
        public boolean requestUpdateUser(User user)
    {
        boolean isUpdateUser = false;
        
        try
        {
            out.writeObject("requestUpdateUser");
            out.flush();
            out.writeObject(user);
            out.flush();
            
            isUpdateUser = in.readBoolean();
        } 
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return isUpdateUser;
    }
        
    public List<Sale> requestSales()
    {
        List<Sale> sales = null;
        
        try
        {
            out.writeObject("requestSales");
            out.flush();
            
            sales = (List<Sale>) in.readObject();
        } 
        catch (IOException | ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        return sales;
    }    
    
     public List<Invoice> requestCustomerInvoices(String name)
    {
        List<Invoice> invoice = null;
        
        try
        {
            out.writeObject("requestCustomerInvoice");
            out.flush();
            
            invoice = (List<Invoice>) in.readObject();
        } 
        catch (IOException | ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        return invoice;
    }   
    
}
