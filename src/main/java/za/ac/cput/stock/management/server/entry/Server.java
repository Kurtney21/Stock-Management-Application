/**
 * @author Curstin Jade Rose (220275408)
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year ADP 262s
 */
package za.ac.cput.stock.management.server.entry;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.List;
import za.ac.cput.stock.management.common.Customer;
import za.ac.cput.stock.management.common.User;

/**
 * This is the server application that processes 
 * the client's requests and sends the appropriate 
 * response back to client side.
 * 
 * @author curstinjr
 */
public class Server 
{
    private final int PORT = 4444;          // Server Port number
    private ServerSocket serverSocket;      // Server Socket
    private Socket clientSocket;            // client/server socket
    private ObjectInputStream in;           
    private ObjectOutputStream out;
    private boolean running = false;        // Server state
    private String request = "";            // Incoming requests from client
    private RequestHandler requestHandler;  // Handles the requests made by client
    
    public Server()
    {
        startServer(this.PORT);
        listen();
        createStream();
        
        requestHandler = new RequestHandler();
    }
    
    /**
     * Initiate the server on a specific port
     * @param port 
     */
    private void startServer(int port)
    {
        try
        {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started. \n"
                    + "Waiting for client to connect...");
        } 
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * Wait for client to connect to the
     * server. Once the connection is made 
     * a pipe is create between the server and 
     * the client.
     */
    private void listen()
    {
        try
        {
            clientSocket = serverSocket.accept();
            System.out.println("Client connected.");
        } 
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
    /**
     * Create object communication streams to send 
     * and receive object data.
     */
    private void createStream()
    {
        try
        {
            // create communication streams
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
        } 
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
    /**
     * Process the client requests and send
     * appropriate response until client disconnects.
     * 
     * @throws IOException 
     */
    public void run() throws IOException
    {
        running = true;
        try 
        {
            do
            {
                request = (String) in.readObject();
                // handles the various request made by the client
                switch(request)
                {
                    case "requestToLogin" -> 
                    {
                        var obj = (User)in.readObject();
                        var userObj = requestHandler.requestToLogin(obj);
                        out.writeObject(userObj);
                        out.flush();
                    }
                    case "requestListOfCustomers" ->
                   {
                        out.writeObject(requestHandler.requestListOfCustomers());
                        out.flush();
                    }
                }
            }
            while (running);
            System.out.println("Server Jumps Out");
        }
        catch (IOException| ClassNotFoundException ex)
        {
            ex.printStackTrace();
        } 
        finally
        {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        }
    }
    
    /**
     * Driver code
     * 
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException
    {
        Server server = new Server();
        server.run();
    }
}
