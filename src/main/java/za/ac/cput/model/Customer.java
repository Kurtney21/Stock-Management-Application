/**
 * @author Curstin Jade Rose (220275408)
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year ADP 262s
 */

package za.ac.cput.model;

public class Customer
{
    int customerId;
    String name;
    String lastname;
    String email;
    
    public Customer() {}

    public Customer(int customerId, String name, String lastname, String email)
    {
        this.customerId = customerId;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }

    public int getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(int customerId)
    {
        this.customerId = customerId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer{customerId=").append(customerId);
        sb.append(", name=").append(name);
        sb.append(", lastname=").append(lastname);
        sb.append(", email=").append(email);
        sb.append('}');
        return sb.toString();
    }
}
