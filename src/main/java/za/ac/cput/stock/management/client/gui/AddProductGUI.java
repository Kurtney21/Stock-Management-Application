/**
 * @author Curstin Jade Rose (220275408)
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year ADP 262s
 */

package za.ac.cput.stock.management.client.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import za.ac.cput.stock.management.controller.Controller;

public class AddProductGUI extends JFrame implements ActionListener{
    private JPanel main;
    private JLabel logoLbl;
    private ImageIcon img;
    private JTextField nameTxt, quantityTxt, priceTxt;
    private JScrollPane sc;
    private JComboBox catBox, vendorBox;
    private JButton addBtn;
    private Controller controller= new Controller();
    
    
    public AddProductGUI(){
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
        nameTxt = new JTextField("Enter Product Name",15);
        quantityTxt  = new JTextField("Enter Product Quantity",15);
        priceTxt = new JTextField("Enter Product Price",15);
    }

    public void initLabels(){
        logoLbl = new JLabel("");
        logoLbl.setIcon(img);
    }

    public void initButtons(){
        addBtn = new JButton("Add");
        addBtn.addActionListener(this);
    }
    
    public void initImageIcon(){
        img = new ImageIcon("resources/user.png");
    }
    
    public void initComboBox(){
        catBox = new JComboBox(controller.getCategories());
        vendorBox = new JComboBox(controller.getVendors());
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
       quantityTxt.setAlignmentX(Component.CENTER_ALIGNMENT);
       main.add(quantityTxt);
       main.add(Box.createRigidArea(new Dimension(0,10)));
       priceTxt.setAlignmentX(Component.CENTER_ALIGNMENT);
       main.add(priceTxt);
       main.add(Box.createRigidArea(new Dimension(0,10)));
       vendorBox.setAlignmentX(Component.CENTER_ALIGNMENT);
       main.add(vendorBox);
       main.add(Box.createRigidArea(new Dimension(0,10)));
       catBox.setAlignmentX(Component.CENTER_ALIGNMENT);
       main.add(catBox);
       main.add(Box.createRigidArea(new Dimension(0,10)));
       addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
       main.add(addBtn);
       main.add(Box.createRigidArea(new Dimension(0,10)));
    }
    
    public void setListenerEvents(){
        
    }
    
    public void setFrameSettings(){
        this.setSize(400,300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource().equals(addBtn))
        {
            if (!isEmpty()) return;
            
            controller.addProduct(
                    getName(), 
                    getQuantity(),
                    getPrice(),
                    getVendor(), 
                    getCategory());
            
            clearFields();
        }
    }
    
    public void clearFields()
    {
        nameTxt.setText("");
        quantityTxt.setText("");
        priceTxt.setText("");
    }
    
    public boolean isEmpty()
    {
        boolean isEmpty = true;
        
        if (getName().trim().equals("") || getName().equals("Enter Product Name"))
        {
            JOptionPane.showMessageDialog(null, "Product Name can't be empty.");
            isEmpty = false;
        }
        else if (quantityTxt.getText().trim().equals("") || 
                quantityTxt.getText().equals("Enter Product Quantity"))
        {
            JOptionPane.showMessageDialog(null, "Quantity can't be empty.");
            isEmpty = false;
        }
        else if (priceTxt.getText().trim().equals("") || 
                priceTxt.getText().equals("Enter Product Price"))
        {
            JOptionPane.showMessageDialog(null, "Price can't be empty.");
            isEmpty = false;
        }
        else if (getPrice() == 0)
        {
            JOptionPane.showMessageDialog(null, "Price can't be 0.");
            isEmpty = false;
        }
        else if (getQuantity() < 0)
        {
            JOptionPane.showMessageDialog(null, "Please enter a valid quantity "
                    + "amount.");
            isEmpty = false;
        }
        
        return isEmpty;
    }    

    public String getName() {
        return nameTxt.getText();
    }
    
    public int getQuantity() 
    {
        return Integer.parseInt(quantityTxt.getText());
    }

    public double getPrice() 
    {
        return Double.parseDouble(priceTxt.getText());
    }
    
    public String getVendor()
    {
        return vendorBox.getSelectedItem().toString();
    }
    
    public String getCategory()
    {
        return catBox.getSelectedItem().toString();
    }
}