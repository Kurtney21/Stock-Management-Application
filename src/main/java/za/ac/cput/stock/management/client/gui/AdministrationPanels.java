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
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
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
    private JButton  productManageBtn, userManageBtn,  addProductBtn, 
            updateProductBtn, backProductBtn, addUserBtn, updateUserBtn, backUserBtn;
    private int x, y = 0;
    private Point currentLocation;
    private Controller controller = new Controller();
    
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
        updateProductBtn.addActionListener(this);
        backProductBtn = new JButton("Back"); 
        
        addUserBtn = new JButton("Add");  
        updateUserBtn = new JButton("Update");  
        backUserBtn = new JButton("Back");
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
        scUser.setPreferredSize(new Dimension(700,400));
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
    }
    
    @Override
    public void itemStateChanged(ItemEvent e)
    {
        if (e.getStateChange() == ItemEvent.SELECTED)
        {
            populateTable();
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

    public DefaultTableModel getTableModel()
    {
        return(DefaultTableModel) productTable.getModel();
    }
}
