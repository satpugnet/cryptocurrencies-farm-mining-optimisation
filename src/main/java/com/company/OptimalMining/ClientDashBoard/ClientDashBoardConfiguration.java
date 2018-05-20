package com.company.OptimalMining.ClientDashBoard;

import com.company.Database.DatabaseAccessor;
import com.company.Server.JsonFormat.General.GPU.GPU;
import com.company.Server.JsonFormat.General.MinedCurrencyShortName;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ClientDashBoardConfiguration {

    private Map<Integer, ClientDashBoardGpuConfiguration> clientDashBoardGpuConfigurations;

    public ClientDashBoardConfiguration(String userEmail, String workerName, List<GPU> gpus) {
        clientDashBoardGpuConfigurations = new HashMap<>();
        DatabaseAccessor databaseAccessor = new DatabaseAccessor(userEmail);
        for(GPU gpu: gpus) {
            boolean activateMining = convertActivateMiningValue(databaseAccessor.getGraphicCardConfigFieldString(workerName, gpu.getId(), "activate_mining"));
            Map<MinedCurrencyShortName, Boolean> minedCryptocurrencies = new HashMap<>();
            for(MinedCurrencyShortName currency : MinedCurrencyShortName.values()) {
                Boolean isMinedCurrency = databaseAccessor.getWorkerConfigFieldBoolean(workerName, gpu.getId(), currency.toString());
                if(isMinedCurrency != null) {
                    minedCryptocurrencies.put(currency, isMinedCurrency);
                }
            }
            clientDashBoardGpuConfigurations.put(gpu.getId(), ClientDashBoardGpuConfiguration.builder()
                    .activateMining(activateMining).minedCryptocurrencies(minedCryptocurrencies).build());
        }
    }

    private boolean convertActivateMiningValue(String value) {
        if(value.equals("t")) {
            return true;
        } else if(value.equals("f")) {
            return false;
        }
        return false;
    }
}
