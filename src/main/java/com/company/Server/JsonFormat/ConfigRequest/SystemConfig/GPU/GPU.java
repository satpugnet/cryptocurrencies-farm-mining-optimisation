package com.company.Server.JsonFormat.ConfigRequest.SystemConfig.GPU;

import lombok.Data;

@Data
public class GPU {
    private GPUType gpuType;
    private Long memorySize;
    private GraphicCard graphicCard;
}
