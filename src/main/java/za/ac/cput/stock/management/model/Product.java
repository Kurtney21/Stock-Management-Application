/**
 * @author Curstin Jade Rose (220275408)
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year ADP 262s
 */

package za.ac.cput.stock.management.model;

public class Product
{
    int productId;
    String prouductName;
    String productDescription;
    String category;
    int stockQuantity;
    double productPrice;
    
    public Product() {}

    public Product(
            int productId, 
            String prouductName, 
            String productDescription, 
            String category, 
            int stockQuantity, 
            double productPrice)
    {
        this.productId = productId;
        this.prouductName = prouductName;
        this.productDescription = productDescription;
        this.category = category;
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

    public String getProductDescription()
    {
        return productDescription;
    }

    public void setProductDescription(String productDescription)
    {
        this.productDescription = productDescription;
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

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Product{productId=").append(productId);
        sb.append(", prouductName=").append(prouductName);
        sb.append(", productDescription=").append(productDescription);
        sb.append(", category=").append(category);
        sb.append(", stockQuantity=").append(stockQuantity);
        sb.append(", productPrice=").append(productPrice);
        sb.append('}');
        return sb.toString();
    }
}
