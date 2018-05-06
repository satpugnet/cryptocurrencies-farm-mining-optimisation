package com.company.CryptoExchange.Bittrex.Json.OrderBook.Result.SellData;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SellData {
    @SerializedName("Quantity")
    private BigDecimal quantity;
    @SerializedName("Rate")
    private BigDecimal rate;
}
