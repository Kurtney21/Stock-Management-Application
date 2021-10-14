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
    private TransactionPanel selectProductPanel;
    private JLabel welcomeLbl, loginIcnLbl;
    private ImageIcon loginIcn;
    private MainFrameController mainFrameController;
    private SalesReportPanel salesReportGUI;
    private AddCustomerPanel addCustomerPanel;
    private InventoryPanel inventoryPanel;
    private InvoicePanel invoiceGUI;
    private Font ft;
    
    
    public MainFrame() {
        // mainFrameController = new MainFrameController();
        initComponents();
        setFrameSettings();
        controller = new ViewController();
    }
    
    private void initComponents() {
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
    
    private void initPanels() {
        adminGUI = new AdministrationPanels();
        cardPnl = new JPanel();
        cardPnl.setLayout(new CardLayout());
        this.add(cardPnl);
        cardPnl.add(welcomeLbl);
        transactionPanel = new JPanel();
        transactionPanel.setLayout(new CardLayout());
        selectProductPanel = new TransactionPanel();
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
        invoiceGUI = new InvoicePanel();
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
    
    public void confirmExit()
    {
        int confirmExit = JOptionPane.showConfirmDialog(
                        MainFrame.this, 
                        "Are sure you want exit?",
                        "Exit",
                        JOptionPane.YES_NO_OPTION);
                
        if (confirmExit == JOptionPane.YES_OPTION)
        {
           dispose();
        }
        else
        {
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }
    
    public void closeFrame()
    {
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent we)
            {
                confirmExit();
            }
        });
    }
    
    public void switchPanels(){
        closeFrame();
        exitMenuItem.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){
                    confirmExit();
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
                        controller.swapPanels(cardPnl, invoiceGUI.getMainPane());
                }  
            });
    }
    
    public void listeners()
    {
      //  mainFrameController.logoutMenu(this, logoutMenuItem);
      salesReportGUI.getBackBtn().addActionListener(new ActionListener()
      {
          public void actionPerformed(ActionEvent e)
          {
              controller.swapPanels(cardPnl, welcomePnl);
          }
      });
      
      adminGUI.getAddProductBtn().addActionListener(new ActionListener()
      {
          public void actionPerformed(ActionEvent e)
          {
              new AddProductGUI().setVisible(true);
          }
      });
      adminGUI.getBackProductBtn().addActionListener(new ActionListener()
      {
          public void actionPerformed(ActionEvent e)
          {
              controller.swapPanels(cardPnl, welcomePnl);
          }
      });
      adminGUI.getBackUserBtn().addActionListener(new ActionListener()
      {
          public void actionPerformed(ActionEvent e)
          {
              controller.swapPanels(cardPnl, welcomePnl);
          }
      });
      adminGUI.getAddUserBtn().addActionListener(new ActionListener()
      {
          public void actionPerformed(ActionEvent e)
          {
              new AddEmployeeGUI().setVisible(true);
          }
      });
      newTransButton.addActionListener(new ActionListener()
      {
          public void actionPerformed(ActionEvent e)
          {  
                 controller.swapPanels(cardPnl, selectProductPanel.getAddProductPnl());
          }
      });
      selectProductPanel.getBackBtn().addActionListener(new ActionListener()
      {
          public void actionPerformed(ActionEvent e)
          {
              controller.swapPanels(cardPnl, welcomePnl);
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

    public JPanel getCardPnl() {
        return cardPnl;
    }

    public JPanel getTransactionPanel() {
        return transactionPanel;
    }

    public JPanel getNorthPnl() {
        return northPnl;
    }

    public JPanel getWelcomePnl() {
        return welcomePnl;
    }

    public ViewController getController() {
        return controller;
    }

    public JMenu getFileMenu() {
        return fileMenu;
    }

    public JMenuItem getAddCustomerMenuItem() {
        return addCustomerMenuItem;
    }

    public JMenuItem getSalesReportMenuItem() {
        return salesReportMenuItem;
    }

    public JMenuItem getInventoryMenuItem() {
        return inventoryMenuItem;
    }

    public AdministrationPanels getAdminGUI() {
        return adminGUI;
    }

    public JButton getNewTransButton() {
        return newTransButton;
    }

    public TransactionPanel getSelectProductPanel() {
        return selectProductPanel;
    }

    public ImageIcon getLoginIcn() {
        return loginIcn;
    }

    public MainFrameController getMainFrameController() {
        return mainFrameController;
    }

    public SalesReportPanel getSalesReportGUI() {
        return salesReportGUI;
    }

    public AddCustomerPanel getAddCustomerPanel() {
        return addCustomerPanel;
    }

    public InventoryPanel getInventoryPanel() {
        return inventoryPanel;
    }

    public InvoicePanel getInvoiceGUI() {
        return invoiceGUI;
    }

    public Font getFt() {
        return ft;
    }   
}
