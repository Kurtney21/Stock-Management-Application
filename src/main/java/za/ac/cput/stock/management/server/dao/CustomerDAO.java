/**
 * @author Curstin Jade Rose (220275408)
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year ADP 262s
 */
package za.ac.cput.stock.management.server.dao;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import za.ac.cput.stock.management.server.dbconnection.DBConnection;
import za.ac.cput.stock.management.common.*;

public class CustomerDAO implements DAO<Customer>
{
    private ArrayList<Customer> customers;
    private Connection conn;
    
    public CustomerDAO() throws SQLException
    {
        this.conn = new DBConnection().getDerbyConnection();
    }
    
    @Override
    public boolean add(Customer cus)
    {
        boolean isAdd = false;
        
        String query = "INSERT INTO CUSTOMERS "
                    + "(CUSTOMER_NAME, CUSTOMER_LASTNAME, CUSTOMER_EMAIL) "
                    + "VALUES (?,?,?)";
        
        try (var pst = this.conn.prepareStatement(query))
        {
            pst.setString(1, cus.getName());
            pst.setString(2, cus.getLastname());
            pst.setString(3, cus.getEmail());
            
            pst.executeUpdate();
            
            isAdd = true;
        }
        catch (SQLException sqle)
        {
            System.out.println("add customer error db: " + sqle.getMessage());
        }
        return isAdd;
    }
    
    

    @Override
    public Customer delete(Customer t)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean update(Customer t)
    {
        String updateQuery = "UPDATE CUSTOMERS SET"
                    + " CUSTOMER_NAME = ?, CUSTOMER_LASTNAME = ?, CUSTOMER_EMAIL = ?"
                    + " WHERE CUSTOMER_ID = ?";
        boolean isUpdate = false;
        try (PreparedStatement ps = conn.prepareStatement(updateQuery)){
            ps.setString(1, t.getName());
            ps.setString(2, t.getLastname());
            ps.setString(3, t.getEmail());
            ps.setInt(4, t.getCustomerId());
            
            ps.executeUpdate();
            isUpdate = true;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isUpdate;
    }

    @Override
    public List<Customer> getAll()
    {
        this.customers = new ArrayList<>();
        
        String query = "SELECT * FROM Customers ORDER BY Customer_Name";
        
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
    
//    public List<Transaction> getTransactionByCustomerID(int id)
//    {
//        List transaction = new ArrayList<>();
//        
//        String query = "SELECT * FROM TRANSACTIONS WHERE CUSTOMER_ID = ? "
//                + "ORDER BY TRANSACTION_ID";
//        
//        try (var pst = this.conn.prepareStatement(query))
//        {
//            pst.setInt(1, id);
//            
//            ResultSet rs = pst.executeQuery();
//            
//            while (rs.next())
//            {
//                int productId = rs.getInt(1);
//                String categoryName = rs.getString(2);
//                String vendor = rs.getString(3);
//                String productName = rs.getString(4);
//                int stockQuantity = rs.getInt(5);
//                double price = rs.getDouble(6);
//                
//                var obj = new Product(
//                        productId, 
//                        productName, 
//                        categoryName,
//                        vendor,
//                        stockQuantity, 
//                        price);
//                
//                this.productsCategory.add(obj);
//            }
//            rs.close();
//        }
//        catch (SQLException sqle)
//        {
//            sqle.printStackTrace();
//        }
//        return productsCategory;
//    }
}
