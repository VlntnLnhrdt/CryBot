package run;

import etc.Bank;
import etc.DataStamp;
import etc.Statistics;
import io.Input;
import io.Logger;
import strats.TrendHoppingShortTerm;


import java.util.List;

public class Main {

    List<DataStamp> list;

    public static void main(String[] args) {

        Main main = new Main();
        main.list =  Input.getData("Kraken_BTCEUR_1h.csv");

        main.runTrendHoppingShortTerm_Strategy();

        Statistics.SHOW_GROWTH_PER_DAY();

    }

    void runTrendHoppingShortTerm_Strategy(){

        TrendHoppingShortTerm.importList(list);
        TrendHoppingShortTerm.setLastValue(1);
        TrendHoppingShortTerm.initStrategy();

        Logger.logStart("Starting TrendHoppingShortTerm with a value of "+TrendHoppingShortTerm.getLastValue());

        // TODO Api hier aktiv

        for (DataStamp dataStamp : list){
            TrendHoppingShortTerm.run(dataStamp);
        }

        Logger.logEnd();
        Bank.printResults(list.get(list.size()-1).getOHLC());
        Bank.saveTrades();

    }






}
