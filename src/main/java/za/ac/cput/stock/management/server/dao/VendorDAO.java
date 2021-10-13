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
import za.ac.cput.stock.management.server.dbconnection.DBConnection;


public class VendorDAO implements DAO
{
    private Connection conn;
    private ArrayList<String> vendors;
    
    public VendorDAO() throws SQLException
    {
        this.conn = new DBConnection().getDerbyConnection();
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
    public List getAll()
    {
        this.vendors = new ArrayList<>();
        
        String query = "SELECT * FROM Vendors";
        
        try (var pst = this.conn.prepareStatement(query);
                ResultSet rs = pst.executeQuery())
        {
            while (rs.next())
            {
                String vendor = rs.getString(1);
                
                this.vendors.add(vendor);
            }
        } 
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return this.vendors;
    }
    
}
