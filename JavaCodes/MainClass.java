import java.io.IOException;

public class MainClass {

    public static final int BASE_PORT = 2000;
    private static final String csvFile = "stocks.csv";

    public static void main(String[] args) throws IOException {

        StockItemsDB stocks = new StockItemsDB(csvFile);
        BidLogs bidLogs = new BidLogs();
        ClientList clients = new ClientList();
        
        Server server = new Server(BASE_PORT);
        ServerGUI view = new ServerGUI(); 
        view.gui.setVisible(true);

        try {
            server.serverLoop();
        } catch (IOException ex) {
        }

    }
}