package com.company.RecurrentJobs;

import com.company.CryptoExchange.Bittrex.BittrexApiInterface;
import com.company.CryptoExchange.Exchange;
import org.apache.log4j.Logger;

import java.util.TimerTask;

public class ExchangesFundConversion extends TimerTask {

    final static Logger logger = Logger.getLogger(ExchangesFundConversion.class);

    @Override
    public void run() {
        logger.info("Starting converting funds on exchange");
        Exchange bittrex = new BittrexApiInterface();
        bittrex.convertFundToBitcoin();
        logger.info("Finished converting funds on exchange");

    }
}
