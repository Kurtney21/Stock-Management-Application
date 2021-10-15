/**
 *
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year
 */

package za.ac.cput.stock.management.server.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import za.ac.cput.stock.management.common.*;
import za.ac.cput.stock.management.server.dbconnection.*;

public class SalesReportDAO implements DAO
{
    private Connection con;
    ArrayList<Sale> sales;
    
    public SalesReportDAO() throws SQLException{
        con = new DBConnection().getDerbyConnection();
    }
    
    @Override
    public boolean add(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object delete(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List getAll() {
        sales = new ArrayList();
        
        String query = "SELECT PRODUCT_NAME, " +
                "SUM(TRANSACTION_QUANTITY) AS TOTAL_QUANTITY, " +
                "SUM(TRANSACTION_TOTAL) AS SUB_TOTAL " +
                "FROM TRANSACTIONS " +
                "INNER JOIN PRODUCTS " +
                "ON TRANSACTIONS.PRODUCT_ID = PRODUCTS.PRODUCT_ID "+
                "GROUP BY PRODUCT_NAME";
                
        try(    PreparedStatement pst = con.prepareStatement(query);
                ResultSet rs = pst.executeQuery()
          ){
            while (rs.next())
            {
                String name = rs.getString(1);
                int quantity = rs.getInt(2);
                double price = rs.getDouble(3);
                
                var obj = new Sale(name,quantity,price);
                
                this.sales.add(obj);
            }
        }
        catch(Exception ea)
        {
            ea.printStackTrace();
        }
        return this.sales;
    }
    
    
    public List getCustomerInvoice(String customerName) {
        ArrayList<Invoice> invoice = new ArrayList();
        String query = "SELECT  " +
                "Transaction_ID, " +
                "Product_Name,  " +
                "TRANSACTION_QUANTITY, TRANSACTION_TOTAL "
                + "FROM Transactions " +
                "INNER JOIN Products  " +
                "    ON PRODUCTS.PRODUCT_ID = TRANSACTIONS.PRODUCT_ID " +
                "INNER JOIN Customers " +
                "    ON CUSTOMERS.Customer_Id = TRANSACTIONS.CUSTOMER_ID " +
                "WHERE Customer_Name = ?";
                
        try(    PreparedStatement pst = con.prepareStatement(query);
                
          ){
            pst.setString(1, customerName);
            ResultSet rs = pst.executeQuery();
            while (rs.next())
            {
                int transactionID = rs.getInt(1);
                String productName = rs.getString(2);
                int quantity = rs.getInt(3);
                double price =  rs.getDouble(4);
                
                var obj = new Invoice(transactionID,productName,quantity,price);
                
                invoice.add(obj);
            }
        }catch(Exception ea){
            ea.printStackTrace();
        }
        return invoice;
    }
    
}
