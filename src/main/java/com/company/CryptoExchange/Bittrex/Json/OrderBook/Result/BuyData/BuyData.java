package com.company.CryptoExchange.Bittrex.Json.OrderBook.Result.BuyData;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BuyData {
    @SerializedName("Quantity")
    private BigDecimal quantity;
    @SerializedName("Rate")
    private BigDecimal rate;
}
