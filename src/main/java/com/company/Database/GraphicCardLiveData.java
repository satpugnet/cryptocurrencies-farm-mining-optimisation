package com.company.Database;

import com.company.crypto_currencies.CurrencyShortName;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class GraphicCardLiveData {
    private CurrencyShortName currencyShortName;
    private BigDecimal profitPerSecond;
}
