package com.company.CryptoExchange.Binance.Json.AccountData.Balance;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AssetData {
    @SerializedName("asset")
    private String name;
    private BigDecimal free;
    private BigDecimal locked;
}
