import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
// import javax.swing.WindowConstants;

public class AllItemsDisplay extends JFrame{

    private static final long serialVersionUID = 1L;
    protected JFrame frame;
    private JTable table;  

  
    // Constructor 
    AllItemsDisplay() {
          
        this.frame = new JFrame();  // Frame initiallization 

        initialize();        
    } 
  
    private void initialize(){
        
        // Frame settings
        frame.setTitle("Stock Items"); 
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   
        // Data & column names to be displayed in the JTable   
        Object[][] data = StockItemsDB.getMapValues();
        String[] columnNames = {"Number", "Symbol", "Security Name ", "Price", "Lot Size" }; 
  
        // Initializing the JTable 
        table = new JTable(data, columnNames); 
        
        // adding it to JScrollPane 
        JScrollPane sp = new JScrollPane(table); 
        frame.add(sp); 

    }
     
}
