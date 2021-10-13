/**
 *
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year
 */

package za.ac.cput.stock.management.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import za.ac.cput.stock.management.client.entry.Client;
import za.ac.cput.stock.management.client.gui.MainFrame;
import za.ac.cput.stock.management.common.Customer;
import za.ac.cput.stock.management.common.User;


public class Controller {
    private Client client;
    private MainFrame mainFrame;
    
    public Controller(){
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
                try {
                    mainFrame = new MainFrame();
                    onStart();
                } 
                catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
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
    
    public void onStart(){
        populateTables();
    }
    
    public void populateTables(){
        try {
            populateCustomerTable(mainFrame.getAddCustomerPanel().getTable());
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //populates Customer Table
        public void populateCustomerTable(JTable table) throws SQLException{
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int rowCount = model.getRowCount();
            //Remove rows one by one from the end of the table
            for (int i = rowCount - 1; i >= 0; i--) {
                model.removeRow(i);
            }
            
            List<Customer> list = client.getListOfCustomer();//Read Products from DB method (getAllProducts)
            if(list!=null){
                Object[] rowData = new Object[4];
                for(int i = 0; i < list.size();i++){
                    rowData[0] = list.get(i).getCustomerId();
                    rowData[1] = list.get(i).getName();
                    rowData[2] = list.get(i).getLastname();
                    rowData[3] = list.get(i).getEmail();
                    model.addRow(rowData);
                }
            }else{
                System.out.println("List is Empty");
            }
            
        }
    
    
    

}
