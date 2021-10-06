/**
 * @author Curstin Jade Rose (220275408)
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year ADP 262s
 */

package za.ac.cput.stock.management.client.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import za.ac.cput.stock.management.common.*;
import za.ac.cput.stock.management.controller.*;

public class SalesReportPanel {
    private JPanel salesReportTablePnl,mainPnl,
            salesReportButtonsPnl, salesReportPnl, northPnl;
    private JButton exportBtn, backBtn;
    private JLabel headingLbl;
    private JComboBox  categorieBox;
    private JTable productTable;
    private JScrollPane productSc;
    private int x, y = 0;
    private ViewController homeController;
    
    public SalesReportPanel(){
        initPanels();
        initButtons();
        initComboBox();
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
        headingLbl = new JLabel("Sales Report:", SwingConstants.LEFT);
        exportBtn = new JButton("Export to txt.");
        backBtn =  new JButton("Back");
    }
    
    public void initComboBox(){
        categorieBox = new JComboBox(new Categories().getCategories().toArray());
    }
    
    public void setProductTable(){
        productSc = new JScrollPane();
        productTable = new JTable();
        productTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "ID", "NAME", "QUANTITY", "PRICE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        productSc.setViewportView(productTable);
        productSc.setBorder(new EmptyBorder(10,10,10,10));
        productSc.setPreferredSize(new Dimension(600,400));
    }
    
    //setting up components
    public void setLayouts(){
        mainPnl.setLayout(new BorderLayout());
        salesReportPnl.setLayout(new BorderLayout());
        northPnl.setLayout(new FlowLayout(FlowLayout.LEFT));
        salesReportTablePnl.setLayout(new BoxLayout(salesReportTablePnl,BoxLayout.X_AXIS));
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
        categorieBox.setMaximumSize(new Dimension(150,20));
        salesReportButtonsPnl.add(Box.createRigidArea(new Dimension(200, 20)));
        salesReportButtonsPnl.add(categorieBox);
        salesReportButtonsPnl.add(Box.createRigidArea(new Dimension(0, 10)));
        exportBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        salesReportButtonsPnl.add(exportBtn);
        salesReportButtonsPnl.add(Box.createRigidArea(new Dimension(0, 50)));
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        salesReportButtonsPnl.add(backBtn);
    }
    
    public JPanel getSalesReportPnl() {
        return salesReportPnl;
    }

    public JButton getExportBtn() {
        return exportBtn;
    }

    public JButton getBackBtn() {
        return backBtn;
    }
    
    
}
