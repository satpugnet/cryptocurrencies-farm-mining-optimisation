package com.company.CryptoExchange.Binance.Json.ExchangeData.Symbol;

import com.company.CryptoExchange.Binance.Json.ExchangeData.Symbol.Filter.FilterData;
import lombok.Data;

import java.util.List;

@Data
public class SymbolData {
    private String symbol;
    private List<FilterData> filters;
}
