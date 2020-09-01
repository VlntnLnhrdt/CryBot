package api;

import etc.Properties;
import io.Input;
import io.Logger;
import io.Output;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class API {

    private static boolean isVirgin = true;

    private static Date DATETODAY;
    private static SimpleDateFormat SDFTODAY = new SimpleDateFormat("yyyy-MM-dd");
    private static int NOR = 0; // NumberOfResponses

    private static KrakenApi api;
    private static String response;
    private static Map<String, String> input;

    // Setzen von Private / Public Key + Initialisierung
    public static void setKeys() {
        api = new KrakenApi();
        api.setKey(Properties.PUBLIC_KEY);
        api.setSecret(Properties.PRIVATE_KEY);
        input = new HashMap<>();
    }

    // Setzen des Pairs
    public static void setPair(pair pairname) {
        input.put("pair", pairname.pairname);
    }

    // Zeitrahmen vom OHLC in Minuten
    public static void setOHLCTimeframe(int timeframe) {
        input.put("interval", String.valueOf(timeframe));
    }

    public static double getOHLC() {
        Logger.logStart("Sending an OHLC-Request");

        // Setzt heutiges Datum
        DATETODAY = new Date();
        sendRequest(KrakenApi.Method.OHLC);

        // prüft ob es die erste Abfrage ist, zur ERsterfassung der LastID
        if (isVirgin) {
            isVirgin = false;

            // Setzen der LastID + Neuabfrage
            input.put("since", String.valueOf(ContentFilter.getLastId(response)));

            sendRequest(KrakenApi.Method.OHLC);
        }

        // +100 sichert ab, dass keine zwei OHLC-Responses zurückgegeben werden (setzt die LastID/letzte Abrufzeit um 100ms nach vorne)
        input.put("since", String.valueOf(ContentFilter.getLastId(response)+100));

        // Auslesen der heute gesammelten Daten
        List<String> LASTRESPONSES = Input.getContent(SDFTODAY.format(DATETODAY) + Properties.REPONSE_FILE_ENDING, false);

        // Falls es noch keine Datei gibt (neuer Tag) wird eine neue Liste / Datei erstellt
        if (LASTRESPONSES == null) {
            LASTRESPONSES = new ArrayList<>();
            LASTRESPONSES.add(Properties.RESPONSE_FILE_HEAD);
            NOR = 0;
        }

        LASTRESPONSES.add(++NOR + " - " +ContentFilter.getOHLC(response) + "€ - " + response);

        // (Über-)Schreiben der Datei
        Output.writeData(SDFTODAY.format(DATETODAY) + Properties.REPONSE_FILE_ENDING, LASTRESPONSES, false);

        Logger.logEnd();

        return ContentFilter.getOHLC(response);
    }


    // Sendet die Abfrage
    private static void sendRequest(KrakenApi.Method method) {
        try {
            response = api.queryPublic(method, input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Pairs für Kraken
    public enum pair {

        BTC("XBTEUR"),
        XRP("XRPEUR");

        String pairname;

        pair (String pairname) {
            this.pairname = pairname;
        }
    }

}
