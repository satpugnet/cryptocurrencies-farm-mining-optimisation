package com.company.Server.JsonFormat.ServerJson;

import com.company.Server.JsonFormat.ServerJson.MiningConfiguration.GraphicCardMiningConfiguration.GraphicCardMiningConfiguration;
import lombok.Data;

import java.util.List;

@Data
public class MiningConfigurationResponse {
    private List<GraphicCardMiningConfiguration> currenciesConfiguration; // TODO check if the list can be of MinedCryptocurrencyShortName
}
