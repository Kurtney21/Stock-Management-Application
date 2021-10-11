/**
 * @author Curstin Jade Rose (220275408)
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year ADP 262s
 */

package za.ac.cput.stock.management.client.entry;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.UIManager;
import za.ac.cput.stock.management.client.gui.*;
import za.ac.cput.stock.management.server.entry.Server;

public class Main {
    public static void main(String[] args) {
        try {
                UIManager.setLookAndFeel(new FlatDarkLaf());
                new LoginGUI().setVisible(true);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
