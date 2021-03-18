import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.*;

public class StockItemsDB {
    
    protected static Map <String, Item> stockItemDetails;
    private String csvFile;
    private String [] headers;

    public StockItemsDB(String csvFile){
        stockItemDetails = new HashMap <String, Item>();
        this.csvFile = csvFile;
        readCSV();
    }

    // Method for read the csv file
    public void readCSV() {
 
        BufferedReader br = null;
        String line = "";
        try {
            
            br = new BufferedReader(new FileReader(csvFile));
            headers = br.readLine().split(","); // save names of headers
            while ((line = br.readLine()) != null) {

                String [] itemInfo = line.split(",");     // split the line by ","
                float price = (float) new Random().nextInt(100); // random price
                Item stockItem = new Item(itemInfo[0],itemInfo[1], price, Integer.parseInt(itemInfo[itemInfo.length-1])); // create an object of Item
                stockItemDetails.put(itemInfo[0], stockItem); // add the created object and its symbol to hashmap
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    // update price of an item corresponding to a symbol
    public static void setPrice(String symbol,float price){
        Item item = stockItemDetails.get(symbol);
        item.setPrice(price);
        stockItemDetails.put(symbol, item);
    }

    // get price of an item corresponding to a symbol
    public static float getPrice(String symbol){
        Item item = stockItemDetails.get(symbol);
        return item.getPrice();
    }

    // get lot size of an item corresponding to a symbol
    public static int getLotSize(String symbol){
        Item item = stockItemDetails.get(symbol);
        return item.getLotSize();
    }
    
    // get the item object , corresponding to a signal 
    public static Item getStockItem(String symbol){
        return stockItemDetails.get(symbol);
    }
    
    // check the existence of a symbol
    public static boolean isExist(String symbol){
        return stockItemDetails.containsKey(symbol);
    }

    // return all data in the hashmap as a 2d array , to insert in JTable
    public static Object[][] getMapValues(){

        Map<String, Item> treeMap = new TreeMap<>(stockItemDetails); // to sort the hashmap
        Object data[][]  = new Object[treeMap.size()][5];
        int i = 0;
        for (String key : treeMap.keySet()) {

            Item value = treeMap.get(key);
            data[i][0] = i;
            data[i][1] = value.getSymbol();
            data[i][2] = value.getSecurityName();
            data[i][3] = value.getPrice();
            data[i][4] = value.getLotSize();
            i++;
        }
        return data;
    }

    
}
