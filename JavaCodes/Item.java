
public class Item{

    private String symbol;
    private String securityName;
    private float price;
    private int lotSize;

    // class constructor
    Item(String symbol, String securityName, float price, int lotSize){
        this.symbol = symbol;
        this.securityName = securityName;
        this.price = price;
        this.lotSize = lotSize;
    }

    // set new price
    public void setPrice(float newPrice){
        this.price = newPrice;
    }

    // get the symbol
    public String getSymbol() {
        return symbol;
    }
    // get the securrity Name
    public String getSecurityName() {
        return securityName;
    }
    // get the price
    public float getPrice() {
        return price;
    }
    // get the lot size
    public int getLotSize() {
        return lotSize;
    }

}