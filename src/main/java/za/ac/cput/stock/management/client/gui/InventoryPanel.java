/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package za.ac.cput.stock.management.client.gui;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import za.ac.cput.stock.management.common.Fonts;
import za.ac.cput.stock.management.controller.Controller;

/**
 *
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year
 */
public class InventoryPanel implements ItemListener {

    private JPanel mainPane, northPnl, eastPnl, centerPnl;
    private JTable inventoryTable;
    private JComboBox categoryBox;
    private JScrollPane pane;
    private JLabel heading = new JLabel("Inventory");
    private Controller controller = new Controller();
    
    public InventoryPanel(){
        mainPane = new JPanel();
        northPnl = new JPanel();
        eastPnl = new JPanel();
        centerPnl = new JPanel();
        
        setCombox();
        setTable();
        setLayouts();
        setComponents();
    }
    
    private void setTable(){
        inventoryTable = new JTable();
        pane = new JScrollPane();
        inventoryTable.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                        "ID","Name","Quantity","Price","Vendor"}
        ){
            public boolean isCellEditable() {
                return false;
            }
        
        });
        
        getTableModel();
        populateTable();
        
        pane.setViewportView(inventoryTable);
        pane.setBorder(new EmptyBorder(10,10,10,10));
        pane.setSize(600,400);
    }
    
    public void setCombox()
    {
        categoryBox = new JComboBox(controller.getCategories());
        categoryBox.addItemListener(this);
    }
    
    public void populateTable()
    {
        String category = categoryBox.getSelectedItem().toString();
        
        int productCategoryLen = 
                controller.getProductsByCategory(category).size();

        getTableModel().setRowCount(0);

        for (int i = 0; i < productCategoryLen; i++)
        {
            var record = controller.getProductsByCategory(category).get(i);
            getTableModel().addRow(new Object[] {
                record.getProductId(), 
                record.getProuductName(), 
                record.getStockQuantity(), 
                record.getProductPrice(),
                record.getVendor()
            });
        }
    }
    
    public void setLayouts(){
        mainPane.setLayout(new BorderLayout());
        northPnl.setLayout(new FlowLayout(FlowLayout.LEFT));
        eastPnl.setLayout(new BoxLayout(eastPnl, BoxLayout.Y_AXIS)); 
        centerPnl.setLayout(new BoxLayout(centerPnl, BoxLayout.Y_AXIS));
    }
    
    public void setComponents(){
        mainPane.add(northPnl, BorderLayout.NORTH);
        mainPane.add(eastPnl, BorderLayout.EAST);
        mainPane.add(centerPnl, BorderLayout.CENTER);
        heading.setFont(new Fonts().getMed());
        northPnl.add(heading);
        eastPnl.add(Box.createRigidArea(new Dimension(200,0)));
        categoryBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        categoryBox.setMaximumSize(new Dimension(150, 30));
        eastPnl.add(categoryBox);
        centerPnl.add(pane);
    }
    
    @Override
    public void itemStateChanged(ItemEvent e)
    {
        if (e.getStateChange() == ItemEvent.SELECTED)
        {
            populateTable();
        }
    }

    public JPanel getMainPane() {
        return mainPane;
    }
    
    public DefaultTableModel getTableModel()
    {
        return (DefaultTableModel) inventoryTable.getModel();
    }

}
