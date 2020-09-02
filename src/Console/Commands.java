package Console;

import java.util.ArrayList;
import java.util.List;

public class Commands {


    static List<String> allCommands;

    Commands(){

        allCommands = new ArrayList<>();

        addCommands();


    }

    void addCommands(){

        allCommands.add("exit"); // Schließt das Programm
        allCommands.add("capital"); // zeigt das Kapital in € an  (Token werden umgerechnet)
        allCommands.add("gpd"); // growth per day
        allCommands.add("gpw"); // growth per week
        allCommands.add("gpm"); // growth per month
        allCommands.add("gpy"); // growth per year
        allCommands.add("trendHoppingShortTerm");// Führt die Strategie aus
        allCommands.add("run"); // Führt die Strategie aus
        allCommands.add("help");// Zeigt alle Commands an


    }

}
