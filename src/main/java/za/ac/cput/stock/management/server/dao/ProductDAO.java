/**
 * @author Curstin Jade Rose (220275408)
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year ADP 262s
 */
package za.ac.cput.stock.management.server.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import za.ac.cput.stock.management.server.dbconnection.DBConnection;
import za.ac.cput.stock.management.common.Product;


public class ProductDAO implements DAO<Product>
{
    private ArrayList<Product> products;
    private Connection conn;
    
    public ProductDAO() throws SQLException
    {
        this.conn = new DBConnection().getDerbyConnection();
    }

    @Override
    public Product add(Product product)
    {
        String query = "INSERT INTO Products VALUES (?,?,?,?,?,?,?)";
        
        try (var pst = this.conn.prepareStatement(query))
        {
            pst.setInt(1, product.getProductId());
            pst.setInt(2, Integer.parseInt(product.getCategory()));
            pst.setInt(3, Integer.parseInt(product.getVendor()));
            pst.setString(4, product.getProuductName());
            pst.setString(5, product.getProductDescription());
            pst.setInt(6, product.getStockQuantity());
            pst.setDouble(7, product.getProductPrice());
            
            pst.executeUpdate();
        }
        catch (SQLException sqle)
        {
            System.out.println("add product db error: " + sqle.getMessage());
        }
        
        return product;
    }

    @Override
    public Product delete(Product t)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Product update(Product t)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Product> getAll()
    {
        products = new ArrayList<>();
        
        String query = "SELECT * FROM Products";
        
        try (var pst = this.conn.prepareStatement(query);
                ResultSet rs = pst.executeQuery())
        {
            while (rs.next())
            {
                int productId = rs.getInt(1);
                int categoryId = rs.getInt(2);
                int vendorId = rs.getInt(3);
                String productName = rs.getString(4);
                String productDescrip = rs.getString(5);
                int stockQuantity = rs.getInt(6);
                double price = rs.getDouble(7);
                
                var obj = new Product(
                        productId, 
                        productName, 
                        productDescrip,
                        String.valueOf(categoryId),
                        String.valueOf(vendorId),
                        stockQuantity, 
                        price);
                
                this.products.add(obj);
            }
        }
        catch (SQLException sqle)
        {
            System.out.println("products getAll db error: " + sqle.getMessage());
        }
        
        return products;
    }
}
