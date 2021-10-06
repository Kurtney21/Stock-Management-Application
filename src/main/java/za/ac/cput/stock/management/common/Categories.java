/**
 * @author Curstin Jade Rose (220275408)
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year ADP 262s
 */

package za.ac.cput.stock.management.common;

import java.util.ArrayList;

public class Categories {
    public String[] categories = {"Arts & Scholistic","Bags & Backpacks",
            "Books & Paper","File & Filling",
            "Inks & Toners","Office Supplies","Office Automation and Electronics",
            "Stationary","Technology",
            "Snacks & Drinks"};
    ArrayList<String> cat = new ArrayList();
    
    public ArrayList<String> getCategories(){
        for(int i = 0; i < categories.length;i++){
            cat.add(categories[i]);
        }
        return cat;
    }
}
