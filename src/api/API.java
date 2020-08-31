package api;

import etc.Properties;
import io.Logger;
import io.Output;

import java.io.IOException;
import java.util.*;

public class API {

    private static boolean isVirgin = true;

    private static KrakenApi api;
    private static String response;
    private static Map<String, String> input;

    public static void setKeys() {
        api = new KrakenApi();
        api.setKey(Properties.PUBLIC_KEY);
        api.setSecret(Properties.PRIVATE_KEY);
        input = new HashMap<>();
    }

    // Pairname of the currency
    public static void setPair(pair pairname) {
        input.put("pair", pairname.pairname);
    }

    // Timeframe in minutes
    public static void setOHLCTimeframe(int timeframe) {
        input.put("interval", String.valueOf(timeframe));
    }

    private static List<String> reponses = new ArrayList<>();

    public static double getOHLC() {
        Logger.logStart("Sending an OHLC-Request");

        sendRequest(KrakenApi.Method.OHLC);

        if (isVirgin) {
            isVirgin = false;

            input.put("since", String.valueOf(ContentFilter.getLastId(response)));

            sendRequest(KrakenApi.Method.OHLC);
        }


        input.put("since", String.valueOf(ContentFilter.getLastId(response)+100));

        reponses.add(ContentFilter.getOHLC(response) + "€ - " + response);

        if (reponses.size()>60) {
            Output.writeData("Responses 60sek.csv",reponses);
            Examples.requestTimer.cancel();
        }

        Logger.logEnd();

        return ContentFilter.getOHLC(response);
    }


    private static void sendRequest(KrakenApi.Method method) {
        try {
            response = api.queryPublic(method, input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public enum pair {

        BTC("XBTEUR"),
        XRP("XRPEUR");

        String pairname;

        pair (String pairname) {
            this.pairname = pairname;
        }
    }

}
