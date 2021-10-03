/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package za.ac.cput.stock.management.client.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year
 */
public class AdministrationGUI extends JFrame implements ActionListener{
    private Font ft, ftTitle,ftMdm, ftSml;
    private JPanel westPnl, northPnl, centerPnl, opsProductPnl, tableProductPnl,opsUserPnl, tableUserPnl;    //Center Panel ==  Parent Panel
    private JPanel productManagePnl, userManagePnl;
    
    //User Management
    
    private JTable productTable, userTable;
    private JScrollPane scProduct, scUser;
    
    private JLabel logoLbl, authorLbl;
    private ImageIcon logoIcn;
    private JComboBox categorieBox, roleComboBox;
    private JButton extBtn, minBtn,  productManageBtn, userManageBtn,  addProductBtn, 
            updateProductBtn, backProductBtn, addUserBtn, updateUserBtn, backUserBtn;
    private int x, y = 0;
    private Point currentLocation;
    
    public AdministrationGUI(){
        super("Admin");
        setUndecorated(true);
        setFormatting();
        setFrameSettings();
        setComboBox();
        initPanels();
        initLabels();
        initButtons();
        setProductTable();
        setUserTable();
        setLayouts();
        setComponents();
        setListenerEvents();
        setProductManagementListener();
        setUserManagementListener();
    }
    
    public void initPanels(){
        northPnl = new JPanel();
        centerPnl = new JPanel();
        westPnl = new JPanel();
        opsProductPnl = new JPanel();
        tableProductPnl = new JPanel();
        //Product && User Management Panels
        opsProductPnl = new JPanel();
        tableProductPnl = new JPanel();
        opsUserPnl = new JPanel();
        tableUserPnl = new JPanel();
        //testing
        userManagePnl = new JPanel(); 
        productManagePnl = new JPanel(); 
    }

    public void initLabels(){
        logoIcn = new ImageIcon("resources/logoLogin.png");
        logoLbl = new JLabel(); 
        logoLbl.setIcon(logoIcn);
        authorLbl = new JLabel("developed by @ Kurtney & Curstin");  
        authorLbl.setFont(ftSml);
    }

    public void initButtons(){
        extBtn = new JButton("X");
        minBtn = new JButton("-");
        productManageBtn = new JButton("Product Management");
        userManageBtn = new JButton("User Management");
         
        addProductBtn = new JButton("Add");  
        updateProductBtn = new JButton("Update");  
        backProductBtn = new JButton("Back"); 
        
        addUserBtn = new JButton("Add");  
        updateUserBtn = new JButton("Update");  
        backUserBtn = new JButton("Back");
    }
    
    public void setLayouts(){
        this.setLayout(new BorderLayout());
        northPnl.setLayout(new FlowLayout(FlowLayout.RIGHT));
        westPnl.setLayout(new BoxLayout(westPnl, BoxLayout.Y_AXIS));
        centerPnl.setLayout(new CardLayout());
        opsProductPnl.setLayout(new BoxLayout(opsProductPnl, BoxLayout.Y_AXIS));//For Button Operations
        opsUserPnl.setLayout(new BoxLayout(opsUserPnl, BoxLayout.Y_AXIS));
        tableProductPnl.setLayout(new FlowLayout(1));                      //Inputs Table
        productManagePnl.setLayout(new FlowLayout(2));
        userManagePnl.setLayout(new FlowLayout(2));
    }
    
    public void setComboBox(){
        //Operation to Populate ComboBox
        String[] categories = {"Dairy","Cold-Beverage","Canned Goods","Condiment","Breakfast Cereal"};
        categorieBox = new JComboBox(categories);
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
        productTable.setAutoCreateRowSorter(true);
        productTable.setBackground(new Color(0, 158, 193));
        productTable.setBorder(new javax.swing.border.MatteBorder(null));
        productTable.setForeground(new Color(255, 255, 255));
        scProduct.setPreferredSize(new Dimension(400,300));
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
        userTable.setAutoCreateRowSorter(true);
        userTable.setBackground(new Color(0, 158, 193));
        userTable.setBorder(new javax.swing.border.MatteBorder(null));
        userTable.setForeground(new Color(255, 255, 255));
        scUser.setPreferredSize(new Dimension(400,300));
    }
    
    public void setComponents(){
        //adding Panels to JFrame
        this.add(northPnl, BorderLayout.NORTH);
        this.add(centerPnl, BorderLayout.CENTER);
        this.add(westPnl, BorderLayout.WEST);
        
        //adding components to panels
        northPnl.add(minBtn);
        northPnl.add(extBtn);
        
        westPnl.setAlignmentX(Component.LEFT_ALIGNMENT);
        westPnl.add(logoLbl);
        //logoLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        westPnl.add(Box.createRigidArea(new Dimension(10, 10)));
        
        //Product Management Button
        productManageBtn.setMaximumSize(new Dimension(180,60));
        productManageBtn.setFont(ftMdm);
        westPnl.add(productManageBtn);
        
        //User Management Button
        userManageBtn.setMaximumSize(new Dimension(180,60));
        userManageBtn.setFont(ftMdm);
        westPnl.add(userManageBtn);
        westPnl.add(Box.createRigidArea(new Dimension(10, 160)));
        westPnl.add(authorLbl);
        
        //Product Management
        tableProductPnl.add(scProduct);
        productManagePnl.add(tableProductPnl);
        productManagePnl.add(Box.createRigidArea(new Dimension(40, 0)));
        
        opsProductPnl.add(Box.createRigidArea(new Dimension(100, 20)));
        opsProductPnl.add(categorieBox);
        opsProductPnl.add(Box.createRigidArea(new Dimension(0, 60)));
        opsProductPnl.add(addProductBtn);
        opsProductPnl.add(Box.createRigidArea(new Dimension(0, 20)));
        opsProductPnl.add(updateProductBtn);
        opsProductPnl.add(Box.createRigidArea(new Dimension(90, 200)));
        opsProductPnl.add(backProductBtn);
        productManagePnl.add(opsProductPnl);
        
        
        //User Management
        tableUserPnl.add(scUser);
        userManagePnl.add(tableUserPnl);
        userManagePnl.add(Box.createRigidArea(new Dimension(40, 0)));
        
        opsUserPnl.add(Box.createRigidArea(new Dimension(100, 20)));
        opsUserPnl.add(roleComboBox);
        opsUserPnl.add(Box.createRigidArea(new Dimension(0, 60)));
        opsUserPnl.add(addUserBtn);
        opsUserPnl.add(Box.createRigidArea(new Dimension(0, 20)));
        opsUserPnl.add(updateUserBtn);
        opsUserPnl.add(Box.createRigidArea(new Dimension(90, 200)));
        opsUserPnl.add(backUserBtn);
        userManagePnl.add(opsUserPnl);
        
        
        //Adding User&Product Managements panels to Main Center Panel
        centerPnl.add(productManagePnl);
        centerPnl.add(userManagePnl);
    }
    
    public void setFormatting(){
        ft = new Font("Arial", Font.ITALIC |  Font.BOLD,16 );
        ftTitle = new Font("Roboto",  Font.BOLD,26 );
        ftMdm = new Font("Roboto",   Font.BOLD,12 ); 
        ftSml = new Font("Roboto", Font.PLAIN,8 );
    }
    
    public void setListenerEvents(){
        extBtn.addActionListener(this);
        minBtn.addActionListener(this); 
        userManageBtn.addActionListener(this);
        productManageBtn.addActionListener(this);
        
        northPnl.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                currentLocation = e.getPoint();
             }
         });
        northPnl.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point currentScreenLocation = e.getLocationOnScreen();
                setLocation(currentScreenLocation.x - currentLocation.x, currentScreenLocation.y - currentLocation.y);
            }
        });
    }
    
    public void setFrameSettings(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,450);
        this.setLocationRelativeTo(null);
    }
    
    public void setProductManagementListener(){
        addProductBtn.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                        
                }  
            }
        );
        updateProductBtn.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                        
                }  
            }
        );
        backProductBtn.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                        
                }  
            }
        ); 
    }
    
    public void setUserManagementListener(){
    
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("X")){
            System.exit(0);
        }
        if(e.getActionCommand().equals("-")){
           setState(Frame.ICONIFIED);
        }else{
            this.setVisible(true);
        }
        if(e.getActionCommand().equals("Product Management")){
            centerPnl.removeAll();
            centerPnl.add(productManagePnl);
            centerPnl.repaint();
            centerPnl.revalidate();
        }
        if(e.getActionCommand().equals("User Management")){
            centerPnl.removeAll();
            centerPnl.add(userManagePnl);
            centerPnl.repaint();
            centerPnl.revalidate();
        }
    }
    
}
