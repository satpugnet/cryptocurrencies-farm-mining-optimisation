package com.company.OptimalMining.ClientDashBoard;

import com.company.Database.DatabaseAccessor;
import com.company.crypto_currencies.CurrencyShortName;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ClientDashBoardConfiguration {

    private boolean activateMining;
    Map<CurrencyShortName, Boolean> minedCryptocurrencies = new HashMap<>();

    public ClientDashBoardConfiguration(String userEmail, String workerName) {
        DatabaseAccessor databaseAccessor = new DatabaseAccessor(userEmail);
        activateMining = convertActivateMiningValue(databaseAccessor.getWorkerConfigFieldString(workerName, "activate_mining"));
        for(CurrencyShortName currency : CurrencyShortName.values()) {
            Boolean isMinedCurrency = databaseAccessor.getWorkerConfigFieldBoolean(workerName, currency.toString());
            if(isMinedCurrency != null) {
                minedCryptocurrencies.put(currency, isMinedCurrency);
            }
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
