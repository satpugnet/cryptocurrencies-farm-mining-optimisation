package com.company.OptimalMining.ClientDashBoard;

import com.company.Server.JsonFormat.General.MinedCurrencyShortName;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class ClientDashBoardGpuConfiguration {
    private boolean activateMining;
    private Map<MinedCurrencyShortName, Boolean> minedCryptocurrencies;
}
