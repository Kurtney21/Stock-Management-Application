/**
 * @author Curstin Jade Rose (220275408)
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year ADP 262s
 */
package za.ac.cput.stock.management.common;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable
{
    private int productId;
    private String prouductName;
    private String category;
    private String vendor;
    private int stockQuantity;
    private double productPrice;
    
    public Product() {}

    public Product(
            int productId, 
            String prouductName,
            String category,
            String vendor,
            int stockQuantity, 
            double productPrice)
    {
        this.productId = productId;
        this.prouductName = prouductName;
        this.category = category;
        this.vendor = vendor;
        this.stockQuantity = stockQuantity;
        this.productPrice = productPrice;
    }
    
    public Product( 
            String prouductName,
            String category,
            String vendor,
            int stockQuantity, 
            double productPrice)
    {
        this.prouductName = prouductName;
        this.category = category;
        this.vendor = vendor;
        this.stockQuantity = stockQuantity;
        this.productPrice = productPrice;
    }

    public int getProductId()
    {
        return productId;
    }

    public void setProductId(int productId)
    {
        this.productId = productId;
    }

    public String getProuductName()
    {
        return prouductName;
    }

    public void setProuductName(String prouductName)
    {
        this.prouductName = prouductName;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public int getStockQuantity()
    {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity)
    {
        this.stockQuantity = stockQuantity;
    }

    public double getProductPrice()
    {
        return productPrice;
    }

    public void setProductPrice(double productPrice)
    {
        this.productPrice = productPrice;
    }

    public String getVendor()
    {
        return vendor;
    }

    public void setVendor(String vendor)
    {
        this.vendor = vendor;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 79 * hash + this.productId;
        hash = 79 * hash + Objects.hashCode(this.prouductName);
        hash = 79 * hash + Objects.hashCode(this.category);
        hash = 79 * hash + Objects.hashCode(this.vendor);
        hash = 79 * hash + this.stockQuantity;
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.productPrice) ^ (Double.doubleToLongBits(this.productPrice) >>> 32));
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
        final Product other = (Product) obj;
        if (this.productId != other.productId)
        {
            return false;
        }
        if (this.stockQuantity != other.stockQuantity)
        {
            return false;
        }
        if (Double.doubleToLongBits(this.productPrice) != Double.doubleToLongBits(other.productPrice))
        {
            return false;
        }
        if (!Objects.equals(this.prouductName, other.prouductName))
        {
            return false;
        }
        if (!Objects.equals(this.category, other.category))
        {
            return false;
        }
        if (!Objects.equals(this.vendor, other.vendor))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Product{productId=").append(productId);
        sb.append(", prouductName=").append(prouductName);
        sb.append(", category=").append(category);
        sb.append(", vendor=").append(vendor);
        sb.append(", stockQuantity=").append(stockQuantity);
        sb.append(", productPrice=").append(productPrice);
        sb.append('}');
        return sb.toString();
    }
}
