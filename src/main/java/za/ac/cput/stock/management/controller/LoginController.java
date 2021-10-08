/**
 *
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year
 */

package za.ac.cput.stock.management.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import za.ac.cput.stock.management.client.entry.Client;
import za.ac.cput.stock.management.client.gui.MainFrame;
import za.ac.cput.stock.management.common.User;


public class LoginController {
    private Client client;
    private MainFrame mainFrame;
    
    public LoginController(){
        client = new Client();
    }
    
    public void checkAuthentication(String userName, String password, JFrame frame){
         User validUser = null;
            var user = new User(userName, password);
            
            // Work in progress
            try
            {
                validUser = this.client.requestToLogin(user);
            }
            catch(NullPointerException ex)
            {
                System.out.println("Server offline");
                this.client = new Client();
            }
            
            if (validUser != null)
            {
                
                mainFrame = new MainFrame();
                // admin login
                if (validUser.getUserRole().getRoleCode() == 1 
                        && validUser.isStatus())
                {
                    mainFrame.setVisible(true);
                    mainFrame.getLoginIcnLbl().setText(userName);
                    mainFrame.getWelcomeLbl().setText("Welcome " + userName + "!!");
                    frame.dispose();
                    System.out.println(userName + " logged in");
                }
                // employee login
                else if (validUser.getUserRole().getRoleCode() == 2 
                        && validUser.isStatus())
                {
                    mainFrame.setVisible(true);
                    mainFrame.getAdminMenu().setVisible(false);
                    mainFrame.getLoginIcnLbl().setText(userName);
                    frame.dispose();
                    System.out.println(userName + " logged in.");
                }
                // account disabled
                else 
                {
                    System.out.println("Account is disabled");
                }
            }
            else
            {
                // the user doesnt have an account
                System.out.println("Incorrect username or password. Try again.");
            }
    }
    
    
    

}
