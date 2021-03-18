
import java.util.ArrayList;

public class BidLogs {
     
    protected static ArrayList<BidInfo> BidList;
    
    public BidLogs() {
        BidList =new ArrayList<BidInfo>(); 
    }
    
    // add bids to the BidList
    public static void addBID(BidInfo newBid){
        BidList.add(newBid);
        ServerGUI.addToBidTable(newBid);
        ServerGUI.addToDisplay(newBid);
    }

    // return the bidinfo based on index
    public static BidInfo getBidInfo(int index){
        return BidList.get(index);
    }

}