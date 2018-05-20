package com.company.Server.JsonFormat.ClientJson.MiningConfiguration.ClientConfiguration.OS;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OS {
    private OSType osType;
    private DataModel dataModel;
    private String version;
    private CommandExecutionEnvironment environment;
}
