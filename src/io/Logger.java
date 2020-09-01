package io;

import java.util.Date;

public class Logger {

    // TODO Schreiben der Logs in eine Datei

    // Beschreibung:
    // Zuerst muss logStart aufgerufen werden, sobald der Task fertig ist, muss logEnd aufgerufen werden
    // Mehrere logStart hintereinander ohne logEnd führen zu Anzeigefehlern

    private static Date TIMESTAMP;

    public static void logStart(String text) {
        System.out.print("Status: "+text+" ...");
        TIMESTAMP = new Date();
    }

    public static void logEnd() {
        long ms = new Date().getTime() - TIMESTAMP.getTime();
        System.out.println(" 100% ("+ms+"ms)");
    }

}
