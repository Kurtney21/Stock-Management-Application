/**
 * @author Curstin Jade Rose (220275408)
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year ADP 262s
 */

package za.ac.cput.stock.management.client.gui;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import za.ac.cput.stock.management.controller.Controller;

public class AddCustomerPanel implements ActionListener{
    private JPanel addCustomerPnl, tablePnl, buttonPnl;
    private JTable table;
    private JScrollPane sc; 
    private JButton addBtn, updateBtn;

    public AddCustomerPanel() throws SQLException{
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
    }

    public void setTable() throws SQLException{
        table = new JTable(new DefaultTableModel(
            new Object[][]{}, 
            new String[]{
                "ID","NAME","USERNAME","EMAIL"
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
    }
    
    public void setLayouts(){
        addCustomerPnl.setLayout(new BoxLayout(addCustomerPnl, BoxLayout.X_AXIS));
        tablePnl.setLayout(new BoxLayout(tablePnl, BoxLayout.X_AXIS));
        buttonPnl.setLayout(new BoxLayout(buttonPnl, BoxLayout.Y_AXIS));
    }
    
    public void setComponents(){
        
        addCustomerPnl.add(tablePnl);
        tablePnl.add(Box.createRigidArea(new Dimension(0,50)));
        tablePnl.add(sc);
        
        addCustomerPnl.add(buttonPnl);
        buttonPnl.add(Box.createRigidArea(new Dimension(0,50)));
        addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPnl.add(addBtn);
        buttonPnl.add(Box.createRigidArea(new Dimension(0,10)));
        updateBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPnl.add(updateBtn);
        buttonPnl.add(Box.createRigidArea(new Dimension(90,230)));
    }
    
    public void setListenerEvents(){
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
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
    
}
