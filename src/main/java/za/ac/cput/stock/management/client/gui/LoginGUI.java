/**
 * @author Curstin Jade Rose (220275408)
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year ADP 262s
 */

package za.ac.cput.stock.management.client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import za.ac.cput.stock.management.controller.Controller;

public class LoginGUI extends JFrame implements ActionListener{
    //variables
    private JPanel northPnl,centerPnl,logoPnl,userLoginPnl;
    private JLabel logoLbl, userNameLbl, passwordLbl, exitLbl, minimizeLbl;
    private JButton loginBtn,minBtn,extBtn;
    private JTextField userNameTxt;
    private JPasswordField passwordFld;
    private ImageIcon logoIcn,userIcon,passwordIcn, loginIcn;
    private Font ft;
    private Controller controller = new Controller();
    
    public LoginGUI(){
        initImageIcon();
        setUndecorated(true);
       
        //Initilize Components
        initPanels();
        
        initLabels();
        initButtons();
        initLoginDetails();
        
        //setup components
        setFormating();
        setComponents();
        setLayouts();
        setFrameSettings();
        setListenerEvents();
    }
    
    public void initPanels(){
        northPnl = new JPanel();
        centerPnl = new JPanel();
        logoPnl = new JPanel();
        userLoginPnl = new JPanel();
    }

    public void initLabels(){
        logoLbl = new JLabel(""); 
        userNameLbl = new JLabel();  
        userNameLbl.setBorder(new EmptyBorder(3,3,3,3));
        passwordLbl = new JLabel();  
        passwordLbl.setBorder(new EmptyBorder(3,3,3,3));
    }

    public void initButtons(){
        loginBtn = new JButton("Login",loginIcn);
        loginBtn.setIcon(new ImageIcon("resources/login.png"));
        loginBtn.setPreferredSize(new Dimension(90,30));
        extBtn = new JButton("X");
        extBtn.setPreferredSize(new Dimension(20,20));
        minBtn = new JButton("-");
        minBtn.setPreferredSize(new Dimension(20,20));
    }
    
    public void initLoginDetails(){
        userNameTxt = new JTextField("Username",20);
        passwordFld = new JPasswordField("Password",20);
    }
    
    public void initImageIcon(){
            logoIcn = new ImageIcon("resources/logoLogin.png");
            userIcon = new ImageIcon("resources/user.png");
            passwordIcn = new ImageIcon("resources/password.png");
            loginIcn = new ImageIcon("resources/slogo.png");
    }
    
    public void setFormating(){
        northPnl.setBackground(new Color(68, 71, 90));
        centerPnl.setBackground(new Color(68, 71, 90));
        userLoginPnl.setBackground(new Color(68, 71, 90));
        logoPnl.setBackground(new Color(68, 71, 90));
        userNameTxt.setBackground(new Color(68, 71, 90));
        passwordFld.setBackground(new Color(68, 71, 90));
    }
    
    public void setLayouts(){
          //getContentPane().setLayout(new BorderLayout());
         northPnl.setLayout(new FlowLayout(FlowLayout.RIGHT));
          //centerPnl.setLayout(new BoxLayout(centerPnl, BoxLayout.Y_AXIS));
//        logoPnl.setLayout(new FlowLayout(1));
        userLoginPnl.setLayout(new GridLayout(2,2));
    }

    public void setComponents(){
        //adding Panels to JFrame
        this.add(northPnl, BorderLayout.NORTH);
        this.add(centerPnl, BorderLayout.CENTER);
        
        //adding components to panels
        northPnl.add(minBtn);
        northPnl.add(extBtn);
        
        centerPnl.add(Box.createRigidArea(new Dimension(0, 80)));
        centerPnl.add(logoPnl);
        logoPnl.add(logoLbl);
        logoLbl.setIcon(logoIcn);
        centerPnl.add(userLoginPnl);
        centerPnl.add(userLoginPnl);
        
        userLoginPnl.add(Box.createRigidArea(new Dimension(0, 30)));
        userLoginPnl.add(userNameTxt);
        userLoginPnl.add(userNameLbl);
        userNameLbl.setIcon(userIcon);
        userLoginPnl.add(Box.createRigidArea(new Dimension(0, 30)));
        userLoginPnl.add(passwordFld);
        userLoginPnl.add(passwordLbl);
        
        passwordLbl.setIcon(passwordIcn);
        
        centerPnl.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPnl.add(loginBtn);
    }
    
    public void setFrameSettings(){
        pack();
        setSize(new Dimension(600,350));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void setListenerEvents(){
        loginBtn.addActionListener(this);
        extBtn.addActionListener(this);
        minBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("X")){
            System.exit(0);
        }
        if(e.getActionCommand().equals("-")){
           setState(Frame.ICONIFIED);
        }else{
            this.setVisible(true);
        }
        if(e.getActionCommand().equals("Login")){
            //User Authentication
           controller.checkAuthentication(getUserName(), getPassword(), this);
        }
    }

    public String getUserName() {
        return userNameTxt.getText();
    }

    public String getPassword(){
        return new String(passwordFld.getPassword());
    }
}
