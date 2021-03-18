import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Connection implements Runnable, ActionListener{

    private Socket connectedSocket;

    // Defining States of client inputs
    public static final int ENTER_NAME = 0;
    public static final int ENTER_SYMBOL = 1;
    public static final int ENTER_BID = 2;

    public String clientName;
    public String symbol = null;
    public float bidPrice;
    private int currentState;
    private Item item;
    private int bidLength = 0;    
    
    private BufferedReader in ;
    private PrintWriter out;

    
    private Timer timer;

    // class constructor
    Connection(Socket connectedSocket) {
        this.connectedSocket = connectedSocket;
        timer = new Timer(500, this);   
        timer.start(); 
    }

    // thread method
    @Override
    public void run() {
        handleConnection();
    }

    public void startThread() {
        Thread thread = new Thread(this);
        thread.start();
    }

    private void handleConnection() {
        try {
            in = new BufferedReader(new InputStreamReader(this.connectedSocket.getInputStream()));                                                                             // stream
            out = new PrintWriter(new OutputStreamWriter(this.connectedSocket.getOutputStream())); 

            out.print("----------------WELCOME TO STOCK EXCHANGE------------------\n"
                    + "---------------------AUCTION SERVER------------------------\n");
            out.println("Enter 'quit' to exit \n");
            out.println("Enter Your Name : ");
            out.flush();
            currentState = ENTER_NAME;
            String line;
            for (line = in.readLine(); line != null && !line.equalsIgnoreCase("quit"); line = in.readLine()) {
                // handling empty inputs
                if (line.equalsIgnoreCase("")) {
                    continue;
                }
                switch (currentState) {

                    // state : Client enters name
                    case ENTER_NAME:
                        clientName = line;
                        if (ClientList.isClientActive(clientName)){
                            out.println("This Client Name is currently active" );
                            out.println("Try another Name : ");
                            currentState = ENTER_NAME;    // stays in the same state 
                        }
                        else{
                            out.println("\nEnter the Symbol of the item you want to bid : ");
                            ClientList.addClient(clientName); // add to active client list 
                            ServerGUI.addToClientInfoDisplay(clientName, true);
                            currentState = ENTER_SYMBOL;    
                        }
                        break;
                    // state : Client enter symbol
                    case ENTER_SYMBOL:
                        symbol = line;
                        if (StockItemsDB.isExist(symbol)) {
                            bidLength = BidLogs.BidList.size(); // keep tracking total number of bids
                            item = StockItemsDB.getStockItem(symbol); // item object corresponding to symbol

                            out.println("\n" + clientName + ", The Current Price of Item " + symbol + " is " + item.getPrice());
                            out.println("Please Enter your Bid: ");

                            currentState = ENTER_BID; // next state

                        } else {

                            out.println("-1 , Invalid Symbol. Please Try again :");
                            out.flush();
                            currentState = ENTER_SYMBOL; // next state
                        }
                        break;

                    case ENTER_BID:
                        currentState = ENTER_BID;
                        bidLength = BidLogs.BidList.size(); // keep tracking total number of bids

                        // Check for valid Format of Bid amount
                        try {
                            bidPrice = Float.parseFloat(line);
                        } catch (NumberFormatException e) {
                            out.println("Invalid Format");
                            out.println("Enter valid bid: ");
                            out.flush();
                            break;
                        }
                        if (bidPrice > item.getPrice()) {

                            // update price of the item
                            StockItemsDB.setPrice(symbol, bidPrice); 
                            // add the bidInfo to Bidlogs
                            BidInfo bid = new BidInfo(clientName, symbol, new Date(), bidPrice);
                            BidLogs.addBID(bid);

                            out.println("You placed a bid of " + bidPrice + " on " + symbol + ".");
                            out.println("Current price is " + item.getPrice() + "\n");
                            out.println("Enter your new bid on " + symbol + ": ");

                        } else { 
                            // If not valid ask for new Bid higher than current price
                            out.println("Your Bid is not accepted. Bid higher than " + item.getPrice() + " on " + symbol + ".");
                            out.println("\nEnter Your new bid on " + symbol + ": ");
                        }
                        break;

                    default:
                        out.println("Undefined state");
                        return;
                }
                out.flush();
            }
            
            out.close();
            in.close();
            ClientList.removeClient(clientName);    // remove from active client list
            ServerGUI.addToClientInfoDisplay(clientName, false);
            this.connectedSocket.close();   // close the connection
            

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                this.connectedSocket.close();
            } catch (Exception e) {
            }
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        // BidInfo lastBid = BidLogs.getBidInfo(BidLogs.BidList.size() - 1); 
        int newBidLength = BidLogs.BidList.size();
        if (newBidLength > 0){
            for (int i = bidLength; i < newBidLength; i++){
                BidInfo bid = BidLogs.getBidInfo(i); 
                if (!bid.getClientName().equals(clientName) && bid.getSymbol().equals(symbol) && currentState == ENTER_BID){
                    bidLength = newBidLength;
                    out.println("\n " + bid.getClientName() + " placed a new bid on "+ symbol + ".\n Current Price is "+ bid.getPrice());
                    out.println("\n Place Your New Bid : ");
                    out.flush();
                }
            }
        } 
        
    }

   
}