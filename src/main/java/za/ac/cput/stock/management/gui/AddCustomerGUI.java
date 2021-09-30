/**
 * @author Curstin Jade Rose (220275408)
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year ADP 262s
 */

package za.ac.cput.stock.management.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AddCustomerGUI implements ActionListener{
    private JPanel addCustomerPnl, tablePnl, buttonPnl;
    private JTable table;
    private JScrollPane sc; 
    private JButton addBtn, updateBtn, backBtn;

    public AddCustomerGUI(){
        
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
        backBtn = new JButton("Back");
    }

    public void setTable(){
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
        sc.setPreferredSize(new Dimension(400,300));
    }
    
    public void setLayouts(){
        addCustomerPnl.setLayout(new BoxLayout(addCustomerPnl, BoxLayout.X_AXIS));
        tablePnl.setLayout(new BoxLayout(tablePnl, BoxLayout.Y_AXIS));
        buttonPnl.setLayout(new BoxLayout(buttonPnl, BoxLayout.Y_AXIS));
    }
    
    public void setComponents(){
        addCustomerPnl.add(tablePnl);
        tablePnl.add(Box.createRigidArea(new Dimension(0,50)));
        tablePnl.add(sc);
        
        addCustomerPnl.add(buttonPnl);
        buttonPnl.add(Box.createRigidArea(new Dimension(0,50)));
        buttonPnl.add(addBtn);
        buttonPnl.add(Box.createRigidArea(new Dimension(0,10)));
        buttonPnl.add(updateBtn);
        buttonPnl.add(Box.createRigidArea(new Dimension(90,230)));
        buttonPnl.add(backBtn);
    }
    
    public void setListenerEvents(){
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    public JPanel getAddCustomerPnl() {
        return addCustomerPnl;
    }
}
