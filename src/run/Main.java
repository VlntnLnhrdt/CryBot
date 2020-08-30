package run;

import etc.Bank;
import etc.DataStamp;
import etc.Statistics;
import io.Input;
import strats.TrendHoppingShortTerm;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<DataStamp> list =  Input.getData("Kraken_BTCEUR_1h.csv");
        List<Double> results = new ArrayList<>();

        TrendHoppingShortTerm.importList(list);

        TrendHoppingShortTerm.setLastValue(1);
        TrendHoppingShortTerm.run();
        Bank.printResults(list.get(list.size()-1).getOHLC());
        Bank.saveTrades();


        Statistics.SHOW_GROWTH_PER_DAY();

        /*for (int i=0;i<50;i++) {
            TrendHoppingShortTerm.setLastValue(i);
            TrendHoppingShortTerm.run();
            Bank.printResults(list.get(list.size() - 1).getOHLC());
            results.add(Bank.getResults(list.get(list.size()-1).getOHLC()));
            Bank.reset();
        }

        for (int i=0;i<results.size();i++) {
            System.out.println(i+" - "+results.get(i));
        }*/


    }


}
