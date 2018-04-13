package com.company.Server.JsonFormat.ConfigRequest;

import com.company.Server.JsonFormat.ConfigRequest.SystemConfig.ConfigRequestSystemConfig;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ConfigRequestData {
    private ConfigRequestSystemConfig systemConfig;
    private List<Map<String,String>> currencyBenchMarking;
}
