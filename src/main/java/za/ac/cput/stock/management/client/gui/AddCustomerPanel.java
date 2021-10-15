/**
 * @author Curstin Jade Rose (220275408)
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year ADP 262s
 */

package za.ac.cput.stock.management.client.gui;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import za.ac.cput.stock.management.common.Customer;
import za.ac.cput.stock.management.controller.Controller;

public class AddCustomerPanel extends JFrame{
    private JPanel addCustomerPnl, tablePnl, buttonPnl;
    private JTable table;
    private JScrollPane sc; 
    private JButton addBtn, updateBtn, refreshBtn;
    private PopTables popTable = new PopTables();
    private Controller controller = new Controller();

    public AddCustomerPanel() {
        initPanels();
        initButtons();
        setTable();
        setLayouts();
        setComponents();
        setListenerEvents();
    }
    
    public void initPanels(){
        addCustomerPnl = new JPanel();
        tablePnl = new JPanel(); 
        buttonPnl = new JPanel();
    }

    public void initButtons(){
        addBtn = new JButton("Add");
        updateBtn = new JButton("Update");
        refreshBtn = new JButton("Refresh Table");
    }

    public void setTable() {
        table = new JTable(new DefaultTableModel(
            new Object[][]{}, 
            new String[]{
                "ID","NAME","LASTNAME","EMAIL"
            }){
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        sc = new JScrollPane(table);
        sc.setViewportView(table);
        sc.setBorder(new EmptyBorder(10,10,10,10));
        sc.setPreferredSize(new Dimension(600,400));
        try {
            popTable.populateCustomerTable(table);
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomerPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setLayouts(){
        addCustomerPnl.setLayout(new BoxLayout(addCustomerPnl, BoxLayout.X_AXIS));
        tablePnl.setLayout(new BoxLayout(tablePnl, BoxLayout.X_AXIS));
        buttonPnl.setLayout(new BoxLayout(buttonPnl, BoxLayout.Y_AXIS));
    }
    
    public void setComponents(){
        this.add(addCustomerPnl);
        addCustomerPnl.add(tablePnl);
        tablePnl.add(Box.createRigidArea(new Dimension(0,50)));
        tablePnl.add(sc);
        
        addCustomerPnl.add(buttonPnl);
        buttonPnl.add(Box.createRigidArea(new Dimension(150,50)));
        addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPnl.add(addBtn);
        buttonPnl.add(Box.createRigidArea(new Dimension(0,10)));
        updateBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPnl.add(updateBtn);
        buttonPnl.add(Box.createRigidArea(new Dimension(0,10)));
        refreshBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPnl.add(refreshBtn);
        buttonPnl.add(Box.createRigidArea(new Dimension(0,200)));
    }
    
    public void setListenerEvents(){
        addBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ea){
                new AddCustomerGUI().setVisible(true);
            }
        });
        updateBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ea){
                updateCustomer();
            }
        });
        refreshBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ea){
                try {
                    popTable.populateCustomerTable(table);
                } catch (SQLException ex) {
                    Logger.getLogger(AddCustomerPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public void updateCustomer(){
        try
            {
                int row = table.getSelectedRow();
                
                String cusID = String.valueOf(getTableModel().getValueAt(row, 0));
                int id = Integer.parseInt(cusID);
                String cusName = String.valueOf(getTableModel().getValueAt(row, 1));
                String cusLastname = String.valueOf(getTableModel().getValueAt(row, 2));
                String cusEmail = String.valueOf(getTableModel().getValueAt(row, 3));

                controller.updateCustomer(id, cusName, cusLastname, cusEmail);
            }
            catch (ArrayIndexOutOfBoundsException ex)
            {
                JOptionPane.showMessageDialog(null, "No record selected.");
            }
    }
    
    public JPanel getAddCustomerPnl() {
        return addCustomerPnl;
    }

    public JPanel getTablePnl() {
        return tablePnl;
    }

    public JPanel getButtonPnl() {
        return buttonPnl;
    }

    public JButton getAddBtn() {
        return addBtn;
    }

    public JTable getTable() {
        return table;
    }
    
    public DefaultTableModel getTableModel()
    {
        return(DefaultTableModel) table.getModel();
    }
}
