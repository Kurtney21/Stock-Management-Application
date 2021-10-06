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
import za.ac.cput.stock.management.common.Categories;

public class AddProductGUI extends JFrame implements ActionListener{
    private JPanel main;
    private JLabel logoLbl;
    private ImageIcon img;
    private JTextField nameTxt, quantityTxt, priceTxt;
    private JScrollPane sc;
    private JComboBox catBox;
    private JButton addBtn;
    
    
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
        logoLbl.setIcon(img);             //
    }

    public void initButtons(){
        addBtn = new JButton("Add");
    }
    
    public void initImageIcon(){
        img = new ImageIcon("resources/user.png");
    }
    
    private void initComboBox(){
        catBox = new JComboBox(new Categories().getCategories().toArray());
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
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getName() {
        return nameTxt.getText();
    }
    
    public String getQuantity() {
        return quantityTxt.getText();
    }

    public String getPrice() {
        return priceTxt.getText();
    }
    
}