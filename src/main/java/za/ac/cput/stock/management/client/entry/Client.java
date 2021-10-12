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
            startConnection(this.IP, this.PORT);
            createStreams();
        } 
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
    /**
     * Starts a connection and attempts to
     * connect to the server on the specified
     * IP address and port number.
     * 
     * @param ip
     * @param port
     * @throws IOException 
     */
    private void startConnection(String ip, int port) throws IOException
    {
        clientSocket = new Socket(ip, port);
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
    public User requestToLogin(User user)
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
    
    
    List<Customer> list;
    List<User> userList;
    public List<Customer> getListOfCustomer(){
        try
        {
            out.writeObject("requestListOfCustomers");
            out.flush();
            
            list = (List<Customer>)in.readObject();
        }
        catch (IOException | ClassNotFoundException ex )
        {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
    public List<User> getListOfUsers(){
        try
        {
            out.writeObject("requestListOfUsers");
            out.flush();
            
            userList = (List<User>)in.readObject();
        }
        catch (IOException | ClassNotFoundException ex )
        {
            System.out.println(ex.getMessage());
        }
        return userList;
    }
    
    public Customer addCustomer(Customer customer){
        try
        {
            out.writeObject("requestToAddCustomer");
            out.flush();
            out.writeObject(customer);
            out.flush();
            
            customer = (Customer)in.readObject();
        }
        catch (IOException | ClassNotFoundException ex )
        {
            System.out.println(ex.getMessage());
        }
        return customer;
    }
}
