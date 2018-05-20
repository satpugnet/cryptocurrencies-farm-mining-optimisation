package com.company.Server.JsonFormat.ClientJson;


import com.company.Server.JsonFormat.ClientJson.MiningConfiguration.MiningConfigurationRequestData;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MiningConfigurationRequest {
    private String userEmail;
    private String workerName;
    private MiningConfigurationRequestData data;
}
