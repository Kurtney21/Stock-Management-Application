/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package za.ac.cput.stock.management.client.gui;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import za.ac.cput.stock.management.common.Customer;
import za.ac.cput.stock.management.controller.Controller;

/**
 *
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year
 */
public class PopTables {
    private Controller controller = new Controller();
    
    
    public void populateCustomerTable(JTable table) throws SQLException{
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        ArrayList<Customer> list = (ArrayList<Customer>) controller.getCustomers();//Read Products from DB method (getAllProducts)
        Object[] rowData = new Object[4];
        for(int i = 0; i < list.size();i++){
            rowData[0] = list.get(i).getCustomerId();
            rowData[1] = list.get(i).getName();
            rowData[2] = list.get(i).getLastname();
            rowData[3] = list.get(i).getEmail();
            model.addRow(rowData);
        }
    }

}
