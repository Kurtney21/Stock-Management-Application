/**
 * @author Curstin Jade Rose (220275408)
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year ADP 262s
 */
package za.ac.cput.stock.management.dbconnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection 
{
    /**
     * Reads db.properties file and returns a 
     * database connection.
     * @return
     * @throws SQLException 
     */
    public Connection getDerbyConnection() throws SQLException
    {
        Properties props = new Properties();
        String filename = "src/main/resources/db.properties";
        
        // open db.properties file
        try (var fis = new FileInputStream(filename))
        {
            // read db settings
            props.load(fis);
        }
        catch(IOException ioe)
        {
            System.out.println("db.properties error: " + ioe.getMessage());
        }
        
        // get db settings
        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");
        
        return DriverManager.getConnection(url, user, password);
    }
}
