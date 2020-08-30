package strats;

import etc.Bank;
import etc.DataStamp;
import etc.Statistics;

import java.util.List;

public class TrendHoppingShortTerm {

    private static List<DataStamp> LIST;
    private static int LASTVALUE;

    private static double oldValue;
    private static int libra;

    public static void importList(List<DataStamp> list) {
        LIST = list;
    }

    public static void setLastValue(int value){
        LASTVALUE = value;
    }

    public static int getLastValue() {
        return LASTVALUE;
    }

    public static void initStrategy(){
        oldValue = 0;
        libra = 0;
    }

    public static void run(DataStamp newValue) {

       if(oldValue != 0) {

            // INFO Wenns fällt und bsp -1 ist, muss der Wert zweimal steigen um den "LastValues" Wert von 1 zu erreichen

            if (oldValue < newValue.getOHLC())
                libra++;
            else if (oldValue > newValue.getOHLC())
                libra--;


            if (libra >= LASTVALUE) {
                Bank.buy(newValue);
                libra--;
            }
            if (libra <= -LASTVALUE) {
                Bank.sell(newValue);
                libra++;
            }
        }

        Statistics.gatherStats(newValue.getOHLC());

        oldValue = newValue.getOHLC();
    }
}
