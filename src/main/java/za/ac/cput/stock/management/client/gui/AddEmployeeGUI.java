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
import za.ac.cput.stock.management.controller.Controller;

public class AddEmployeeGUI extends JFrame implements ActionListener{
    private JPanel main;
    private JLabel logoLbl;
    private ImageIcon img;
    private JTextField usernameTxt, passwordTxt;
    private JComboBox roleBox;
    private JButton addBtn;
    private Controller controller = new Controller();
    
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
        usernameTxt = new JTextField("Enter Employee User Name",15);
        passwordTxt  = new JTextField("Enter Employee Password",15);
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
       usernameTxt.setAlignmentX(Component.CENTER_ALIGNMENT);
       main.add(usernameTxt);
       main.add(Box.createRigidArea(new Dimension(0,10)));
       passwordTxt.setAlignmentX(Component.CENTER_ALIGNMENT);
       main.add(passwordTxt);
       main.add(Box.createRigidArea(new Dimension(0,10)));
       roleBox.setAlignmentX(Component.CENTER_ALIGNMENT);
       main.add(roleBox);
       main.add(Box.createRigidArea(new Dimension(0,10)));
       addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
       main.add(addBtn);
       main.add(Box.createRigidArea(new Dimension(0,10)));
    }
    
    public void setListenerEvents(){
        addBtn.addActionListener(this);
    }
    
    public void setFrameSettings(){
        this.setSize(400,300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Add")){
           User user = new User(getUserName(),getPassword(), getRole(), true);
           controller.addEmployee(user);
           this.dispose();
        }
    }

    public String getUserName() {
        return usernameTxt.getText();
    }
    
    public String getPassword() {
        return passwordTxt.getText();
    }
    
    public UserRole getRole() {
      return (UserRole) roleBox.getSelectedItem();
    }
}