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


public class CategoryDAO implements DAO
{
    private Connection conn;
    private ArrayList<String> categories;
    
    public CategoryDAO() throws SQLException
    {
        this.conn = new DBConnection().getDerbyConnection();
    }

    @Override
    public Object add(Object t)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object delete(Object t)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object update(Object t)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List getAll()
    {
        this.categories = new ArrayList<>();
        
        String query = "SELECT * FROM categories";
        
        try (var pst = conn.prepareStatement(query);
                ResultSet rs = pst.executeQuery())
        {
            while (rs.next())
            {
                // int categoryId = rs.getInt(1);
                String categoryName = rs.getString(2);
                this.categories.add(categoryName);
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        
        return categories;
    }
}
