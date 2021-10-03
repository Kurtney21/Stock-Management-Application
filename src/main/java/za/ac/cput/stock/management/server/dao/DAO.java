/**
 * @author Curstin Jade Rose (220275408)
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year ADP 262s
 */
package za.ac.cput.stock.management.server.dao;

import java.util.List;

public interface DAO<T>
{
    public T add(T t);
    public T delete(T t);
    public T update(T t);
    public List<T> getAll();
}
