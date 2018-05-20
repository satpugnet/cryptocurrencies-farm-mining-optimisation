package com.company.Server.RequestHandler;

import com.company.OptimalMining.ClientDashBoard.ClientDashBoardConfiguration;
import com.company.OptimalMining.OptimalMiningConfigCalculation.OptimalMiningConfigCalculation;
import com.company.Server.JsonFormat.ClientJson.MiningConfigurationRequest;
import com.company.Server.JsonFormat.ServerJson.MiningConfigurationResponse;
import com.company.crypto_currencies.currencies_retrieval.CurrencyInformationRetriever;
import com.company.crypto_currencies.currencies_retrieval.WhatToMineCurrencyInformationRetriever;

public class CryptoCurrencyOptimalMiningConfigHandler implements Handler {

    // TODO: implement this
    @Override
    public MiningConfigurationResponse handle(MiningConfigurationRequest miningConfigurationRequest) {
        // TODO: add dashboard interface (electricity cost and consumption)
        ClientDashBoardConfiguration clientDashBoardConfiguration = new ClientDashBoardConfiguration(miningConfigurationRequest.getUserEmail(),
                miningConfigurationRequest.getWorkerName(), miningConfigurationRequest.getData().getClientConfiguration().getGpus());
        OptimalMiningConfigCalculation optimalMiningConfigCalculation = new OptimalMiningConfigCalculation();
        CurrencyInformationRetriever coinWarzCurrencyInformationRetriever = new WhatToMineCurrencyInformationRetriever();

        return optimalMiningConfigCalculation.calculateOptimalConfig(miningConfigurationRequest, clientDashBoardConfiguration, coinWarzCurrencyInformationRetriever);
    }
}
