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
import za.ac.cput.stock.management.common.Fonts;
import za.ac.cput.stock.management.common.Invoice;
import za.ac.cput.stock.management.controller.Controller;

public class InvoicePanel extends JFrame implements  ItemListener{

    private JPanel mainPane, centerPnl, eastPnl, westPnl;
    private  JComboBox customerBox;
    private JLabel label, totalLbl;
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
        customerBox = new JComboBox(controller.getCustomerNamesString());
        
        sc = new JScrollPane();
        txtArea = new JTextArea();
        sc.add(txtArea);
        txtArea.setEditable(false);
        sc.setPreferredSize(new Dimension(600,300));
        totalLbl = new JLabel("Total Sales: ");
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
        totalLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        totalLbl.setMaximumSize(new Dimension(200,30));
        westPnl.add(totalLbl);
        westPnl.add(Box.createRigidArea(new Dimension(0,150)));
        
        txtArea.setForeground(new Color(255,255,255));
        centerPnl.add(txtArea);
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
        String name = String.valueOf(customerBox.getSelectedItem());
        ArrayList<Invoice> pop = (ArrayList<Invoice>) controller.getInvoices(name);
        double total = 0;
        for(int i = 0; i < pop.size(); i++){
            total += pop.get(i).getTotal();
        }
        double price = Math.round(total*100.00)/100.00;
        totalLbl.setText("Sum of Transactions: R "+price);
            
        txtArea.setText("");
        txtArea.append(name+"\n");
        String heading = String.format("%-30s%-25s\t%-20s\t%-20s\n",
                "Transaction_ID","Product","Quantity","Total");
        txtArea.append(heading);
        for(Invoice a : pop){
            txtArea.append(a + "\n");
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        
        if(e.getStateChange()==ItemEvent.SELECTED){
            populateSalesTextArea();
        }
    }
    
}
