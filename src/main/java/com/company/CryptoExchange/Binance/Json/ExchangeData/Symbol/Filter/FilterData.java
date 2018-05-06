package com.company.CryptoExchange.Binance.Json.ExchangeData.Symbol.Filter;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FilterData {
    private String filterType;
    private BigDecimal minQty;
    private BigDecimal maxQty;
    private BigDecimal stepSize;
}
