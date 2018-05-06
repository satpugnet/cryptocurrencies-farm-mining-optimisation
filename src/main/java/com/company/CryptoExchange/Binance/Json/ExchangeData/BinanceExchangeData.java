package com.company.CryptoExchange.Binance.Json.ExchangeData;

import com.company.CryptoExchange.Binance.Json.ExchangeData.Symbol.SymbolData;
import lombok.Data;

import java.util.List;

@Data
public class BinanceExchangeData {
    private List<SymbolData> symbols;
}
