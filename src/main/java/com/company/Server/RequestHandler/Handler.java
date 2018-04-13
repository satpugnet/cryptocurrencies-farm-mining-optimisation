package com.company.Server.RequestHandler;

import com.company.OptimalMining.MiningConfig.MiningConfiguration;
import com.company.Server.JsonFormat.ConfigRequest.ConfigRequestProperties;

public interface Handler {
    public MiningConfiguration handle(ConfigRequestProperties requestData);
}
