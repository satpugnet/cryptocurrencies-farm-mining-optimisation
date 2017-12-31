package OptimalMining.MiningConfig;

import OptimalMining.Configuration;
import lombok.Data;
import org.json.JSONException;
import org.json.JSONObject;

//TODO
@Data
public class MiningConfiguration implements Configuration {

    Boolean activateMining;

    public MiningConfiguration() {
        activateMining = true;
    }

    public JSONObject toJson() {
        JSONObject finalObject = new JSONObject();
        try {
            finalObject.put("activateMining", activateMining);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return finalObject;
    }
}
