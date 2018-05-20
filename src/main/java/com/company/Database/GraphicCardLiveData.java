package com.company.Database;

import com.company.Server.JsonFormat.General.MinedCurrencyShortName;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class GraphicCardLiveData {
    private MinedCurrencyShortName currencyShortName;
    private BigDecimal profitPerSecond;
}
