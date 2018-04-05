package OptimalMining.OptimalMiningConfigCalculation;

importgi OptimalMining.ClientConfig.ClientConfiguration;
import OptimalMining.ClientDashBoard.ClientDashBoardConfiguration;
import OptimalMining.MiningConfig.MiningConfiguration;
import crypto_currencies.CurrenciesShortName;
import crypto_currencies.currencies_retrieval.CurrencyInformationRetriever;

import java.util.List;

public class OptimalMiningConfigCalculation {
    //TODO
    public MiningConfiguration calculateOptimalConfig(ClientConfiguration clientConfiguration, ClientDashBoardConfiguration clientDashBoardConfiguration, CurrencyInformationRetriever currencyInformationRetriever) {
        MiningConfiguration miningConfig = new MiningConfiguration();

        List<CurrenciesShortName> currenciesShortName = currencyInformationRetriever.getOrderedListRecommendedMining();
        miningConfig.setCurrenciesToMine(currenciesShortName);
        miningConfig.setActivateMining(clientDashBoardConfiguration.isActivateMining());

        return miningConfig;
    }
}

