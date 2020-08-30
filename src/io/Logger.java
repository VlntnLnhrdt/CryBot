package io;

public class Logger {

    public static void logStart(String text) {
        System.out.print("Status: "+text+" ...");
    }

    public static void logEnd() {
        System.out.println(" 100%");
    }

}
