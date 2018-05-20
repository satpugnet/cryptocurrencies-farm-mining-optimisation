package com.company.Server.RequestHandler;

import com.company.Server.JsonFormat.ClientJson.MiningConfigurationRequest;
import com.company.Server.JsonFormat.ServerJson.MiningConfigurationResponse;

public interface Handler {
    public MiningConfigurationResponse handle(MiningConfigurationRequest clientConfig);
}
