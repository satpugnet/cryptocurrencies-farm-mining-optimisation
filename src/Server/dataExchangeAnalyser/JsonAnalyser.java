package Server.dataExchangeAnalyser;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonAnalyser implements DataExchangeMedium {
    private JSONObject jsonObject = null;

    public JsonAnalyser(String stringJson) {
        try {
            jsonObject = new JSONObject(stringJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getUserId() {
        try {
            return jsonObject.getString("userId");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
