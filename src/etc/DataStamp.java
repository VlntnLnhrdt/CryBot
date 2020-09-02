package etc;

public class DataStamp {

    public String getSYMBOL() {
        return SYMBOL;
    }

    public double getOHLC() {
        return OHLC;
    }

    public double getSPECIALVALUE() {
        return SPECIAL;
    }

    public String getTIMESTAMP() { return TIMESTAMP; }

    private final String SYMBOL;    // Pair-Name
    private final String TIMESTAMP; // Zeitstempel
    private final double OHLC;      // Open,High,Low,Close-Durchschnitt
    private final double SPECIAL;     // Close-Wert

    public DataStamp(String timestamp, String symbol, double ohlc, double special) {
        this.SYMBOL    = symbol;
        this.TIMESTAMP = timestamp;
        this.OHLC      = ohlc;
        this.SPECIAL    = special;
    }

    public String print() {
        return getTIMESTAMP()+","+getSYMBOL()+","+ getSPECIALVALUE();
    }
}
