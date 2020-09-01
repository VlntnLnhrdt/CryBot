package io;

import etc.DataStamp;
import etc.Properties;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Output {

    // Schreiben von DataStamps (vereinfachte Datenansicht)
    public static void writeDataStamps(String name, List<DataStamp> list) {

        List<String> newList = new ArrayList<>();

        for (DataStamp line : list)
            newList.add(line.print());

        writeData(name, newList);

    }

    // Schreiben von Daten jeglicher Art mit Logger
    public static void writeData(String name, List<String> list) {
        writeData(name, list, true);
    }

    // Schreiben von Daten jeglicher Art mit optinalem Logger
    public static void writeData(String name, List<String> list, boolean log) {

        if (log)
            Logger.logStart("Writing data into file");

        try {
            BufferedWriter wr = new BufferedWriter(new FileWriter(Properties.DATAPATH + name));

            for (String line : list) {
                wr.write(line);
                wr.newLine();
            }
            wr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (log)
            Logger.logEnd();
    }

}
