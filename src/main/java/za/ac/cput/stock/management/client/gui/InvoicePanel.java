/**
 * @author Curstin Jade Rose (220275408)
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year ADP 262s
 */

package za.ac.cput.stock.management.client.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import za.ac.cput.stock.management.common.Invoice;
import za.ac.cput.stock.management.controller.Controller;

public class InvoicePanel extends JFrame implements  ItemListener{

    private JPanel mainPane, centerPnl, eastPnl, westPnl;
    private  JComboBox customerBox, transactionBox;
    private JLabel label;
    private JTextArea txtArea;
    private JScrollPane sc;
    private Controller controller = new Controller();
    
    public InvoicePanel(){
        //initialize components
	initPanels();
        initComboBox();
        
	//setup components;
        setLayouts();
        setComponents();
    }
    
    public void initComboBox(){
        //Dummy Values
        String[] trans = {"1328421","1328422","1328423","1328424","1328425"};
        
        sc = new JScrollPane();
        txtArea = new JTextArea();
        sc.add(txtArea);
        sc.setPreferredSize(new Dimension(600,300));
        
        customerBox = new JComboBox(controller.getCustomerNames());
        transactionBox = new JComboBox(trans);
        
    }
    
    public void initPanels(){
        mainPane = new JPanel();
        centerPnl = new JPanel();
        eastPnl = new JPanel();
        westPnl = new JPanel();
    }

    
    public void setLayouts(){
        mainPane.setLayout(new BorderLayout());
        centerPnl.setLayout(new BoxLayout(centerPnl, BoxLayout.X_AXIS));
        eastPnl.setLayout(new BoxLayout(eastPnl, BoxLayout.Y_AXIS));
        westPnl.setLayout(new BoxLayout(westPnl, BoxLayout.Y_AXIS));
    }
    
    public void setComponents(){
        mainPane.add(westPnl, BorderLayout.WEST);
        mainPane.add(centerPnl, BorderLayout.CENTER);
        mainPane.add(eastPnl, BorderLayout.EAST);
        
        westPnl.add(Box.createRigidArea(new Dimension(250,150)));
        customerBox.setMaximumSize(new Dimension(150,30));
        westPnl.add(customerBox);
        westPnl.add(Box.createRigidArea(new Dimension(0,10)));
        transactionBox.setMaximumSize(new Dimension(200,30));
        westPnl.add(transactionBox);
        westPnl.add(Box.createRigidArea(new Dimension(0,150)));
        
        txtArea.setForeground(new Color(255,255,255));
        centerPnl.add(sc);
        customerBox.addItemListener(this);
        
    }

    public JPanel getMainPane() {
        return mainPane;
    }

    public JPanel getCenterPnl() {
        return centerPnl;
    }

    public JPanel getEastPnl() {
        return eastPnl;
    }

    public JPanel getWestPnl() {
        return westPnl;
    }

    public JComboBox getCustomerBox() {
        return customerBox;
    }

    public JComboBox getTransactionBox() {
        return transactionBox;
    }

    public JLabel getLabel() {
        return label;
    }

    public JTextArea getTxtArea() {
        return txtArea;
    }

    public JScrollPane getSc() {
        return sc;
    }
    
    //FIXME
    public void populateSalesTextArea(){
        String name = customerBox.getSelectedItem().toString();
        ArrayList<Invoice> pop = (ArrayList<Invoice>) controller.getInvoices(name);
        for(Invoice a : pop){
            txtArea.append(a + "\n");
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        
        if(e.getStateChange()==ItemEvent.SELECTED){
            System.out.println("ibsvc");
            txtArea.append("Koe");
           // populateSalesTextArea();
        }
    }
    
}
