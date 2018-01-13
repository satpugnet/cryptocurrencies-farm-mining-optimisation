package OptimalMining.MiningConfig;

import OptimalMining.Configuration;
import crypto_currencies.CurrenciesShortName;
import lombok.Data;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

//TODO
@Data
public class MiningConfiguration implements Configuration {

    Boolean activateMining;
    List<CurrenciesShortName> currenciesToMine;

    public MiningConfiguration() {
        activateMining = true;
        currenciesToMine = new LinkedList<>();
    }

    public JSONObject toJson() {
        JSONObject finalObject = new JSONObject();
        System.out.println(currenciesToMine);
        try {
            finalObject.put("activateMining", activateMining);
            JSONArray jsonArrayCurrenciesToMine = new JSONArray(currenciesToMine);
            finalObject.put("currenciesToMine", jsonArrayCurrenciesToMine);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(finalObject);
        return finalObject;
    }
}
