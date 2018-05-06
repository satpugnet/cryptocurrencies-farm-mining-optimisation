package com.company.CryptoExchange.Bittrex.Json.OrderBook.Result;

import com.company.CryptoExchange.Bittrex.Json.OrderBook.Result.BuyData.BuyData;
import com.company.CryptoExchange.Bittrex.Json.OrderBook.Result.SellData.SellData;
import lombok.Data;

import java.util.List;

@Data
public class ResultData {
    private List<SellData> sell;
    private List<BuyData> buy;
}
