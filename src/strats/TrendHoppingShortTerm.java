package strats;

import etc.Bank;
import etc.DataStamp;
import etc.Properties;
import etc.Statistics;
import io.Logger;

import java.util.ArrayList;
import java.util.List;

public class TrendHoppingShortTerm {

    private static List<DataStamp> LIST;
    private static int LASTVALUE;

    public static void importList(List<DataStamp> list) {
        LIST = list;
    }

    public static void setLastValue(int value){
        LASTVALUE = value;
    }

    public static void run() {
        Logger.logStart("Starting TrendHoppingShortTerm with "+LIST.size()+" DataStamps and a value of "+LASTVALUE);

        // INFO Wenns fällt und bsp -1 ist, muss der Wert zweimal steigen um den "LastValues" Wert von 1 zu erreichen
        int libra = 0;

        for(int i=0; i < LIST.size() - 1; i++) {
            if (LIST.get(i).getOHLC() < LIST.get(i + 1).getOHLC())
                libra++;
            else if (LIST.get(i).getOHLC() > LIST.get(i + 1).getOHLC())
                libra--;




            if (libra >= LASTVALUE) {
                Bank.buy(LIST.get(i + 1).getOHLC());
                libra--;
            }
            if (libra <= -LASTVALUE) {
                Bank.sell(LIST.get(i + 1).getOHLC());
                libra++;
            }


            // Ein mal Täglich wird das Kapital zwischengespeichert
            if(i % 24 == 0){
                if(Bank.CAPITAL_HISTORY.size() < 1){
                    Statistics.gatherStats(Properties.CAPITAL);
                }else{
                    Statistics.gatherStats(Bank.CAPITAL_HISTORY.get(Bank.CAPITAL_HISTORY.size()-1));
                }

            }


        }

        Logger.logEnd();
    }





}
