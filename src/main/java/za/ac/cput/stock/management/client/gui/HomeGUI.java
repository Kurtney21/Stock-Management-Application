/**
 * @author Curstin Jade Rose (220275408)
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year ADP 262s
 */
package za.ac.cput.stock.management.client.gui;

import  java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.sql.*;

public class HomeGUI extends JFrame implements ActionListener{

    private Font ft, ftTitle,ftMdm, ftSml;
    private JPanel westPnl, northPnl, centerPnl;
    private JLabel logoLbl, authorLbl, replaceLbl;
    private ImageIcon imgIcon;
    private JButton extBtn, minBtn, adminBtn, productLookupBtn, posBtn, reportBtn, addCustomerBtn;
    private int x, y = 0;
    private Point currentLocation;
    
    public HomeGUI(){
        setUndecorated(true);
        setFormatting();
        setFrameSettings();  
        initPanels();
        initLabels();
        initButtons();
        setLayouts();
        setComponents();
        setListenerEvents();
    }
    
    public void initPanels(){
        northPnl = new JPanel();
        centerPnl = new JPanel();
        westPnl = new JPanel();
    }

    public void initLabels(){
        imgIcon = new ImageIcon("resources/logoDesktop.png");
        logoLbl = new JLabel(); 
        logoLbl.setIcon(imgIcon);
        authorLbl = new JLabel("developed by @ Kurtney & Curstin");  
        authorLbl.setFont(ftSml);
        replaceLbl = new JLabel("Welcome to ADP \nSolutions $username!!");  
    }

    public void initButtons(){
        extBtn = new JButton("X");
        minBtn = new JButton("-");
        adminBtn = new JButton("Adminstration");
        productLookupBtn = new JButton("Inventory"); 
        posBtn = new JButton("Sales");
        reportBtn = new JButton("Sales Report"); 
        addCustomerBtn = new JButton("Add Customer");
    }
    
    public void setLayouts(){
        this.setLayout(new BorderLayout());
        northPnl.setLayout(new FlowLayout(FlowLayout.RIGHT));
        westPnl.setLayout(new BoxLayout(westPnl, BoxLayout.Y_AXIS));
        centerPnl.setLayout(new FlowLayout(1));
    }
    
    public void setComponents(){
        //adding Panels to JFrame
        this.add(northPnl, BorderLayout.NORTH);
        this.add(centerPnl, BorderLayout.CENTER);
        this.add(westPnl, BorderLayout.WEST);
        
        //adding components to panels
        northPnl.add(minBtn);
        northPnl.add(extBtn);
        
        westPnl.add(logoLbl);
        westPnl.add(Box.createRigidArea(new Dimension(10, 10)));
        
        posBtn.setMaximumSize(new Dimension(120,60));
        westPnl.add(posBtn);
        posBtn.setFont(ftMdm);
        reportBtn.setMaximumSize(new Dimension(120,60));
        reportBtn.setFont(ftMdm);
        westPnl.add(reportBtn);
        addCustomerBtn.setFont(ftMdm);
        addCustomerBtn.setMaximumSize(new Dimension(120,60));
        westPnl.add(addCustomerBtn);
        productLookupBtn.setFont(ftMdm);
        productLookupBtn.setMaximumSize(new Dimension(120,60));
        westPnl.add(productLookupBtn);
        adminBtn.setFont(ftMdm);
        adminBtn.setMaximumSize(new Dimension(120,60));
        westPnl.add(adminBtn);
        westPnl.add(Box.createRigidArea(new Dimension(10, 30)));
        westPnl.add(authorLbl);
        
        centerPnl.add(replaceLbl);
    }
    
    public void setFormatting(){
        ft = new Font("Arial", Font.ITALIC |  Font.BOLD,16 );
        ftTitle = new Font("Roboto",  Font.BOLD,26 );
        ftMdm = new Font("Roboto",   Font.BOLD,12 ); 
        ftSml = new Font("Roboto", Font.PLAIN,8 );
    }
    
    public void setListenerEvents(){
        extBtn.addActionListener(this);
        minBtn.addActionListener(this); 
        adminBtn.addActionListener(this); 
        productLookupBtn.addActionListener(this); 
        posBtn.addActionListener(this);
        reportBtn.addActionListener(this);
        addCustomerBtn.addActionListener(this);
        
        northPnl.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                currentLocation = e.getPoint();
             }
         });
        northPnl.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point currentScreenLocation = e.getLocationOnScreen();
                setLocation(currentScreenLocation.x - currentLocation.x, currentScreenLocation.y - currentLocation.y);
            }
        });
    }
    
    public void setFrameSettings(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,450);
        this.setLocationRelativeTo(null);
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
        if(e.getActionCommand().equals("Administration")){
        
        }
        if(e.getActionCommand().equals("Inventory")){
        
        }
        if(e.getActionCommand().equals("Add Customer")){
        
        }
        if(e.getActionCommand().equals("Sales")){
        
        }
        if(e.getActionCommand().equals("Sales Report")){
        
        }
        
    }

}
