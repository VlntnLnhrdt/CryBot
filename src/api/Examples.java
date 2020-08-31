package api;

import etc.Properties;

import java.util.Timer;
import java.util.TimerTask;

public class Examples {

    // Steht hier, damit man den Abfragerythmus von überall abstellen kann (Examples.requestTimer.cancel())
    public static Timer requestTimer;

    public static void main(String[] args) {

        API.setKeys();
        API.setPair(API.pair.BTC);
        API.setOHLCTimeframe(1);
        API.getOHLC();

        TimerTask requestTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println(API.getOHLC());
            }
        };

        requestTimer = new Timer();
        requestTimer.scheduleAtFixedRate(requestTask, Properties.REQUESTTIME,Properties.REQUESTTIME);
    }
}
