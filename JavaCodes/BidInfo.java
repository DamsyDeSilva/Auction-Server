import java.util.Date;

public class BidInfo {
    private String clientName;
    private String symbol;
    private Date date;
    private Float price;

    // class constructor
    BidInfo(String clientName, String symbol, Date date, Float price) {
        this.clientName = clientName;
        this.symbol = symbol;
        this.date = date;
        this.price = price;
    }

   // methods for get clientName, symbol, date and price
    public String getClientName() {
        return clientName;
    }

    public String getSymbol() {
        return symbol;
    }

    public Date getDate() {
        return date;
    }

    public Float getPrice() {
        return price;
    }
}
