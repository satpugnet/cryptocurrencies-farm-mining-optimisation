package com.company.OptimalMining.OptimalMiningConfigCalculation;

import com.company.OptimalMining.ClientConfig.ClientConfiguration;
import com.company.OptimalMining.ClientDashBoard.ClientDashBoardConfiguration;
import com.company.OptimalMining.MiningConfig.MiningConfiguration;
import com.company.crypto_currencies.CurrenciesShortName;
import com.company.crypto_currencies.currencies_retrieval.CurrencyInformationRetriever;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OptimalMiningConfigCalculation {
    //TODO
    public MiningConfiguration calculateOptimalConfig(ClientConfiguration clientConfiguration, ClientDashBoardConfiguration clientDashBoardConfiguration, CurrencyInformationRetriever currencyInformationRetriever) {
        MiningConfiguration miningConfig = new MiningConfiguration();

        List<CurrenciesShortName> currencies = currencyInformationRetriever.getOrderedListRecommendedMining();
        List<CurrenciesShortName> minedCurrencies = filterOutCurrenciesMined(currencies, clientDashBoardConfiguration.getMinedCryptocurrencies());
        miningConfig.setCurrenciesToMine(minedCurrencies);
        miningConfig.setActivateMining(clientDashBoardConfiguration.isActivateMining());

        return miningConfig;
    }

    private List<CurrenciesShortName> filterOutCurrenciesMined(List<CurrenciesShortName> minedCurrencies, Map<CurrenciesShortName, Boolean> allowedMinedCurrenciesDashBoard) {
        Set<CurrenciesShortName> allowedCurrencies = allowedMinedCurrenciesDashBoard.entrySet().stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        return minedCurrencies.stream()
                .filter(allowedCurrencies::contains)
                .collect(Collectors.toList());
    }
}

