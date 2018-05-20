package com.company.OptimalMining.OptimalMiningConfigCalculation;

import com.company.Database.DatabaseAccessor;
import com.company.Database.GraphicCardLiveData;
import com.company.OptimalMining.ClientDashBoard.ClientDashBoardConfiguration;
import com.company.OptimalMining.ClientDashBoard.ClientDashBoardGpuConfiguration;
import com.company.Server.JsonFormat.General.GPU.GPU;
import com.company.Server.JsonFormat.ClientJson.MiningConfigurationRequest;
import com.company.Server.JsonFormat.General.GPU.GraphicCard;
import com.company.Server.JsonFormat.General.MinedCurrencyShortName;
import com.company.Server.JsonFormat.ServerJson.MiningConfiguration.GraphicCardMiningConfiguration.GraphicCardMiningConfiguration;
import com.company.Server.JsonFormat.ServerJson.MiningConfigurationResponse;
import com.company.crypto_currencies.currencies_retrieval.CurrencyInformationRetriever;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.company.Server.JsonFormat.General.GPU.GPUType.CUDA;
import static com.company.Server.JsonFormat.General.GPU.GPUType.OPEN_CL;

public class OptimalMiningConfigCalculation {

    private final static Logger logger = Logger.getLogger(OptimalMiningConfigCalculation.class);

    //TODO
    public MiningConfigurationResponse calculateOptimalConfig(MiningConfigurationRequest miningConfigurationRequest, ClientDashBoardConfiguration clientDashBoardConfiguration,
                                                              CurrencyInformationRetriever currencyInformationRetriever) {
        MiningConfigurationResponse miningConfig = new MiningConfigurationResponse();

//        List<MinedCurrencyShortName> currencies = currencyInformationRetriever.getOrderedListRecommendedMining();
        DatabaseAccessor db = new DatabaseAccessor(miningConfigurationRequest.getUserEmail());

        // TODO: remove the hardcoded 1 value to get the gpus
        List<GPU> gpus = miningConfigurationRequest.getData().getClientConfiguration().getGpus();
        List<GraphicCardMiningConfiguration> currenciesConfiguration = new LinkedList<>();
        for(GPU gpu: gpus) {
            GraphicCard currentGraphicCard = identifyGraphicCard(gpu);
            List<GraphicCardLiveData> currencies = new LinkedList<>();
            if(currentGraphicCard != null) {
                currencies = db.getOrderedListCurrencyToMine(currentGraphicCard);
            }
            ClientDashBoardGpuConfiguration clientDashBoardGpuConfiguration = clientDashBoardConfiguration.getClientDashBoardGpuConfigurations().get(gpu.getId());
            List<MinedCurrencyShortName> minedCurrencies = filterOutCurrenciesMined(currencies, clientDashBoardGpuConfiguration.getMinedCryptocurrencies());
            currenciesConfiguration.add(GraphicCardMiningConfiguration.builder().activateMining(clientDashBoardGpuConfiguration.isActivateMining()).gpu(gpu).currenciesToMine(minedCurrencies).build());
        }

        miningConfig.setCurrenciesConfiguration(currenciesConfiguration);

        return miningConfig;
    }

    private GraphicCard identifyGraphicCard(GPU gpu) {
        if(gpu.getGraphicCard() == null) {
            // TODO: put back the default card to worst cards
            if(gpu.getGpuType() == CUDA) {
                logger.warn("The graphic card could not be identified for " + gpu + " defaulting to GTX_1080_TI");
                return GraphicCard.GTX_1080_TI;
            } else if(gpu.getGpuType() == OPEN_CL){
                logger.warn("The graphic card could not be identified for " + gpu + " defaulting to AMD_VEGA_64");
                return GraphicCard.AMD_VEGA_64;
            }
        }
        return gpu.getGraphicCard();
    }

    private List<MinedCurrencyShortName> filterOutCurrenciesMined(List<GraphicCardLiveData> minedCurrencies, Map<MinedCurrencyShortName, Boolean> allowedMinedCurrenciesDashBoard) {
        Set<MinedCurrencyShortName> allowedCurrencies = allowedMinedCurrenciesDashBoard.entrySet().stream()
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

