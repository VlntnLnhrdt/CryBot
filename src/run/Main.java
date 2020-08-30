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
    List<Double> results;

    public static void main(String[] args) {

        Main main = new Main();
        main.list =  Input.getData("Kraken_BTCEUR_1h.csv");

        main.runTrendHoppingShortTerm_Strategy();

        Statistics.SHOW_GROWTH_PER_DAY();


        /*for (int i=0;i<50;i++) {
            TrendHoppingShortTerm.setLastValue(i);
            TrendHoppingShortTerm.run();
            Bank.printResults(list.get(list.size() - 1).getOHLC());
            results.add(Bank.getResults(list.get(list.size()-1).getOHLC()));
            Bank.reset();
        }

        for (int i=0;i<results.size();i++) {                9960.5
            System.out.println(i+" - "+results.get(i));
        }*/

    }

    void runTrendHoppingShortTerm_Strategy(){

        TrendHoppingShortTerm.importList(list);
        TrendHoppingShortTerm.setLastValue(1);
        TrendHoppingShortTerm.initStrategy();

        for (DataStamp dataStamp : list){
            TrendHoppingShortTerm.run(dataStamp.getOHLC());
        }

        Logger.logEnd();
        Bank.printResults(list.get(list.size()-1).getOHLC());
        Bank.saveTrades();

    }






}
