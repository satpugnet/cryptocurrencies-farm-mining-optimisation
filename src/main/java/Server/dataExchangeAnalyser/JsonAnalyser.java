package Server.dataExchangeAnalyser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JsonAnalyser implements DataExchangeMedium {
    private JSONObject jsonObject = null;

    public JsonAnalyser(String stringJson) {
        try {
            jsonObject = new JSONObject(stringJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getUserId() {
        try {
            return jsonObject.getString("userId");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getOS() {
        try {
            return getData().getJSONObject("sysconfig").getString("OS");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO: replace hardcoded names by constant
    @Override
    public List<Map<String,String>> getCurrencyBenchMarking() {
        try {
            JSONArray benchMarking = getData().getJSONArray("benchMarking");
            List<String> values = Arrays.asList("hashRate", "currencyShortName");
            return getValueInJSONArray(benchMarking, values);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private JSONObject getData() {
        try {
            return jsonObject.getJSONObject("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Map<String,String>> getValueInJSONArray(JSONArray jsonArray, List<String> values) {
        List<Map<String, String>> result = new LinkedList<>();

        for(int i = 0; i < jsonArray.length(); i++) {
            Map<String, String> map = new HashMap<String, String>();
            for (String currentValue : values) {
                try {
                    map.put(currentValue, jsonArray.getJSONObject(i).getString(currentValue));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            result.add(map);
        }

        return result;
    }
}
