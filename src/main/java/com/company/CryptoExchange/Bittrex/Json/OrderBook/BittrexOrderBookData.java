package com.company.CryptoExchange.Bittrex.Json.OrderBook;

import com.company.CryptoExchange.Bittrex.Json.OrderBook.Result.ResultData;
import lombok.Data;

@Data
public class BittrexOrderBookData {
    private boolean success;
    private String message;
    private ResultData result;
}
