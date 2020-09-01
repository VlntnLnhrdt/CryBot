package etc;

public class DataStamp {

    public String getSYMBOL() {
        return SYMBOL;
    }

    public double getOHLC() {
        return OHLC;
    }
    public double getClose() {
        return close;
    }

    public String getTIMESTAMP() { return TIMESTAMP; }

    private final String SYMBOL;    // Pair-Name
    private final String TIMESTAMP; // Zeitstempel
    private final double OHLC;      // Open,High,Low,Close-Durchschnitt
    private final double close;

    public DataStamp(String timestamp, String symbol, double ohlc, double close) {
        this.SYMBOL    = symbol;
        this.TIMESTAMP = timestamp;
        this.OHLC      = ohlc;
        this.close = close;
    }

    public String print() {
        return getTIMESTAMP()+","+getSYMBOL()+","+getOHLC();
    }
}
