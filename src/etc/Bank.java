package etc;

import io.Input;
import io.Output;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bank {

    private static double TOKEN = 0;
    private static double CAPITAL = Properties.CAPITAL;

    // NumberOfTrades
    private static int NOT = 1;
    private static String TRADE;

    private static double FEELOST      = 0;
    private static double OVERALLSALES = 0;

    private static double BUYPRICE = 0;

    private static Date DATEWEEK;
    private static SimpleDateFormat SDFWEEK = new SimpleDateFormat("yyyy-ww");

    // Zurücksetzen aller Daten (Testzwecke)
    public static void reset() {
        TOKEN   = 0;
        CAPITAL = Properties.CAPITAL;
        NOT     = 1;
        FEELOST = 0;
    }

    public static void buy(DataStamp value) {
        if (TOKEN == 0 && CAPITAL > 0 && value.getSPECIALVALUE() - value.getSPECIALVALUE() > -100) {//die differenz zwischen breechnetem- und richtigen-Preis darf maximal 100€ betragen

            FEELOST += CAPITAL * Properties.FEE; // Verlust durch Gebühren

            OVERALLSALES += CAPITAL; // Umsatz

            // Ausrechnen von Token / Kapital
            TOKEN = (CAPITAL * Properties.CALCFEE) / value.getSPECIALVALUE();
            CAPITAL = 0;

            BUYPRICE = value.getSPECIALVALUE();

            TRADE = (NOT++) + " - BUY at "+ value.getTIMESTAMP() +" for " + cutDouble(value.getSPECIALVALUE()) + "€";

        }
    }

    public static void sell(DataStamp value) {
        if (CAPITAL == 0 && TOKEN > 0 && value.getSPECIALVALUE() - value.getSPECIALVALUE() < 100) {
            FEELOST += CAPITAL * Properties.FEE;

            OVERALLSALES += TOKEN * value.getSPECIALVALUE();

            CAPITAL = (TOKEN * value.getSPECIALVALUE()) * Properties.CALCFEE;

            TRADE += " - SELL at " + value.getTIMESTAMP() + " for " + cutDouble(value.getSPECIALVALUE()) + "€ with a result of " + cutDouble(value.getSPECIALVALUE() - BUYPRICE) + "€ equals " + cutDouble((value.getSPECIALVALUE() - BUYPRICE) * TOKEN) + "€";

            // Live-Writing der Trade-History Datei

            DATEWEEK = new Date();
            List<String> LASTTRADES = Input.getContent(SDFWEEK.format(DATEWEEK) + Properties.TRADE_FILE_ENDING);

            if (LASTTRADES == null) {
                LASTTRADES = new ArrayList<>();
                LASTTRADES.add(Properties.TRADE_FILE_HEAD);
            }

            LASTTRADES.add(TRADE);

            Output.writeData(SDFWEEK.format(DATEWEEK) + Properties.TRADE_FILE_ENDING, LASTTRADES);

            //System.out.println(value.getOHLC() - value.getClose());

            TOKEN = 0;

            BUYPRICE = 0;
        }
    }

    // TODO Feelost und Overall Sales neu einbringen (Lösung finden)
    /*
    public static void saveTrades() {
        TRADES.add("Lost trough Fees: " +FEELOST+"€ and "+OVERALLSALES+"€ overall sales");
    }
    */

    public static void printResults(double price) {
        if (TOKEN != 0)
            System.out.println("Bank: (" + TOKEN * price + ")€ - " + TOKEN + " Token with " + NOT + " Trades");
        else
            System.out.println("Bank: " + CAPITAL + "€ - " + TOKEN + " Token with " + NOT + " Trades");
    }

    public static double getResults(double price) {
        if (CAPITAL==0 && TOKEN>0) {
            CAPITAL = TOKEN * price;
            TOKEN   = 0;
        }
        return CAPITAL;
    }


    public static int GET_TRADES_TOTAL(){
        return NOT * 2;
    }


    public static double getTOKEN() {
        return TOKEN;
    }

    public static double getCAPITAL() {
        return CAPITAL;
    }

    // Abschneiden von überflüssigen Kommastellen
    public static double cutDouble(double number){
        number = ((int)(number*100));
        return number /= 100;
    }

}
