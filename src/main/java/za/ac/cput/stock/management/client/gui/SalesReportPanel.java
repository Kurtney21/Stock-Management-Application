/**
 * @author Curstin Jade Rose (220275408)
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year ADP 262s
 */

package za.ac.cput.stock.management.client.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.*;
import za.ac.cput.stock.management.common.*;
import za.ac.cput.stock.management.controller.*;

public class SalesReportPanel implements ActionListener{
    private JPanel salesReportTablePnl,mainPnl,
            salesReportButtonsPnl, salesReportPnl, northPnl;
    private JButton exportBtn, refreshBtn;
    private JLabel headingLbl, totalSales;
    private JTable productTable;
    private JScrollPane productSc;
    private int x, y = 0;
    private ViewController homeController;
    private Controller controller = new Controller();
    private PopTables pop = new PopTables();  
    
    public SalesReportPanel(){
        initPanels();
        initButtons();
        setProductTable();
        setLayouts();
        setComponents();
        setControllers();
    }
    
    public void setControllers(){
        homeController = new ViewController();
    }
    
    //Initialize Components
    public void initPanels(){
        salesReportTablePnl = new JPanel();
        salesReportButtonsPnl = new JPanel();
        salesReportPnl = new JPanel();
        mainPnl = new JPanel();
        northPnl = new JPanel();
    }

    public void initButtons(){
        headingLbl = new JLabel("Sales Report", SwingConstants.LEFT);
        totalSales = new JLabel("");
        refreshTotal();
        
        exportBtn = new JButton("Export to .txt");
        refreshBtn =  new JButton("Refresh");
    }
    public void refreshTotal(){
        totalSales.setText("Total: R " + controller.getSalesTotal());
    }
    
    public void setProductTable(){
        productSc = new JScrollPane();
        productTable = new JTable();
        productTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                 "PRODUCT_NAME", "TOTAL_QUANTITY", "SUB-TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        productSc.setViewportView(productTable);
        productSc.setBorder(new EmptyBorder(10,10,10,10));
        productSc.setPreferredSize(new Dimension(600,400));
        try {
            pop.populateSalesTable(productTable);
        } catch (SQLException ex) {
            Logger.getLogger(SalesReportPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //setting up components
    public void setLayouts(){
        mainPnl.setLayout(new BorderLayout());
        salesReportPnl.setLayout(new BorderLayout());
        northPnl.setLayout(new FlowLayout(FlowLayout.LEFT));
        salesReportTablePnl.setLayout(new BoxLayout(salesReportTablePnl,BoxLayout.Y_AXIS));
        salesReportButtonsPnl.setLayout(new BoxLayout(salesReportButtonsPnl,BoxLayout.Y_AXIS));
    }
    
    public void setComponents(){
        headingLbl.setFont(new Fonts().getMed());
        northPnl.add(headingLbl);
        salesReportPnl.add(northPnl, BorderLayout.NORTH);
        salesReportPnl.add(salesReportTablePnl, BorderLayout.CENTER);
        salesReportPnl.add(salesReportButtonsPnl, BorderLayout.EAST);
        
        //center Table
        productSc.setBorder(new EmptyBorder(10,10,10,10));
        salesReportTablePnl.add(productSc);
        
        //center Buttons
        salesReportButtonsPnl.add(Box.createRigidArea(new Dimension(250, 50)));
        salesReportButtonsPnl.add(Box.createRigidArea(new Dimension(0, 10)));
        exportBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        salesReportButtonsPnl.add(exportBtn);
        salesReportButtonsPnl.add(Box.createRigidArea(new Dimension(0, 10)));
        salesReportButtonsPnl.add(Box.createRigidArea(new Dimension(0, 50)));
        totalSales.setAlignmentX(Component.CENTER_ALIGNMENT);
        salesReportButtonsPnl.add(totalSales);
        totalSales.setFont(new Fonts().getMed());
        salesReportButtonsPnl.add(Box.createRigidArea(new Dimension(0, 50)));
        refreshBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        salesReportButtonsPnl.add(refreshBtn);
        
        refreshBtn.addActionListener(this);
    }
    
    public JPanel getSalesReportPnl() {
        return salesReportPnl;
    }

    public JButton getExportBtn() {
        return exportBtn;
    }

    public JTable getProductTable() {
        return productTable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getActionCommand().equals("Refresh")){
            refreshTotal();
            try {
                new PopTables().populateSalesTable(getProductTable());
            } catch (SQLException ex) {
                Logger.getLogger(TransactionPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
    }
    
}
