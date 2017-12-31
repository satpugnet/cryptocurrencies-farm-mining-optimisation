package OptimalMining.ClientConfig;

import OptimalMining.Configuration;
import Server.dataExchangeAnalyser.DataExchangeMedium;
import lombok.Data;
import org.json.JSONObject;

//TODO
@Data
public class ClientConfiguration implements Configuration {

    private OS osType= OS.linux;

    public ClientConfiguration(DataExchangeMedium clientConfigJson) {

    }

    @Override
    public JSONObject toJson() {
        return null;
    }
}
