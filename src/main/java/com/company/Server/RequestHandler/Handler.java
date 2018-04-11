package com.company.Server.RequestHandler;

import com.company.OptimalMining.MiningConfig.MiningConfiguration;
import com.company.Server.dataExchangeAnalyser.DataExchangeMedium;

public interface Handler {
    public MiningConfiguration handle(DataExchangeMedium requestData);
}
