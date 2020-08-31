package etc;

public class Properties {

    // Pfad zu den Chart-Daten
    public static String DATAPATH = "./data/";

    // Startkapital
    public static double CAPITAL = 20000;

    // Gebühren
    public static double FEE = 0.001;
    public static double CALCFEE = 1-FEE;

    // Kraken API-Keys
    public static String PUBLIC_KEY = "public key"; //FIXME
    public static String PRIVATE_KEY = "private key"; //FIXME

    // Abfragerythmus in ms
    public static int REQUESTTIME = 60000;

    // Benennung der Responses-Datei
    public static String REPONSE_FILE_ENDING = " Response-List.csv";

    // Head der Reponses-Datei
    public static String RESPONSE_FILE_HEAD = "Response No - OHLC - origial unfiltered reponse";

}
