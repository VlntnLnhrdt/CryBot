package etc;

public class DataStamp {

    public String getSYMBOL() {
        return SYMBOL;
    }

    public double getOHLC() {
        return OHLC;
    }

    public String getTIMESTAMP() { return TIMESTAMP; }

    private final String SYMBOL;    // Pair-Name
    private final String TIMESTAMP; // Zeitstempel
    private final double OHLC;      // Open,High,Low,Close-Durchschnitt

    public DataStamp(String timestamp, String symbol, double ohlc) {
        this.SYMBOL    = symbol;
        this.TIMESTAMP = timestamp;
        this.OHLC      = ohlc;
    }

    public String print() {
        return getTIMESTAMP()+","+getSYMBOL()+","+getOHLC();
    }
}
