package api;

public class ContentFilter {

    // Beispiel-Response zur Veranschaulichung
    // {"error":[],"result":{"XXBTZEUR":[[1598878980,"9850.1","9850.2","9850.0","9850.2","9850.0","2.33057715",19]],"last":1598878920}}

    // Filtert die LastID aus einem Response
    public static int getLastId(String response) {
        String [] splits = response.split("\"last\":");
        return Integer.parseInt(splits[1].substring(0,splits[1].length()-2));
    }

    // Filtert und berechnet den OHLC aus einem Response
    public static double getOHLC(String response) {
        response = response.substring(response.indexOf("[[")+2,response.indexOf("]]"));
        response = response.replace("\"","");

        String [] splits = response.split(",");

        // Zur Veranschaulichung nicht verkürzt
        double open  = Double.parseDouble(splits[1]);
        double high  = Double.parseDouble(splits[2]);
        double low   = Double.parseDouble(splits[3]);
        double close = Double.parseDouble(splits[4]);
        double ohlc = (open+high+low+close)/4;

        return Math.round(ohlc*100)/100;
    }

}
