package com.company;

import com.company.RecurrentJobs.ExchangesFundConversion;
import com.company.RecurrentJobs.WorkersTableCleaning;
import com.company.Server.HttpRequestHandling;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Timer;

import static com.company.Variables.FUND_CONVERSION_RATE;
import static com.company.Variables.GRAPHIC_CARD_TABLE_DATA_TIMEOUT;

// TODO: learn about Spring and add to the app
// TODO: add dependencies to maven
// TODO: set heroku
public class Main {

    private final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        int portValue = 8000;
        if(args.length != 0) {
            String portOption = args[0];
            if(portOption != null && portOption.equals("--port")) {
                portValue = Integer.parseInt(args[1]);
            } else {
                logger.warn("No port given in the command line parameters, using default of " + portValue);
            }
        } else {
            logger.warn("No parameter given in the command line parameters, using default with port " + portValue);
        }

        ScheduleRecurrentJobs();
        HttpRequestHandling.startServer(portValue);
    }

    private static void ScheduleRecurrentJobs() {
        WorkersTableCleaning workersTableCleaning = new WorkersTableCleaning();
        Timer timer1 = new Timer(true);
        timer1.scheduleAtFixedRate(workersTableCleaning,0 , GRAPHIC_CARD_TABLE_DATA_TIMEOUT / 2);

        ExchangesFundConversion exchangesFundConversion = new ExchangesFundConversion();
        Timer timer2 = new Timer(true);
        timer2.scheduleAtFixedRate(exchangesFundConversion,0 , FUND_CONVERSION_RATE);
    }
}
