package com.company.CryptoExchange.Binance.Json.AccountData;

import com.company.CryptoExchange.Binance.Json.AccountData.Balance.AssetData;
import lombok.Data;

import java.util.List;

@Data
public class BinanceAccountData {
    private List<AssetData> balances;
}
