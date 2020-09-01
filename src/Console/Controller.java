package Console;

import run.Main;
import java.util.Scanner;
import java.util.Timer;



public class Controller {

    Main main;

    Scanner sc;
    Timer timer;
    Commands commands;


    public Controller(Main main){

        this.main = main;
        sc  = new Scanner(System.in);
        timer = new Timer();
        commands = new Commands();



    }

    public void update(){

       while (true) {
           run();
       }

    }


    public void run(){

        checkConsole();

    }

    void checkConsole(){

        String consoleContent = sc.next();

        for(String s : Commands.allCommands){

            if(s.contains(consoleContent)){

                System.out.println("Command detected: "+s+"\n");

                CommandExecuter.exec(s, main);

                System.out.println();

            }

        }

    }



}
