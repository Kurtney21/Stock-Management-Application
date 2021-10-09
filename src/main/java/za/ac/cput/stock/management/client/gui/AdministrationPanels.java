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
import za.ac.cput.stock.management.client.entry.Client;
import za.ac.cput.stock.management.common.Categories;
import za.ac.cput.stock.management.common.Fonts;
import za.ac.cput.stock.management.controller.LoginController;

public class AdministrationPanels {
    private JPanel westPnl, northPnl, centerPnl, opsProductPnl, tableProductPnl,
            opsUserPnl, tableUserPnl, mainContainer;    //Center Panel ==  Parent Panel
    private JPanel productManagePnl, userManagePnl;
    
    //User Management
    private JTable productTable, userTable;
    private JScrollPane scProduct, scUser;
    
    private JLabel logoLbl, authorLbl;
    private ImageIcon logoIcn;
    private JComboBox categorieBox, roleComboBox;
    private JButton  productManageBtn, userManageBtn,  addProductBtn, 
            updateProductBtn, backProductBtn, addUserBtn, updateUserBtn, backUserBtn;
    private int x, y = 0;
    private Point currentLocation;
    
    public AdministrationPanels(){
        setComboBox();
        initPanels();
        initButtons();
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
        backProductBtn = new JButton("Back"); 
        
        addUserBtn = new JButton("Add");  
        updateUserBtn = new JButton("Update");  
        backUserBtn = new JButton("Back");
    }
    
    public void setComboBox(){
        //Operation to Populate ComboBox
        /*String[] categories = {"Arts & Scholistic","Bags & Backpacks",
            "Books & Paper","File & Filling",
            "Inks & Toners","Office Supplies","Office Automation and Electronics",
            "Stationary","Technology",
            "Snacks & Drinks"};*/
        categorieBox = new JComboBox(new Categories().getCategories().toArray());
        String[] roles = {"ADMIN","USER"};
        roleComboBox = new JComboBox(roles);
    }
    
    public void setProductTable(){
        scProduct = new JScrollPane();
        productTable = new JTable();
        productTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "ID", "NAME", "QUANTITY", "PRICE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scProduct.setViewportView(productTable);
        scProduct.setBorder(new EmptyBorder(10,10,10,10));
        scProduct.setPreferredSize(new Dimension(600,350));
    }
    
    public void setUserTable(){
        scUser = new JScrollPane();
        userTable = new JTable();
        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "ID", "NAME", "SURNAME", "ROLE","STATUS"
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
        scUser.setPreferredSize(new Dimension(600,350));
        scUser.setBorder(new EmptyBorder(10,10,10,10));
    }
    
    public void setLayouts(){
        opsProductPnl.setLayout(new BoxLayout(opsProductPnl, BoxLayout.Y_AXIS));//For Button Operations
        tableProductPnl.setLayout(new FlowLayout(1));
        productManagePnl.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        opsUserPnl.setLayout(new BoxLayout(opsUserPnl, BoxLayout.Y_AXIS));
        tableUserPnl.setLayout(new FlowLayout(1)); //Inputs Table
        userManagePnl.setLayout(new FlowLayout(FlowLayout.LEFT));
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
        opsProductPnl.add(backProductBtn);
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
        opsUserPnl.add(backUserBtn);
        userManagePnl.add(opsUserPnl);
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

    public JButton getBackProductBtn() {
        return backProductBtn;
    }

    public JButton getAddUserBtn() {
        return addUserBtn;
    }

    public JButton getUpdateUserBtn() {
        return updateUserBtn;
    }

    public JButton getBackUserBtn() {
        return backUserBtn;
    }

}
