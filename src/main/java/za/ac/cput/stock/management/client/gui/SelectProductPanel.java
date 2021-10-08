/**
 * @author Curstin Jade Rose (220275408)
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year ADP 262s
 */

package za.ac.cput.stock.management.client.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import za.ac.cput.stock.management.common.Categories;

public class SelectProductPanel implements ActionListener{
    private JPanel addProductPnl, tablePnl, buttonPnl;
    private Font ft;
    private JTable table;
    private JLabel selectLbl;
    private JComboBox catBox, customerBox;
    private JScrollPane sc; 
    private JButton addBtn, displayCartBtn, checkoutBtn, cartBtn,backBtn;

    public SelectProductPanel(){
        initPanels();
        initButtons();
        initComboBox();
        setTable();
        setLayouts();
        setComponents();
        setListenerEvents();
    }
    
    public void initComboBox(){
        catBox = new JComboBox(new Categories().getCategories().toArray());
        String[]dummy = {"-select customer-","jane@fosn.co","jane@fosn.co",
            "jane@fosn.co","jane@fosn.co","jane@fosn.co","jane@fosn.co",
            "jane@fosn.co","jane@fosn.co"};
        customerBox = new JComboBox(dummy);
    }
    
    public void initPanels(){
        addProductPnl = new JPanel();
        tablePnl = new JPanel(); 
        buttonPnl = new JPanel();
        selectLbl = new JLabel("Add Products to Cart:", SwingConstants.LEADING);
        ft = new Font("Roboto", Font.PLAIN|Font.BOLD,12);
        selectLbl.setFont(ft);
        selectLbl.setForeground(new Color(50,127,90));
    }

    public void initButtons(){
        addBtn = new JButton("Add");
        addBtn.setToolTipText("Add A Product to the Cart");
        displayCartBtn = new JButton("Display Cart");
        displayCartBtn.setToolTipText("Click here to display Cart");
        checkoutBtn = new JButton("Checkout");
        checkoutBtn.setToolTipText("Continue With Selected Products!");
        cartBtn = new JButton("Cart");
        cartBtn.setIcon(new ImageIcon("resources/cart.png"));
        backBtn = new JButton("Back");
        backBtn.setToolTipText("Back to DashBoard");
        
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
        sc.setBorder(new EmptyBorder(10,10,10,10));
        sc.setPreferredSize(new Dimension(600,400));
        sc.setToolTipText("Select Customer From Table:");
    }
    
    public void setLayouts(){
        addProductPnl.setLayout(new BoxLayout(addProductPnl, BoxLayout.X_AXIS));
        tablePnl.setLayout(new BoxLayout(tablePnl, BoxLayout.Y_AXIS));
        buttonPnl.setLayout(new BoxLayout(buttonPnl, BoxLayout.Y_AXIS));
    }
    
    public void setComponents(){
        addProductPnl.add(tablePnl);
        addProductPnl.add(buttonPnl);
        
        //Table
        selectLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        tablePnl.add(selectLbl);
        tablePnl.add(Box.createRigidArea(new Dimension(0,10)));
        tablePnl.add(sc);
        
        //Buttons and Boxes
        
        
        catBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        catBox.setMaximumSize(new Dimension(250,20));
        buttonPnl.add(catBox);
        buttonPnl.add(Box.createRigidArea(new Dimension(100,10)));
        customerBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        customerBox.setMaximumSize(new Dimension(200,20));
        buttonPnl.add(customerBox);
        buttonPnl.add(Box.createRigidArea(new Dimension(0,10)));
        addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPnl.add(addBtn);
        buttonPnl.add(Box.createRigidArea(new Dimension(0,10)));
        checkoutBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPnl.add(checkoutBtn);
        buttonPnl.add(Box.createRigidArea(new Dimension(0,10)));
        displayCartBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPnl.add(displayCartBtn);
        buttonPnl.add(Box.createRigidArea(new Dimension(0,30)));
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPnl.add(backBtn);
    }
    
    public void setListenerEvents(){
        addBtn.addActionListener(this);
        checkoutBtn.addActionListener(this);
        displayCartBtn.addActionListener(this);
        backBtn.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    public JPanel getAddProductPnl() {
        return addProductPnl;
    }

    public JPanel getTablePnl() {
        return tablePnl;
    }

    public JPanel getButtonPnl() {
        return buttonPnl;
    }

    public Font getFt() {
        return ft;
    }

    public JTable getTable() {
        return table;
    }

    public JLabel getSelectLbl() {
        return selectLbl;
    }

    public JScrollPane getSc() {
        return sc;
    }

    public JButton getAddBtn() {
        return addBtn;
    }

    public JButton getDisplayCartBtn() {
        return displayCartBtn;
    }

    public JButton getCheckoutBtn() {
        return checkoutBtn;
    }

    public JButton getCartBtn() {
        return cartBtn;
    }

    public JButton getBackBtn() {
        return backBtn;
    }
    
    
}

