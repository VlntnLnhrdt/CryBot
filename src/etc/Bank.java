package etc;

import io.Output;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    // TODO Gebührenintegration
    // TODO Gebührenintegration mit Gesamtverlust

    private static double TOKEN = 0;
    private static double CAPITAL = Properties.CAPITAL;

    // NumberOfTrades
    private static int NOT = 1;

    private static List<String> TRADES = new ArrayList<>();
    private static String TRADE        = "Trading History";

    private static double FEELOST      = 0;
    private static double OVERALLSALES = 0;

    private static double BUYPRICE = 0;

    public static void reset() {
        TOKEN   = 0;
        CAPITAL = Properties.CAPITAL;
        NOT     = 1;
        TRADES  = new ArrayList<>();
        TRADE   = "Trading History\n" +
                "TradeNo - Timestamp - Info";
        FEELOST = 0;
    }

    public static void buy(DataStamp value) {
        if (TOKEN == 0 && CAPITAL > 0) {
            FEELOST += CAPITAL * Properties.FEE;

            OVERALLSALES += CAPITAL;

            TOKEN = (CAPITAL * Properties.CALCFEE) / value.getOHLC();
            CAPITAL = 0;
            TRADES.add(TRADE);

            BUYPRICE = value.getOHLC();

            TRADE = (NOT++) + " - "+ value.getTIMESTAMP() +" - BUY at " + cutDouble(value.getOHLC()) + "€ ";
        }
    }

    public static void sell(DataStamp value) {
        if (CAPITAL == 0 && TOKEN > 0) {
            FEELOST += CAPITAL * Properties.FEE;

            OVERALLSALES += TOKEN * value.getOHLC();

            CAPITAL = (TOKEN * value.getOHLC()) * Properties.CALCFEE;

            TRADE += "SELL at " + cutDouble(value.getOHLC()) + "€ with a result of " + (cutDouble(value.getOHLC()) - BUYPRICE) + "€ equals " + cutDouble((value.getOHLC() - BUYPRICE) * TOKEN) + "€";

            TOKEN = 0;

            BUYPRICE = 0;
        }
    }

    public static void saveTrades() {
        TRADES.add("Lost trough Fees: " +FEELOST+"€ and "+OVERALLSALES+"€ overall sales");
        Output.writeData("Trade-History", TRADES);
    }

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
