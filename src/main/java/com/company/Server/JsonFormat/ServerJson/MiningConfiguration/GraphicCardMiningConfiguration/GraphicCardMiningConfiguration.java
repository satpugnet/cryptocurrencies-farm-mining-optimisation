package com.company.Server.JsonFormat.ServerJson.MiningConfiguration.GraphicCardMiningConfiguration;

import com.company.Server.JsonFormat.General.GPU.GPU;
import com.company.Server.JsonFormat.General.GPU.GraphicCard;
import com.company.Server.JsonFormat.General.MinedCurrencyShortName;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GraphicCardMiningConfiguration {
    private boolean activateMining;
    private GPU gpu;
    private List<MinedCurrencyShortName> currenciesToMine; // TODO: replace this String by MinedCurrencyShortName
}
