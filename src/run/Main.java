package run;

import Console.Controller;
import etc.Bank;
import etc.DataStamp;
import etc.Statistics;
import io.Input;
import io.Logger;
import strats.TrendHoppingShortTerm;


import java.util.List;

public class Main {

    public List<DataStamp> list;

    Controller con;
    public static Thread consThread;


    public static void main(String[] args) {

        Main main = new Main();
        main.list =  Input.getData("Kraken_BTCEUR_1h.csv");

        main.con = new Controller(main);


        main.runConsole();

    }

    void runConsole(){

        System.out.println("Console running... ready for input");

        consThread = new Thread(() -> con.update());

        consThread.start();

    }


    public void runTrendHoppingShortTerm_Strategy(){

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

    }






}
