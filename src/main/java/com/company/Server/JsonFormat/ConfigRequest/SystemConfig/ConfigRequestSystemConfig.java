package com.company.Server.JsonFormat.ConfigRequest.SystemConfig;

import com.company.Server.JsonFormat.ConfigRequest.SystemConfig.GPU.GPUs;
import com.company.Server.JsonFormat.ConfigRequest.SystemConfig.OS.OS;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConfigRequestSystemConfig {
    private OS os;
    private GPUs gpus;
}
