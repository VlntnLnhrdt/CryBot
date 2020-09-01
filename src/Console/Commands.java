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

        allCommands.add("exit");
        allCommands.add("capital");
        allCommands.add("gpd");
        allCommands.add("gpw");
        allCommands.add("gpm");
        allCommands.add("gpy");
        allCommands.add("trendHoppingShortTerm");
        allCommands.add("help");


    }

}
