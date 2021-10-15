/**
 * @author Curstin Jade Rose (220275408)
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year ADP 262s
 */

package za.ac.cput.stock.management.controller;

import javax.swing.*;

public class ViewController 
{    
    public void swapPanels(JPanel oldPanel, JPanel newPanel)
    { 
        //oldPanel == panel with cardlayout
        oldPanel.removeAll();
        oldPanel.add(newPanel);
        oldPanel.repaint();
        oldPanel.revalidate();
    }
}
