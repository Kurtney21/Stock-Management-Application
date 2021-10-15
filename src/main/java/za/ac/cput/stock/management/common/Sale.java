/**
 * @author Curstin Jade Rose (220275408)
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year ADP 262s
 */
package za.ac.cput.stock.management.common;

import java.io.Serializable;

public class Sale implements Serializable{
    private int id;
    private String name;
    private int quantity;
    private double subTotal;
    private double total;
    
    public Sale(){
        this.id = 0;
        this.name = "";
        this.quantity = 0;
        this.subTotal = 0.0;
        this.total = 0.0;
    }

    public Sale(String name, int quantity, double subTotal) {
        this.name = name;
        this.quantity = quantity;
        this.subTotal = subTotal;
    }
    
    public Sale(int id, int quantity, double subTotal, double total) {
        this.id = id;
        this.quantity = quantity;
        this.subTotal = subTotal;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public double getTotal() {
        return total;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Sale{" + "name=" + name + ", quantity=" + quantity 
                + ", subTotal=" + subTotal;
    }
    
    
    
}
