package com.company.Server.JsonFormat.ClientJson.MiningConfiguration.ClientConfiguration;

import com.company.Server.JsonFormat.General.GPU.GPU;
import com.company.Server.JsonFormat.ClientJson.MiningConfiguration.ClientConfiguration.OS.OS;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClientConfiguration {
    private OS os;
    private List<GPU> gpus;
}
