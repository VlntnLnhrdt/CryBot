package io;

import etc.DataStamp;
import etc.Properties;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Input {

    // Auslesen von bereits vorliegenden Daten
    public static List<DataStamp> getData(String filename) {

        Logger.logStart("Reading "+filename);

        List<DataStamp> list = new ArrayList<>();
        String line;

        try {
            BufferedReader rd = new BufferedReader(new FileReader(Properties.DATAPATH + filename));

            rd.readLine();
            rd.readLine();

            while ((line = rd.readLine()) != null) {

                String[] splits = line.split(",");

                list.add(0 ,new DataStamp(splits[0], splits[1], ((Double.parseDouble(splits[2]) + Double.parseDouble(splits[3]) + Double.parseDouble(splits[4]) + Double.parseDouble(splits[5])) / 4)));
            }

            rd.close();

        } catch (FileNotFoundException e) {
            System.err.println("File not found! Correct the filename and retry.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Logger.logEnd();

        Output.writeDataStamps(filename+"_generated.csv",list);

        return list;
    }

    // Auslesen von Daten jeglicher Art mit Logger
    public static List<String> getContent(String filename) {
        return getContent(filename, true);
    }


    // Auslesen von Daten jeglicher Art mit optionalem Logger
    public static List<String> getContent(String filename, boolean log) {

        if (log)
            Logger.logStart("Reading "+ filename);

        List<String> list = new ArrayList<>();
        String line;

        try {
            BufferedReader rd = new BufferedReader(new FileReader(Properties.DATAPATH + filename));

            while ((line = rd.readLine()) != null)
                list.add(line);

            rd.close();

        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (log)
            Logger.logEnd();

        return list;
    }

}
