package OptimalMining.ClientConfig;

import OptimalMining.Configuration;
import Server.dataExchangeAnalyser.DataExchangeMedium;
import crypto_currencies.AllCurrenciesShortNames;
import lombok.Data;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//TODO
@Data
public class ClientConfiguration implements Configuration {

    private OS osType = OS.linux;
    List<CurrencyBenchMarking> currencyBenchMarkings = new LinkedList<>();

    public ClientConfiguration(DataExchangeMedium clientConfigJson) {
        setupOsType(clientConfigJson);
        setupCurrencyBenchMarking(clientConfigJson);
    }

    private void setupCurrencyBenchMarking(DataExchangeMedium clientConfigJson) {
        List<Map<String, String>> currencyBenchMarking = clientConfigJson.getCurrencyBenchMarking();

        for (int i = 0; i < currencyBenchMarking.size(); i++) {
            Map<String, String> currentMap = currencyBenchMarking.get(i);
            CurrencyBenchMarking currentCurrencyBenchMarking = CurrencyBenchMarking.builder()
                .currenciesShortNames(AllCurrenciesShortNames.valueOf(currentMap.get("currencyShortName")))
                .hashRate(Double.parseDouble(currentMap.get("hashRate")))
                .build();
            this.currencyBenchMarkings.add(currentCurrencyBenchMarking);
        }
    }

    // TODO handle case where the valueOf does not work
    private void setupOsType(DataExchangeMedium clientConfigJson) {
        osType = OS.valueOf(clientConfigJson.getOS());
    }

    @Override
    public JSONObject toJson() {
        return null;
    }
}
