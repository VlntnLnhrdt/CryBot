package etc;

public class DataStamp {

    public String getSYMBOL() {
        return SYMBOL;
    }

    public double getOHLC() {
        return OHLC;
    }

    private final String SYMBOL;
    private final double OHLC;

    public DataStamp(String symbol, double ohlc) {
        this.SYMBOL = symbol;
        this.OHLC = ohlc;
    }

    public String print() {
        return getSYMBOL()+","+getOHLC();
    }
}
