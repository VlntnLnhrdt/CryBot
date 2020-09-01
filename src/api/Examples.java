package api;

import etc.Properties;

import java.util.Timer;
import java.util.TimerTask;

public class Examples {

    // TODO Dieser Bereich muss in die Main integriert werden

    // Steht hier, damit man den Abfragerythmus von überall abstellen kann (Examples.requestTimer.cancel())
    public static Timer requestTimer;

    public static void main(String[] args) {

        API.setKeys();
        API.setPair(API.pair.BTC);
        API.setOHLCTimeframe(1); // Eine Minute, für die Testphase 60
        API.getOHLC();

        TimerTask requestTask = new TimerTask() {
            @Override
            public void run() {
                API.getOHLC(); // Rückgabewert wird hier nicht verwendet
            }
        };

        requestTimer = new Timer();
        requestTimer.scheduleAtFixedRate(requestTask, Properties.REQUESTTIME,Properties.REQUESTTIME);
    }
}
