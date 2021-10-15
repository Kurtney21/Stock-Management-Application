/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package za.ac.cput.stock.management.common;

import java.io.Serializable;

/**
 *
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year
 */
public class Invoice implements Serializable {
    private int transaction;
    private String name;
    private int quantity;
    private double total;

    public Invoice(int transaction, String name, int quantity, double total) {
        this.transaction = transaction;
        this.name = name;
        this.quantity = quantity;
        this.total = total;
    }

    public Invoice(String name, int quantity, double total) {
        this.name = name;
        this.quantity = quantity;
        this.total = total;
    }

    public int getTransaction() {
        return transaction;
    }

    public void setTransaction(int transaction) {
        this.transaction = transaction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Invoice [Transaction_ID " + transaction 
                + " Product_Name = " + name 
                + " Product_Quantity = " + quantity 
                + " Total = " + total + ']';
    }
    
    
}
