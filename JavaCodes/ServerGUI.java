import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerGUI extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected JFrame gui;
    private JButton allItems;
    private JButton changePrice ;
    private JButton exit ;
    private JPanel centerPanel;
    private JPanel details;
    private JPanel tablePanel;
    private JPanel southPanel; 
    protected static JTable bidTable;
    private JScrollPane scrollPane; 
    private JScrollPane scrollPane2;
    
    private JPanel headingPanel;
    private JLabel headingLabel1;
    private JLabel headingLabel2;

    private JPanel subheadingPanel1;
    private JPanel subheadingPanel2;

    private JLabel subheadingLabel1;
    private JLabel subheadingLabel2;

    
    private JPanel bidTablePanel;
    private JPanel clientPanel;
    private static JTextArea clientInfo;

    private static ItemGUIComp fb;
    private static ItemGUIComp vrtu;
    private static ItemGUIComp msft;
    private static ItemGUIComp googl;
    private static ItemGUIComp yhoo;
    private static ItemGUIComp xlnx;
    private static ItemGUIComp tsla;
    private static ItemGUIComp txn;



    // class constructor
    ServerGUI() {

        this.allItems = new JButton("View All Items");
        this.changePrice = new JButton ("Change Price");
        this.exit = new JButton ("EXIT");
        this.gui = new JFrame("Auction server");

        // add action listnerfor view all button
        this.allItems.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                AllItemsDisplay view = new AllItemsDisplay();
                view.frame.setVisible(true);

            }

        });
        this.exit.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                gui.dispose();
                System.exit(0);
            }
            
        });

        initializeComp();
    }

    private void initializeComp() {

        // JFrame Properties
        // gui.setSize(new Dimension(1300, 750));
        // gui.setResizable(false);
        gui.setLocationRelativeTo(null);
        gui.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.headingPanel = new JPanel();
        headingPanel.setLayout(new GridLayout(2,1));
        headingPanel.setBackground(new Color(0,0,0));
        gui.add(headingPanel, BorderLayout.NORTH);

        this.headingLabel1 = new JLabel();
        this.headingLabel2 = new JLabel();
        headingLabel1.setFont(new Font("Tahoma", 1, 36)); 
        headingLabel2.setFont(new Font("Tahoma", 1, 36)); 
        headingLabel1.setForeground(new Color(50, 145, 168));
        headingLabel2.setForeground(new Color(50, 145, 168));
        headingLabel1.setText("Stock Exchange");
        headingLabel2.setText("Auction Server");
        headingLabel1.setHorizontalAlignment(JLabel.CENTER);
        headingLabel2.setHorizontalAlignment(JLabel.CENTER);
        headingPanel.add(headingLabel1);
        headingPanel.add(headingLabel2);

			
		// add JPanels and add components on them
        this.centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1,2));
        gui.add(centerPanel, BorderLayout.CENTER);
        
        this.details = new JPanel();
        this.tablePanel = new JPanel();  
        details.setBackground(new Color(0, 0, 0));
        centerPanel.add(details);
        centerPanel.add(tablePanel);

        this.bidTablePanel = new JPanel();
        this.clientPanel = new JPanel();
         
        this.subheadingPanel1 = new JPanel();
        this.subheadingPanel2 = new JPanel();
        
        clientPanel.setBackground(new Color(255, 255, 255));
        tablePanel.setBackground(new Color(0,0,0));
        tablePanel.setLayout(null);
        
        this.subheadingLabel1 = new JLabel("Bid History");
        this.subheadingLabel2 = new JLabel("Client Connection History");
        subheadingLabel1.setFont(new Font("Tahoma", 1, 20)); 
        subheadingLabel2.setFont(new Font("Tahoma", 1, 20)); 
        subheadingLabel1.setForeground(new Color(204, 164, 43));
        subheadingLabel2.setForeground(new Color(204, 164, 43));


        subheadingPanel1.add(subheadingLabel1);
        subheadingPanel2.add(subheadingLabel2);

        subheadingPanel1.setBounds(0, 0, 925, 50);
        tablePanel.add(subheadingPanel1);
        
        bidTablePanel.setBounds(0,50,925,400);
        tablePanel.add(bidTablePanel);
        
        subheadingPanel2.setBounds(0, 450, 925, 50);
        tablePanel.add(subheadingPanel2);
        
        clientPanel.setBounds(0,500,925,400);
        clientPanel.setBackground(new Color (141,182,205));
        tablePanel.add(clientPanel);
    
  
        // Initializing the bid history table
        bidTable = new JTable(new DefaultTableModel(new Object [][]{}, new String [] {"Client Name", "Symbol", "Date", "Time", "Price"} ));    
        this.scrollPane =  new JScrollPane(bidTable);
	
	// set bidTabel properties
        bidTable.setFillsViewportHeight(true);
        bidTable.setBackground(new Color(92, 82, 81));
        bidTable.setForeground(new Color(0,0,0));
        bidTablePanel.setBackground(new Color(0,0,0));
        bidTablePanel.setLayout(new BorderLayout());
        bidTablePanel.add(scrollPane, BorderLayout.CENTER);
        
	// create text area for client history
        clientInfo = new JTextArea();
        clientInfo.setLayout(new BorderLayout());
        clientInfo.setEditable(false);
        clientInfo.setCaretPosition(clientInfo.getDocument().getLength());
        this.scrollPane2 = new JScrollPane(clientInfo);
        scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        clientPanel.add(clientInfo);

        details.setLayout(new GridLayout(4,2));
        // create 8 items
	
	// for FB
        fb = new ItemGUIComp("FB", "Face Book");
        JPanel panel1 = new JPanel ();
        panel1 = fb.createItem();
        fb.setPriceValue(StockItemsDB.getPrice("FB"));
        fb.setLotSizeValue(StockItemsDB.getLotSize("FB"));
        
	// for VRTU
        vrtu = new ItemGUIComp ("VRTU", "Virtusa Coporation");
        JPanel panel2 = new JPanel ();
        panel2 = vrtu.createItem();
        vrtu.setPriceValue(StockItemsDB.getPrice("VRTU"));
        vrtu.setLotSizeValue(StockItemsDB.getLotSize("VRTU"));
        
	// for MSFT
        msft = new ItemGUIComp ("MSFT", "Microsoft Coporation");
        JPanel panel3 = new JPanel ();
        panel3 = msft.createItem();
        msft.setPriceValue(StockItemsDB.getPrice("MSFT"));
        msft.setLotSizeValue(StockItemsDB.getLotSize("MSFT"));
        
	// for google
        googl = new ItemGUIComp ("GOOGL", "Google Inc.");
        JPanel panel4 = new JPanel ();
        panel4 = googl.createItem();
        googl.setPriceValue(StockItemsDB.getPrice("GOOGL"));
        googl.setLotSizeValue(StockItemsDB.getLotSize("GOOGL"));
        
	// for xlnx
        xlnx = new ItemGUIComp ("XLNX", "Xlinx");
        JPanel panel5 = new JPanel ();
        panel5 = xlnx.createItem();
        xlnx.setPriceValue(StockItemsDB.getPrice("XLNX"));
        xlnx.setLotSizeValue(StockItemsDB.getLotSize("XLNX"));
        
	// for tsla
        tsla = new ItemGUIComp ("TSLA", "Tesla Motors");
        JPanel panel6 = new JPanel ();
        panel6 = tsla.createItem();
        tsla.setPriceValue(StockItemsDB.getPrice("TSLA"));
        tsla.setLotSizeValue(StockItemsDB.getLotSize("TSLA"));
        
	//for txn
        txn = new ItemGUIComp ("TXN", "Texas Instruments Incorporate");
        JPanel panel7 = new JPanel ();
        panel7 = txn.createItem();
        txn.setPriceValue(StockItemsDB.getPrice("TXN"));
        txn.setLotSizeValue(StockItemsDB.getLotSize("TXN"));
        
	// for yahoo
        yhoo = new ItemGUIComp ("YHOO", "Yahoo! Inc.");
        JPanel panel8 = new JPanel();
        panel8 = yhoo.createItem();
        yhoo.setPriceValue(StockItemsDB.getPrice("YHOO"));
        yhoo.setLotSizeValue(StockItemsDB.getLotSize("YHOO"));
        
        // add items to the detail panel
        details.add(panel1);
        details.add(panel2);
        details.add(panel3);
        details.add(panel4);
        details.add(panel5);
        details.add(panel6);
        details.add(panel7);
        details.add(panel8);
        
	// Create JPanel and add buttons
	this.southPanel = new JPanel();
        gui.add(southPanel, BorderLayout.SOUTH);
        southPanel.setBackground(new Color(0,0,0));
        southPanel.add(allItems);
        southPanel.add(exit);
	
	// set button properties for view all items button
        allItems.setBackground(new Color(0, 153, 102));
        allItems.setFont(new Font("Tahoma", 1, 20)); 
        allItems.setForeground(new Color(204, 255, 204));     
 
	// set button properties for exit button       
        exit.setBackground(new Color(0, 153, 102));
        exit.setFont(new Font("Tahoma", 1, 20)); 
        exit.setForeground(new Color(204, 255, 204));       	    
    }

    // add new bid to bid table in gui
    public static void addToBidTable(BidInfo newBid){
        DefaultTableModel model = (DefaultTableModel) ServerGUI.bidTable.getModel();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat timeFormat = new SimpleDateFormat("HH-mm-ss");
        String date = dateFormat.format(newBid.getDate());
        String time = timeFormat.format(newBid.getDate());
        Object newRow []={newBid.getClientName(), newBid.getSymbol(), date, time, newBid.getPrice()};
        model.addRow(newRow);
    }

    // add new client to the screen
    public static void addToClientInfoDisplay(String clientName, boolean logStatus){

        //logstatus : true - connected, false - disconnected
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat timeFormat = new SimpleDateFormat("HH-mm-ss");
        String date = dateFormat.format(new Date());
        String time = timeFormat.format(new Date());
        
        if (logStatus){
            clientInfo.append(clientName + " connected at " + time + " on " + date + "\n ");
        }else{
            clientInfo.append(clientName + " disconnected at " + time + " on " + date + "\n");
        }
        
    }

    //update price in gui
    public static void addToDisplay(BidInfo newBid){
        String symbol = newBid.getSymbol();
        float price = newBid.getPrice();
        switch (symbol) {
            case "FB":
                fb.setPriceValue(price);
                break;
            case "VRTU":
                vrtu.setPriceValue(price);
                break;
            case "MSFT":
                msft.setPriceValue(price);
                break;
            case "GOOGL":
                googl.setPriceValue(price);
                break;
            case "YHOO":
                yhoo.setPriceValue(price);
                break;
            case "XLNX":
                xlnx.setPriceValue(price);
                break;
            case "TSLA":
                tsla.setPriceValue(price);
                break;
            case "TXN":
                txn.setPriceValue(price);
                break;
            default:
                break;
        }
    }
    
}
