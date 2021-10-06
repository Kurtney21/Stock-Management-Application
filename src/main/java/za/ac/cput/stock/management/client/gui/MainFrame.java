/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package za.ac.cput.stock.management.client.gui;

import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import za.ac.cput.stock.management.controller.MainFrameController;
import za.ac.cput.stock.management.controller.ViewController;

public class MainFrame extends JFrame implements ActionListener {
    private JPanel cardPnl, transactionPanel, northPnl, welcomePnl;
    private JMenuBar menuBar;
    private ViewController controller;
    private JMenu fileMenu, adminMenu;
    private JMenuItem exitMenuItem, productManagementMenuItem, userManagementMenuItem,
            transactionMenuItem, addCustomerMenuItem,invoiceMenuItem, salesReportMenuItem,
            inventoryMenuItem;
    private AdministrationPanels adminGUI;
    private JButton newTransButton;
    private SelectCustomerPanel selectCustomerPnl;
    private SelectProductPanel selectProductPanel;
    private JLabel welcomeLbl, loginIcnLbl;
    private ImageIcon loginIcn;
    private MainFrameController mainFrameController;
    private SalesReportPanel salesReportGUI;
    private AddCustomerPanel addCustomerPanel;
    private InventoryPanel inventoryPanel;
    private Font ft;
    
    
    public MainFrame(){
        // mainFrameController = new MainFrameController();
        initComponents();
        setFrameSettings();
        controller = new ViewController();
    }
    
    private void initComponents(){
        initLabels();
        initPanels();
        initButtons();
        setMenuBar();
        switchPanels();
        listeners();
        setComponents();
    }
    
    public void initLabels(){
        ft = new Font("Roboto",Font.BOLD,36);
        welcomeLbl = new JLabel("Welcome $Username(status)", SwingConstants.CENTER); 
        welcomeLbl.setFont(ft);
        loginIcnLbl = new JLabel("$Username");
        loginIcn = new ImageIcon("resources/userLogin.png");
        loginIcnLbl.setIcon(loginIcn);
    }
    
    private void initPanels(){
        adminGUI = new AdministrationPanels();
        cardPnl = new JPanel();
        cardPnl.setLayout(new CardLayout());
        this.add(cardPnl);
        cardPnl.add(welcomeLbl);
        transactionPanel = new JPanel();
        transactionPanel.setLayout(new CardLayout());
        selectCustomerPnl = new SelectCustomerPanel();
        selectProductPanel = new SelectProductPanel();
        northPnl = new JPanel();
        northPnl.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(northPnl, BorderLayout.NORTH);
        northPnl.add(loginIcnLbl);
        salesReportGUI = new SalesReportPanel();
        addCustomerPanel = new AddCustomerPanel();
        welcomePnl = new JPanel();
        welcomePnl.setLayout(new CardLayout());
        welcomePnl.add(welcomeLbl);
        inventoryPanel = new InventoryPanel();
    }
    
    public void initButtons(){
        newTransButton = new JButton("+New Transaction");
        newTransButton.setFont(new Font("Roboto", Font.BOLD |Font.PLAIN, 28));
        transactionPanel.add(newTransButton);
    }
    
    public void setTitle(String title){
        this.setTitle(title);
    }
    
    public void setMenuBar(){
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        
        fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        
        transactionMenuItem = new JMenuItem("Transaction");
        fileMenu.add(transactionMenuItem);
        salesReportMenuItem = new JMenuItem("Sales Report");
        fileMenu.add(salesReportMenuItem);
        addCustomerMenuItem = new JMenuItem("Add Customer");
        fileMenu.add(addCustomerMenuItem);
        invoiceMenuItem = new JMenuItem("Invoice");
        fileMenu.add(invoiceMenuItem);
        inventoryMenuItem = new JMenuItem("Inventory");
        fileMenu.add(inventoryMenuItem);
        exitMenuItem = new JMenuItem("Exit");
        fileMenu.add(exitMenuItem);
       
        adminMenu  = new JMenu("Administration");
        menuBar.add(adminMenu);
        
        productManagementMenuItem = new JMenuItem("Product management");
        userManagementMenuItem = new JMenuItem("User management");
        adminMenu.add(productManagementMenuItem);
        adminMenu.add(userManagementMenuItem);
    }
    
    public void closeFrame(){
        this.dispose();
    }
    
    public void switchPanels(){
        exitMenuItem.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){
                    closeFrame();
                }  
            });
        
        salesReportMenuItem.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                        controller.swapPanels(cardPnl, salesReportGUI.getSalesReportPnl());
                }  
            });
        inventoryMenuItem.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                        controller.swapPanels(cardPnl, inventoryPanel.getMainPane());
                }  
            });
        productManagementMenuItem.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){
                    controller.swapPanels(cardPnl, adminGUI.getProductManagePnl());  
                }  
            });
       
        userManagementMenuItem.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){
                        controller.swapPanels(cardPnl, adminGUI.getUserManagePnl());
                }  
            });
        transactionMenuItem.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                        controller.swapPanels(cardPnl, transactionPanel);
                }  
            }); 
        addCustomerMenuItem.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                        controller.swapPanels(cardPnl, addCustomerPanel.getAddCustomerPnl());
                }  
            });
        invoiceMenuItem.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                        
                }  
            });
    }
    
    public void listeners(){
      //  mainFrameController.logoutMenu(this, logoutMenuItem);
      adminGUI.getAddProductBtn().addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                    new AddProductGUI().setVisible(true);
                }  
            });
      adminGUI.getAddUserBtn().addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){
                    new AddEmployeeGUI().setVisible(true);
                }  
            });
      addCustomerPanel.getAddBtn().addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                    new AddCustomerGUI().setVisible(true);
                }  
            });
        
        newTransButton.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                        controller.swapPanels(cardPnl, selectCustomerPnl.getAddCustomerPnl());
                }  
            });
        selectCustomerPnl.getNextBtn().addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                        controller.swapPanels(cardPnl, selectProductPanel.getAddProductPnl());
                }  
            });
        selectCustomerPnl.getBackBtn().addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                        controller.swapPanels(cardPnl, welcomePnl);
                }  
            });
        selectProductPanel.getBackBtn().addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                        controller.swapPanels(cardPnl, selectCustomerPnl.getAddCustomerPnl());
                }  
            });
    }
    
    public void setComponents(){
    }
    
    public void setFrameSettings(){
        this.setSize(1000,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public JLabel getWelcomeLbl() {
        return welcomeLbl;
    }
    
    public JMenu getAdminMenu() {
        return adminMenu;
    }

    public JLabel getLoginIcnLbl() {
        return loginIcnLbl;
    }

    public JMenuItem getExitMenuItem() {
        return exitMenuItem;
    }

    public JMenuItem getProductManagementMenuItem() {
        return productManagementMenuItem;
    }

    public JMenuItem getUserManagementMenuItem() {
        return userManagementMenuItem;
    }

    public JMenuItem getTransactionMenuItem() {
        return transactionMenuItem;
    }

    public JMenuItem getInvoiceMenuItem() {
        return invoiceMenuItem;
    }
    
    
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
            new MainFrame().setVisible(true);
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
