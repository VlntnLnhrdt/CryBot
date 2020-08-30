package etc;

public class DataStamp {

    public String getSYMBOL() {
        return SYMBOL;
    }

    public double getOHLC() {
        return OHLC;
    }

    private final String SYMBOL;
    private final String TIMESTAMP;
    private final double OHLC;

    public DataStamp(String symbol, String timestamp, double ohlc) {
        this.SYMBOL    = symbol;
        this.TIMESTAMP = timestamp;
        this.OHLC      = ohlc;
    }

    public String print() {
        return getSYMBOL()+","+getOHLC();
    }
}
