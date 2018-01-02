package OptimalMining.ClientConfig;

import crypto_currencies.AllCurrenciesShortNames;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrencyBenchMarking {
    AllCurrenciesShortNames currenciesShortNames;
    double hashRate;
}
