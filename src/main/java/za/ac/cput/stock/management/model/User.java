/**
 * @author Curstin Jade Rose (220275408)
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year ADP 262s
 */
package za.ac.cput.stock.management.model;

import java.util.Objects;

public class User
{
    int userId;
    String username;
    String password;
    UserRole userRole;
    boolean status;
    
    public User() {}

    public User(
            int userId, 
            String username, 
            String password, 
            UserRole userRole)
    {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.status = true;
    }
    
    public User(
            int userId, 
            String username, 
            String password, 
            UserRole userRole,
            boolean status)
    {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.status = status;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public UserRole getUserRole()
    {
        return userRole;
    }

    public void setUserRole(UserRole userRole)
    {
        this.userRole = userRole;
    }

    public boolean isStatus()
    {
        return status;
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 29 * hash + this.userId;
        hash = 29 * hash + Objects.hashCode(this.username);
        hash = 29 * hash + Objects.hashCode(this.password);
        hash = 29 * hash + Objects.hashCode(this.userRole);
        hash = 29 * hash + (this.status ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final User other = (User) obj;
        if (this.userId != other.userId)
        {
            return false;
        }
        if (this.status != other.status)
        {
            return false;
        }
        if (!Objects.equals(this.username, other.username))
        {
            return false;
        }
        if (!Objects.equals(this.password, other.password))
        {
            return false;
        }
        if (this.userRole != other.userRole)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("User{userId=").append(userId);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", userRole=").append(userRole);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
