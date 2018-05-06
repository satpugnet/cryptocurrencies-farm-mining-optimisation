package com.company.CryptoExchange.Bittrex.Json.Balances.Result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CurrencyData {
    @SerializedName("Currency")
    private String currency;
    @SerializedName("Balance")
    private BigDecimal balance;
    @SerializedName("Available")
    private BigDecimal available;
    @SerializedName("Pending")
    private BigDecimal pending;
    @SerializedName("CryptoAddress")
    private String cryptoAddress;
}
