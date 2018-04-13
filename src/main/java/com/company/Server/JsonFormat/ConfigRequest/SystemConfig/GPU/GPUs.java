package com.company.Server.JsonFormat.ConfigRequest.SystemConfig.GPU;

import lombok.Data;

import java.util.List;

@Data
public class GPUs {
    private List<GPU> gpus;
}
