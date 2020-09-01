package Console;

import etc.Bank;
import etc.Statistics;
import run.Main;


public class CommandExecuter {

    static void exec(String command, Main main){


        if(command.equals("exit")){
           System.out.println("exiting...");
           System.exit(123456789);
        }else if(command.equals("capital")){

            if(Bank.getCAPITAL() == 0){
                System.out.println( Bank.getTOKEN()+"BTC = " + (Bank.getTOKEN()*main.list.get(main.list.size()-1).getOHLC()) + "€");
            }else{
                System.out.println(Bank.getCAPITAL() + "€");
            }

        }else if(command.equals("gpd")){

            Statistics.SHOW_GROWTH_PER_DAY();

        }else if(command.equals("gpw")){

            Statistics.SHOW_GROWTH_PER_WEEK();

        }else if(command.equals("gpm")){

            Statistics.SHOW_GROWTH_PER_MONTH();

        }else if(command.equals("gpy")){

            Statistics.SHOW_GROWTH_PER_YEAR();

        }else if(command.equals("trendHoppingShortTerm")){

            main.runTrendHoppingShortTerm_Strategy();

        }else if(command.equals("help")){
            System.out.println("Printing all commands...");

            for(String s : Commands.allCommands){
                System.out.println(s);
            }

        }


    }


}
