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
import za.ac.cput.stock.management.model.User;
import za.ac.cput.stock.management.model.UserRole;

public class UserDAO implements DAO<User>
{
    private ArrayList<User> users;
    private Connection conn;
    
    public UserDAO() throws SQLException
    {
        this.conn = new DBConnection().getDerbyConnection();
    }
    
    @Override
    public User add(User user)
    {
        String query = "INSERT INTO Users VALUES(?,?,?,?,?)";
        
        try (var pst = this.conn.prepareStatement(query))
        {
            pst.setInt(1, user.getUserId());
            pst.setInt(2, user.getUserRole().getRoleCode());
            pst.setString(3, user.getUsername());
            pst.setString(4, user.getPassword());
            pst.setBoolean(5, user.isStatus());
            
            pst.executeUpdate();
        }
        catch (SQLException sqle)
        {
            System.out.println("add user db error: " + sqle.getMessage());
        }
        return user;
    }

    @Override
    public User delete(User user)
    {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public User update(User user)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<User> getAll()
    {
        this.users = new ArrayList<>();
        
        String query = "SELECT * FROM Users";
        
        try (var pst = this.conn.prepareStatement(query);
                ResultSet rs = pst.executeQuery())
        {
            while (rs.next())
            {
               int userId = rs.getInt(1);
               int roleId = rs.getInt(2);
               String username = rs.getString(3);
               String password = rs.getString(4);
               boolean status = rs.getBoolean(5);
               
               var userRole = roleId == UserRole.ADMIN.getRoleCode() 
                       ? UserRole.ADMIN 
                       : UserRole.USER;
               
               var obj = new User(userId, username, password, userRole, status);
               
               this.users.add(obj);
            }
        }
        catch (SQLException sqle)
        {
            System.out.println("read users error db: " + sqle.getMessage());
        }
        
        return users;
    }
    
    public boolean checkAuthentication(String userName, String password)
    {
        return true;
    }
}
