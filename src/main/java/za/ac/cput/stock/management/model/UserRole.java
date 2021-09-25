/**
 * @author Curstin Jade Rose (220275408)
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year ADP 262s
 */
package za.ac.cput.stock.management.model;

public enum UserRole
{
    ADMIN(1), 
    USER(2);
    
    private final int roleCode;
    
    private UserRole(int roleCode)
    {
        this.roleCode = roleCode;
    }
    
    public int getRoleCode()
    {
        return this.roleCode;
    }
}
