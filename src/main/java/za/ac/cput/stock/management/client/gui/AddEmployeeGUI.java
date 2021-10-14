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
import za.ac.cput.stock.management.common.*;

public class AddEmployeeGUI extends JFrame implements ActionListener{
    private JPanel main;
    private JLabel logoLbl;
    private ImageIcon img;
    private JTextField nameTxt, surnameTxt, emailTxt;
    private JComboBox roleBox;
    private JButton addBtn;
    
    
    public AddEmployeeGUI(){
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
        nameTxt = new JTextField("Enter Employee Name",15);
        surnameTxt  = new JTextField("Enter Employee Surname",15);
        emailTxt = new JTextField("Enter Employee email",15);
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
       roleBox.setAlignmentX(Component.CENTER_ALIGNMENT);
       main.add(roleBox);
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
        
    }

    public String getName() {
        return nameTxt.getText();
    }
    
    public String getSurname() {
        return surnameTxt.getText();
    }

    public String getEmail() {
        return emailTxt.getText();
    }
    
}