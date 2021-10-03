/**
 * @author Curstin Jade Rose (220275408)
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year ADP 262s
 */
package za.ac.cput.stock.management.common;

/**
 * Enumeration for the different users interacting
 * with the application.
 * 
 * @author curstinjr
 */
public enum UserRole
{
    ADMIN(1),   // ADMIN code is 1 
    USER(2);    // USER code is 2
    
    private final int roleCode;
    
    private UserRole(int roleCode)
    {
        this.roleCode = roleCode;
    }
    
    /**
     * Returns the enum's value.
     * 
     * @return 
     */
    public int getRoleCode()
    {
        return this.roleCode;
    }
}
