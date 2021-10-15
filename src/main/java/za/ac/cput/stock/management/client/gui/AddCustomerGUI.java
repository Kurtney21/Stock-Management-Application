/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package za.ac.cput.stock.management.client.gui;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import za.ac.cput.stock.management.common.Customer;
import za.ac.cput.stock.management.common.UserRole;
import za.ac.cput.stock.management.controller.Controller;

public class AddCustomerGUI extends JFrame implements ActionListener{
    private JPanel main;
    private JLabel logoLbl;
    private ImageIcon img;
    private JTextField nameTxt, surnameTxt, emailTxt;
    private JComboBox roleBox;
    private JButton addBtn;
    private Controller controller = new Controller();
    
    public AddCustomerGUI(){
    //initialize components
	initPanels(); 
        initImageIcon();
        initLabels();
        initButtons();
        initTextFields();
        initComboBox();
	//setup components
        setLayouts();
        setComponents();
        setListenerEvents();
        setFrameSettings();
    }
    
    public void initPanels(){
        main = new JPanel();
    }
    
    public void initTextFields(){
        nameTxt = new JTextField("Enter Customer Name",15);
        surnameTxt  = new JTextField("Enter Customer Surname",15);
        emailTxt = new JTextField("Enter Customer Email",15);
    }

    public void initLabels(){
        logoLbl = new JLabel("");
        logoLbl.setIcon(img);             //
    }

    public void initButtons(){
        addBtn = new JButton("Add");
    }
    
    public void initImageIcon(){
        img = new ImageIcon("resources/user.png");
    }
    
    private void initComboBox(){
        roleBox = new JComboBox(UserRole.values());
    }
    
    public void setLayouts(){
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
    
    public void setComponents(){
       this.add(main, BorderLayout.CENTER);
       this.add(new JPanel(), BorderLayout.EAST);
       this.add(new JPanel(), BorderLayout.WEST);
       
       main.add(Box.createRigidArea(new Dimension(0,10)));
       logoLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
       main.add(logoLbl);
       main.add(Box.createRigidArea(new Dimension(0,10)));
       nameTxt.setAlignmentX(Component.CENTER_ALIGNMENT);
       main.add(nameTxt);
       main.add(Box.createRigidArea(new Dimension(0,10)));
       surnameTxt.setAlignmentX(Component.CENTER_ALIGNMENT);
       main.add(surnameTxt);
       main.add(Box.createRigidArea(new Dimension(0,10)));
       emailTxt.setAlignmentX(Component.CENTER_ALIGNMENT);
       main.add(emailTxt);
       main.add(Box.createRigidArea(new Dimension(0,10)));
       addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
       main.add(addBtn);
       main.add(Box.createRigidArea(new Dimension(0,10)));
    }
    
    public void populateCustomerTable(JTable table)
    {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        
        //Read Products from DB method (getAllProducts)
        int customersLen = controller.getCustomers().size();
        if(customersLen == 0)
        {
            var record = controller.getCustomers();
            Object[] rowData = new Object[4];
            for(int i = 0; i < customersLen;i++){
                rowData[0] = record.get(i).getCustomerId();
                rowData[1] = record.get(i).getName();
                rowData[2] = record.get(i).getLastname();
                rowData[3] = record.get(i).getEmail();
                model.addRow(rowData);
            }
        }
        else
        {
            System.out.println("List is Empty");
        }
    }
    
    public void setListenerEvents(){
        addBtn.addActionListener(this);
    }
    
    public void setFrameSettings(){
        this.setSize(400,300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public String getName() {
        return this.nameTxt.getText();
    }
    
    public String getSurname() {
        return this.surnameTxt.getText();
    }

    public String getEmail() {
        return this.emailTxt.getText();
    }
    
    public static void main(String[] args) {
        new AddCustomerGUI().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Add")){
            String name = getName(); 
            String lastname = getSurname();
            String email = getEmail();
            
            controller.addCustomer(name, lastname, email);
            this.dispose();
        }
    }
}