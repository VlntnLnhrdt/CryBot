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
    private static int NOT = 0;

    private static List<String> TRADES = new ArrayList<>();
    private static String TRADE        = "Trading History";

    private static double FEELOST      = 0;
    private static double OVERALLSALES = 0;

    private static double BUYPRICE = 0;

    public static void reset() {
        TOKEN   = 0;
        CAPITAL = Properties.CAPITAL;
        NOT     = 0;
        TRADES  = new ArrayList<>();
        TRADE   = "Trading History";
        FEELOST = 0;
    }

    public static void buy(double price) {
        if (TOKEN == 0 && CAPITAL > 0) {
            FEELOST += CAPITAL * Properties.FEE;

            OVERALLSALES += CAPITAL;

            TOKEN = (CAPITAL * Properties.CALCFEE) / price;
            CAPITAL = 0;
            TRADES.add(TRADE);

            BUYPRICE = price;

            TRADE = (NOT++) + " - BUY at " + cutDouble(price) + "€ ";
        }
    }

    public static void sell(double price) {
        if (CAPITAL == 0 && TOKEN > 0) {
            FEELOST += CAPITAL * Properties.FEE;

            OVERALLSALES += TOKEN * price;

            CAPITAL = (TOKEN * price) * Properties.CALCFEE;

            TRADE += "SELL at " + cutDouble(price) + "€ with a result of " + cutDouble(price - BUYPRICE) + "€ equals " + cutDouble((price - BUYPRICE) * TOKEN) + "€";

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
