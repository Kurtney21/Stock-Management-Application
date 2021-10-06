/**
 *
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year
 */

package za.ac.cput.stock.management.common;

import java.awt.*;

public class Fonts {
    private Font sml, med, large;
    
    public Fonts(){
        sml = new Font("Roboto", Font.PLAIN, 12);
        med = new Font("Roboto", Font.PLAIN, 18);
        large = new Font("Roboto", Font.PLAIN, 28);
    }

    public Font getSml() {
        return sml;
    }

    public Font getMed() {
        return med;
    }

    public Font getLarge() {
        return large;
    }
}
