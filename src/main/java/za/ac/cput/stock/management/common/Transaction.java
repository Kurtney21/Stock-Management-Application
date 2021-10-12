/**
 *
 * @author curstinjr
 */
package za.ac.cput.stock.management.common;

import java.io.Serializable;


public class Transaction implements Serializable
{
    private int transactionId = 0;
    private int productId = 0;
    private int customerId;
    private int userId;
    private int totalQuantity = 0;
    private double totalPrice = 0.0;
    
    public Transaction()
    {
    }
    
    public Transaction(int transactionId, int productId)
    {
        this.transactionId = transactionId;
        this.productId = productId;
    }

    public Transaction(
            int transactionId,
            int product, 
            int customer, 
            int user, 
            int totalQuantity,
            double totalPrice)
    {
        this.transactionId = transactionId;
        this.productId = product;
        this.customerId = customer;
        this.userId = user;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
    }

    public int getTransactionId()
    {
        return transactionId;
    }

    public void setTransactionId(int transactionId)
    {
        this.transactionId = transactionId;
    }
    
    public int getProductId()
    {
        return productId;
    }

    public void setProductId(int productId)
    {
        this.productId = productId;
    }

    public int getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(int customer)
    {
        this.customerId = customer;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int user)
    {
        this.userId = user;
    }

    public int getTotalQuantity()
    {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity)
    {
        this.totalQuantity = totalQuantity;
    }

    public double getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Transaction{transactionId=").append(transactionId);
        sb.append(", productId=").append(productId);
        sb.append(", customerId=").append(customerId);
        sb.append(", userId=").append(userId);
        sb.append(", totalQuantity=").append(totalQuantity);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append('}');
        return sb.toString();
    }

    
}
