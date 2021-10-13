/**
 *
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year
 */

package za.ac.cput.stock.management.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import za.ac.cput.stock.management.client.gui.LoginGUI;
import za.ac.cput.stock.management.client.gui.MainFrame;

public class MainFrameController 
{
    MainFrame mainFrame;
    
    public MainFrameController()
    {
        // mainFrame = new MainFrame();
    }
    
    public void logoutMenu(MainFrame frame, JMenuItem item)
    {
        item.addActionListener(new ActionListener()
        {  
            public void actionPerformed(ActionEvent e)
            {
                new LoginGUI().setVisible(true);
                frame.dispose();
            }  
        });
    }
}
