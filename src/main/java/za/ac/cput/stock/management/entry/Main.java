/**
 * @author Curstin Jade Rose (220275408)
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year ADP 262s
 */

package za.ac.cput.stock.management.entry;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.UIManager;
import za.ac.cput.stock.management.gui.*;

public class Main {
    public static void main(String[] args) {
        try {
        UIManager.setLookAndFeel(new FlatDarkLaf());
        }catch (Exception ex) {
        }
        //Testing Ground for View
        
        new HomeGUI().setVisible(true);
        //new LoginGUI().setVisible(true);
        //new AdministrationGUI().setVisible(true);
        //new SalesReportGUI().setVisible(true);
    }
}
