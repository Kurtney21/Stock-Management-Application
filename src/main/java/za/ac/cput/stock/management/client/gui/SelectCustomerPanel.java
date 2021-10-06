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

public class SelectCustomerPanel implements ActionListener{
    private JPanel addCustomerPnl, tablePnl, buttonPnl;
    private Font ft;
    private JTable table;
    private JLabel selectLbl;
    private JScrollPane sc; 
    private JButton addBtn, nextBtn, backBtn;

    public SelectCustomerPanel(){
        initPanels();
        initButtons();
        setTable();
        setLayouts();
        setComponents();
        setListenerEvents();
    }
    
    public void initPanels(){
        addCustomerPnl = new JPanel();
        tablePnl = new JPanel(); 
        buttonPnl = new JPanel();
        selectLbl = new JLabel("Select Customer and click 'next':", SwingConstants.LEADING);
        ft = new Font("Roboto", Font.PLAIN|Font.BOLD,12);
        selectLbl.setFont(ft);
        selectLbl.setForeground(new Color(50,127,90));
    }

    public void initButtons(){
        addBtn = new JButton("Add");
        addBtn.setToolTipText("Add A New Customer");
        nextBtn = new JButton("Next");
        nextBtn.setToolTipText("Continue With Selected Customer!");
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
        addCustomerPnl.setLayout(new BoxLayout(addCustomerPnl, BoxLayout.X_AXIS));
        tablePnl.setLayout(new BoxLayout(tablePnl, BoxLayout.Y_AXIS));
        buttonPnl.setLayout(new BoxLayout(buttonPnl, BoxLayout.Y_AXIS));
    }
    
    public void setComponents(){
        addCustomerPnl.add(tablePnl);
        selectLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        tablePnl.add(selectLbl);
        tablePnl.add(Box.createRigidArea(new Dimension(0,10)));
        tablePnl.add(sc);
        
        addCustomerPnl.add(buttonPnl);
        buttonPnl.add(Box.createRigidArea(new Dimension(0,50)));
        addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPnl.add(addBtn);
        buttonPnl.add(Box.createRigidArea(new Dimension(0,10)));
        nextBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPnl.add(nextBtn);
        buttonPnl.add(Box.createRigidArea(new Dimension(90,150)));
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPnl.add(backBtn);
    }
    
    public void setListenerEvents(){
        addBtn.addActionListener(this);
        nextBtn.addActionListener(this);
        backBtn.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    public JPanel getAddCustomerPnl() {
        return addCustomerPnl;
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

    public JButton getNextBtn() {
        return nextBtn;
    }

    public JButton getBackBtn() {
        return backBtn;
    }

    
}
