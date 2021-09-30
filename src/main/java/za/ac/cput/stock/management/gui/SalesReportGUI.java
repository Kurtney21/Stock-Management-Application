/**
 * @author Curstin Jade Rose (220275408)
 * @author Kurtney Clyde Jantjies (218138105)
 * @group: Second Year ADP 262s
 */

package za.ac.cput.stock.management.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class SalesReportGUI extends JFrame implements ActionListener {
    //Variables
    private Font ft, ftTitle,ftMdm, ftSml;
    private JPanel westPnl, northPnl, centerPnl,salesReportTablePnl,salesReportButtonsPnl, salesReportPnl;
    private JLabel logoLbl, authorLbl;
    private ImageIcon logoIcon;
    private JButton extBtn, minBtn, salesReportBtn, exportBtn, backBtn;
    private JComboBox  categorieBox;
    private JTable productTable;
    private JScrollPane productSc;
    private Border  raisedbevel;
    private int x, y = 0;
    private Point currentLocation;
    
    public SalesReportGUI(){
        setUndecorated(true);
        setFrameSettings();
        initPanels();
        initLabels();
        initButtons();
        initComboBox();
        initImageIcon();
        setProductTable();
        setLayouts();
        setComponents();
        setListenerEvents();
    }
    
    public void setFormatting(){
        ft = new Font("Arial", Font.ITALIC |  Font.BOLD,16 );
        ftTitle = new Font("Roboto",  Font.BOLD,26 );
        ftMdm = new Font("Roboto",   Font.BOLD,12 ); 
        ftSml = new Font("Roboto", Font.PLAIN,8 );
        raisedbevel = BorderFactory.createRaisedBevelBorder();
    }
    
    //Initialize Components
    public void initPanels(){
        northPnl = new JPanel();
        centerPnl = new JPanel();
        westPnl = new JPanel();
        salesReportTablePnl = new JPanel();
        salesReportButtonsPnl = new JPanel();
        salesReportPnl = new JPanel();
    }

    public void initLabels(){
        logoLbl = new JLabel(""); 
        authorLbl = new JLabel("Developed by @ Kurtney & Curstin");  
    }

    public void initButtons(){
        extBtn = new JButton("X");
        minBtn = new JButton("-");
        salesReportBtn = new JButton("Sales Report");
        exportBtn = new JButton("Export to cvv.");
        backBtn =  new JButton("Back");
    }
    
    public void initComboBox(){
        String[] cats = {"-no selection made-","Paper","Filing","Printing","Pens","Paint"};
        categorieBox = new JComboBox(cats);
    }
    
    public void initImageIcon(){
        logoIcon = new ImageIcon("resources/logoLogin.png");
        logoLbl.setIcon(logoIcon);
    }
    
    public void setProductTable(){
        productSc = new JScrollPane();
        productTable = new JTable();
        productTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "ID", "NAME", "QUANTITY", "PRICE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        productSc.setViewportView(productTable);
        productTable.setAutoCreateRowSorter(true);
        productTable.setBackground(new Color(0, 158, 193));
        productTable.setBorder(new javax.swing.border.MatteBorder(null));
        productTable.setForeground(new Color(255, 255, 255));
        productSc.setPreferredSize(new Dimension(400,300));
    }
    
    //setting up components
    public void setLayouts(){
        this.setLayout(new BorderLayout());
        northPnl.setLayout(new FlowLayout(FlowLayout.RIGHT));
        westPnl.setLayout(new BoxLayout(westPnl, BoxLayout.Y_AXIS));
        centerPnl.setLayout(new CardLayout());
        salesReportPnl.setLayout(new FlowLayout(FlowLayout.LEFT));
        salesReportTablePnl.setLayout(new FlowLayout(FlowLayout.LEFT));
        salesReportButtonsPnl.setLayout(new BoxLayout(salesReportButtonsPnl,BoxLayout.Y_AXIS));
    }
    
    public void setComponents(){
        //adding main panels to frame
        this.add(northPnl, BorderLayout.NORTH);
        this.add(centerPnl, BorderLayout.CENTER);
        this.add(westPnl, BorderLayout.WEST);
        
        //adding sub-panels to main panels
        centerPnl.add(salesReportPnl);
        salesReportPnl.add(salesReportTablePnl);
        salesReportPnl.add(salesReportButtonsPnl);
        
        //North
        northPnl.add(minBtn);
        northPnl.add(extBtn);
        
        //west
        westPnl.add(logoLbl);
        //logoLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        westPnl.add(Box.createRigidArea(new Dimension(10, 10)));
        westPnl.add(salesReportBtn);
        salesReportBtn.setMaximumSize(new Dimension(180,60));
        westPnl.add(Box.createRigidArea(new Dimension(10, 150)));
        westPnl.add(authorLbl);
        
        //center Table
        salesReportTablePnl.add(productSc);
        
        //center Buttons
        salesReportButtonsPnl.add(Box.createRigidArea(new Dimension(100, 10)));
        salesReportButtonsPnl.add(categorieBox);
        categorieBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        salesReportButtonsPnl.add(Box.createRigidArea(new Dimension(0, 30)));
        salesReportButtonsPnl.add(exportBtn);
        salesReportButtonsPnl.add(Box.createRigidArea(new Dimension(0, 130)));
        salesReportButtonsPnl.add(backBtn);
        
    }
    
    public void setListenerEvents(){
        extBtn.addActionListener(this);
        minBtn.addActionListener(this);
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
        this.setSize(new Dimension(800,400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    }

}
