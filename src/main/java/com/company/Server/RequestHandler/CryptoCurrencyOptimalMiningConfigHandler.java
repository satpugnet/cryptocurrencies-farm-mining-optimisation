package com.company.Server.RequestHandler;

import com.company.OptimalMining.ClientDashBoard.ClientDashBoardConfiguration;
import com.company.OptimalMining.MiningConfig.MiningConfiguration;
import com.company.OptimalMining.OptimalMiningConfigCalculation.OptimalMiningConfigCalculation;
import com.company.Server.JsonFormat.ConfigRequest.ConfigRequestProperties;
import com.company.crypto_currencies.currencies_retrieval.CurrencyInformationRetriever;
import com.company.crypto_currencies.currencies_retrieval.WhatToMineCurrencyInformationRetriever;

public class CryptoCurrencyOptimalMiningConfigHandler implements Handler {

    // TODO: implement this
    @Override
    public MiningConfiguration handle(ConfigRequestProperties clientConfig) {
        // TODO: add dashboard interface (electricity cost and consumption)
        ClientDashBoardConfiguration clientDashBoardConfiguration = new ClientDashBoardConfiguration(clientConfig.getUserEmail(), clientConfig.getWorkerName());
        OptimalMiningConfigCalculation optimalMiningConfigCalculation = new OptimalMiningConfigCalculation();
        CurrencyInformationRetriever coinWarzCurrencyInformationRetriever = new WhatToMineCurrencyInformationRetriever();

        return optimalMiningConfigCalculation.calculateOptimalConfig(clientConfig, clientDashBoardConfiguration, coinWarzCurrencyInformationRetriever);
    }
}
