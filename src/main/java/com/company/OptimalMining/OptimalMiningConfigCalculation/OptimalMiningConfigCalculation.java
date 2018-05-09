package com.company.OptimalMining.OptimalMiningConfigCalculation;

import com.company.Database.DatabaseAccessor;
import com.company.Database.GraphicCardLiveData;
import com.company.OptimalMining.ClientDashBoard.ClientDashBoardConfiguration;
import com.company.OptimalMining.MiningConfig.MiningConfiguration;
import com.company.Server.JsonFormat.ConfigRequest.ConfigRequestProperties;
import com.company.Server.JsonFormat.ConfigRequest.SystemConfig.GPU.GPU;
import com.company.Server.JsonFormat.ConfigRequest.SystemConfig.GPU.GraphicCard;
import com.company.crypto_currencies.CurrencyShortName;
import com.company.crypto_currencies.currencies_retrieval.CurrencyInformationRetriever;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.company.Server.JsonFormat.ConfigRequest.SystemConfig.GPU.GPUType.CUDA;

public class OptimalMiningConfigCalculation {

    private final static Logger logger = Logger.getLogger(OptimalMiningConfigCalculation.class);

    //TODO
    public MiningConfiguration calculateOptimalConfig(ConfigRequestProperties clientConfiguration, ClientDashBoardConfiguration clientDashBoardConfiguration,
                                                      CurrencyInformationRetriever currencyInformationRetriever) {
        MiningConfiguration miningConfig = new MiningConfiguration();

//        List<CurrencyShortName> currencies = currencyInformationRetriever.getOrderedListRecommendedMining();
        DatabaseAccessor db = new DatabaseAccessor(clientConfiguration.getUserEmail());

        // TODO: remove the hardcoded 1 value to get the gpus
        GraphicCard graphicCard = identifyGraphicCard(clientConfiguration.getData().getSystemConfig().getGpus().getGpus().get(1));
        List<GraphicCardLiveData> currencies = db.getOrderedListCurrencyToMine(graphicCard);
        List<CurrencyShortName> minedCurrencies = filterOutCurrenciesMined(currencies, clientDashBoardConfiguration.getMinedCryptocurrencies());

        miningConfig.setCurrenciesToMine(minedCurrencies);
        miningConfig.setActivateMining(clientDashBoardConfiguration.isActivateMining());

        return miningConfig;
    }

    private GraphicCard identifyGraphicCard(GPU gpu) {
        if(gpu.getGraphicCard() == null) {
            // TODO: put back the default card to worst cards
            if(gpu.getGpuType() == CUDA) {
                logger.warn("The graphic card could not be identified for " + gpu + " defaulting to GTX_1080_TI");
                return GraphicCard.GTX_1080_TI;
            } else {
                logger.warn("The graphic card could not be identified for " + gpu + " defaulting to AMD_VEGA_64");
                return GraphicCard.AMD_VEGA_64;
            }
        }
        return gpu.getGraphicCard();
    }

    private List<CurrencyShortName> filterOutCurrenciesMined(List<GraphicCardLiveData> minedCurrencies, Map<CurrencyShortName, Boolean> allowedMinedCurrenciesDashBoard) {
        Set<CurrencyShortName> allowedCurrencies = allowedMinedCurrenciesDashBoard.entrySet().stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        return minedCurrencies.stream()
                .filter(item -> item.getProfitPerSecond().compareTo(BigDecimal.ZERO) > 0)
                .filter(item -> allowedCurrencies.contains(item.getCurrencyShortName()))
                .map(GraphicCardLiveData::getCurrencyShortName)
                .collect(Collectors.toList());
    }
}

