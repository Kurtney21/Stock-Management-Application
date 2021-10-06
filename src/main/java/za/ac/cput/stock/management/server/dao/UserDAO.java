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
import za.ac.cput.stock.management.common.User;
import za.ac.cput.stock.management.common.UserRole;

/**
 * The UserDAO accesses the User Table in database 
 * and performs CRUD methods and brief specific methods
 * according to use cases.
 * 
 * @author curstinjr
 */
public class UserDAO implements DAO<User>
{
    private ArrayList<User> users;  // used to store user objects
    private Connection conn;        // database connection object
    
    public UserDAO() throws SQLException
    {
        this.conn = new DBConnection().getDerbyConnection();
    }
    
    /**
     * Adds a user object to the User table
     * in the database.
     * 
     * @param user
     * @return 
     */
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
    
    /**
     * Retrieves all the users from the User table 
     * and stores it in an ArrayList.
     * 
     * @return 
     */
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
    
    /**
     * This method checks to see if the user logging in
     * is in the database if the user is, it returns the
     * user object. Else it returns null.
     * 
     * @param user
     * @return 
     */
    public User getUserLogins(User user)
    {
        User userObj = null;
        String query = "SELECT * FROM Users WHERE Username = ? AND Password = ?";
        
        try (var pst = this.conn.prepareStatement(query))
        {
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            
            ResultSet rs = pst.executeQuery();
            
            if (rs.next())
            {
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                int roleId = rs.getInt("Role_ID");
                boolean userStatus = rs.getBoolean("Status");
                
                var userRole = roleId == UserRole.ADMIN.getRoleCode() 
                       ? UserRole.ADMIN 
                       : UserRole.USER;
                
                userObj = new User(username, password, userRole, userStatus);
            }
            
            rs.close();
        }
        catch (SQLException sqle)
        {
            System.out.println(sqle.getMessage());
        }
        
        return userObj;
    }
}
