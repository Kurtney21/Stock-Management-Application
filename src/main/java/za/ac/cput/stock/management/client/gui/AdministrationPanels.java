/**
 * @author Curstin Jade Rose (220275408)
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year ADP 262s
 */

package za.ac.cput.stock.management.client.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import za.ac.cput.stock.management.common.Customer;
import za.ac.cput.stock.management.common.User;
import za.ac.cput.stock.management.common.UserRole;
import za.ac.cput.stock.management.controller.Controller;

public class AdministrationPanels implements ActionListener, ItemListener
{
    private JPanel westPnl, northPnl, centerPnl, opsProductPnl, tableProductPnl,
            opsUserPnl, tableUserPnl, mainContainer;    //Center Panel ==  Parent Panel
    private JPanel productManagePnl, userManagePnl;
    
    //User Management
    private JTable productTable, userTable;
    private JScrollPane scProduct, scUser;
    
    private JLabel logoLbl, authorLbl;
    private ImageIcon logoIcn;
    private JComboBox categorieBox, roleComboBox;
    private JButton  productManageBtn, userManageBtn, addProductBtn, refreshBtn, 
            updateProductBtn, backProductBtn, addUserBtn, updateUserBtn, refreshUserBtn;
    private PopTables popTable = new PopTables();
    private int x, y = 0;
    private Point currentLocation;
    private Controller controller = new Controller();
    
    public AdministrationPanels(){
        setComboBox();
        initPanels();
        initButtons();
        setListeners();
        setProductTable();
        setUserTable();
        setLayouts();
        setComponents();
    }
    
    public void initPanels(){
        //Product && User Management Panels
        opsProductPnl = new JPanel();
        tableProductPnl = new JPanel();
        opsUserPnl = new JPanel();
        tableUserPnl = new JPanel();
        //testing
        userManagePnl = new JPanel(); 
        productManagePnl = new JPanel(); 
    }

    public void initButtons(){
        addProductBtn = new JButton("Add");  
        updateProductBtn = new JButton("Update");
        updateProductBtn.addActionListener(this);
        refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(this);
        
        addUserBtn = new JButton("Add");  
        updateUserBtn = new JButton("Update");  
        refreshUserBtn = new JButton("Refresh");
    }
    
    public void setComboBox(){
        //Operation to Populate ComboBox
        categorieBox = new JComboBox(controller.getCategories());
        categorieBox.addItemListener(this);
        String[] roles = {"ADMIN","USER"};
        roleComboBox = new JComboBox(roles);
    }
    
    public void setProductTable()
    {
        scProduct = new JScrollPane();
        productTable = new JTable();
        productTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                },
                new String [] {
                    "ID", "NAME", "QUANTITY", "PRICE", "VENDOR"
                })
        {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        getTableModel();
        populateTable();
        
        scProduct.setViewportView(productTable);
        scProduct.setBorder(new EmptyBorder(10,10,10,10));
        scProduct.setPreferredSize(new Dimension(600,400));
    }
    
    public void populateTable()    
    {
        String category = categorieBox.getSelectedItem().toString();
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
    
    public void setUserTable(){
        scUser = new JScrollPane();
        userTable = new JTable();
        userTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                },
                new String [] {
                    "ID", "NAME", "PASSWORD", "ROLE","STATUS"
                }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scUser.setViewportView(userTable);
        scUser.setPreferredSize(new Dimension(700,400));
        scUser.setBorder(new EmptyBorder(10,10,10,10));
        try {
            popTable.populateUserTable(userTable);
        } catch (SQLException ex) {
            Logger.getLogger(AdministrationPanels.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setLayouts(){
        opsProductPnl.setLayout(new BoxLayout(opsProductPnl, BoxLayout.Y_AXIS));//For Button Operations
        tableProductPnl.setLayout(new FlowLayout(1));
        productManagePnl.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        opsUserPnl.setLayout(new BoxLayout(opsUserPnl, BoxLayout.Y_AXIS));
        tableUserPnl.setLayout(new FlowLayout(1)); //Inputs Table
        userManagePnl.setLayout(new FlowLayout(FlowLayout.LEFT));
    }
    
    public void setListeners(){
        addUserBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ea){
                new AddEmployeeGUI().setVisible(true);
            }
        });
        updateUserBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ea){
                updateUser();
            }
        });
        refreshUserBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ea){
                try {
                    popTable.populateUserTable(userTable);
                } catch (SQLException ex) {
                    Logger.getLogger(AdministrationPanels.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    
    }
    
    public void setComponents(){
        //Product Management
        tableProductPnl.add(scProduct);
        productManagePnl.add(tableProductPnl);
        productManagePnl.add(Box.createRigidArea(new Dimension(40, 0)));
        
        opsProductPnl.add(Box.createRigidArea(new Dimension(0, 10)));
        opsProductPnl.add(categorieBox);
        opsProductPnl.add(Box.createRigidArea(new Dimension(0, 60)));
        opsProductPnl.add(addProductBtn);
        opsProductPnl.add(Box.createRigidArea(new Dimension(0, 20)));
        opsProductPnl.add(updateProductBtn);
        opsProductPnl.add(Box.createRigidArea(new Dimension(0, 20)));
        opsProductPnl.add(refreshBtn);
        productManagePnl.add(opsProductPnl);
        
        //User Management
        tableUserPnl.add(scUser);
        userManagePnl.add(tableUserPnl);
        userManagePnl.add(Box.createRigidArea(new Dimension(40, 0)));
        
        opsUserPnl.add(Box.createRigidArea(new Dimension(0, 10)));
        opsUserPnl.add(roleComboBox);
        opsUserPnl.add(Box.createRigidArea(new Dimension(0, 60)));
        opsUserPnl.add(addUserBtn);
        opsUserPnl.add(Box.createRigidArea(new Dimension(0, 20)));
        opsUserPnl.add(updateUserBtn);
        opsUserPnl.add(Box.createRigidArea(new Dimension(0, 20)));
        opsUserPnl.add(refreshUserBtn);
        userManagePnl.add(opsUserPnl);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(updateProductBtn))
        {
            try
            {
                int row = productTable.getSelectedRow();
                
                String productId = String.valueOf(getTableModel().getValueAt(row, 0));
                int id = Integer.parseInt(productId);

                String productName = String.valueOf(getTableModel().getValueAt(row, 1));

                String quantity = String.valueOf(getTableModel().getValueAt(row, 2));
                int quan = Integer.parseInt(quantity);

                String price = String.valueOf(getTableModel().getValueAt(row, 3));
                double pri = Double.parseDouble(price);

                String vendor = String.valueOf(getTableModel().getValueAt(row, 4));
                String category = categorieBox.getSelectedItem().toString();

                controller.updateProduct(
                        id,
                        productName, 
                        category,
                        vendor, 
                        quan, 
                        pri);
            }
            catch (ArrayIndexOutOfBoundsException ex)
            {
                JOptionPane.showMessageDialog(null, "No record selected.");
            }
        }
        else if (e.getSource().equals(refreshBtn))
        {
            populateTable();
        }
    }
    
    @Override
    public void itemStateChanged(ItemEvent e)
    {
        if (e.getStateChange() == ItemEvent.SELECTED)
        {
            populateTable();
        }
    }
    
     public void updateUser(){
        try{
            int row = userTable.getSelectedRow();

            String usID = String.valueOf(getUserTableModel().getValueAt(row, 0));
            int id = Integer.parseInt(usID);

            String usName = String.valueOf(getUserTableModel().getValueAt(row, 1));
            String usPassword = String.valueOf(getUserTableModel().getValueAt(row, 2));

            String userRole = String.valueOf(getUserTableModel().getValueAt(row, 3));
            UserRole role = getUserRole(userRole);

            boolean userStatus = Boolean.parseBoolean(String.valueOf(getUserTableModel().getValueAt(row, 4)));

            User user = new User(id, usName, usPassword, role,userStatus);
            controller.updateUser(new User(id, usName, usPassword, role,userStatus));
        }catch (ArrayIndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null, "No record selected.");
        }
    }
     
     public UserRole getUserRole(String role){
        if(role.equals("ADMIN")){
            return UserRole.ADMIN;
        }
        else{
           return UserRole.USER;
        }
     }
     

    public JPanel getProductManagePnl() {
        return productManagePnl;
    }

    public JPanel getUserManagePnl() {
        return userManagePnl;
    }

    public JButton getProductManageBtn() {
        return productManageBtn;
    }

    public JButton getUserManageBtn() {
        return userManageBtn;
    }

    public JButton getAddProductBtn() {
        return addProductBtn;
    }

    public JButton getUpdateProductBtn() {
        return updateProductBtn;
    }

    public JButton getRefreshBtn() {
        return refreshBtn;
    }

    public JButton getAddUserBtn() {
        return addUserBtn;
    }

    public JButton getUpdateUserBtn() {
        return updateUserBtn;
    }

    public DefaultTableModel getTableModel()
    {
        return(DefaultTableModel) productTable.getModel();
    }
    public DefaultTableModel getUserTableModel()
    {
        return(DefaultTableModel) userTable.getModel();
    }
}
