package com.company.CryptoExchange.Bittrex.Json.Balances;

import com.company.CryptoExchange.Bittrex.Json.Balances.Result.CurrencyData;
import lombok.Data;

import java.util.List;

@Data
public class BittrexBalances {
    private boolean success;
    private String message;
    private List<CurrencyData> result;
}
