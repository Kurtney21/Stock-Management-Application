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
    private ArrayList<Product> productsCategory;
    private Connection conn;
    
    public ProductDAO() throws SQLException
    {
        this.conn = new DBConnection().getDerbyConnection();
    }

    @Override
    public boolean add(Product product)
    {
        boolean isAdd = false;
        
        String query = "INSERT INTO Products "
                + "(Category, Vendor, Product_Name, Stock_Quantity, Price) "
                + "VALUES (?,?,?,?,?)";
        
        try (var pst = this.conn.prepareStatement(query))
        {
            pst.setString(1, product.getCategory());
            pst.setString(2, product.getVendor());
            pst.setString(3, product.getProuductName());
            pst.setInt(4, product.getStockQuantity());
            pst.setDouble(5, product.getProductPrice());
            
            pst.executeUpdate();
            
            isAdd = true;
        }
        catch (SQLException sqle)
        {
            System.out.println("add product db error: " + sqle.getMessage());
        }
        return isAdd;
    }

    @Override
    public Product delete(Product t)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean update(Product product)
    {
        boolean isUpdate = false;
        
        String query = "UPDATE Products SET "
                + "Product_Name = ?,"
                + "Stock_Quantity = ?, "
                + "Price = ? "
                + "WHERE Product_Id = ?";
        
        try (var pst = this.conn.prepareStatement(query))
        {
            pst.setString(1, product.getProuductName());
            pst.setInt(2, product.getStockQuantity());
            pst.setDouble(3, product.getProductPrice());
            pst.setInt(4, product.getProductId());
            
            pst.executeUpdate();
            
            isUpdate = true;
        } 
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return isUpdate;
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
                String category = rs.getString(2);
                String vendor = rs.getString(3);
                String productName = rs.getString(4);
                int stockQuantity = rs.getInt(5);
                double price = rs.getDouble(6);
                
                var obj = new Product(
                        productId, 
                        productName, 
                        category,
                        vendor,
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
    
    public List<Product> getProductsByCategory(String category)
    {
        productsCategory = new ArrayList<>();
        
        String query = "SELECT * FROM Products WHERE Category = ?";
        
        try (var pst = this.conn.prepareStatement(query))
        {
            pst.setString(1, category);
            
            ResultSet rs = pst.executeQuery();
            
            while (rs.next())
            {
                int productId = rs.getInt(1);
                String categoryName = rs.getString(2);
                String vendor = rs.getString(3);
                String productName = rs.getString(4);
                int stockQuantity = rs.getInt(5);
                double price = rs.getDouble(6);
                
                var obj = new Product(
                        productId, 
                        productName, 
                        categoryName,
                        vendor,
                        stockQuantity, 
                        price);
                
                this.productsCategory.add(obj);
            }
            rs.close();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        return productsCategory;
    }
}
