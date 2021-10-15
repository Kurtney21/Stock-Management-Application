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
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import za.ac.cput.stock.management.controller.Controller;

public class TransactionPanel implements ActionListener
{
    private JPanel addProductPnl, tablePnl, buttonPnl;
    private Font ft;
    private JTable transactionTable;
    private JLabel selectLbl;
    private JComboBox catBox, customerBox, productBox;
    private JScrollPane sc; 
    private JButton addBtn, backBtn;
    private JSpinner quantitySpinner;
    private JTextField priceTxt;
    private SalesReportPanel salesReport = new SalesReportPanel();
    private Controller controller = new Controller();

    public TransactionPanel(){
        initPanels();
        initButtons();
        initComboBox();
        setTable();
        setLayouts();
        setComponents();
        setListenerEvents();
    }
    
    public void initComboBox(){
        catBox = new JComboBox(controller.getCategories());
        customerBox = new JComboBox(controller.getCustomerNames());
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
        addBtn.setToolTipText("Add New Transaction");
        backBtn = new JButton("Back");
        backBtn.setToolTipText("Back to DashBoard");
    }

    public void setTable(){
        transactionTable = new JTable(new DefaultTableModel(
            new Object[][] {
            }, 
            new String[] {
                "TRANSACTION_ID","PRODUCT_ID","CUSTOMER_ID","USER_ID", "TOTAL_QUANTITY", "TOTAL_PRICE"
            }) 
        {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        getTableModel();
        populateTable();
        
        sc = new JScrollPane(transactionTable);
        sc.setViewportView(transactionTable);
        sc.setBorder(new EmptyBorder(10,10,10,10));
        sc.setPreferredSize(new Dimension(600,400));
        sc.setToolTipText("Select Customer From Table:");
    }
    
    public void populateTable()
    {
        getTableModel().setRowCount(0);
        
        int transactionsLen = controller.getTransactions().size();
        
        for (int i = 0; i < transactionsLen; i++)
        {
            var record = controller.getTransactions().get(i);
            getTableModel().addRow(new Object [] {
                record.getTransactionId(),
                record.getProductId(),
                record.getCustomerId(),
                record.getUserId(),
                record.getTotalQuantity(),
                Math.round(record.getTotalPrice()*100.00)/100.00
            });
        }
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
        buttonPnl.add(Box.createRigidArea(new Dimension(0,10)));
        addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPnl.add(addBtn);
        buttonPnl.add(Box.createRigidArea(new Dimension(0,10)));
        buttonPnl.add(Box.createRigidArea(new Dimension(0,10)));
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPnl.add(backBtn);
    }
    
    public void setListenerEvents(){
        addBtn.addActionListener(this);
        backBtn.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if (ae.getSource().equals(addBtn))
        {
            quantitySpinner = new JSpinner();
            priceTxt = new JTextField();
            priceTxt.setEnabled(false);
            productBox = new JComboBox(controller.getProductNames());
            customerBox = new JComboBox(controller.getCustomerNames());
            priceTxt.setText(String.valueOf(
                    controller
                            .getProducts()
                            .get(productBox.getSelectedIndex())
                            .getProductPrice()));
            
            productBox.addItemListener((ItemEvent ie) ->
            {
                if (ie.getStateChange() == ItemEvent.SELECTED)
                {
                    String productPrice = String.valueOf(
                    controller.getProducts()
                            .get(productBox.getSelectedIndex())
                            .getProductPrice());
                    priceTxt.setText(productPrice);
                }
            });
            
            quantitySpinner.addChangeListener((ChangeEvent ce) ->
            {
                if (ce.getSource().equals(quantitySpinner))
                {
                    double price = controller
                            .getProducts()
                            .get(productBox.getSelectedIndex())
                            .getProductPrice();
                    Integer quantity = (Integer) quantitySpinner.getValue();
                    String total = String.valueOf(Math.round(
                            (price*quantity)*100.00)/100.00);
                    priceTxt.setText(total);
                }
            });
            
            Object [] inputs = {
                "Product:", productBox,
                "Quantity: ", quantitySpinner,
                "Total: ", priceTxt,
                "Customer:", customerBox
            };
            
            int confirm = JOptionPane.showConfirmDialog(
                    null, 
                    inputs, 
                    "Add New Transaction", 
                    JOptionPane.OK_CANCEL_OPTION);
            
            if(confirm == JOptionPane.OK_OPTION)
            {
                
                if (!validateTransaction()) return;
                
                int transactionId = controller.addTransaction(
                        productBox.getSelectedItem().toString(), 
                        (Integer) quantitySpinner.getValue(), 
                        Double.parseDouble(priceTxt.getText()), 
                        customerBox.getSelectedItem().toString(), 
                        controller.getValidUser().getUsername());
                
                controller.updateStockQuantity(controller
                        .getProducts()
                        .get(productBox.getSelectedIndex())
                        .getProductId(),
                        transactionId);
                
                populateTable();
            }
        }
    }
    
    public boolean validateTransaction()
    {
        boolean isValidateTransaction = true;
        String product = productBox.getSelectedItem().toString();
        int stockQuantity = controller
                .getProducts()
                .get(productBox.getSelectedIndex())
                .getStockQuantity();

        
        if ((Integer)quantitySpinner.getValue() <= 0)
        {
            JOptionPane.showMessageDialog(null, "Customer must buy "
                    + "one or more products.\n"
                    + "Please ensure the quantity is atleast one.");
            isValidateTransaction = false;
        }
        else if ((Integer)quantitySpinner.getValue() > stockQuantity)
        {
            JOptionPane.showMessageDialog(null, "There are only "
                    + stockQuantity + " " + product + " in-stock");
            isValidateTransaction = false;
        }
        
        return isValidateTransaction;
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

    public JTable getTransactionTable() {
        return transactionTable;
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

    public JButton getBackBtn() {
        return backBtn;
    }
    
    public DefaultTableModel getTableModel()
    {
        return (DefaultTableModel) transactionTable.getModel();
    }
}

