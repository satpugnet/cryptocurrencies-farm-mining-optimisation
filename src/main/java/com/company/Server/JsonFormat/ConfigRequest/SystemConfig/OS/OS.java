package com.company.Server.JsonFormat.ConfigRequest.SystemConfig.OS;

import lombok.Data;

@Data
public class OS {
    private OSType osType;
    private DataModel dataModel;
    private String version;
}
