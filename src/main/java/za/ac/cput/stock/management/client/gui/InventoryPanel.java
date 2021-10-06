/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package za.ac.cput.stock.management.client.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import za.ac.cput.stock.management.common.Fonts;

/**
 *
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year
 */
public class InventoryPanel {

    private JPanel mainPane, northPnl, eastPnl, centerPnl;
    private JTable table;
    private JScrollPane pane;
    private JLabel heading = new JLabel("Inventory");
    
    public InventoryPanel(){
        mainPane = new JPanel();
        northPnl = new JPanel();
        eastPnl = new JPanel();
        centerPnl = new JPanel();
        
        setTable();
        setLayouts();
        setComponents();
    }
    
    private void setTable(){
        table = new JTable();
        pane = new JScrollPane();
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                        "ID","Name","Quantity","Price","In-Stock"}
        ){
            public boolean isCellEditable() {
                return false;
            }
        
        });
        pane.setViewportView(table);
        pane.setBorder(new EmptyBorder(10,10,10,10));
        pane.setSize(600,400);
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
        eastPnl.add(Box.createRigidArea(new Dimension(150,0)));
        centerPnl.add(pane);
    }

    public JPanel getMainPane() {
        return mainPane;
    }
    
    
}
