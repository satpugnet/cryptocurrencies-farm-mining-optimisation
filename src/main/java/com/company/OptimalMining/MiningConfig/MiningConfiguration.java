package com.company.OptimalMining.MiningConfig;

import com.company.OptimalMining.Configuration;
import com.company.crypto_currencies.CurrencyShortName;
import lombok.Data;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

//TODO
@Data
public class MiningConfiguration implements Configuration {

    Boolean activateMining;
    List<CurrencyShortName> currenciesToMine;

    public MiningConfiguration() {
        activateMining = true;
        currenciesToMine = new LinkedList<>();
    }

    public JSONObject toJson() {
        JSONObject finalObject = new JSONObject();
        try {
            finalObject.put("activateMining", activateMining);
            finalObject.put("currenciesToMine", toStringList(currenciesToMine));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return finalObject;
    }

    private List<String> toStringList(List<CurrencyShortName> originalList) {
        return originalList.stream().map(Enum::toString).collect(Collectors.toList());
    }
}
