/**
 * @author Curstin Jade Rose (220275408)
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year ADP 262s
 */
package za.ac.cput.stock.management.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import za.ac.cput.stock.management.dbconnection.DBConnection;
import za.ac.cput.stock.management.model.Customer;

public class CustomerDAO implements DAO<Customer>
{
    private ArrayList<Customer> customers;
    private Connection conn;
    
    public CustomerDAO() throws SQLException
    {
        this.conn = new DBConnection().getDerbyConnection();
    }
    
    @Override
    public Customer add(Customer cus)
    {
        String query = "INSERT INTO Customers VALUES (?,?,?,?)";
        
        try (var pst = this.conn.prepareStatement(query))
        {
            pst.setInt(1, cus.getCustomerId());
            pst.setString(2, cus.getName());
            pst.setString(3, cus.getLastname());
            pst.setString(4, cus.getEmail());
            
            pst.executeUpdate();
        }
        catch (SQLException sqle)
        {
            System.out.println("add customer error db: " + sqle.getMessage());
        }
        return cus;
    }

    @Override
    public Customer delete(Customer t)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Customer update(Customer t)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Customer> getAll()
    {
        this.customers = new ArrayList<>();
        
        String query = "SELECT * FROM Customers";
        
        try (var pst = this.conn.prepareStatement(query);
                ResultSet rs = pst.executeQuery())
        {
            while (rs.next())
            {
                int cusId = rs.getInt(1);
                String cusName = rs.getString(2);
                String cusLastName = rs.getString(3);
                String cusEmail = rs.getString(4);
                
                var obj = new Customer(cusId, cusName, cusLastName, cusEmail);
                
                this.customers.add(obj);
            }
        }
        catch (SQLException sqle)
        {
            System.out.println("read customers error db: " + sqle.getMessage());
        }
        
        return customers;
    }
}
