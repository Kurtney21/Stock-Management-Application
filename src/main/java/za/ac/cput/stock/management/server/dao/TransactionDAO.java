/**
 *
 * @author curstinjr
 */
package za.ac.cput.stock.management.server.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import za.ac.cput.stock.management.common.Customer;
import za.ac.cput.stock.management.common.Product;
import za.ac.cput.stock.management.common.Transaction;
import za.ac.cput.stock.management.common.User;
import za.ac.cput.stock.management.server.dbconnection.DBConnection;


public class TransactionDAO implements DAO
{
    private Connection conn;
    private List transactions;
    
    public TransactionDAO() throws SQLException
    {
        this.conn = new DBConnection().getDerbyConnection();
    }

    public int addTransaction(
            Product product, 
            Customer customer, 
            User user,
            int totalQuantity,
            double totalPrice)
    {
        int transactionId = 0;
        
        String query = "INSERT INTO Transactions ("
                + "Product_Id, "
                + "Customer_Id,"
                + "User_Id,"
                + "Transaction_Quantity,"
                + "Transaction_Total)"
                + "VALUES (?,?,?,?,?)";
        
        try (var pst = this.conn.prepareStatement(query, 
                new String []{"Transaction_Id"}))
        {
            pst.setInt(1, product.getProductId());
            pst.setInt(2, customer.getCustomerId());
            pst.setInt(3, user.getUserId());
            pst.setInt(4, totalQuantity);
            pst.setDouble(5, totalPrice);
            
            pst.executeUpdate();
            
            ResultSet rs = pst.getGeneratedKeys();
            
            if (rs.next())
            {
                transactionId = rs.getInt(1);
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        return transactionId;
    }
    
    public boolean updateStockQuantity(Transaction transaction)
    {
        boolean isUpdateStockQuantity = false;
        
        String query = "UPDATE Products " + 
                "SET Stock_Quantity = Stock_Quantity - " +
                "(SELECT Transaction_Quantity " + 
                "FROM Transactions WHERE Transaction_Id = ?) " +
                "WHERE Product_Id = ?";
        
        try (var pst = this.conn.prepareStatement(query))
        {
            pst.setInt(1, transaction.getTransactionId());
            pst.setInt(2, transaction.getProductId());
            pst.executeUpdate();
            isUpdateStockQuantity = true;
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        
        return isUpdateStockQuantity;
    }
    
    @Override
    public boolean add(Object t)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object delete(Object t)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean update(Object t)
    {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<Transaction> getAll()
    {
        transactions = new ArrayList<>();
        
        String query = "SELECT * FROM Transactions ORDER BY Transaction_Id";
        
        try (var pst = this.conn.prepareStatement(query);
                ResultSet rs = pst.executeQuery())
        {
            while (rs.next())
            {
                int transactionId = rs.getInt(1);
                int productId = rs.getInt(2);
                int customerId = rs.getInt(3);
                int userId = rs.getInt(4);
                int totalQuantity = rs.getInt(5);
                double totalPrice = rs.getDouble(6);
                
                Transaction transaction = new Transaction(
                        transactionId, 
                        productId, 
                        customerId, 
                        userId, 
                        totalQuantity, 
                        totalPrice);
                
                this.transactions.add(transaction);
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        return transactions;
    }
}
